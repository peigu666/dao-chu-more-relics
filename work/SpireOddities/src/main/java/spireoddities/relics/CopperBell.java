package spireoddities.relics;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class CopperBell extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("CopperBell");

    public CopperBell() {
        super(ID, "CopperBell.png", RelicTier.UNCOMMON, LandingSound.CLINK);
        this.counter = 0;
    }

    @Override
    public void atBattleStart() {
        this.counter = 0;
    }

    @Override
    public void onMonsterDeath(AbstractMonster monster) {
        if (this.counter == 0) {
            this.counter = 1;
            draw(2);
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new CopperBell();
    }
}
