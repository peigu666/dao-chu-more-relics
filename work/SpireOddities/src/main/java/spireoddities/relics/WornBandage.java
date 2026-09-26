package spireoddities.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class WornBandage extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("WornBandage");

    public WornBandage() {
        super(ID, "WornBandage.png", RelicTier.COMMON, LandingSound.FLAT);
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
            gainStrength(2);
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new WornBandage();
    }
}
