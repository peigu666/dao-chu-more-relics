package spireoddities.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.EventRoom;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class FoldedFan extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("FoldedFan");

    public FoldedFan() {
        super(ID, "FoldedFan.png", RelicTier.UNCOMMON, LandingSound.CLINK);
        this.counter = 0;
    }

    @Override
    public void atBattleStart() {
        if (this.counter == 1) {
            trigger();
            for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
                if (monster != null && !monster.isDeadOrEscaped()) {
                    AbstractDungeon.actionManager.addToBottom(
                            new ApplyPowerAction(monster, AbstractDungeon.player,
                                    new WeakPower(monster, 1, false)));
                }
            }
        }
        this.counter = 0;
    }

    @Override
    public void onEnterRoom(AbstractRoom room) {
        if (room instanceof EventRoom) {
            this.counter = 1;
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new FoldedFan();
    }
}
