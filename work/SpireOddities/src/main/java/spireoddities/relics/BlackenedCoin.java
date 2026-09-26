package spireoddities.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class BlackenedCoin extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("BlackenedCoin");

    public BlackenedCoin() {
        super(ID, "BlackenedCoin.png", RelicTier.UNCOMMON, LandingSound.CLINK);
    }

    @Override
    public void onSpendGold() {
        gainBlock(3);
    }

    @Override
    public AbstractRelic makeCopy() {
        return new BlackenedCoin();
    }
}
