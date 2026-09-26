package spireoddities.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class WaxedCharm extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("WaxedCharm");

    public WaxedCharm() {
        super(ID, "WaxedCharm.png", RelicTier.COMMON, LandingSound.FLAT);
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
    public void onManualDiscard() {
        if (this.counter == 0) {
            this.counter = 1;
            gainBlock(3);
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new WaxedCharm();
    }
}
