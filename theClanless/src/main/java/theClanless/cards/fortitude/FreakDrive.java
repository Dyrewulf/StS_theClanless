package theClanless.cards.fortitude;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theClanless.actions.FreakDriveAction;
import theClanless.cards.AbstractDynamicCard;
import theClanless.characters.TheClanless;
import theClanless.theClanlessMod;

import static theClanless.theClanlessMod.makeCardPath;

public class FreakDrive extends AbstractDynamicCard {

    public static final String ID = theClanlessMod.makeID(FreakDrive.class.getSimpleName());
    public static final String IMG = makeCardPath("FreakDrive.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheClanless.Enums.FORTITUDE;

    private static final int COST = 2;

    private static final int DAMAGE = 14;
    private static final int UPGRADE_PLUS_DMG = 4;

    public FreakDrive() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = DAMAGE;
        this.exhaust = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new FreakDriveAction(p, m, new DamageInfo(p, damage, damageTypeForTurn), upgraded));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeDescription();
            initializeDescription();
        }
    }
}
