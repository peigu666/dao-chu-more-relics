package spireoddities.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class IronRing extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("IronRing");

    public IronRing() {
        super(ID, "IronRing.png", RelicTier.COMMON, LandingSound.CLINK);
        this.counter = 0;
    }

    @Override
    public void atBattleStart() {
        this.counter = 0;
    }

    @Override
    public void atTurnStart() {
        this.counter = 0;
    }

    @Override
    public void onLoseHp(int amount) {
        if (amount > 0 && this.counter == 0) {
            this.counter = 1;
            gainBlock(2);
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new IronRing();
    }
}
