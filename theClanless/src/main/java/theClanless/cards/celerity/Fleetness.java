package theClanless.cards.celerity;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theClanless.cards.AbstractDynamicCard;
import theClanless.characters.TheClanless;
import theClanless.powers.FleetnessPower;
import theClanless.theClanlessMod;

public class Fleetness extends AbstractDynamicCard {

    public static final String ID = theClanlessMod.makeID("Fleetness");
    private static final String IMG = theClanlessMod.makeCardPath("Skill.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    private static final CardColor COLOR = TheClanless.Enums.CELERITY;

    private static final int COST = 2;
    private static final int COST_UPGRADE = 1;
    private static final int MANEUVERS = 1;

    public Fleetness() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = MANEUVERS;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(COST_UPGRADE);
            initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new FleetnessPower(p, this.magicNumber)));
    }
}
