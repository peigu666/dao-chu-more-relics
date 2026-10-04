package spireoddities.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import spireoddities.relics.NovelRelic;

@SpirePatch(clz = AbstractCreature.class, method = "brokeBlock")
public class PlayerBlockBrokenPatch {
    @SpirePostfixPatch
    public static void postfix(AbstractCreature __instance) {
        if (!(__instance instanceof AbstractPlayer) || __instance != AbstractDungeon.player) {
            return;
        }
        for (AbstractRelic relic : AbstractDungeon.player.relics) {
            if (relic instanceof NovelRelic) {
                ((NovelRelic) relic).onPlayerBlockBroken();
            }
        }
    }
}
