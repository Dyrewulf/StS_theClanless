package theClanless.cards.fortitude;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import theClanless.cards.AbstractDynamicCard;
import theClanless.characters.TheClanless;
import theClanless.powers.SkinOfNightPower;
import theClanless.theClanlessMod;

import static theClanless.theClanlessMod.makeCardPath;

public class SkinOfNight extends AbstractDynamicCard {

    public static final String ID = theClanlessMod.makeID("SkinOfNight");
    public static final String IMG = makeCardPath("SkinOfNight.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;


    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheClanless.Enums.FORTITUDE;

    private static final int COST = 0;
    private static final int MAGIC = 3;
    private static final int UPGRADE_MAGIC = 3;



    public SkinOfNight() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = MAGIC;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new SkinOfNightPower(p, p, magicNumber), magicNumber)
        );
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_MAGIC);
            initializeDescription();
        }
    }
}