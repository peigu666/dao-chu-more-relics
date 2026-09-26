package spireoddities.relics;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class FoldedFan extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("FoldedFan");

    public FoldedFan() {
        super(ID, "FoldedFan.png", RelicTier.UNCOMMON, LandingSound.CLINK);
        this.counter = 0;
    }

    @Override
    public void atBattleStart() {
        this.counter = 0;
    }

    @Override
    public void atTurnStartPostDraw() {
        if (this.counter == 2) {
            draw(2);
        }
        this.counter = 0;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            this.counter = 1;
        }
    }

    @Override
    public void onPlayerEndTurn() {
        if (this.counter == 0) {
            this.counter = 2;
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new FoldedFan();
    }
}
