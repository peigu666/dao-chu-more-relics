package spireoddities.relics;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class AshenNeedle extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("AshenNeedle");

    public AshenNeedle() {
        super(ID, "AshenNeedle.png", RelicTier.COMMON, LandingSound.CLINK);
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
        if (this.counter == 0 && card.costForTurn == 0) {
            this.counter = 1;
            draw(1);
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new AshenNeedle();
    }
}
