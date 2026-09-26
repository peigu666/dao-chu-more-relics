package spireoddities.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class AshPouch extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("AshPouch");

    public AshPouch() {
        super(ID, "AshPouch.png", RelicTier.COMMON, LandingSound.FLAT);
    }

    @Override
    public void onExhaust(AbstractCard card) {
        if (card.type == AbstractCard.CardType.STATUS || card.type == AbstractCard.CardType.CURSE) {
            damageAll(6);
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new AshPouch();
    }
}
