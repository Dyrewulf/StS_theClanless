package theClanless.cards.celerity;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theClanless.actions.AdditionalStrikeAction;
import theClanless.cards.AbstractDynamicCard;
import theClanless.cards.core.QuickJab;
import theClanless.characters.TheClanless;
import theClanless.powers.ManeuverPower;
import theClanless.theClanlessMod;

public class Pursuit extends AbstractDynamicCard {

    public static final String ID = theClanlessMod.makeID("Pursuit");
    private static final String IMG = theClanlessMod.makeCardPath("Skill.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = TheClanless.Enums.CELERITY;

    private static final int COST = 1;
    private static final int BLOCK = 5;
    private static final int BLOCK_UPGRADE = 1;
    private static final int MANEUVER = 1;
    private static final int MANEUVER_UPGRADE = 1;

    public Pursuit() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.block = this.baseBlock = BLOCK;
        this.magicNumber = this.baseMagicNumber = MANEUVER;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDescription();
            upgradeBlock(BLOCK_UPGRADE);
            upgradeMagicNumber(MANEUVER_UPGRADE);
            initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new ManeuverPower(p, p, magicNumber)));
        addToBot(new GainBlockAction(p, p, block));
        if (upgraded) {
            addToBot(new AdditionalStrikeAction(p, new QuickJab()));
        }
    }
}
