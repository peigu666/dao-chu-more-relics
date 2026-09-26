package spireoddities.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class WaxedThread extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("WaxedThread");

    public WaxedThread() {
        super(ID, "WaxedThread.png", RelicTier.UNCOMMON, LandingSound.FLAT);
        this.counter = 0;
    }

    @Override
    public void atBattleStart() {
        this.counter = 0;
    }

    @Override
    public void onManualDiscard() {
        this.counter++;
        if (this.counter >= 3) {
            this.counter = 0;
            gainEnergy(1);
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new WaxedThread();
    }
}
