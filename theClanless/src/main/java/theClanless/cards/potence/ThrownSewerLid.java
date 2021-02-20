package theClanless.cards.potence;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theClanless.actions.RangedAttackAction;
import theClanless.cards.AbstractDynamicCard;
import theClanless.characters.TheClanless;
import theClanless.theClanlessMod;

public class ThrownSewerLid extends AbstractDynamicCard {

    public static final String ID = theClanlessMod.makeID("ThrownSewerLid");
    public static final String IMG = theClanlessMod.makeCardPath("ThrownSewerLid.png");


    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheClanless.Enums.POTENCE;

    private static final int COST = 2;

    private static final int DAMAGE = 12;
    private static final int DAMAGE_PLUS = 4;

    private static final int MAGICNUMBER = 4;
    private static final int MAGICNUMBER_PLUS = 4;


    public ThrownSewerLid() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = DAMAGE;
        this.magicNumber = this.baseMagicNumber = MAGICNUMBER;

        this.clanlessTags.add(clanlessCardTags.RANGED);
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new RangedAttackAction(p, m, damage, this.damageTypeForTurn, magicNumber, 1)
        );
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(DAMAGE_PLUS);
            upgradeMagicNumber(MAGICNUMBER_PLUS);
            initializeDescription();
        }
    }
}
