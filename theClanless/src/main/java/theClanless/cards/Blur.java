package theClanless.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theClanless.characters.TheClanless;
import theClanless.theClanlessMod;

import static theClanless.theClanlessMod.makeCardPath;

public class Blur extends AbstractDynamicCard {

    // public static final String ID = DefaultMod.makeID(${NAME}.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    public static final String ID = theClanlessMod.makeID("Blur"); // DELETE THIS ONE.
    public static final String IMG = makeCardPath("Blur.png");// "public static final String IMG = makeCardPath("${NAME}.png");
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.


    // /TEXT DECLARATION/


    // STAT DECLARATION
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.UNCOMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = TheClanless.Enums.CELERITY;

    private static final int COST = 1;  // COST = ${COST}
    private static final int UPGRADED_COST = 1; // UPGRADED_COST = ${UPGRADED_COST}

    private static final int DAMAGE = 5;    // DAMAGE = ${DAMAGE}
    private static final int MAGICNUMBER = 2;
    private static final int MAGICNUMBER_PLUS = 1;


    // /STAT DECLARATION/


    public Blur() { // public ${NAME}() - This one and the one right under the imports are the most important ones, don't forget them
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = DAMAGE;
        this.magicNumber = this.baseMagicNumber = MAGICNUMBER;

        this.tags.add(CardTags.STRIKE);
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = this.magicNumber; i > 0; i--) {
            AbstractDungeon.actionManager.addToBottom(
                    new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
            upgradeMagicNumber(MAGICNUMBER_PLUS);
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}