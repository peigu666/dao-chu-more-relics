package spireoddities.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class CopperButton extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("CopperButton");

    public CopperButton() {
        super(ID, "CopperButton.png", RelicTier.COMMON, LandingSound.CLINK);
    }

    @Override
    public void onPlayerEndTurn() {
        if (AbstractDungeon.player.energy.energy == 0) {
            gainBlock(3);
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new CopperButton();
    }
}
