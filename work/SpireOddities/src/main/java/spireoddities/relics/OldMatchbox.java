package spireoddities.relics;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class OldMatchbox extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("OldMatchbox");

    public OldMatchbox() {
        super(ID, "OldMatchbox.png", RelicTier.COMMON, LandingSound.CLINK);
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
        if (this.counter == 0 && card.costForTurn >= 2) {
            this.counter = 1;
            gainEnergy(1);
            AbstractDungeon.actionManager.addToBottom(
                    new MakeTempCardInDiscardAction(new Dazed(), true));
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new OldMatchbox();
    }
}
