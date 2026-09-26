package spireoddities.relics;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class FineCopperChain extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("FineCopperChain");

    public FineCopperChain() {
        super(ID, "FineCopperChain.png", RelicTier.COMMON, LandingSound.CLINK);
        this.counter = 0;
    }

    @Override
    public void atBattleStart() {
        this.counter = 0;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.SKILL) {
            this.counter = 1;
        }
    }

    @Override
    public int onAttackToChangeDamage(DamageInfo info, int damageAmount) {
        if (this.counter == 1) {
            this.counter = 0;
            trigger();
            return damageAmount + 4;
        }
        return damageAmount;
    }

    @Override
    public AbstractRelic makeCopy() {
        return new FineCopperChain();
    }
}
