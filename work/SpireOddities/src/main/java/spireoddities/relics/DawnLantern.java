package spireoddities.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class DawnLantern extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("DawnLantern");

    public DawnLantern() {
        super(ID, "DawnLantern.png", RelicTier.RARE, LandingSound.MAGICAL);
    }

    @Override
    public void atBattleStartPreDraw() {
        gainEnergy(1);
    }

    @Override
    public AbstractRelic makeCopy() {
        return new DawnLantern();
    }
}
