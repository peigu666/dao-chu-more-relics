package spireoddities.relics;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class MirrorShard extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("MirrorShard");

    public MirrorShard() {
        super(ID, "MirrorShard.png", RelicTier.UNCOMMON, LandingSound.MAGICAL);
        this.counter = 0;
    }

    @Override
    public void atBattleStart() {
        this.counter = 0;
    }

    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        if (this.counter == 0 && damageAmount > 0) {
            this.counter = 1;
            gainEnergy(1);
        }
        return damageAmount;
    }

    @Override
    public AbstractRelic makeCopy() {
        return new MirrorShard();
    }
}
