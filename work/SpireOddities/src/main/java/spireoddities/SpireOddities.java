package spireoddities;

import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import spireoddities.relics.*;

@SpireInitializer
public class SpireOddities implements EditRelicsSubscriber, EditStringsSubscriber {
    public static final String MOD_ID = "SpireOddities";
    public static final String RESOURCE_PREFIX = MOD_ID + "Resources";

    public SpireOddities() {
        BaseMod.subscribe(this);
    }

    public static void initialize() {
        new SpireOddities();
    }

    public static String makeID(String name) {
        return MOD_ID + ":" + name;
    }

    public static String makeRelicPath(String fileName) {
        return RESOURCE_PREFIX + "/images/relics/" + fileName;
    }

    public static String makeRelicOutlinePath(String fileName) {
        return RESOURCE_PREFIX + "/images/relics/outline/" + fileName;
    }

    @Override
    public void receiveEditStrings() {
        String language = Settings.language == Settings.GameLanguage.ZHS ? "zhs" : "eng";
        BaseMod.loadCustomStringsFile(
                RelicStrings.class,
                RESOURCE_PREFIX + "/localization/" + language + "/SpireOddities-Relic-Strings.json");
    }

    @Override
    public void receiveEditRelics() {
        BaseMod.addRelic(new TightKnot(), RelicType.SHARED);
        BaseMod.addRelic(new CrackedWaxSeal(), RelicType.SHARED);
        BaseMod.addRelic(new InvertedHourglass(), RelicType.SHARED);
        BaseMod.addRelic(new FineCopperChain(), RelicType.SHARED);
        BaseMod.addRelic(new WornBandage(), RelicType.SHARED);
        BaseMod.addRelic(new EmptyFlask(), RelicType.SHARED);
        BaseMod.addRelic(new AshPouch(), RelicType.SHARED);
        BaseMod.addRelic(new PatchedLeather(), RelicType.SHARED);
        BaseMod.addRelic(new LooseButton(), RelicType.SHARED);
        BaseMod.addRelic(new BentNail(), RelicType.SHARED);
        BaseMod.addRelic(new FoldedFan(), RelicType.SHARED);
        BaseMod.addRelic(new SealedLetter(), RelicType.SHARED);
        BaseMod.addRelic(new CopperBell(), RelicType.SHARED);
        BaseMod.addRelic(new WaxedThread(), RelicType.SHARED);
        BaseMod.addRelic(new RustyCompass(), RelicType.SHARED);
        BaseMod.addRelic(new Paperweight(), RelicType.SHARED);
        BaseMod.addRelic(new SpareKey(), RelicType.SHARED);
        BaseMod.addRelic(new GoldenThread(), RelicType.SHARED);
        BaseMod.addRelic(new ClockworkFeather(), RelicType.SHARED);
        BaseMod.addRelic(new CinderCrown(), RelicType.SHARED);
        BaseMod.addRelic(new ArchivistSeal(), RelicType.SHARED);
        BaseMod.addRelic(new LeadCounterweight(), RelicType.SHARED);
        BaseMod.addRelic(new EmptyDiceCup(), RelicType.SHARED);
    }
}
