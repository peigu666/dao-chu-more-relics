package spireoddities.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class EmptyBell extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("EmptyBell");

    public EmptyBell() {
        super(ID, "EmptyBell.png", RelicTier.UNCOMMON, LandingSound.CLINK);
    }

    @Override
    public void atTurnStartPostDraw() {
        if (AbstractDungeon.player.hand.isEmpty()) {
            gainEnergy(1);
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new EmptyBell();
    }
}
