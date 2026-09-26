package spireoddities.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class BloodLedger extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("BloodLedger");

    public BloodLedger() {
        super(ID, "BloodLedger.png", RelicTier.RARE, LandingSound.MAGICAL);
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
        if (this.counter == 0 && amount > 0) {
            this.counter = 1;
            gainEnergy(1);
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new BloodLedger();
    }
}
