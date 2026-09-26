package spireoddities.relics;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class SilverScale extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("SilverScale");

    public SilverScale() {
        super(ID, "SilverScale.png", RelicTier.RARE, LandingSound.MAGICAL);
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
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        if (this.counter == 0 && damageAmount > 0) {
            this.counter = 1;
            trigger();
            return Math.max(0, damageAmount - 3);
        }
        return damageAmount;
    }

    @Override
    public AbstractRelic makeCopy() {
        return new SilverScale();
    }
}
