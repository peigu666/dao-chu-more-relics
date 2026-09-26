package spireoddities.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.stances.AbstractStance;
import spireoddities.SpireOddities;

public class StanceTalisman extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("StanceTalisman");

    public StanceTalisman() {
        super(ID, "StanceTalisman.png", RelicTier.RARE, LandingSound.MAGICAL);
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
    public void onChangeStance(AbstractStance previousStance, AbstractStance newStance) {
        if (this.counter == 0 && previousStance != newStance) {
            this.counter = 1;
            gainBlock(3);
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new StanceTalisman();
    }
}
