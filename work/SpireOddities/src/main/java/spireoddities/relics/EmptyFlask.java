package spireoddities.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.SpireOddities;

public class EmptyFlask extends SpireOdditiesRelic {
    public static final String ID = SpireOddities.makeID("EmptyFlask");

    public EmptyFlask() {
        super(ID, "EmptyFlask.png", RelicTier.COMMON, LandingSound.CLINK);
    }

    @Override
    public void onUsePotion() {
        gainStrength(1);
    }

    @Override
    public AbstractRelic makeCopy() {
        return new EmptyFlask();
    }
}
