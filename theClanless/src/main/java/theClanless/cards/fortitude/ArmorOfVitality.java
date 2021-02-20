package theClanless.cards.fortitude;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theClanless.cards.AbstractDynamicCard;
import theClanless.characters.TheClanless;
import theClanless.powers.ArmorOfVitalityPower;
import theClanless.theClanlessMod;

public class ArmorOfVitality extends AbstractDynamicCard {

    public static final String ID = theClanlessMod.makeID("ArmorOfVitality");
    public static final String IMG = theClanlessMod.makeCardPath("Skill.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = TheClanless.Enums.FORTITUDE;

    private static final int COST = 1;
    private static final int BLOCK = 5;
    private static final int BLOCK_UPGRADE = 3;
    private static final int THORNS = 2;
    private static final int THORNS_UPGRADE = 1;

    public ArmorOfVitality() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        block = baseBlock = BLOCK;
        magicNumber = baseMagicNumber = THORNS;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDescription();
            upgradeBlock(BLOCK_UPGRADE);
            upgradeMagicNumber(THORNS_UPGRADE);
            initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        addToBot(new ApplyPowerAction(p, p, new ArmorOfVitalityPower(p, magicNumber)));
    }
}
