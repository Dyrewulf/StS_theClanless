package theClanless.cards.core;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theClanless.actions.ScroungeAction;
import theClanless.cards.AbstractDynamicCard;
import theClanless.characters.TheClanless;
import theClanless.theClanlessMod;

import static theClanless.theClanlessMod.makeCardPath;

public class Scrounging extends AbstractDynamicCard {
    // TEXT DECLARATION
    public static final String ID = theClanlessMod.makeID(Scrounging.class.getSimpleName());
    public static final String IMG = makeCardPath("Scrounging.png");
    // /TEXT DECLARATION/

    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheClanless.Enums.COLOR_CLANLESSRED;

    private static final int COST = 1;
    private static final int LOOK = 3;
    private static final int LOOK_UPGRADE = 2;
    private static final int KEEP = 2;
    private static final int KEEP_UPGRADE = 1;
    // /STAT DECLARATION/

    public Scrounging() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = LOOK;
        clanlessSecondMagicNumber = baseClanlessSecondMagicNumber = KEEP;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ScroungeAction(p, this.magicNumber, this.clanlessSecondMagicNumber));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(LOOK_UPGRADE);
            upgradeClanlessSecondMagicNumber(KEEP_UPGRADE);
            initializeDescription();
        }
    }
}
