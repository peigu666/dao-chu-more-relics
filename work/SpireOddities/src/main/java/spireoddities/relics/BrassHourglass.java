package spireoddities.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class BrassHourglass extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("BrassHourglass");

    public BrassHourglass() {
        super(ID, "BrassHourglass.png", RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    @Override
    public void onShuffle() {
        gainBlock(2);
    }

    @Override
    public AbstractRelic makeCopy() {
        return new BrassHourglass();
    }
}
