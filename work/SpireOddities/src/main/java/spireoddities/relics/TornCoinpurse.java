package spireoddities.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.ShopRoom;
import spireoddities.SpireOddities;

public class TornCoinpurse extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("TornCoinpurse");

    public TornCoinpurse() {
        super(ID, "TornCoinpurse.png", RelicTier.COMMON, LandingSound.CLINK);
        this.counter = 0;
    }

    @Override
    public void onEnterRoom(AbstractRoom room) {
        if (room instanceof ShopRoom) {
            this.counter = 0;
        }
    }

    @Override
    public void onObtainCard(AbstractCard card) {
        if (this.counter == 0 && card.canUpgrade()
                && AbstractDungeon.getCurrRoom() instanceof ShopRoom) {
            this.counter = 1;
            card.upgrade();
            flash();
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new TornCoinpurse();
    }
}
