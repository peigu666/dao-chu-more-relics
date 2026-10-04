package spireoddities.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class CinderCrown extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("CinderCrown");

    public CinderCrown() {
        super(ID, "CinderCrown.png", RelicTier.RARE, LandingSound.MAGICAL);
        this.counter = 0;
    }

    @Override
    public void atBattleStart() {
        this.counter = 0;
    }

    @Override
    public void onExhaust(AbstractCard card) {
        this.counter++;
        if (this.counter >= 3) {
            this.counter = 0;
            AbstractMonster target = null;
            for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
                if (monster != null && !monster.isDeadOrEscaped()
                        && (target == null || monster.currentHealth > target.currentHealth)) {
                    target = monster;
                }
            }
            if (target != null) {
                trigger();
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target,
                        AbstractDungeon.player, new WeakPower(target, 1, false)));
            }
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new CinderCrown();
    }
}
