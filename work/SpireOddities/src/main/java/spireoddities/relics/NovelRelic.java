package spireoddities.relics;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.actions.common.UpgradeRandomCardAction;
import com.megacrit.cardcrawl.actions.unique.RandomCardFromDiscardPileToHandAction;
import com.megacrit.cardcrawl.actions.unique.RandomizeHandCostAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.curses.Regret;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.cards.status.Wound;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.EventRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;
import com.megacrit.cardcrawl.stances.AbstractStance;
import spireoddities.SpireOddities;

/**
 * The v0.6.0 relic set uses one small dispatcher so every relic still has its
 * own ID, text and artwork without copying the same lifecycle boilerplate 100
 * times. All effects use base-game cards, actions, powers and relic hooks.
 */
public class NovelRelic extends SpireOdditiesRelic {
    public enum Mode {
        KNUCKLEBONE, EMBER_PIN, PAPER_CROWN, MOTH_WING, PATIENCE_PEBBLE,
        BROKEN_RULER, INKBLOT, LOOSE_GEAR, MATCHBOOK, RIBBON_LOOP,
        DRIED_APPLE, TANGLE_HOOK, CANDLE_STUB, TIN_CROWN, HOLLOW_MARBLE,
        QUIET_BELL, THREAD_SPOOL, SOOT_MARK, COPPER_LATCH, WINDUP_KEY,
        SEED_POUCH, MUDDY_BOOTS, STAINED_MAP, WICK_CANDLE, COPPER_SCALE,
        IRON_ACORN, THORN_BUTTON, ASHEN_RIBBON, POCKET_WHISTLE, CARDBOARD_MASK,
        TORN_BOOKMARK, FADED_DICE, SPARE_SPRING, FULL_SALT_STONE, DULL_NEEDLE,
        POCKET_CHALK, GREASED_KEY, MASON_CHIP, QUIET_COIN, BRITTLE_CROWN,
        SEVEN_KNOT_CORD, RESONANT_FORK, FALSE_BOTTOM, CINDER_COMPASS,
        CROWN_OF_THORNS, BLUE_HOURGLASS, SPLIT_COIN, MIRROR_DICE,
        PAINTED_MASK, SALT_CROWN, TUNING_FORK, THREADED_COMPASS,
        BLACK_RIBBON, GLASS_ORCHARD, BURIED_KEY, RED_LEDGER, SOOT_CAGE,
        ALCHEMIST_CORK, LUCKY_SPLINTER, UNDERSTUDY_SEAL, HAUNTED_BOOKMARK,
        STOLEN_HOUR, CRACKED_BELLOWS, FALSE_CROWN, THORNY_DICE, DAMPENED_BELL,
        BORROWED_QUILL, CLOCKWORK_NEST, POCKET_TELESCOPE, SLOTTED_STONE,
        FATES_THREAD, VACANT_SCABBARD, TALLY_STONE, STITCHED_MASK, COAL_LANTERN,
        OUROBOROS_LOOP, RED_STRING, EMPTY_CROWN, UNSTABLE_PRISM,
        GLASS_GUILLOTINE, BLACK_TIDE, CHOIR_OF_NAILS, LAST_MATCH,
        MISMATCHED_COMPASS, CROWN_OF_DETOURS, PAPER_MOON, HOLLOW_CONTRACT,
        ENGINE_OF_MAYBE, TIDE_CLOCK, PERMANENT_MARKER, REVERSE_BELL,
        SPIDER_BARGAIN, ATLAS_OF_ERRORS, PRISM_CAGE, FATES_RECEIPT,
        CRIMSON_NEEDLE, STORM_CHIME, FRACTURED_CROWN, QUIET_STORM,
        LIBRARY_OF_ASH
    }

    private final String idSuffix;
    private final RelicTier relicTier;
    private final Mode mode;
    private AbstractCard firstCard;
    private AbstractCard storedCard;

    public NovelRelic(String idSuffix, RelicTier relicTier, Mode mode) {
        super(SpireOddities.makeID(idSuffix), idSuffix + ".png", relicTier,
                soundFor(relicTier));
        this.idSuffix = idSuffix;
        this.relicTier = relicTier;
        this.mode = mode;
        this.counter = 0;
    }

    private static LandingSound soundFor(RelicTier tier) {
        if (tier == RelicTier.RARE) {
            return LandingSound.MAGICAL;
        }
        if (tier == RelicTier.UNCOMMON) {
            return LandingSound.FLAT;
        }
        return LandingSound.CLINK;
    }

    @Override
    public AbstractRelic makeCopy() {
        return new NovelRelic(idSuffix, relicTier, mode);
    }

    private void addDiscardCard(AbstractCard card) {
        if (card == null) {
            return;
        }
        trigger();
        AbstractDungeon.actionManager.addToBottom(
                new MakeTempCardInDiscardAction(card, true));
    }

    private void addRandomColorlessToDraw(int cost, boolean exhaust, boolean top) {
        AbstractCard card = AbstractDungeon.returnTrulyRandomColorlessCardInCombat();
        if (card == null) {
            return;
        }
        card.setCostForTurn(cost);
        card.exhaust = exhaust;
        if (top) {
            AbstractDungeon.player.drawPile.addToTop(card);
        } else {
            AbstractDungeon.player.drawPile.addToBottom(card);
        }
        trigger();
    }

    private void addRandomColorlessToDiscard(int cost, boolean exhaust) {
        AbstractCard card = AbstractDungeon.returnTrulyRandomColorlessCardInCombat();
        if (card == null) {
            return;
        }
        card.setCostForTurn(cost);
        card.exhaust = exhaust;
        AbstractDungeon.player.discardPile.addToBottom(card);
        trigger();
    }

    private void addRetainedRandomColorlessCardToHand() {
        AbstractCard card = AbstractDungeon.returnTrulyRandomColorlessCardInCombat();
        if (card == null) {
            return;
        }
        card.setCostForTurn(0);
        card.exhaust = true;
        card.retain = true;
        addCardToHand(card);
    }

    private void copyToHand(AbstractCard card) {
        if (card == null) {
            return;
        }
        addCardToHand(card.makeStatEquivalentCopy());
    }

    private void copyToDrawTop(AbstractCard card) {
        if (card == null) {
            return;
        }
        AbstractDungeon.player.drawPile.addToTop(card.makeStatEquivalentCopy());
        trigger();
    }

    private AbstractCard randomDiscard() {
        if (AbstractDungeon.player.discardPile.isEmpty()) {
            return null;
        }
        return AbstractDungeon.player.discardPile.getRandomCard(AbstractDungeon.cardRandomRng);
    }

    private void moveRandomDiscardToTop() {
        AbstractCard card = randomDiscard();
        if (card == null) {
            return;
        }
        AbstractDungeon.player.discardPile.removeCard(card);
        AbstractDungeon.player.drawPile.addToTop(card);
        trigger();
    }

    private void randomizeHand() {
        if (AbstractDungeon.player.hand.isEmpty()) {
            return;
        }
        trigger();
        addToBot(new RandomizeHandCostAction());
    }

    private void playTopCard() {
        if (AbstractDungeon.player.drawPile.isEmpty()) {
            return;
        }
        trigger();
        addToBot(new PlayTopCardAction(AbstractDungeon.player, true));
    }

    private void applyWeakToAll(int amount) {
        trigger();
        for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (monster != null) {
                addToBot(new ApplyPowerAction(monster, AbstractDungeon.player,
                        new WeakPower(monster, amount, false)));
            }
        }
    }

    private void applyVulnerableToAll(int amount) {
        trigger();
        for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (monster != null) {
                addToBot(new ApplyPowerAction(monster, AbstractDungeon.player,
                        new VulnerablePower(monster, amount, false)));
            }
        }
    }

    private void loseHp(int amount) {
        trigger();
        addToBot(new LoseHPAction(AbstractDungeon.player, AbstractDungeon.player, amount));
    }

    private boolean hasRetainedCard() {
        for (AbstractCard card : AbstractDungeon.player.hand.group) {
            if (card.retain || card.selfRetain) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void atBattleStart() {
        this.counter = 0;
        this.firstCard = null;
        this.storedCard = null;
        switch (mode) {
            case SEED_POUCH:
                addRandomColorlessToDiscard(1, true);
                break;
            case MUDDY_BOOTS:
                if (AbstractDungeon.getCurrRoom() instanceof MonsterRoomElite) {
                    gainPlayerPower(new PlatedArmorPower(AbstractDungeon.player, 2));
                }
                break;
            case ASHEN_RIBBON:
                addDiscardCard(new Burn());
                break;
            case FULL_SALT_STONE:
                if (AbstractDungeon.player.currentHealth >= AbstractDungeon.player.maxHealth) {
                    gainStrength(1);
                } else {
                    heal(3);
                }
                break;
            case GLASS_ORCHARD:
                if (AbstractDungeon.player.currentHealth >= AbstractDungeon.player.maxHealth) {
                    gainBlock(8);
                } else {
                    gainDexterity(1);
                }
                break;
            case BRITTLE_CROWN:
                gainEnergy(1);
                gainPlayerPower(new VulnerablePower(AbstractDungeon.player, 1, false));
                break;
            case MISMATCHED_COMPASS:
                switch (AbstractDungeon.cardRandomRng.random(3)) {
                    case 0:
                        gainStrength(2);
                        break;
                    case 1:
                        gainDexterity(2);
                        break;
                    case 2:
                        gainBlock(8);
                        break;
                    default:
                        gainEnergy(1);
                        break;
                }
                break;
            case UNSTABLE_PRISM:
                addRandomColorlessCardToHand(0, true);
                break;
            case BLACK_TIDE:
                gainStrength(2);
                addDiscardCard(new Wound());
                addDiscardCard(new Wound());
                break;
            case SPIDER_BARGAIN:
                gainEnergy(2);
                addDiscardCard(new Wound());
                addDiscardCard(new Wound());
                break;
            default:
                break;
        }
    }

    @Override
    public void atBattleStartPreDraw() {
        switch (mode) {
            case WINDUP_KEY:
                addRandomColorlessToDraw(1, true, true);
                break;
            case STOLEN_HOUR:
                draw(2);
                addDiscardCard(new Dazed());
                addDiscardCard(new Dazed());
                break;
            case POCKET_TELESCOPE:
                if (!AbstractDungeon.player.drawPile.isEmpty()) {
                    AbstractCard card = AbstractDungeon.player.drawPile.group.get(0);
                    card.setCostForTurn(0);
                    trigger();
                }
                break;
            case CROWN_OF_DETOURS:
                if (AbstractDungeon.getCurrRoom() instanceof MonsterRoomElite) {
                    gainEnergy(1);
                    draw(2);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void atTurnStart() {
        switch (mode) {
            case KNUCKLEBONE:
            case EMBER_PIN:
            case MOTH_WING:
            case BROKEN_RULER:
            case CANDLE_STUB:
            case TIN_CROWN:
            case SOOT_MARK:
            case COPPER_LATCH:
            case COPPER_SCALE:
            case THORN_BUTTON:
            case DULL_NEEDLE:
            case POCKET_CHALK:
            case SEVEN_KNOT_CORD:
            case CINDER_COMPASS:
            case CROWN_OF_THORNS:
            case BLUE_HOURGLASS:
            case PAINTED_MASK:
            case TUNING_FORK:
            case BLACK_RIBBON:
            case CRACKED_BELLOWS:
            case HAUNTED_BOOKMARK:
            case VACANT_SCABBARD:
            case TALLY_STONE:
            case STITCHED_MASK:
            case RED_STRING:
            case PRISM_CAGE:
            case QUIET_STORM:
                this.counter = 0;
                break;
            case PAPER_CROWN:
                if (this.counter == 1) {
                    draw(2);
                }
                this.counter = 0;
                break;
            case WICK_CANDLE:
                if (AbstractDungeon.player.currentHealth <= AbstractDungeon.player.maxHealth / 2) {
                    gainEnergy(1);
                    loseHp(1);
                }
                break;
            case SPARE_SPRING:
                break;
            case EMPTY_CROWN:
                if (this.counter == 1) {
                    draw(3);
                }
                this.counter = 0;
                break;
            case PAPER_MOON:
                if (this.counter == 1 && this.storedCard != null) {
                    copyToHand(this.storedCard);
                }
                this.counter = 0;
                this.storedCard = null;
                break;
            case ENGINE_OF_MAYBE:
                this.counter++;
                if (this.counter >= 3) {
                    this.counter = 0;
                    switch (AbstractDungeon.cardRandomRng.random(3)) {
                        case 0:
                            gainStrength(1);
                            break;
                        case 1:
                            gainDexterity(1);
                            break;
                        case 2:
                            gainEnergy(1);
                            break;
                        default:
                            draw(2);
                            break;
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void atTurnStartPostDraw() {
        switch (mode) {
            case QUIET_BELL:
                if (AbstractDungeon.player.hand.size() < 3) {
                    draw(1);
                }
                break;
            case TORN_BOOKMARK:
                if (AbstractDungeon.player.discardPile.size() >= 5) {
                    trigger();
                    addToBot(new RandomCardFromDiscardPileToHandAction());
                }
                break;
            case SPARE_SPRING:
                if (this.counter == 1) {
                    draw(1);
                    this.counter = 0;
                }
                break;
            case QUIET_COIN:
                if (this.counter == 1) {
                    draw(1);
                    this.counter = 0;
                }
                break;
            case HAUNTED_BOOKMARK:
                if (this.counter == 0 && AbstractDungeon.player.hand.size() >= 8) {
                    this.counter = 1;
                    gainEnergy(1);
                    loseHp(1);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onPlayerEndTurn() {
        switch (mode) {
            case PATIENCE_PEBBLE:
                if (AbstractDungeon.player.energy.energy > 0) {
                    gainBlock(AbstractDungeon.player.energy.energy * 2);
                }
                break;
            case PAPER_CROWN:
                if (AbstractDungeon.player.hand.isEmpty()) {
                    this.counter = 1;
                }
                break;
            case RIBBON_LOOP:
                if (hasRetainedCard()) {
                    gainBlock(2);
                }
                break;
            case COPPER_LATCH:
                if (this.counter == 0) {
                    gainStrength(1);
                }
                break;
            case TUNING_FORK:
                if (this.counter == 0) {
                    addRetainedRandomColorlessCardToHand();
                }
                break;
            case SOOT_CAGE:
                if (AbstractDungeon.player.currentBlock > 0 && !AbstractDungeon.player.hand.isEmpty()) {
                    AbstractDungeon.player.hand.getRandomCard(AbstractDungeon.cardRandomRng).retain = true;
                    gainBlock(2);
                }
                break;
            case VACANT_SCABBARD:
                if (this.counter == 1) {
                    draw(1);
                }
                break;
            case REVERSE_BELL:
                if (this.counter >= 3) {
                    gainEnergy(1);
                    draw(2);
                }
                this.counter = 0;
                break;
            case EMPTY_CROWN:
                if (AbstractDungeon.player.energy.energy == 0) {
                    this.counter = 1;
                }
                break;
            case PAPER_MOON:
                if (AbstractDungeon.player.hand.size() == 1) {
                    this.storedCard = AbstractDungeon.player.hand.group.get(0).makeStatEquivalentCopy();
                    AbstractDungeon.player.hand.group.get(0).retain = true;
                    this.counter = 1;
                }
                break;
            case QUIET_STORM:
                if (this.counter == 0) {
                    gainEnergy(1);
                    draw(1);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (this.firstCard == null) {
            this.firstCard = card.makeStatEquivalentCopy();
        }
        switch (mode) {
            case KNUCKLEBONE:
                if (this.counter == 0 && card.costForTurn == 0) {
                    this.counter = 1;
                    gainBlock(4);
                }
                break;
            case TUNING_FORK:
                if (card.type == AbstractCard.CardType.ATTACK) {
                    this.counter = 1;
                }
                break;
            case EMBER_PIN:
                if (card.type == AbstractCard.CardType.SKILL) {
                    this.counter |= 1;
                } else if (card.type == AbstractCard.CardType.ATTACK
                        && (this.counter & 1) != 0 && (this.counter & 2) == 0) {
                    this.counter |= 2;
                    gainEnergy(1);
                }
                break;
            case BROKEN_RULER:
                if (this.counter == 0 && card.costForTurn >= 2) {
                    this.counter = 1;
                    gainBlock(card.costForTurn);
                }
                break;
            case TIN_CROWN:
                if (this.counter == 0 && card.type == AbstractCard.CardType.POWER) {
                    this.counter = 1;
                    gainBlock(4);
                }
                break;
            case POCKET_CHALK:
                if (this.counter == 0 && card.type == AbstractCard.CardType.ATTACK) {
                    this.counter = 1;
                } else if (this.counter == 1 && card.type == AbstractCard.CardType.SKILL) {
                    this.counter = 2;
                    card.modifyCostForCombat(-1);
                    trigger();
                }
                break;
            case DULL_NEEDLE:
                if (this.counter == 0 && card.costForTurn == 0) {
                    this.counter = 1;
                    gainEnergy(1);
                    addDiscardCard(new Dazed());
                }
                break;
            case SEVEN_KNOT_CORD:
                if (card.type == AbstractCard.CardType.ATTACK) {
                    this.counter |= 1;
                } else if (card.type == AbstractCard.CardType.SKILL) {
                    this.counter |= 2;
                } else if (card.type == AbstractCard.CardType.POWER) {
                    this.counter |= 4;
                }
                if (this.counter == 7) {
                    this.counter = 0;
                    gainEnergy(1);
                }
                break;
            case RESONANT_FORK:
                this.counter++;
                if (this.counter >= 4) {
                    this.counter = 0;
                    playTopCard();
                }
                break;
            case PAINTED_MASK:
                if (this.counter == 1) {
                    this.counter = 2;
                    card.setCostForTurn(0);
                    trigger();
                }
                break;
            case FADED_DICE:
                if (this.counter == 0 && card.type == AbstractCard.CardType.POWER) {
                    this.counter = 1;
                    randomizeHand();
                }
                break;
            case VACANT_SCABBARD:
                if (this.counter == 0) {
                    this.counter = card.type == AbstractCard.CardType.ATTACK ? 1 : 2;
                }
                break;
            case TALLY_STONE:
                this.counter++;
                if (this.counter >= 3) {
                    this.counter = 0;
                    gainBlock(5);
                }
                break;
            case STITCHED_MASK:
                if (this.counter == 1) {
                    if (card.type == AbstractCard.CardType.ATTACK) {
                        this.counter = 2;
                    } else if (card.type == AbstractCard.CardType.SKILL) {
                        this.counter = 0;
                        card.modifyCostForCombat(-1);
                        trigger();
                    } else if (card.type == AbstractCard.CardType.POWER) {
                        this.counter = 0;
                        draw(1);
                    }
                }
                break;
            case RED_STRING:
                if (this.counter == 0 && card.type == AbstractCard.CardType.ATTACK) {
                    this.counter = 1;
                } else if (this.counter == 1 && card.type == AbstractCard.CardType.SKILL) {
                    this.counter = 2;
                } else if (this.counter == 2 && card.type == AbstractCard.CardType.POWER) {
                    this.counter = 0;
                    gainEnergy(2);
                    draw(2);
                } else if (card.type != AbstractCard.CardType.POWER) {
                    this.counter = 0;
                }
                break;
            case PRISM_CAGE:
                if (this.counter == 0 && card.type == AbstractCard.CardType.POWER) {
                    this.counter = 1;
                    gainEnergy(1);
                    randomizeHand();
                }
                break;
            case CHOIR_OF_NAILS:
                this.counter++;
                if (this.counter >= 6) {
                    this.counter = 0;
                    damageAll(4);
                    applyVulnerableToAll(1);
                }
                break;
            case STORM_CHIME:
                this.counter++;
                if (this.counter >= 5) {
                    this.counter = 0;
                    damageAll(12);
                    draw(1);
                }
                break;
            case FRACTURED_CROWN:
                break;
            case QUIET_STORM:
                this.counter = 1;
                break;
            default:
                break;
        }
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        switch (mode) {
            case MOTH_WING:
                if (this.counter == 0
                        && (card.type == AbstractCard.CardType.STATUS
                        || card.type == AbstractCard.CardType.CURSE)) {
                    this.counter = 1;
                    card.retain = true;
                    gainBlock(3);
                }
                break;
            case CARDBOARD_MASK:
                if (this.counter == 0
                        && (card.type == AbstractCard.CardType.STATUS
                        || card.type == AbstractCard.CardType.CURSE)) {
                    this.counter = 1;
                    gainPlayerPower(new ArtifactPower(AbstractDungeon.player, 1));
                }
                break;
            case FATES_THREAD:
                if (this.counter == 0 && card.type == AbstractCard.CardType.CURSE) {
                    this.counter = 1;
                    gainEnergy(1);
                    gainBlock(2);
                }
                break;
            case PERMANENT_MARKER:
                if (this.counter == 0) {
                    this.counter = 1;
                    card.retain = true;
                    card.modifyCostForCombat(-1);
                    trigger();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onManualDiscard() {
        switch (mode) {
            case TANGLE_HOOK:
                if (this.counter == 0) {
                    this.counter = 1;
                    moveRandomDiscardToTop();
                }
                break;
            case SOOT_MARK:
                if (this.counter == 0) {
                    this.counter = 1;
                    gainStrength(1);
                }
                break;
            case BLACK_RIBBON:
                this.counter++;
                if (this.counter >= 2) {
                    this.counter = 0;
                    damageAll(7);
                }
                break;
            case REVERSE_BELL:
                this.counter++;
                break;
            case STITCHED_MASK:
                if (this.counter == 0) {
                    this.counter = 1;
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onExhaust(AbstractCard card) {
        switch (mode) {
            case INKBLOT:
                if (this.counter == 0 && card.type == AbstractCard.CardType.SKILL) {
                    this.counter = 1;
                    draw(1);
                }
                break;
            case ASHEN_RIBBON:
                if (this.counter == 0) {
                    this.counter = 1;
                    gainEnergy(1);
                }
                break;
            case CINDER_COMPASS:
                if (this.counter == 0) {
                    this.counter = 1;
                    applyWeakToAll(1);
                }
                break;
            case BORROWED_QUILL:
                if (this.counter == 0) {
                    this.counter = 1;
                    copyToDrawTop(card);
                }
                break;
            case ATLAS_OF_ERRORS:
                AbstractCard copy = randomDiscard();
                copyToDrawTop(copy == null ? card : copy);
                break;
            default:
                break;
        }
    }

    @Override
    public void onShuffle() {
        switch (mode) {
            case LOOSE_GEAR:
                if (this.counter == 0) {
                    this.counter = 1;
                    addRandomColorlessToDiscard(0, true);
                }
                break;
            case THREAD_SPOOL:
                if (this.counter == 0) {
                    this.counter = 1;
                    gainDexterity(2);
                }
                break;
            case FALSE_BOTTOM:
                if (this.counter == 0) {
                    this.counter = 1;
                    gainEnergy(1);
                    addRandomColorlessToDraw(0, true, true);
                }
                break;
            case BLUE_HOURGLASS:
                if (this.counter == 0) {
                    this.counter = 1;
                    gainEnergy(1);
                    addDiscardCard(new Wound());
                }
                break;
            case THREADED_COMPASS:
                moveRandomDiscardToTop();
                break;
            case COAL_LANTERN:
                applyWeakToAll(1);
                break;
            case OUROBOROS_LOOP:
                if (this.counter == 0 && this.firstCard != null) {
                    this.counter = 1;
                    copyToHand(this.firstCard);
                }
                break;
            case LAST_MATCH:
                if (this.counter == 0) {
                    this.counter = 1;
                    gainEnergy(2);
                    draw(3);
                }
                break;
            case TIDE_CLOCK:
                copyToHand(randomDiscard());
                break;
            case LIBRARY_OF_ASH:
                if (this.counter == 0) {
                    this.counter = 1;
                    trigger();
                    addToBot(new UpgradeRandomCardAction());
                    draw(1);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public int onPlayerGainedBlock(float blockAmount) {
        int block = MathUtils.floor(blockAmount);
        if (block > 0) {
            switch (mode) {
                case COPPER_LATCH:
                    this.counter = 1;
                    break;
                case CRACKED_BELLOWS:
                    if (this.counter == 0 && block >= 8) {
                        this.counter = 1;
                        gainStrength(1);
                    }
                    break;
                default:
                    break;
            }
        }
        return block;
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (damageAmount <= 0) {
            return damageAmount;
        }
        if (mode == Mode.THORN_BUTTON && this.counter == 0) {
            this.counter = 1;
            damageAll(2);
        }
        return damageAmount;
    }

    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        if (damageAmount <= 0) {
            return damageAmount;
        }
        switch (mode) {
            case COPPER_SCALE:
                if (this.counter == 0) {
                    this.counter = 1;
                    gainBlock(6);
                }
                break;
            case SALT_CROWN:
                if (this.counter == 0) {
                    this.counter = 1;
                    gainPlayerPower(new ArtifactPower(AbstractDungeon.player, 1));
                    gainPlayerPower(new VulnerablePower(AbstractDungeon.player, 1, false));
                }
                break;
            case THORNY_DICE:
                if (this.counter == 0) {
                    this.counter = 1;
                    damageAll(3);
                    return 0;
                }
                break;
            default:
                break;
        }
        return damageAmount;
    }

    @Override
    public int onAttackToChangeDamage(DamageInfo info, int damageAmount) {
        switch (mode) {
            case MIRROR_DICE:
                if (this.counter == 0) {
                    this.counter = 1;
                    int change = AbstractDungeon.cardRandomRng.randomBoolean() ? 8 : -4;
                    trigger();
                    return Math.max(0, damageAmount + change);
                }
                break;
            case RED_LEDGER:
                if (this.counter == 1) {
                    this.counter = 0;
                    trigger();
                    return damageAmount + 6;
                }
                break;
            case FALSE_CROWN:
                if (this.counter == 0) {
                    this.counter = 1;
                    trigger();
                    return damageAmount * 2;
                }
                break;
            case STITCHED_MASK:
                if (this.counter == 2) {
                    this.counter = 0;
                    trigger();
                    return damageAmount + 3;
                }
                break;
            default:
                break;
        }
        return damageAmount;
    }

    @Override
    public void onBlockBroken(AbstractCreature creature) {
        switch (mode) {
            case MATCHBOOK:
                if (this.counter == 0) {
                    this.counter = 1;
                    damageAll(5);
                }
                break;
            case IRON_ACORN:
                if (this.counter == 0) {
                    this.counter = 1;
                    gainPlayerPower(new ArtifactPower(AbstractDungeon.player, 1));
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onLoseHp(int amount) {
        if (amount <= 0) {
            return;
        }
        switch (mode) {
            case CANDLE_STUB:
                if (this.counter == 0) {
                    this.counter = 1;
                    gainBlock(3);
                }
                break;
            case CROWN_OF_THORNS:
                if (this.counter == 0) {
                    this.counter = 1;
                    gainStrength(1);
                }
                break;
            case SPARE_SPRING:
                if (this.counter == 0) {
                    this.counter = 1;
                }
                break;
            case GLASS_GUILLOTINE:
                if (this.counter == 0
                        && AbstractDungeon.player.currentHealth <= AbstractDungeon.player.maxHealth / 2) {
                    this.counter = 1;
                    gainPlayerPower(new IntangiblePlayerPower(AbstractDungeon.player, 1));
                    addDiscardCard(new Wound());
                    addDiscardCard(new Wound());
                }
                break;
            case HOLLOW_CONTRACT:
                if (this.counter == 0) {
                    this.counter = 1;
                    gainEnergy(2);
                    addDiscardCard(new Regret());
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onUsePotion() {
        switch (mode) {
            case MASON_CHIP:
                if (this.counter == 0) {
                    this.counter = 1;
                    int block = Math.min(8, AbstractDungeon.player.exhaustPile.size() * 2);
                    if (block > 0) {
                        gainBlock(block);
                    }
                }
                break;
            case PAINTED_MASK:
                if (this.counter == 0) {
                    this.counter = 1;
                    trigger();
                }
                break;
            case ALCHEMIST_CORK:
                if (this.counter == 0) {
                    this.counter = 1;
                    addRandomColorlessToDraw(0, true, true);
                }
                break;
            case FATES_RECEIPT:
                this.counter = 1;
                break;
            default:
                break;
        }
    }

    @Override
    public void onGainGold() {
        if (mode == Mode.RED_LEDGER) {
            this.counter = 1;
        }
    }

    @Override
    public void onSpendGold() {
        switch (mode) {
            case SPLIT_COIN:
                if (AbstractDungeon.cardRandomRng.randomBoolean()) {
                    gainEnergy(1);
                } else {
                    gainBlock(4);
                }
                break;
            case QUIET_COIN:
                this.counter = 1;
                break;
            default:
                break;
        }
    }

    @Override
    public void onEnterRoom(com.megacrit.cardcrawl.rooms.AbstractRoom room) {
        if (mode == Mode.STAINED_MAP && room instanceof EventRoom) {
            gainGold(8);
        }
    }

    @Override
    public void onChestOpen(boolean bossChest) {
        if (mode == Mode.GREASED_KEY && !bossChest && AbstractDungeon.player.gold >= 5) {
            AbstractDungeon.player.loseGold(5);
            obtainRandomPotion();
        }
    }

    @Override
    public void onVictory() {
        switch (mode) {
            case DAMPENED_BELL:
                heal(3);
                break;
            case FATES_RECEIPT:
                if (this.counter == 0) {
                    gainGold(25);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onMonsterDeath(AbstractMonster monster) {
        switch (mode) {
            case POCKET_WHISTLE:
                this.counter++;
                if (this.counter == 2) {
                    draw(2);
                }
                break;
            case CLOCKWORK_NEST:
                this.counter++;
                if (this.counter >= 2) {
                    this.counter = 0;
                    gainEnergy(1);
                    draw(1);
                }
                break;
            case BURIED_KEY:
                if (this.counter == 0 && AbstractDungeon.getCurrRoom() instanceof MonsterRoomElite) {
                    this.counter = 1;
                    gainEnergy(1);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onChangeStance(AbstractStance previousStance, AbstractStance newStance) {
        if (mode == Mode.FRACTURED_CROWN && this.counter == 0) {
            this.counter = 1;
            gainBlock(6);
            draw(2);
        }
    }

    @Override
    public void onEvokeOrb(AbstractOrb orb) {
        if (mode == Mode.HOLLOW_MARBLE && this.counter == 0) {
            this.counter = 1;
            draw(1);
        }
    }

    @Override
    public void onObtainCard(AbstractCard card) {
        if (mode == Mode.UNDERSTUDY_SEAL && card.rarity == AbstractCard.CardRarity.RARE
                && !card.upgraded) {
            card.upgrade();
            flash();
        }
    }

    @Override
    public int changeNumberOfCardsInReward(int numberOfCards) {
        if (mode == Mode.LUCKY_SPLINTER) {
            return numberOfCards + 1;
        }
        return numberOfCards;
    }

    @Override
    public int changeRareCardRewardChance(int chance) {
        if (mode == Mode.SLOTTED_STONE) {
            return chance + 3;
        }
        return chance;
    }

    @Override
    public int onPlayerHeal(int amount) {
        if (amount <= 0) {
            return amount;
        }
        if (mode == Mode.DRIED_APPLE && this.counter == 0) {
            this.counter = 1;
            gainDexterity(1);
        } else if (mode == Mode.CRIMSON_NEEDLE && this.counter == 0) {
            this.counter = 1;
            gainStrength(1);
            gainBlock(Math.max(1, amount / 2));
        }
        return amount;
    }
}
