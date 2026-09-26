package spireoddities.relics;

import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class OrbEmblem extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("OrbEmblem");

    public OrbEmblem() {
        super(ID, "OrbEmblem.png", RelicTier.RARE, LandingSound.MAGICAL);
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
    public void onEvokeOrb(AbstractOrb orb) {
        if (this.counter == 0) {
            this.counter = 1;
            gainBlock(3);
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new OrbEmblem();
    }
}
