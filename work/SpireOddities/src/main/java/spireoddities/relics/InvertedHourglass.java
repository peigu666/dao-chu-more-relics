package spireoddities.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;
import spireoddities.util.TextureLoader;

public class InvertedHourglass extends CustomRelic {
    public static final String ID = SpireOddities.makeID("InvertedHourglass");
    private static final String IMG = SpireOddities.makeRelicPath("InvertedHourglass.png");
    private static final String OUTLINE = SpireOddities.makeRelicOutlinePath("InvertedHourglass.png");

    public InvertedHourglass() {
        super(ID, TextureLoader.getTexture(IMG), TextureLoader.getTexture(OUTLINE),
                RelicTier.RARE, LandingSound.MAGICAL);
        this.counter = 0;
    }

    @Override
    public void atBattleStart() {
        this.counter = 0;
    }

    @Override
    public void onShuffle() {
        if (this.counter == 0) {
            this.counter = 1;
            flash();
            AbstractDungeon.actionManager.addToBottom(
                    new RelicAboveCreatureAction(AbstractDungeon.player, this));
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(2));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new InvertedHourglass();
    }
}
