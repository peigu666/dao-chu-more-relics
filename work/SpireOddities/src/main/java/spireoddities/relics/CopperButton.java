package spireoddities.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class CopperButton extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("CopperButton");

    public CopperButton() {
        super(ID, "CopperButton.png", RelicTier.COMMON, LandingSound.CLINK);
    }

    @Override
    public void atBattleStart() {
        this.counter = 0;
    }

    @Override
    public void atTurnStart() {
        if (this.counter > 0) {
            int storedBlock = this.counter;
            this.counter = 0;
            gainBlock(storedBlock);
        }
    }

    @Override
    public void onPlayerEndTurn() {
        if (AbstractDungeon.player.energy.energy == 0 && AbstractDungeon.player.currentBlock > 0) {
            this.counter = Math.min(8, AbstractDungeon.player.currentBlock);
            trigger();
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new CopperButton();
    }
}
