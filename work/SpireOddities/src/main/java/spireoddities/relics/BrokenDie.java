package spireoddities.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class BrokenDie extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("BrokenDie");

    public BrokenDie() {
        super(ID, "BrokenDie.png", RelicTier.UNCOMMON, LandingSound.CLINK);
    }

    @Override
    public int changeRareCardRewardChance(int chance) {
        return chance + 3;
    }

    @Override
    public AbstractRelic makeCopy() {
        return new BrokenDie();
    }
}
