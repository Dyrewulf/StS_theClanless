package theClanless.characters;

import basemod.abstracts.CustomPlayer;
import basemod.animations.SpriterAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import theClanless.relics.CelerityRelic;
import theClanless.relics.FortitudeRelic;
import theClanless.relics.PotenceRelic;
import theClanless.theClanlessMod;
import theClanless.cards.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import static theClanless.characters.TheClanless.Enums.*;
import static theClanless.theClanlessMod.*;

//Wiki-page https://github.com/daviscook477/BaseMod/wiki/Custom-Characters
//and https://github.com/daviscook477/BaseMod/wiki/Migrating-to-5.0
//All text (starting description and loadout, anything labeled TEXT[]) can be found in DefaultMod-character-Strings.json in the resources

public class TheClanless extends CustomPlayer {

    public static final Logger logger = LogManager.getLogger(theClanlessMod.class.getName());

    // =============== CHARACTER ENUMERATORS =================
    // These are enums for your Characters color (both general color and for the card library) as well as
    // an enum for the name of the player class - IRONCLAD, THE_SILENT, DEFECT, YOUR_CLASS ...
    // These are all necessary for creating a character. If you want to find out where and how exactly they are used
    // in the basegame (for fun and education) Ctrl+click on the PlayerClass, CardColor and/or LibraryType below and go down the
    // Ctrl+click rabbit hole

    public static class Enums {
        @SpireEnum
        public static AbstractPlayer.PlayerClass THE_CLANLESS;
        @SpireEnum(name = "CLANLESS_RED") // These two HAVE to have the same absolutely identical name.
        public static AbstractCard.CardColor COLOR_CLANLESSRED;
        @SpireEnum(name = "CLANLESS_RED") @SuppressWarnings("unused")
        public static CardLibrary.LibraryType CLANLESS_RED_LIB;

        @SpireEnum(name = "CELERITY")
        public static AbstractCard.CardColor CELERITY;
        @SpireEnum(name = "CELERITY") @SuppressWarnings("unused")
        public static CardLibrary.LibraryType CELERITY_LIB;
        @SpireEnum(name = "FORTITUDE")
        public static AbstractCard.CardColor FORTITUDE;
        @SpireEnum(name = "FORTITUDE") @SuppressWarnings("unused")
        public static CardLibrary.LibraryType FORTITUDE_LIB;
        @SpireEnum(name = "POTENCE")
        public static AbstractCard.CardColor POTENCE;
        @SpireEnum(name = "POTENCE") @SuppressWarnings("unused")
        public static CardLibrary.LibraryType POTENCE_LIB;

    }

    // =============== CHARACTER ENUMERATORS  =================


    // =============== BASE STATS =================

    public static final int ENERGY_PER_TURN = 3;
    public static final int STARTING_HP = 75;
    public static final int MAX_HP = 75;
    public static final int STARTING_GOLD = 99;
    public static final int CARD_DRAW = 5;
    public static final int ORB_SLOTS = 0;
    public static ArrayList<AbstractCard.CardColor> DisciplinePool;

    // =============== /BASE STATS/ =================


    // =============== STRINGS =================

    private static final String ID = makeID("ClanlessCharacter");
    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(ID);
    private static final String[] NAMES = characterStrings.NAMES;
    private static final String[] TEXT = characterStrings.TEXT;

    // =============== /STRINGS/ =================


    // =============== TEXTURES OF BIG ENERGY ORB ===============

    public static final String[] orbTextures = {
            "theClanlessResources/images/char/defaultCharacter/orb/layer1.png",
            "theClanlessResources/images/char/defaultCharacter/orb/layer2.png",
            "theClanlessResources/images/char/defaultCharacter/orb/layer3.png",
            "theClanlessResources/images/char/defaultCharacter/orb/layer4.png",
            "theClanlessResources/images/char/defaultCharacter/orb/layer5.png",
            "theClanlessResources/images/char/defaultCharacter/orb/layer6.png",
            "theClanlessResources/images/char/defaultCharacter/orb/layer1d.png",
            "theClanlessResources/images/char/defaultCharacter/orb/layer2d.png",
            "theClanlessResources/images/char/defaultCharacter/orb/layer3d.png",
            "theClanlessResources/images/char/defaultCharacter/orb/layer4d.png",
            "theClanlessResources/images/char/defaultCharacter/orb/layer5d.png",};

    // =============== /TEXTURES OF BIG ENERGY ORB/ ===============

    // =============== CHARACTER CLASS START =================

    public TheClanless(String name, PlayerClass setClass) {
        super(name, setClass, orbTextures,
                "theClanlessResources/images/char/defaultCharacter/orb/vfx.png", null,
                new SpriterAnimation(
                        "theClanlessResources/images/char/clanlessCharacter/Spriter/theClanlessAnimation.scml"));


        // =============== TEXTURES, ENERGY, LOADOUT =================  

        initializeClass(null, // required call to load textures and setup energy/loadout.
                // I left these in DefaultMod.java (Ctrl+click them to see where they are, Ctrl+hover to see what they read.)
                THE_CLANLESS_SHOULDER_2, // campfire pose
                THE_CLANLESS_SHOULDER_1, // another campfire pose
                THE_CLANLESS_CORPSE, // dead corpse
                getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN)); // energy manager

        // =============== /TEXTURES, ENERGY, LOADOUT/ =================


        // =============== ANIMATIONS =================  

        loadAnimation(
                THE_DEFAULT_SKELETON_ATLAS,
                THE_DEFAULT_SKELETON_JSON,
                1.0f);
        AnimationState.TrackEntry e = state.setAnimation(0, "animation", true);
        e.setTime(e.getEndTime() * MathUtils.random());

        // =============== /ANIMATIONS/ =================


        // =============== TEXT BUBBLE LOCATION =================

        dialogX = (drawX + 0.0F * Settings.scale); // set location for text bubbles
        dialogY = (drawY + 220.0F * Settings.scale); // you can just copy these values

        // =============== /TEXT BUBBLE LOCATION/ =================

    }

    // =============== /CHARACTER CLASS END/ =================

    // Starting description and loadout
    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], TEXT[0],
                STARTING_HP, MAX_HP, ORB_SLOTS, STARTING_GOLD, CARD_DRAW, this, getStartingRelics(),
                getStartingDeck(), false);
    }

    // Starting Deck
    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();

        logger.info("Begin loading starter Deck Strings");

        retVal.add(LuckyBlow.ID);
        retVal.add(LuckyBlow.ID);
        retVal.add(LuckyBlow.ID);
        retVal.add(LuckyBlow.ID);

        retVal.add(Dodge.ID);
        retVal.add(Dodge.ID);
        retVal.add(Dodge.ID);
        retVal.add(Dodge.ID);

        retVal.add(TasteOfVitae.ID);
        retVal.add(ComputerHacking.ID);

        return retVal;
    }

    // Starting Relics	
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();

        retVal.add(CelerityRelic.ID);
        retVal.add(FortitudeRelic.ID);
        retVal.add(PotenceRelic.ID);

        // Mark relics as seen - makes it visible in the compendium immediately
        // If you don't have this it won't be visible in the compendium until you see them in game
        UnlockTracker.markRelicAsSeen(CelerityRelic.ID);
        UnlockTracker.markRelicAsSeen(FortitudeRelic.ID);
        UnlockTracker.markRelicAsSeen(PotenceRelic.ID);

        return retVal;
    }

    //Discipline Management
    public static boolean hasDiscipline(String inDisc) {
        Iterator var1 = AbstractDungeon.player.getRelicNames().iterator();
        while(var1.hasNext()) {
            String tempColor = (String) var1.next();
            if (tempColor == inDisc) {
                return true;
            }
        }
        return false;
    }

    private boolean disciplineColorCheck(ArrayList<AbstractCard.CardColor> inList, AbstractCard.CardColor inColor) {
        Iterator var2 = inList.iterator();
        while(var2.hasNext()) {
            AbstractCard.CardColor color = (AbstractCard.CardColor) var2.next();
            if (color == inColor) {
                return true;
            }
        }
        return false;
    }

    private ArrayList<AbstractCard.CardColor> getDisciplineColors() {
        ArrayList<AbstractCard.CardColor> disciplineColors = new ArrayList<>();
        disciplineColors.add(COLOR_CLANLESSRED);
        String name;
        Iterator var1 = AbstractDungeon.player.getRelicNames().iterator();
        while(var1.hasNext()) {
            name = (String) var1.next();
            logger.info("Iterating Relics: relic name is: " + name);
            switch (name) {
                case "theClanless:CelerityRelic":
                    disciplineColors.add(CELERITY);
                case "theClanless:PotenceRelic":
                    disciplineColors.add(POTENCE);
                case "theClanless:FortitudeRelic":
                    disciplineColors.add(FORTITUDE);
            }
        }
        return disciplineColors;
    }

    // character Select screen effect
    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("ATTACK_DAGGER_1", 1.25f); // Sound Effect
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT,
                false); // Screen Effect
    }

    @Override
    public ArrayList<AbstractCard> getCardPool(ArrayList<AbstractCard> tmpPool) {
        super.getCardPool(tmpPool);

        //getDisciplineColors includes COLOR_CLANLESS_RED
        //AbstractCard.CardColor color = this.getCardColor();
        ArrayList<AbstractCard.CardColor> disciplineColors = getDisciplineColors();

        Iterator<Map.Entry<String, AbstractCard>> var3 = CardLibrary.cards.entrySet().iterator();

        while(var3.hasNext()) {
            Map.Entry<String, AbstractCard> c = var3.next();
            AbstractCard card = c.getValue();

            if (disciplineColorCheck(disciplineColors, card.color) &&
                    !card.rarity.equals(AbstractCard.CardRarity.BASIC) &&
                    !card.rarity.equals(AbstractCard.CardRarity.SPECIAL) &&
                    (!UnlockTracker.isCardLocked(c.getKey()) || Settings.isDailyRun)
            ) {
                tmpPool.add(card);
                logger.info("Adding: " + card.cardID + "to CardPool");
            }
        }

        return tmpPool;

    }



    // character Select on-button-press sound effect
    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_DAGGER_1";
    }

    // Should return how much HP your maximum HP reduces by when starting a run at
    // Ascension 14 or higher. (ironclad loses 5, defect and silent lose 4 hp respectively)
    @Override
    public int getAscensionMaxHPLoss() {
        return 5;
    }

    // Should return the card color enum to be associated with your character.
    @Override
    public AbstractCard.CardColor getCardColor() {
        return COLOR_CLANLESSRED;
    }

    // Should return a color object to be used to color the trail of moving cards
    @Override
    public Color getCardTrailColor() {
        return theClanlessMod.CLANLESS_RED;
    }

    // Should return a BitmapFont object that you can use to customize how your
    // energy is displayed from within the energy orb.
    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    // Should return class name as it appears in run history screen.
    @Override
    public String getLocalizedCharacterName() {
        return NAMES[0];
    }

    //Which card should be obtainable from the Match and Keep event?
    @Override
    public AbstractCard getStartCardForEvent() {
        return new DefaultCommonAttack();
    }

    // The class name as it appears next to your player name in-game
    @Override
    public String getTitle(AbstractPlayer.PlayerClass playerClass) {
        return NAMES[1];
    }

    // Should return a new instance of your character, sending name as its name parameter.
    @Override
    public AbstractPlayer newInstance() {
        return new TheClanless(name, chosenClass);
    }

    // Should return a Color object to be used to color the miniature card images in run history.
    @Override
    public Color getCardRenderColor() {
        return theClanlessMod.CLANLESS_RED;
    }

    // Should return a Color object to be used as screen tint effect when your
    // character attacks the heart.
    @Override
    public Color getSlashAttackColor() {
        return theClanlessMod.CLANLESS_RED;
    }

    // Should return an AttackEffect array of any size greater than 0. These effects
    // will be played in sequence as your character's finishing combo on the heart.
    // Attack effects are the same as used in DamageAction and the like.
    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY};
    }

    // Should return a string containing what text is shown when your character is
    // about to attack the heart. For example, the defect is "NL You charge your
    // core to its maximum..."
    @Override
    public String getSpireHeartText() {
        return TEXT[1];
    }

    // The vampire events refer to the base game characters as "brother", "sister",
    // and "broken one" respectively.This method should return a String containing
    // the full text that will be displayed as the first screen of the vampires event.
    @Override
    public String getVampireText() {
        return TEXT[2];
    }

}
