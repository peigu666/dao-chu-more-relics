package spireoddities.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class TornCoinpurse extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("TornCoinpurse");

    public TornCoinpurse() {
        super(ID, "TornCoinpurse.png", RelicTier.COMMON, LandingSound.CLINK);
    }

    @Override
    public void onGainGold() {
        gainBlock(1);
    }

    @Override
    public AbstractRelic makeCopy() {
        return new TornCoinpurse();
    }
}
