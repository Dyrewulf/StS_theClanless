package theClanless.cards.fortitude;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import theClanless.cards.AbstractDynamicCard;
import theClanless.characters.TheClanless;
import theClanless.theClanlessMod;

import static theClanless.theClanlessMod.makeCardPath;

public class AblativeSkin extends AbstractDynamicCard {

    public static final String ID = theClanlessMod.makeID("AblativeSkin");
    private static final String IMG = makeCardPath("AblativeSkin.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    private static final CardColor COLOR = TheClanless.Enums.FORTITUDE;

    private static final int COST = 2;
    private static final int COST_UPGRADE = 1;
    private static final int ARMOR = 4;
    private static final int ARMOR_UPGRADE = 2;

    public AblativeSkin() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = ARMOR;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(COST_UPGRADE);
            upgradeMagicNumber(ARMOR_UPGRADE);
            initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new PlatedArmorPower(p, this.magicNumber)));
    }
}
