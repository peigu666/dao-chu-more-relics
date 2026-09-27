package spireoddities.relics;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class GoldenThread extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("GoldenThread");

    public GoldenThread() {
        super(ID, "GoldenThread.png", RelicTier.RARE, LandingSound.MAGICAL);
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
    public int onPlayerGainedBlock(float blockAmount) {
        if (blockAmount >= 10 && this.counter == 0) {
            this.counter = 1;
            gainDexterity(1);
        }
        return MathUtils.floor(blockAmount);
    }

    @Override
    public AbstractRelic makeCopy() {
        return new GoldenThread();
    }
}
