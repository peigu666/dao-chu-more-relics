package spireoddities.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class GildedScissors extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("GildedScissors");

    public GildedScissors() {
        super(ID, "GildedScissors.png", RelicTier.RARE, LandingSound.MAGICAL);
        this.counter = 0;
    }

    @Override
    public void atBattleStart() {
        this.counter = 0;
    }

    @Override
    public void onExhaust(AbstractCard card) {
        if (this.counter == 0) {
            this.counter = 1;
            gainEnergy(1);
            draw(2);
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new GildedScissors();
    }
}
