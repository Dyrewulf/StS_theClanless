package theClanless.actions;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import theClanless.powers.ExtraTurnPower;

public class FreakDriveAction extends DamageAction {
    private final boolean isUpgraded;
    private final AbstractPlayer player;

    public FreakDriveAction(AbstractPlayer player, AbstractCreature target, DamageInfo info, boolean isUpgraded) {
        super(target, info, AttackEffect.BLUNT_HEAVY);
        this.isUpgraded = isUpgraded;
        this.player = player;
    }

    @Override
    public void update() {
        super.update();
        if (this.isDone) {
            if (this.isUpgraded || target.isDead || target.isDying) {
                if (!player.hasPower(ExtraTurnPower.POWER_ID)) {
                    addToBot(new ApplyPowerAction(player, player, new ExtraTurnPower(player)));
                }
            }
        }
    }
}
