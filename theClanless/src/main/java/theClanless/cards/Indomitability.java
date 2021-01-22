package theClanless.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theClanless.characters.TheClanless;
import theClanless.powers.PressPower;
import theClanless.theClanlessMod;

import static theClanless.theClanlessMod.makeCardPath;

public class Indomitability extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Defend Gain 5 (8) block.
     */


    // TEXT DECLARATION

    public static final String ID = theClanlessMod.makeID(Indomitability.class.getSimpleName());
    public static final String IMG = makeCardPath("Indomitability.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheClanless.Enums.FORTITUDE;

    private static final int COST = 1;
    private static final int BLOCK = 7;
    private static final int BLOCK_INCREASE = 3;

    private static final int MAGICNUMBER = 1;
    private static final int MAGICNUMBER_PLUS = 1;


    // /STAT DECLARATION/


    public Indomitability() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.block = this.baseBlock = BLOCK;
        this.magicNumber = this.baseMagicNumber = MAGICNUMBER;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new GainBlockAction(p, p, block)
        );
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new PressPower(p, p, this.magicNumber), this.magicNumber)
        );
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(BLOCK_INCREASE);
            upgradeMagicNumber(MAGICNUMBER_PLUS);
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
