package spireoddities.relics;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class MirrorShield extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("MirrorShield");

    public MirrorShield() {
        super(ID, "MirrorShield.png", RelicTier.RARE, LandingSound.MAGICAL);
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
            damageAll(6);
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new MirrorShield();
    }
}
