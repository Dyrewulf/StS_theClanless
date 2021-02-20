package theClanless.cards.potence;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theClanless.cards.AbstractDynamicCard;
import theClanless.characters.TheClanless;
import theClanless.theClanlessMod;

import static theClanless.theClanlessMod.makeCardPath;

public class Disarm extends AbstractDynamicCard {

    public static final String ID = theClanlessMod.makeID(Disarm.class.getSimpleName());
    public static final String IMG = makeCardPath("Disarm.png");


    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheClanless.Enums.POTENCE;

    private static final int COST = 0;

    private static final int MAGICNUMBER = 0;
    private static final int MAGICNUMBER_PLUS = 2;

    private int buffAmount = 0;

    public Disarm() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = MAGICNUMBER;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new StrengthPower(m, -this.magicNumber), -this.magicNumber));
        addToBot(new ApplyPowerAction(m, p, new GainStrengthPower(m, this.magicNumber / 2), this.magicNumber / 2));
        if (this.buffAmount > 0) {
            this.baseMagicNumber -= this.buffAmount;
            this.magicNumber = this.baseMagicNumber;
            this.buffAmount = 0;
        }
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        super.onPlayCard(c, m);
        if (AbstractDungeon.player.hand.contains(this) && c.type == CardType.ATTACK) {
            this.baseMagicNumber += 1;
            this.magicNumber = baseMagicNumber;
            this.buffAmount += 1;
        }
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(MAGICNUMBER_PLUS);
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
