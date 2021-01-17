package theClanless.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theClanless.characters.TheClanless;
import theClanless.powers.PressPower;
import theClanless.theClanlessMod;

import static theClanless.theClanlessMod.makeCardPath;

public class TornSignpost extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Defend Gain 5 (8) block.
     */


    // TEXT DECLARATION

    public static final String ID = theClanlessMod.makeID("TornSignpost");
    public static final String IMG = makeCardPath("TornSignpost.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheClanless.Enums.COLOR_CLANLESSRED;

    private static final int COST = 0;
    private static final int MAGICNUMBER  = 2;
    private static final int MAGICNUMBER_PLUS = 2;


    // /STAT DECLARATION/


    public TornSignpost() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = MAGICNUMBER;
        this.isInnate = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber), magicNumber)
        );
        if (!this.upgraded) {
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(p, p, new LoseStrengthPower(p, magicNumber), magicNumber)
            );
        } else {
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(p, p, new LoseStrengthPower(p, magicNumber -2), magicNumber -2)
            );
        }
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.exhaust = true;
            upgradeMagicNumber(MAGICNUMBER_PLUS);
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
