package spireoddities.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class RoyalSeal extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("RoyalSeal");

    public RoyalSeal() {
        super(ID, "RoyalSeal.png", RelicTier.RARE, LandingSound.MAGICAL);
    }

    @Override
    public void atBattleStartPreDraw() {
        draw(1);
    }

    @Override
    public AbstractRelic makeCopy() {
        return new RoyalSeal();
    }
}
