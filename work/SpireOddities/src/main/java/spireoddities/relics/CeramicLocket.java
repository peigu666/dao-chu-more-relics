package spireoddities.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class CeramicLocket extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("CeramicLocket");

    public CeramicLocket() {
        super(ID, "CeramicLocket.png", RelicTier.UNCOMMON, LandingSound.FLAT);
        this.counter = 0;
    }

    @Override
    public void atBattleStart() {
        this.counter = 0;
    }

    @Override
    public void onLoseHp(int amount) {
        if (this.counter == 0 && amount > 0
                && AbstractDungeon.player.currentHealth - amount <= AbstractDungeon.player.maxHealth / 2) {
            this.counter = 1;
            gainBlock(6);
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new CeramicLocket();
    }
}
