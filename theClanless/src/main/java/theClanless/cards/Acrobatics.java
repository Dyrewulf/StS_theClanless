package theClanless.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.FreeAttackPower;
import theClanless.characters.TheClanless;
import theClanless.powers.TempBuffer;
import theClanless.theClanlessMod;

import static theClanless.theClanlessMod.makeCardPath;

public class Acrobatics extends AbstractDynamicCard {

    public static final String ID = theClanlessMod.makeID("Acrobatics");
    public static final String IMG = makeCardPath("Acrobatics.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheClanless.Enums.CELERITY;

    private static final int COST = 2;
    private static final int MAGICNUMBER = 1;


    // /STAT DECLARATION/


    public Acrobatics() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = MAGICNUMBER;

    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new TempBuffer(p, this.magicNumber))
        );
        if (this.upgraded) {
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(p, p, new FreeAttackPower(p, this.magicNumber), this.magicNumber)
            );
        }
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
