package spireoddities.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class PocketSand extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("PocketSand");

    public PocketSand() {
        super(ID, "PocketSand.png", RelicTier.COMMON, LandingSound.FLAT);
        this.counter = 0;
    }

    @Override
    public void atBattleStart() {
        this.counter = 0;
    }

    @Override
    public void onBloodied() {
        if (this.counter == 0) {
            this.counter = 1;
            damageAll(5);
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new PocketSand();
    }
}
