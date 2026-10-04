package spireoddities.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import spireoddities.relics.NovelRelic;
import spireoddities.SpireOddities;

@SpirePatch(clz = CardRewardScreen.class, method = "skippedCards")
public class SlottedStoneRewardSkipPatch {
    @SpirePostfixPatch
    public static void postfix(CardRewardScreen __instance) {
        if (AbstractDungeon.player != null
                && AbstractDungeon.player.hasRelic(SpireOddities.makeID("SlottedStone"))) {
            ((NovelRelic) AbstractDungeon.player.getRelic(
                    SpireOddities.makeID("SlottedStone"))).onCardRewardSkipped();
        }
    }
}
