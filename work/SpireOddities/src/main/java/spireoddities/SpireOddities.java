package spireoddities;

import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
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

    private static void addNovel(String id, AbstractRelic.RelicTier tier, NovelRelic.Mode mode) {
        addNovel(id, tier, mode, RelicType.SHARED);
    }

    private static void addNovel(String id, AbstractRelic.RelicTier tier, NovelRelic.Mode mode,
                                 RelicType pool) {
        BaseMod.addRelic(new NovelRelic(id, tier, mode), pool);
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
        BaseMod.addRelic(new OldMatchbox(), RelicType.SHARED);
        BaseMod.addRelic(new SplitBuckle(), RelicType.SHARED);
        BaseMod.addRelic(new WaxedCharm(), RelicType.GREEN);
        BaseMod.addRelic(new IronRing(), RelicType.SHARED);
        BaseMod.addRelic(new TornCoinpurse(), RelicType.SHARED);
        BaseMod.addRelic(new AshenNeedle(), RelicType.SHARED);
        BaseMod.addRelic(new SaltedRation(), RelicType.SHARED);
        BaseMod.addRelic(new CrackedLens(), RelicType.SHARED);
        BaseMod.addRelic(new SewnPocket(), RelicType.SHARED);
        BaseMod.addRelic(new BrassPin(), RelicType.SHARED);
        BaseMod.addRelic(new HeavyBookmark(), RelicType.SHARED);
        BaseMod.addRelic(new PocketSand(), RelicType.SHARED);
        BaseMod.addRelic(new CorkStub(), RelicType.SHARED);
        BaseMod.addRelic(new CopperButton(), RelicType.SHARED);
        BaseMod.addRelic(new MirrorShard(), RelicType.SHARED);
        BaseMod.addRelic(new BlackenedCoin(), RelicType.SHARED);
        BaseMod.addRelic(new TravelerToken(), RelicType.SHARED);
        BaseMod.addRelic(new CrumpledMap(), RelicType.SHARED);
        BaseMod.addRelic(new HollowQuill(), RelicType.SHARED);
        BaseMod.addRelic(new CorkedVial(), RelicType.SHARED);
        BaseMod.addRelic(new ThreadedNeedle(), RelicType.SHARED);
        BaseMod.addRelic(new SootyMirror(), RelicType.SHARED);
        BaseMod.addRelic(new CeramicLocket(), RelicType.SHARED);
        BaseMod.addRelic(new BrassHourglass(), RelicType.SHARED);
        BaseMod.addRelic(new RationTin(), RelicType.SHARED);
        BaseMod.addRelic(new BrokenDie(), RelicType.SHARED);
        BaseMod.addRelic(new PolishedStone(), RelicType.SHARED);
        BaseMod.addRelic(new EmptyBell(), RelicType.SHARED);
        BaseMod.addRelic(new MirrorShield(), RelicType.SHARED);
        BaseMod.addRelic(new SilverThread(), RelicType.SHARED);
        BaseMod.addRelic(new RoyalSeal(), RelicType.SHARED);
        BaseMod.addRelic(new BloodLedger(), RelicType.SHARED);
        BaseMod.addRelic(new CagedSpark(), RelicType.SHARED);
        BaseMod.addRelic(new StanceTalisman(), RelicType.PURPLE);
        BaseMod.addRelic(new OrbEmblem(), RelicType.BLUE);
        BaseMod.addRelic(new GildedScissors(), RelicType.SHARED);
        BaseMod.addRelic(new CompassRose(), RelicType.SHARED);
        BaseMod.addRelic(new DawnLantern(), RelicType.SHARED);
        BaseMod.addRelic(new SilverScale(), RelicType.SHARED);
        BaseMod.addRelic(new SilentLedger(), RelicType.SHARED);

        addNovel("Knucklebone", AbstractRelic.RelicTier.COMMON, NovelRelic.Mode.KNUCKLEBONE);
        addNovel("EmberPin", AbstractRelic.RelicTier.COMMON, NovelRelic.Mode.EMBER_PIN);
        addNovel("PaperCrown", AbstractRelic.RelicTier.COMMON, NovelRelic.Mode.PAPER_CROWN);
        addNovel("MothWing", AbstractRelic.RelicTier.COMMON, NovelRelic.Mode.MOTH_WING);
        addNovel("PatiencePebble", AbstractRelic.RelicTier.COMMON, NovelRelic.Mode.PATIENCE_PEBBLE);
        addNovel("BrokenRuler", AbstractRelic.RelicTier.COMMON, NovelRelic.Mode.BROKEN_RULER);
        addNovel("Inkblot", AbstractRelic.RelicTier.COMMON, NovelRelic.Mode.INKBLOT);
        addNovel("LooseGear", AbstractRelic.RelicTier.COMMON, NovelRelic.Mode.LOOSE_GEAR);
        addNovel("Matchbook", AbstractRelic.RelicTier.COMMON, NovelRelic.Mode.MATCHBOOK);
        addNovel("RibbonLoop", AbstractRelic.RelicTier.COMMON, NovelRelic.Mode.RIBBON_LOOP);
        addNovel("DriedApple", AbstractRelic.RelicTier.COMMON, NovelRelic.Mode.DRIED_APPLE);
        addNovel("TangleHook", AbstractRelic.RelicTier.COMMON, NovelRelic.Mode.TANGLE_HOOK);
        addNovel("CandleStub", AbstractRelic.RelicTier.COMMON, NovelRelic.Mode.CANDLE_STUB);
        addNovel("TinCrown", AbstractRelic.RelicTier.COMMON, NovelRelic.Mode.TIN_CROWN);
        addNovel("HollowMarble", AbstractRelic.RelicTier.COMMON,
                NovelRelic.Mode.HOLLOW_MARBLE, RelicType.BLUE);
        addNovel("QuietBell", AbstractRelic.RelicTier.COMMON, NovelRelic.Mode.QUIET_BELL);
        addNovel("ThreadSpool", AbstractRelic.RelicTier.COMMON, NovelRelic.Mode.THREAD_SPOOL);
        addNovel("SootMark", AbstractRelic.RelicTier.COMMON, NovelRelic.Mode.SOOT_MARK);
        addNovel("CopperLatch", AbstractRelic.RelicTier.COMMON, NovelRelic.Mode.COPPER_LATCH);
        addNovel("WindupKey", AbstractRelic.RelicTier.COMMON, NovelRelic.Mode.WINDUP_KEY);
        addNovel("SeedPouch", AbstractRelic.RelicTier.COMMON, NovelRelic.Mode.SEED_POUCH);
        addNovel("MuddyBoots", AbstractRelic.RelicTier.COMMON, NovelRelic.Mode.MUDDY_BOOTS);
        addNovel("StainedMap", AbstractRelic.RelicTier.COMMON, NovelRelic.Mode.STAINED_MAP);
        addNovel("WickCandle", AbstractRelic.RelicTier.COMMON, NovelRelic.Mode.WICK_CANDLE);
        addNovel("CopperScale", AbstractRelic.RelicTier.COMMON, NovelRelic.Mode.COPPER_SCALE);
        addNovel("IronAcorn", AbstractRelic.RelicTier.COMMON, NovelRelic.Mode.IRON_ACORN);
        addNovel("ThornButton", AbstractRelic.RelicTier.COMMON, NovelRelic.Mode.THORN_BUTTON);
        addNovel("AshenRibbon", AbstractRelic.RelicTier.COMMON, NovelRelic.Mode.ASHEN_RIBBON);
        addNovel("PocketWhistle", AbstractRelic.RelicTier.COMMON, NovelRelic.Mode.POCKET_WHISTLE);
        addNovel("CardboardMask", AbstractRelic.RelicTier.COMMON, NovelRelic.Mode.CARDBOARD_MASK);
        addNovel("TornBookmark", AbstractRelic.RelicTier.COMMON, NovelRelic.Mode.TORN_BOOKMARK);
        addNovel("FadedDice", AbstractRelic.RelicTier.COMMON, NovelRelic.Mode.FADED_DICE);
        addNovel("SpareSpring", AbstractRelic.RelicTier.COMMON, NovelRelic.Mode.SPARE_SPRING);
        addNovel("FullSaltStone", AbstractRelic.RelicTier.COMMON, NovelRelic.Mode.FULL_SALT_STONE);
        addNovel("DullNeedle", AbstractRelic.RelicTier.COMMON, NovelRelic.Mode.DULL_NEEDLE);
        addNovel("PocketChalk", AbstractRelic.RelicTier.COMMON, NovelRelic.Mode.POCKET_CHALK);
        addNovel("GreasedKey", AbstractRelic.RelicTier.COMMON, NovelRelic.Mode.GREASED_KEY);
        addNovel("MasonChip", AbstractRelic.RelicTier.COMMON, NovelRelic.Mode.MASON_CHIP);
        addNovel("QuietCoin", AbstractRelic.RelicTier.COMMON, NovelRelic.Mode.QUIET_COIN);
        addNovel("BrittleCrown", AbstractRelic.RelicTier.COMMON, NovelRelic.Mode.BRITTLE_CROWN);

        addNovel("SevenKnotCord", AbstractRelic.RelicTier.UNCOMMON, NovelRelic.Mode.SEVEN_KNOT_CORD);
        addNovel("ResonantFork", AbstractRelic.RelicTier.UNCOMMON, NovelRelic.Mode.RESONANT_FORK);
        addNovel("FalseBottom", AbstractRelic.RelicTier.UNCOMMON, NovelRelic.Mode.FALSE_BOTTOM);
        addNovel("CinderCompass", AbstractRelic.RelicTier.UNCOMMON, NovelRelic.Mode.CINDER_COMPASS);
        addNovel("CrownOfThorns", AbstractRelic.RelicTier.UNCOMMON, NovelRelic.Mode.CROWN_OF_THORNS);
        addNovel("BlueHourglass", AbstractRelic.RelicTier.UNCOMMON, NovelRelic.Mode.BLUE_HOURGLASS);
        addNovel("SplitCoin", AbstractRelic.RelicTier.UNCOMMON, NovelRelic.Mode.SPLIT_COIN);
        addNovel("MirrorDice", AbstractRelic.RelicTier.UNCOMMON, NovelRelic.Mode.MIRROR_DICE);
        addNovel("PaintedMask", AbstractRelic.RelicTier.UNCOMMON, NovelRelic.Mode.PAINTED_MASK);
        addNovel("SaltCrown", AbstractRelic.RelicTier.UNCOMMON, NovelRelic.Mode.SALT_CROWN);
        addNovel("TuningFork", AbstractRelic.RelicTier.UNCOMMON, NovelRelic.Mode.TUNING_FORK);
        addNovel("ThreadedCompass", AbstractRelic.RelicTier.UNCOMMON, NovelRelic.Mode.THREADED_COMPASS);
        addNovel("BlackRibbon", AbstractRelic.RelicTier.UNCOMMON, NovelRelic.Mode.BLACK_RIBBON);
        addNovel("GlassOrchard", AbstractRelic.RelicTier.UNCOMMON, NovelRelic.Mode.GLASS_ORCHARD);
        addNovel("BuriedKey", AbstractRelic.RelicTier.UNCOMMON, NovelRelic.Mode.BURIED_KEY);
        addNovel("RedLedger", AbstractRelic.RelicTier.UNCOMMON, NovelRelic.Mode.RED_LEDGER);
        addNovel("SootCage", AbstractRelic.RelicTier.UNCOMMON, NovelRelic.Mode.SOOT_CAGE);
        addNovel("AlchemistCork", AbstractRelic.RelicTier.UNCOMMON, NovelRelic.Mode.ALCHEMIST_CORK);
        addNovel("LuckySplinter", AbstractRelic.RelicTier.UNCOMMON, NovelRelic.Mode.LUCKY_SPLINTER);
        addNovel("UnderstudySeal", AbstractRelic.RelicTier.UNCOMMON, NovelRelic.Mode.UNDERSTUDY_SEAL);
        addNovel("HauntedBookmark", AbstractRelic.RelicTier.UNCOMMON, NovelRelic.Mode.HAUNTED_BOOKMARK);
        addNovel("StolenHour", AbstractRelic.RelicTier.UNCOMMON, NovelRelic.Mode.STOLEN_HOUR);
        addNovel("CrackedBellows", AbstractRelic.RelicTier.UNCOMMON, NovelRelic.Mode.CRACKED_BELLOWS);
        addNovel("FalseCrown", AbstractRelic.RelicTier.UNCOMMON, NovelRelic.Mode.FALSE_CROWN);
        addNovel("ThornyDice", AbstractRelic.RelicTier.UNCOMMON, NovelRelic.Mode.THORNY_DICE);
        addNovel("DampenedBell", AbstractRelic.RelicTier.UNCOMMON, NovelRelic.Mode.DAMPENED_BELL);
        addNovel("BorrowedQuill", AbstractRelic.RelicTier.UNCOMMON, NovelRelic.Mode.BORROWED_QUILL);
        addNovel("ClockworkNest", AbstractRelic.RelicTier.UNCOMMON, NovelRelic.Mode.CLOCKWORK_NEST);
        addNovel("PocketTelescope", AbstractRelic.RelicTier.UNCOMMON, NovelRelic.Mode.POCKET_TELESCOPE);
        addNovel("SlottedStone", AbstractRelic.RelicTier.UNCOMMON, NovelRelic.Mode.SLOTTED_STONE);
        addNovel("FatesThread", AbstractRelic.RelicTier.UNCOMMON, NovelRelic.Mode.FATES_THREAD);
        addNovel("VacantScabbard", AbstractRelic.RelicTier.UNCOMMON, NovelRelic.Mode.VACANT_SCABBARD);
        addNovel("TallyStone", AbstractRelic.RelicTier.UNCOMMON, NovelRelic.Mode.TALLY_STONE);
        addNovel("StitchedMask", AbstractRelic.RelicTier.UNCOMMON, NovelRelic.Mode.STITCHED_MASK);
        addNovel("CoalLantern", AbstractRelic.RelicTier.UNCOMMON, NovelRelic.Mode.COAL_LANTERN);

        addNovel("OuroborosLoop", AbstractRelic.RelicTier.RARE, NovelRelic.Mode.OUROBOROS_LOOP);
        addNovel("RedString", AbstractRelic.RelicTier.RARE, NovelRelic.Mode.RED_STRING);
        addNovel("EmptyCrown", AbstractRelic.RelicTier.RARE, NovelRelic.Mode.EMPTY_CROWN);
        addNovel("UnstablePrism", AbstractRelic.RelicTier.RARE, NovelRelic.Mode.UNSTABLE_PRISM);
        addNovel("GlassGuillotine", AbstractRelic.RelicTier.BOSS, NovelRelic.Mode.GLASS_GUILLOTINE);
        addNovel("BlackTide", AbstractRelic.RelicTier.BOSS, NovelRelic.Mode.BLACK_TIDE);
        addNovel("ChoirOfNails", AbstractRelic.RelicTier.RARE, NovelRelic.Mode.CHOIR_OF_NAILS);
        addNovel("LastMatch", AbstractRelic.RelicTier.RARE, NovelRelic.Mode.LAST_MATCH);
        addNovel("MismatchedCompass", AbstractRelic.RelicTier.RARE, NovelRelic.Mode.MISMATCHED_COMPASS);
        addNovel("CrownOfDetours", AbstractRelic.RelicTier.RARE, NovelRelic.Mode.CROWN_OF_DETOURS);
        addNovel("PaperMoon", AbstractRelic.RelicTier.RARE, NovelRelic.Mode.PAPER_MOON);
        addNovel("HollowContract", AbstractRelic.RelicTier.BOSS, NovelRelic.Mode.HOLLOW_CONTRACT);
        addNovel("EngineOfMaybe", AbstractRelic.RelicTier.RARE, NovelRelic.Mode.ENGINE_OF_MAYBE);
        addNovel("TideClock", AbstractRelic.RelicTier.RARE, NovelRelic.Mode.TIDE_CLOCK);
        addNovel("PermanentMarker", AbstractRelic.RelicTier.RARE, NovelRelic.Mode.PERMANENT_MARKER);
        addNovel("ReverseBell", AbstractRelic.RelicTier.RARE, NovelRelic.Mode.REVERSE_BELL);
        addNovel("SpiderBargain", AbstractRelic.RelicTier.BOSS, NovelRelic.Mode.SPIDER_BARGAIN);
        addNovel("AtlasOfErrors", AbstractRelic.RelicTier.RARE, NovelRelic.Mode.ATLAS_OF_ERRORS);
        addNovel("PrismCage", AbstractRelic.RelicTier.RARE, NovelRelic.Mode.PRISM_CAGE);
        addNovel("FatesReceipt", AbstractRelic.RelicTier.RARE, NovelRelic.Mode.FATES_RECEIPT);
        addNovel("CrimsonNeedle", AbstractRelic.RelicTier.RARE, NovelRelic.Mode.CRIMSON_NEEDLE);
        addNovel("StormChime", AbstractRelic.RelicTier.RARE, NovelRelic.Mode.STORM_CHIME);
        addNovel("FracturedCrown", AbstractRelic.RelicTier.RARE,
                NovelRelic.Mode.FRACTURED_CROWN, RelicType.PURPLE);
        addNovel("QuietStorm", AbstractRelic.RelicTier.RARE, NovelRelic.Mode.QUIET_STORM);
        addNovel("LibraryOfAsh", AbstractRelic.RelicTier.RARE, NovelRelic.Mode.LIBRARY_OF_ASH);
    }
}
