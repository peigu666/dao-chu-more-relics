package spireoddities.relics;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class SplitBuckle extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("SplitBuckle");

    public SplitBuckle() {
        super(ID, "SplitBuckle.png", RelicTier.COMMON, LandingSound.CLINK);
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
    public void onBlockBroken(AbstractCreature creature) {
        if (creature == AbstractDungeon.player && this.counter == 0) {
            this.counter = 1;
            gainStrength(1);
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new SplitBuckle();
    }
}
