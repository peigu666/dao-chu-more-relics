package spireoddities.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class SilentLedger extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("SilentLedger");

    public SilentLedger() {
        super(ID, "SilentLedger.png", RelicTier.RARE, LandingSound.MAGICAL);
        this.counter = 0;
    }

    @Override
    public void atBattleStart() {
        this.counter = 0;
    }

    @Override
    public void onPlayerEndTurn() {
        if (AbstractDungeon.player.energy.energy == 0) {
            this.counter = 1;
        }
    }

    @Override
    public void atTurnStartPostDraw() {
        if (this.counter == 1) {
            this.counter = 0;
            draw(2);
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new SilentLedger();
    }
}
