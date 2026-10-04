package spireoddities.relics;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class CagedSpark extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("CagedSpark");

    public CagedSpark() {
        super(ID, "CagedSpark.png", RelicTier.RARE, LandingSound.MAGICAL);
        this.counter = 0;
    }

    @Override
    public void atBattleStart() {
        this.counter = 0;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        int energyCost = card.costForTurn == -1 ? card.energyOnUse : card.costForTurn;
        boolean spendsEnergy = !card.freeToPlay() && !card.isInAutoplay
                && !(AbstractDungeon.player.hasPower("Corruption")
                && card.type == AbstractCard.CardType.SKILL);
        if (this.counter == 0 && spendsEnergy && energyCost >= 2
                && AbstractDungeon.player.energy.energy == energyCost) {
            this.counter = 1;
            gainPlayerPower(new ArtifactPower(AbstractDungeon.player, 1));
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new CagedSpark();
    }
}
