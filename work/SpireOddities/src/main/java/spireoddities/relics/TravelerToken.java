package spireoddities.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class TravelerToken extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("TravelerToken");

    public TravelerToken() {
        super(ID, "TravelerToken.png", RelicTier.UNCOMMON, LandingSound.CLINK);
    }

    @Override
    public void onVictory() {
        gainGold(5);
    }

    @Override
    public AbstractRelic makeCopy() {
        return new TravelerToken();
    }
}
