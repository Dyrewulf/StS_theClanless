package theClanless.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;

import java.util.ArrayList;

public abstract class AbstractClanlessCard extends CustomCard {

    // Custom Abstract Cards can be a bit confusing. While this is a simple base for simply adding a second magic number,
    // if you're new to modding I suggest you skip this file until you know what unique things that aren't provided
    // by default, that you need in your own cards.

    // In this example, we use a custom Abstract Card in order to define a new magic number. From here on out, we can
    // simply use that in our cards, so long as we put "extends AbstractDynamicCard" instead of "extends CustomCard" at the start.
    // In simple terms, it's for things that we don't want to define again and again in every single card we make.

    public int clanlessSecondMagicNumber;        // Just like magic number, or any number for that matter, we want our regular, modifiable stat
    public int baseClanlessSecondMagicNumber;    // And our base stat - the number in it's base state. It will reset to that by default.
    public boolean upgradedClanlessSecondMagicNumber; // A boolean to check whether the number has been upgraded or not.
    public boolean isClanlessSecondMagicNumberModified; // A boolean to check whether the number has been modified or not, for coloring purposes. (red/green)

    public final ArrayList<clanlessCardTags> clanlessTags = new ArrayList<>();

    public AbstractClanlessCard(final String id,
                                final String name,
                                final String img,
                                final int cost,
                                final String rawDescription,
                                final CardType type,
                                final CardColor color,
                                final CardRarity rarity,
                                final CardTarget target) {

        super(id, name, img, cost, rawDescription, type, color, rarity, target);
    }

    public void displayUpgrades() { // Display the upgrade - when you click a card to upgrade it
        super.displayUpgrades();
        if (upgradedClanlessSecondMagicNumber) { // If we set upgradedDefaultSecondMagicNumber = true in our card.
            clanlessSecondMagicNumber = baseClanlessSecondMagicNumber; // Show how the number changes, as out of combat, the base number of a card is shown.
            isClanlessSecondMagicNumberModified = true; // Modified = true, color it green to highlight that the number is being changed.
        }
    }

    public void upgradeClanlessSecondMagicNumber(int amount) { // If we're upgrading (read: changing) the number. Note "upgrade" and NOT "upgraded" - 2 different things. One is a boolean, and then this one is what you will usually use - change the integer by how much you want to upgrade.
        baseClanlessSecondMagicNumber += amount; // Upgrade the number by the amount you provide in your card.
        clanlessSecondMagicNumber = baseClanlessSecondMagicNumber; // Set the number to be equal to the base value.
        upgradedClanlessSecondMagicNumber = true; // Upgraded = true - which does what the above method does.
    }

    public boolean hasClanlessTag(clanlessCardTags tagToCheck) {
        return this.clanlessTags.contains(tagToCheck);
    }

    public boolean isRangedWeapon() {
        return (this.hasClanlessTag(clanlessCardTags.RANGED) && this.hasClanlessTag(clanlessCardTags.WEAPON));
    }

    public boolean isMeleeWeapon() {
        return (this.hasClanlessTag(clanlessCardTags.MELEE) && this.hasClanlessTag(clanlessCardTags.WEAPON));
    }

    protected void upgradeDescription() {
        CardStrings cardText = CardCrawlGame.languagePack.getCardStrings(this.cardID);
        if (cardText.UPGRADE_DESCRIPTION != null) {
            this.rawDescription = cardText.UPGRADE_DESCRIPTION;
        }
    }

    public enum clanlessCardTags {
        MELEE,
        RANGED,
        WEAPON,
        REACTION,
        ADDITIONAL,
        GRAPPLE;

        clanlessCardTags() {

        }
    }
}