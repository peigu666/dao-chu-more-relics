package spireoddities.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;
import spireoddities.util.TextureLoader;

public abstract class SpireOdditiesRelic extends CustomRelic {
    protected SpireOdditiesRelic(String id, String fileName, RelicTier tier, LandingSound sound) {
        super(id,
                TextureLoader.getTexture(SpireOddities.makeRelicPath(fileName)),
                TextureLoader.getTexture(SpireOddities.makeRelicOutlinePath(fileName)),
                tier,
                sound);
    }

    protected void trigger() {
        flash();
        AbstractDungeon.actionManager.addToBottom(
                new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }

    protected void gainBlock(int amount) {
        trigger();
        AbstractDungeon.actionManager.addToBottom(
                new GainBlockAction(AbstractDungeon.player, amount));
    }

    protected void draw(int amount) {
        trigger();
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(amount));
    }

    protected void gainEnergy(int amount) {
        trigger();
        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(amount));
    }

    protected void damageAll(int amount) {
        trigger();
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(
                AbstractDungeon.player,
                amount,
                DamageInfo.DamageType.THORNS,
                AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }

    protected void heal(int amount) {
        trigger();
        AbstractDungeon.actionManager.addToBottom(
                new HealAction(AbstractDungeon.player, AbstractDungeon.player, amount));
    }

    protected void gainGold(int amount) {
        trigger();
        AbstractDungeon.actionManager.addToBottom(new GainGoldAction(amount));
    }

    protected void gainPlayerPower(AbstractPower power) {
        trigger();
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, power));
    }

    protected void gainStrength(int amount) {
        gainPlayerPower(new StrengthPower(AbstractDungeon.player, amount));
    }

    protected void gainDexterity(int amount) {
        gainPlayerPower(new DexterityPower(AbstractDungeon.player, amount));
    }

    protected void obtainRandomPotion() {
        trigger();
        AbstractDungeon.actionManager.addToBottom(
                new ObtainPotionAction(AbstractDungeon.returnRandomPotion()));
    }

    protected void addCardToHand(AbstractCard card) {
        trigger();
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(card));
    }

    protected void addRandomColorlessCardToHand(int cost, boolean exhaust) {
        AbstractCard card = AbstractDungeon.returnTrulyRandomColorlessCardInCombat();
        if (card != null) {
            card.setCostForTurn(cost);
            card.exhaust = exhaust;
            addCardToHand(card);
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
