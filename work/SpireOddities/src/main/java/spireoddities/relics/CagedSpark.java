package spireoddities.relics;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class CagedSpark extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("CagedSpark");

    public CagedSpark() {
        super(ID, "CagedSpark.png", RelicTier.RARE, LandingSound.MAGICAL);
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
    public void onUseCard(AbstractCard card, UseCardAction action) {
        this.counter++;
        if (this.counter >= 5) {
            this.counter = 0;
            gainEnergy(1);
            draw(1);
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new CagedSpark();
    }
}
