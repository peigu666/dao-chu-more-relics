package spireoddities.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class LooseButton extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("LooseButton");

    public LooseButton() {
        super(ID, "LooseButton.png", RelicTier.COMMON, LandingSound.CLINK);
    }

    @Override
    public void onPlayerEndTurn() {
        if (AbstractDungeon.player.hand.size() >= 5) {
            AbstractCard chosen = AbstractDungeon.player.hand.getRandomCard(AbstractDungeon.cardRandomRng);
            if (chosen != null) {
                chosen.retain = true;
                trigger();
            }
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new LooseButton();
    }
}
