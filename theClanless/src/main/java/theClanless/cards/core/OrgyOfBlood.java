package theClanless.cards.core;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theClanless.actions.OrgyOfBloodAction;
import theClanless.cards.AbstractDynamicCard;
import theClanless.characters.TheClanless;
import theClanless.theClanlessMod;

import static theClanless.theClanlessMod.makeCardPath;

public class OrgyOfBlood extends AbstractDynamicCard {
    // TEXT DECLARATION
    public static final String ID = theClanlessMod.makeID(OrgyOfBlood.class.getSimpleName());
    public static final String IMG = makeCardPath("OrgyOfBlood.png");
    // /TEXT DECLARATION/

    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheClanless.Enums.COLOR_CLANLESSRED;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;

    private static final int HEAL = 3;
    private static final int HEAL_UPGRADE = 1;
    // /STAT DECLARATION/

    public OrgyOfBlood() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = HEAL;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new OrgyOfBloodAction(this.magicNumber));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
            upgradeMagicNumber(HEAL_UPGRADE);
            initializeDescription();
        }
    }
}
