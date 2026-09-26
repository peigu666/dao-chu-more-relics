package spireoddities.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class RationTin extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("RationTin");

    public RationTin() {
        super(ID, "RationTin.png", RelicTier.UNCOMMON, LandingSound.FLAT);
    }

    @Override
    public void atBattleStart() {
        gainBlock(5);
    }

    @Override
    public AbstractRelic makeCopy() {
        return new RationTin();
    }
}
