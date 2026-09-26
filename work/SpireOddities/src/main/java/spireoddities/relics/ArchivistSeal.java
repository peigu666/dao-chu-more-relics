package spireoddities.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class ArchivistSeal extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("ArchivistSeal");

    public ArchivistSeal() {
        super(ID, "ArchivistSeal.png", RelicTier.RARE, LandingSound.MAGICAL);
    }

    @Override
    public int changeNumberOfCardsInReward(int numberOfCards) {
        return numberOfCards + 1;
    }

    @Override
    public AbstractRelic makeCopy() {
        return new ArchivistSeal();
    }
}
