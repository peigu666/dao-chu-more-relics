package spireoddities.relics;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class BrassPin extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("BrassPin");

    public BrassPin() {
        super(ID, "BrassPin.png", RelicTier.COMMON, LandingSound.CLINK);
        this.counter = 0;
    }

    @Override
    public void atBattleStart() {
        this.counter = 0;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (this.counter == 0 && card.type == AbstractCard.CardType.SKILL) {
            this.counter = 1;
            gainDexterity(1);
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new BrassPin();
    }
}
