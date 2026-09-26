package spireoddities.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class EmptyDiceCup extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("EmptyDiceCup");

    public EmptyDiceCup() {
        super(ID, "EmptyDiceCup.png", RelicTier.RARE, LandingSound.MAGICAL);
        this.counter = 0;
    }

    @Override
    public void atBattleStart() {
        this.counter = 0;
    }

    @Override
    public void onPlayerEndTurn() {
        if (AbstractDungeon.player.hand.isEmpty()) {
            this.counter = 1;
        }
    }

    @Override
    public void atTurnStartPostDraw() {
        if (this.counter == 1) {
            this.counter = 0;
            addRandomColorlessCardToHand(0, true);
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new EmptyDiceCup();
    }
}
