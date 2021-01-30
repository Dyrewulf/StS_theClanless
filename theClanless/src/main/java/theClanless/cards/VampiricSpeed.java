package theClanless.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theClanless.characters.TheClanless;
import theClanless.powers.ManeuverPower;
import theClanless.theClanlessMod;

import static theClanless.theClanlessMod.makeCardPath;

public class VampiricSpeed extends AbstractDynamicCard {
    public static final String ID = theClanlessMod.makeID(VampiricSpeed.class.getSimpleName());
    public static final String IMG = makeCardPath("VampiricSpeed.png");


    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheClanless.Enums.CELERITY;

    private static final int COST = 1;
    private static final int BLOCK = 5;
    private static final int UPGRADE_PLUS_BLOCK = 3;
    private static final int MAGICNUMBER = 2;
    private static final int MAGICNUMBER_PLUS = 1;


    public VampiricSpeed() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.block = this.baseBlock = BLOCK;
        this.magicNumber = this.baseMagicNumber = MAGICNUMBER;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new GainBlockAction(p, p, block)
        );

        if (AbstractDungeon.player.hasPower(ManeuverPower.POWER_ID)) {

            if (AbstractDungeon.player.getPower(ManeuverPower.POWER_ID).amount > 0) {
                AbstractDungeon.actionManager.addToBottom(
                        new GainBlockAction(p, p, this.magicNumber)
                );
                AbstractDungeon.actionManager.addToBottom(
                        new ReducePowerAction(p, p, ManeuverPower.POWER_ID, 1)
                );
            }
            if (this.upgraded) {
                if (AbstractDungeon.player.getPower(ManeuverPower.POWER_ID).amount > 0) {
                    AbstractDungeon.actionManager.addToBottom(
                            new GainBlockAction(p, p, this.magicNumber)
                    );
                    AbstractDungeon.actionManager.addToBottom(
                            new ReducePowerAction(p, p, ManeuverPower.POWER_ID, 1)
                    );
                }
            }
        }
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            upgradeMagicNumber(MAGICNUMBER_PLUS);
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
