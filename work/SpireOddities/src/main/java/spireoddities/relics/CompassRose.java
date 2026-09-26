package spireoddities.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;
import spireoddities.SpireOddities;

public class CompassRose extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("CompassRose");

    public CompassRose() {
        super(ID, "CompassRose.png", RelicTier.RARE, LandingSound.MAGICAL);
        this.counter = 0;
    }

    @Override
    public void onEnterRoom(AbstractRoom room) {
        if (room instanceof MonsterRoomElite) {
            this.counter = 1;
        }
    }

    @Override
    public void atBattleStart() {
        if (this.counter == 1) {
            this.counter = 0;
            gainEnergy(1);
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new CompassRose();
    }
}
