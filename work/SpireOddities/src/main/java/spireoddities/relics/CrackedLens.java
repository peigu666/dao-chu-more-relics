package spireoddities.relics;

import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class CrackedLens extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("CrackedLens");

    public CrackedLens() {
        super(ID, "CrackedLens.png", RelicTier.COMMON, LandingSound.CLINK);
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
        if (this.counter == 0
                && (card.type == AbstractCard.CardType.STATUS || card.type == AbstractCard.CardType.CURSE)) {
            this.counter = 1;
            card.setCostForTurn(0);
            card.exhaust = true;
            trigger();
            AbstractDungeon.actionManager.addToBottom(
                    new ExhaustSpecificCardAction(card, AbstractDungeon.player.hand));
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new CrackedLens();
    }
}
