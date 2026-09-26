package spireoddities.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class CinderCrown extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("CinderCrown");

    public CinderCrown() {
        super(ID, "CinderCrown.png", RelicTier.RARE, LandingSound.MAGICAL);
        this.counter = 0;
    }

    @Override
    public void atBattleStart() {
        this.counter = 0;
    }

    @Override
    public void onExhaust(AbstractCard card) {
        this.counter++;
        if (this.counter >= 3) {
            this.counter = 0;
            damageAll(10);
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new CinderCrown();
    }
}
