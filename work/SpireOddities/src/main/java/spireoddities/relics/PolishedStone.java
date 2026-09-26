package spireoddities.relics;

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
    public int onPlayerGainBlock(int blockAmount) {
        if (this.counter == 0 && blockAmount > 0) {
            this.counter = 1;
            draw(1);
        }
        return blockAmount;
    }

    @Override
    public AbstractRelic makeCopy() {
        return new PolishedStone();
    }
}
