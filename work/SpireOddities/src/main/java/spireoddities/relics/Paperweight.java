package spireoddities.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class Paperweight extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("Paperweight");

    public Paperweight() {
        super(ID, "Paperweight.png", RelicTier.UNCOMMON, LandingSound.FLAT);
    }

    @Override
    public void atTurnStartPostDraw() {
        if (AbstractDungeon.player.drawPile.size() <= 5) {
            gainEnergy(1);
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Paperweight();
    }
}
