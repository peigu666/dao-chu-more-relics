package spireoddities.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;
import spireoddities.util.TextureLoader;

public class TightKnot extends CustomRelic {
    public static final String ID = SpireOddities.makeID("TightKnot");
    private static final String IMG = SpireOddities.makeRelicPath("TightKnot.png");
    private static final String OUTLINE = SpireOddities.makeRelicOutlinePath("TightKnot.png");
    private static final int CARD_THRESHOLD = 3;
    private static final int BLOCK_AMOUNT = 3;

    public TightKnot() {
        super(ID, TextureLoader.getTexture(IMG), TextureLoader.getTexture(OUTLINE),
                RelicTier.COMMON, LandingSound.CLINK);
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
    public void onUseCard(AbstractCard card, UseCardAction action) {
        this.counter++;
        if (this.counter >= CARD_THRESHOLD) {
            this.counter = 0;
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
        return new TightKnot();
    }
}
