package spireoddities.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class SewnPocket extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("SewnPocket");

    public SewnPocket() {
        super(ID, "SewnPocket.png", RelicTier.COMMON, LandingSound.FLAT);
    }

    @Override
    public void onPlayerEndTurn() {
        if (AbstractDungeon.player.discardPile.size() >= 5) {
            AbstractCard chosen = AbstractDungeon.player.discardPile.getRandomCard(AbstractDungeon.cardRandomRng);
            if (chosen != null) {
                AbstractDungeon.player.drawPile.addToTop(chosen.makeStatEquivalentCopy());
                trigger();
            }
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new SewnPocket();
    }
}
