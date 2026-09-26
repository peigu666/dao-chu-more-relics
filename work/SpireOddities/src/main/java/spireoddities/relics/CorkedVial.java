package spireoddities.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class CorkedVial extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("CorkedVial");

    public CorkedVial() {
        super(ID, "CorkedVial.png", RelicTier.UNCOMMON, LandingSound.FLAT);
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
            draw(1);
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new CorkedVial();
    }
}
