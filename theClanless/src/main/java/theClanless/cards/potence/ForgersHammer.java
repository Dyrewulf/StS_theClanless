package theClanless.cards.potence;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theClanless.actions.ForgersHammerAction;
import theClanless.cards.AbstractDynamicCard;
import theClanless.characters.TheClanless;
import theClanless.theClanlessMod;

import static theClanless.theClanlessMod.makeCardPath;


public class ForgersHammer extends AbstractDynamicCard {

    public static final String ID = theClanlessMod.makeID(ForgersHammer.class.getSimpleName());
    public static final String IMG = makeCardPath("ForgersHammer.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheClanless.Enums.POTENCE;

    private static final int COST = 1;

    public ForgersHammer() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        //this.exhaust = true;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
            addToBot(new ForgersHammerAction(this.upgraded));

    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            initializeDescription();
        }
    }
}
