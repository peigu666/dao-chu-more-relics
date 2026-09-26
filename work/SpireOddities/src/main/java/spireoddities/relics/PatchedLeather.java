package spireoddities.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class PatchedLeather extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("PatchedLeather");

    public PatchedLeather() {
        super(ID, "PatchedLeather.png", RelicTier.COMMON, LandingSound.FLAT);
    }

    @Override
    public void onRest() {
        heal(4);
    }

    @Override
    public AbstractRelic makeCopy() {
        return new PatchedLeather();
    }
}
