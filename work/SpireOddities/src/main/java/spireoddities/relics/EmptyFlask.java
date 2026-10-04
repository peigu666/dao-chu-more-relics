package spireoddities.relics;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class EmptyFlask extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("EmptyFlask");
    private boolean primed;

    public EmptyFlask() {
        super(ID, "EmptyFlask.png", RelicTier.COMMON, LandingSound.CLINK);
    }

    @Override
    public void atBattleStart() {
        this.primed = false;
    }

    @Override
    public void onUsePotion() {
        this.primed = true;
        flash();
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (this.primed && card.type == AbstractCard.CardType.ATTACK) {
            this.primed = false;
            AbstractCard copy = card.makeStatEquivalentCopy();
            copy.setCostForTurn(0);
            copy.exhaust = true;
            flash();
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(copy));
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new EmptyFlask();
    }
}
