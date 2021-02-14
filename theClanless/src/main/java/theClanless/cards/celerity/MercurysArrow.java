package theClanless.cards.celerity;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theClanless.actions.RangedAttackAction;
import theClanless.cards.AbstractDynamicCard;
import theClanless.characters.TheClanless;
import theClanless.theClanlessMod;

import static theClanless.theClanlessMod.makeCardPath;

public class MercurysArrow extends AbstractDynamicCard {

    public static final String ID = theClanlessMod.makeID(MercurysArrow.class.getSimpleName());
    public static final String IMG = makeCardPath("MercurysArrow.png");


    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheClanless.Enums.CELERITY;

    private static final int COST = 1;

    private static final int DAMAGE = 3;

    private static final int MAGICNUMBER = 2;
    private static final int MAGICNUMBER_PLUS = 2;

    private static final int SECONDMAGICNUMBER = 2;
    private static final int SECONDMAGICNUMBER_PLUS = 1;

    // /STAT DECLARATION/


    public MercurysArrow() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = DAMAGE;
        this.magicNumber = this.baseMagicNumber = MAGICNUMBER;
        this.clanlessSecondMagicNumber = this.baseClanlessSecondMagicNumber = SECONDMAGICNUMBER;

        this.clanlessTags.add(clanlessCardTags.RANGED);
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new RangedAttackAction(p, m, damage, this.damageTypeForTurn, magicNumber, clanlessSecondMagicNumber)
        );
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(MAGICNUMBER_PLUS);
            upgradeClanlessSecondMagicNumber(SECONDMAGICNUMBER_PLUS);
            initializeDescription();
        }
    }
}
