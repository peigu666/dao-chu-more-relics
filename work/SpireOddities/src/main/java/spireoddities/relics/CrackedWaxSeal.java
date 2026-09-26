package spireoddities.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;
import spireoddities.util.TextureLoader;

public class CrackedWaxSeal extends CustomRelic {
    public static final String ID = SpireOddities.makeID("CrackedWaxSeal");
    private static final String IMG = SpireOddities.makeRelicPath("CrackedWaxSeal.png");
    private static final String OUTLINE = SpireOddities.makeRelicOutlinePath("CrackedWaxSeal.png");
    private static final int BLOCK_AMOUNT = 3;

    public CrackedWaxSeal() {
        super(ID, TextureLoader.getTexture(IMG), TextureLoader.getTexture(OUTLINE),
                RelicTier.UNCOMMON, LandingSound.FLAT);
        this.counter = 0;
    }

    @Override
    public void atBattleStart() {
        this.counter = 0;
    }

    @Override
    public void atTurnStart() {
        this.counter = 0;
    }

    @Override
    public void onExhaust(AbstractCard card) {
        if (this.counter == 0) {
            this.counter = 1;
            flash();
            AbstractDungeon.actionManager.addToBottom(
                    new RelicAboveCreatureAction(AbstractDungeon.player, this));
            AbstractDungeon.actionManager.addToBottom(
                    new GainBlockAction(AbstractDungeon.player, BLOCK_AMOUNT));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new CrackedWaxSeal();
    }
}
