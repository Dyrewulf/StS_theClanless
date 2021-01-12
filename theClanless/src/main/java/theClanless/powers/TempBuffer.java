package theClanless.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.BufferPower;
import theClanless.theClanlessMod;


public class TempBuffer extends BufferPower implements CloneablePowerInterface {

    public static final String POWER_ID = theClanlessMod.makeID("TempBufferPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    public TempBuffer(AbstractCreature owner, int bufferAmt) {


        super(owner, bufferAmt);
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = bufferAmt;

        type = PowerType.BUFF;
        isTurnBased = false;

    }

    @Override
    public void atStartOfTurn() {
        AbstractDungeon.actionManager.addToBottom(
                new ReducePowerAction(owner, owner, this, this.amount)
        );
    }

    @Override
    public AbstractPower makeCopy() {
        return new TempBuffer(owner, amount);
    }
}
