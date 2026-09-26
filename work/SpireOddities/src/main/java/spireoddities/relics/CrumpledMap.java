package spireoddities.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;
import spireoddities.SpireOddities;

public class CrumpledMap extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("CrumpledMap");

    public CrumpledMap() {
        super(ID, "CrumpledMap.png", RelicTier.UNCOMMON, LandingSound.FLAT);
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
            gainStrength(1);
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new CrumpledMap();
    }
}
