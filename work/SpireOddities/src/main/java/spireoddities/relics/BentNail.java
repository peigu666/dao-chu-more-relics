package spireoddities.relics;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class BentNail extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("BentNail");

    public BentNail() {
        super(ID, "BentNail.png", RelicTier.COMMON, LandingSound.CLINK);
        this.counter = 0;
    }

    @Override
    public void atBattleStart() {
        this.counter = 0;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK && card.costForTurn == 0) {
            this.counter = 1;
        }
    }

    @Override
    public int onAttackToChangeDamage(DamageInfo info, int damageAmount) {
        if (this.counter == 1) {
            this.counter = 0;
            trigger();
            return damageAmount + 5;
        }
        return damageAmount;
    }

    @Override
    public AbstractRelic makeCopy() {
        return new BentNail();
    }
}
