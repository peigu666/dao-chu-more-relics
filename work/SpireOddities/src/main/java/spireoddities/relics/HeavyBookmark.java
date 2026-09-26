package spireoddities.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class HeavyBookmark extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("HeavyBookmark");

    public HeavyBookmark() {
        super(ID, "HeavyBookmark.png", RelicTier.COMMON, LandingSound.FLAT);
    }

    @Override
    public void atTurnStartPostDraw() {
        if (AbstractDungeon.player.drawPile.size() >= 10) {
            draw(1);
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new HeavyBookmark();
    }
}
