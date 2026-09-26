package spireoddities.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class ThreadedNeedle extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("ThreadedNeedle");

    public ThreadedNeedle() {
        super(ID, "ThreadedNeedle.png", RelicTier.UNCOMMON, LandingSound.CLINK);
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
            gainDexterity(1);
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new ThreadedNeedle();
    }
}
