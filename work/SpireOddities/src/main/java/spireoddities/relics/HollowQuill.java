package spireoddities.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class HollowQuill extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("HollowQuill");

    public HollowQuill() {
        super(ID, "HollowQuill.png", RelicTier.UNCOMMON, LandingSound.CLINK);
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
    public void onCardDraw(AbstractCard card) {
        if (this.counter < 5) {
            this.counter++;
            if (this.counter == 5) {
                gainBlock(2);
            }
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new HollowQuill();
    }
}
