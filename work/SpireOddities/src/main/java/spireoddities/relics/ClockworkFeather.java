package spireoddities.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class ClockworkFeather extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("ClockworkFeather");

    public ClockworkFeather() {
        super(ID, "ClockworkFeather.png", RelicTier.RARE, LandingSound.MAGICAL);
        this.counter = 0;
    }

    @Override
    public void atBattleStart() {
        this.counter = 0;
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        if (this.counter == 0 && AbstractDungeon.player.drawPile.isEmpty()) {
            this.counter = 1;
            draw(2);
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new ClockworkFeather();
    }
}
