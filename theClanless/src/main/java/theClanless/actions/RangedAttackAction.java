package theClanless.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;
import theClanless.powers.ManeuverPower;

public class RangedAttackAction extends AbstractGameAction {

    private AbstractPlayer shooter;
    private AbstractMonster target;
    private int pewpew = 0;
    private int clipSize = 0;
    private int bullet = 0;
    private DamageInfo.DamageType packing;

    public RangedAttackAction(AbstractPlayer p, AbstractMonster m, int damage, DamageInfo.DamageType damageTypeForTurn, int magicNumber, int clanlessSecondMagicNumber) {
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.WAIT;
        this.shooter = p;
        this.target = m;
        this.pewpew = damage;
        this.bullet = magicNumber;
        this.clipSize = clanlessSecondMagicNumber;
        this.packing = damageTypeForTurn;
    }



    @Override
    public void update() {
        if (shooter.hasPower(ManeuverPower.POWER_ID)) {
            this.pewpew += bullet;

            if (target.hasPower(ThornsPower.POWER_ID)) {
                AbstractDungeon.actionManager.addToBottom(
                        new ReducePowerAction(target, shooter, ThornsPower.POWER_ID, bullet)
                );
            }

            AbstractDungeon.actionManager.addToBottom(
                    new ReducePowerAction(shooter, shooter, ManeuverPower.POWER_ID, 1)
            );
        }

        for (int i = 0; i < clipSize; i++) {
            AbstractDungeon.actionManager.addToBottom(
                    new DamageAction(target, new DamageInfo(target, pewpew, packing))
            );

        }

        this.isDone = true;
    }
}
