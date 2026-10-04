package spireoddities.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class IronRing extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("IronRing");

    public IronRing() {
        super(ID, "IronRing.png", RelicTier.COMMON, LandingSound.CLINK);
        this.counter = 0;
    }

    @Override
    public void atBattleStart() {
        this.counter = 0;
    }

    @Override
    public void onLoseHp(int amount) {
        if (amount > 0 && this.counter == 0) {
            this.counter = 1;
            flash();
        }
    }

    @Override
    public void onPlayerEndTurn() {
        if (this.counter == 1) {
            AbstractCard highestCost = null;
            for (AbstractCard card : AbstractDungeon.player.hand.group) {
                if (card.costForTurn >= 0
                        && (highestCost == null || card.costForTurn > highestCost.costForTurn)) {
                    highestCost = card;
                }
            }
            if (highestCost != null) {
                highestCost.retain = true;
                trigger();
            }
        }
        this.counter = 0;
    }

    @Override
    public AbstractRelic makeCopy() {
        return new IronRing();
    }
}
