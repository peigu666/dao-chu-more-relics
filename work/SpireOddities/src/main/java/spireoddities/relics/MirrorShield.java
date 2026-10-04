package spireoddities.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class MirrorShield extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("MirrorShield");

    public MirrorShield() {
        super(ID, "MirrorShield.png", RelicTier.RARE, LandingSound.MAGICAL);
        this.counter = 0;
    }

    @Override
    public void atBattleStart() {
        this.counter = 0;
    }

    @Override
    public void onMonsterDeath(AbstractMonster deadMonster) {
        if (this.counter != 0) {
            return;
        }

        AbstractPower weak = deadMonster.getPower(WeakPower.POWER_ID);
        AbstractPower vulnerable = deadMonster.getPower(VulnerablePower.POWER_ID);
        int weakAmount = weak == null ? 0 : Math.min(2, weak.amount);
        int vulnerableAmount = vulnerable == null ? 0 : Math.min(2, vulnerable.amount);
        if (weakAmount == 0 && vulnerableAmount == 0) {
            return;
        }

        this.counter = 1;
        trigger();
        for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (monster == null || monster == deadMonster || monster.isDeadOrEscaped()) {
                continue;
            }
            if (weakAmount > 0) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster,
                        AbstractDungeon.player, new WeakPower(monster, weakAmount, false)));
            }
            if (vulnerableAmount > 0) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster,
                        AbstractDungeon.player,
                        new VulnerablePower(monster, vulnerableAmount, false)));
            }
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new MirrorShield();
    }
}
