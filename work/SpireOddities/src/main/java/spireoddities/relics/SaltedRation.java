package spireoddities.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class SaltedRation extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("SaltedRation");

    public SaltedRation() {
        super(ID, "SaltedRation.png", RelicTier.COMMON, LandingSound.FLAT);
    }

    @Override
    public void atBattleStart() {
        heal(2);
    }

    @Override
    public AbstractRelic makeCopy() {
        return new SaltedRation();
    }
}
