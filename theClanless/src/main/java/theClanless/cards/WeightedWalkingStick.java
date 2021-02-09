package theClanless.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theClanless.characters.TheClanless;
import theClanless.powers.PressPower;
import theClanless.theClanlessMod;

import static theClanless.theClanlessMod.makeCardPath;

public class WeightedWalkingStick extends AbstractDynamicCard {
    // TEXT DECLARATION
    public static final String ID = theClanlessMod.makeID(WeightedWalkingStick.class.getSimpleName());
    public static final String IMG = makeCardPath("WeightedWalkingStick.png");
    // /TEXT DECLARATION/

    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheClanless.Enums.COLOR_CLANLESSRED;

    private static final int COST = 1;

    private static final int DAMAGE = 10;
    private static final int UPGRADE_PLUS_DMG = 2;

    private static final int DURABILITY = 3;
    private static final int DURABILITY_UPGRADE = 4;
    // /STAT DECLARATION/

    public WeightedWalkingStick() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        damage = baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = DURABILITY;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractPower presses = p.getPower(PressPower.POWER_ID);
        if (presses == null || presses.amount < 1) {
            addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        } else {
            for (int i = 0; i < presses.amount; ++i) {
                AbstractGameAction.AttackEffect effect = AbstractGameAction.AttackEffect.BLUNT_LIGHT;
                if (i % 3 == 0) {
                    effect = AbstractGameAction.AttackEffect.BLUNT_HEAVY;
                }
                addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), effect));
            }
            if (presses.amount > this.magicNumber) {
                this.exhaust = true;
            }
        }
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeMagicNumber(DURABILITY_UPGRADE);
            initializeDescription();
        }
    }

    @Override
    public float getTitleFontSize() {
        return 16.0F;
    }
}
