package spireoddities.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class LeadCounterweight extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("LeadCounterweight");

    public LeadCounterweight() {
        super(ID, "LeadCounterweight.png", RelicTier.RARE, LandingSound.MAGICAL);
        this.counter = 0;
    }

    @Override
    public void atBattleStart() {
        this.counter = 1;
        gainStrength(2);
    }

    @Override
    public void atTurnStartPostDraw() {
        if (this.counter == 1) {
            this.counter = 0;
            AbstractDungeon.player.loseEnergy(1);
            trigger();
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new LeadCounterweight();
    }
}
