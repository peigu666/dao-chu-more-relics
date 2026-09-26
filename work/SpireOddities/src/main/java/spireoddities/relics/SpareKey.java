package spireoddities.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class SpareKey extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("SpareKey");

    public SpareKey() {
        super(ID, "SpareKey.png", RelicTier.UNCOMMON, LandingSound.CLINK);
    }

    @Override
    public void onChestOpen(boolean bossChest) {
        if (!bossChest) {
            obtainRandomPotion();
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new SpareKey();
    }
}
