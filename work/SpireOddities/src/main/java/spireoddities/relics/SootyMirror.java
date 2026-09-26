package spireoddities.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class SootyMirror extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("SootyMirror");

    public SootyMirror() {
        super(ID, "SootyMirror.png", RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    @Override
    public void atTurnStartPostDraw() {
        if (AbstractDungeon.player.currentHealth * 2 <= AbstractDungeon.player.maxHealth) {
            draw(1);
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new SootyMirror();
    }
}
