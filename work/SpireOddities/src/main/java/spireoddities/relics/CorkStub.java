package spireoddities.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class CorkStub extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("CorkStub");

    public CorkStub() {
        super(ID, "CorkStub.png", RelicTier.COMMON, LandingSound.FLAT);
        this.counter = 0;
    }

    @Override
    public void atBattleStart() {
        this.counter = 0;
    }

    @Override
    public void onUsePotion() {
        if (this.counter == 0) {
            this.counter = 1;
            gainBlock(3);
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new CorkStub();
    }
}
