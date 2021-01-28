package theClanless.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theClanless.characters.TheClanless;
import theClanless.powers.ManeuverPower;
import theClanless.powers.PressPower;
import theClanless.theClanlessMod;

import static theClanless.theClanlessMod.makeCardPath;

public class BumsRush extends AbstractDynamicCard {
    // TEXT DECLARATION
    public static final String ID = theClanlessMod.makeID(BumsRush.class.getSimpleName());
    public static final String IMG = makeCardPath("BumsRush.png");
    // /TEXT DECLARATION/

    // STAT DECLARATION
    public static final CardColor COLOR = TheClanless.Enums.COLOR_CLANLESSRED;
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final int COST = 1;

    private static final int DAMAGE = 9;
    private static final int UPGRADE_PLUS_DMG = 3;

    private static final int MANEUVER = 1;
    private static final int MANEUVER_UPGRADE = 2;

    private static final int PRESS = 1;
    private static final int PRESS_UPGRADE = 2;
    // /STAT DECLARATION/

    public BumsRush() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseMagicNumber = MANEUVER;
        baseClanlessSecondMagicNumber = PRESS;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new ManeuverPower(p, p, this.magicNumber)));
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new PressPower(p, p, this.clanlessSecondMagicNumber)));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeMagicNumber(MANEUVER_UPGRADE);
            upgradeClanlessSecondMagicNumber(PRESS_UPGRADE);
            initializeDescription();
        }
    }
}
