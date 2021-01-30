package theClanless.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theClanless.characters.TheClanless;
import theClanless.powers.ForcedVigilancePower;
import theClanless.powers.InfernalPursuitPower;
import theClanless.theClanlessMod;

import static theClanless.theClanlessMod.makeCardPath;

public class ForcedVigilance extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Hold Place Gain 1(2) Keywords(s).
     */


    // TEXT DECLARATION

    public static final String ID = theClanlessMod.makeID("ForcedVigilance");
    public static final String IMG = makeCardPath("ForcedVigilance.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION


    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheClanless.Enums.FORTITUDE;

    private static final int COST = 2;
    private static final int MAGIC = 1;
    private static final int UPGRADE_MAGIC = 1;

    // /STAT DECLARATION/


    public ForcedVigilance() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = MAGIC;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new ForcedVigilancePower(p, p, magicNumber), magicNumber)
        );
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_MAGIC);
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}