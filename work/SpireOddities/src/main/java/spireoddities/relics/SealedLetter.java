package spireoddities.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class SealedLetter extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("SealedLetter");

    public SealedLetter() {
        super(ID, "SealedLetter.png", RelicTier.UNCOMMON, LandingSound.FLAT);
    }

    @Override
    public void onObtainCard(AbstractCard card) {
        if (card.type == AbstractCard.CardType.POWER) {
            flash();
            AbstractDungeon.player.gainGold(10);
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new SealedLetter();
    }
}
