package spireoddities.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class SilverThread extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("SilverThread");

    public SilverThread() {
        super(ID, "SilverThread.png", RelicTier.RARE, LandingSound.MAGICAL);
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
    public int onPlayerGainBlock(int blockAmount) {
        if (this.counter == 0 && blockAmount >= 15) {
            this.counter = 1;
            draw(2);
        }
        return blockAmount;
    }

    @Override
    public AbstractRelic makeCopy() {
        return new SilverThread();
    }
}
