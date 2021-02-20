package theClanless.cards.core;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theClanless.actions.RangedAttackAction;
import theClanless.cards.AbstractDynamicCard;
import theClanless.powers.ManeuverPower;
import theClanless.theClanlessMod;

import static theClanless.theClanlessMod.makeCardPath;


public class Handgun extends AbstractDynamicCard {

    public static final String ID = theClanlessMod.makeID(Handgun.class.getSimpleName());
    public static final String IMG = makeCardPath("SaturdayNightSpecial.png");
    public static final String[] UPGRADE_IMAGES = new String[]{
            makeCardPath("SaturdayNightSpecial.png"),
            makeCardPath("Beretta9mm.png"),
            makeCardPath("44Magnum.png"),
            makeCardPath("DesertEagle.png")};


    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String[] GUN_NAMES = cardStrings.EXTENDED_DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = CardColor.COLORLESS;

    private static final int COST = 1;

    private static final int DAMAGE = 6;
    private static final int DAMAGE_PLUS = 3;

    private static final int MAGICNUMBER = 2;
    private static final int MAGICNUMBER_PLUS = 2;

    // /STAT DECLARATION/

    public Handgun() {
        this(0);
    }

    public Handgun(int timesUpgraded) {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = DAMAGE;
        this.magicNumber = this.baseMagicNumber = MAGICNUMBER;
        this.timesUpgraded = timesUpgraded;

        this.clanlessTags.add(clanlessCardTags.RANGED);
        this.clanlessTags.add(clanlessCardTags.WEAPON);
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new RangedAttackAction(p, m, damage, this.damageTypeForTurn, magicNumber, 1));
        addToBot(new ApplyPowerAction(p, p, new ManeuverPower(p, p, 1), 1));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (this.timesUpgraded < 3) {
            upgradeName();
            upgradeDamage(DAMAGE_PLUS);
            upgradeMagicNumber(MAGICNUMBER_PLUS);
            upgradeImage(timesUpgraded);
            initializeDescription();
        }
    }

    private void upgradeImage(int timesUpgraded) {
        this.loadCardImage(UPGRADE_IMAGES[timesUpgraded]);

    }

    @Override
    protected void upgradeName() {
        ++this.timesUpgraded;
        if (this.timesUpgraded >= 3) this.upgraded = true;
        this.name = GUN_NAMES[this.timesUpgraded];
        this.initializeTitle();
    }

    @Override
    public AbstractCard makeCopy() {
        return new Handgun(this.timesUpgraded);
    }

    @Override
    public boolean canUpgrade() {
        return (this.timesUpgraded < 3);
    }
}
