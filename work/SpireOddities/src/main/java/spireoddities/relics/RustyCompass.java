package spireoddities.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.EventRoom;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import spireoddities.SpireOddities;

public class RustyCompass extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("RustyCompass");

    public RustyCompass() {
        super(ID, "RustyCompass.png", RelicTier.UNCOMMON, LandingSound.CLINK);
    }

    @Override
    public void onEnterRoom(AbstractRoom room) {
        if (room instanceof EventRoom) {
            AbstractDungeon.player.gainGold(15);
            flash();
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new RustyCompass();
    }
}
