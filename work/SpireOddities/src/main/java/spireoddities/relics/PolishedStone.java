package spireoddities.relics;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class PolishedStone extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("PolishedStone");

    public PolishedStone() {
        super(ID, "PolishedStone.png", RelicTier.UNCOMMON, LandingSound.FLAT);
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
        if (this.counter == 0 && blockAmount > 0) {
            this.counter = 1;
            addRandomColorlessCardToHand(0, true);
        }
        return MathUtils.floor(blockAmount);
    }

    @Override
    public AbstractRelic makeCopy() {
        return new PolishedStone();
    }
}
