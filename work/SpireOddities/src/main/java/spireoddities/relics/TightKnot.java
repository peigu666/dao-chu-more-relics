package spireoddities.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
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
            if (!AbstractDungeon.player.hand.isEmpty()) {
                AbstractCard chosen = AbstractDungeon.player.hand.getRandomCard(AbstractDungeon.cardRandomRng);
                chosen.modifyCostForCombat(-1);
                flash();
                AbstractDungeon.actionManager.addToBottom(
                        new RelicAboveCreatureAction(AbstractDungeon.player, this));
            }
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
