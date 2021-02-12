package theClanless.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.RegenPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import theClanless.cards.Haymaker;
import theClanless.cards.QuickJab;
import theClanless.powers.AdditionalStrikePower;

public class LightningReflexesAction extends AbstractGameAction {

    private boolean freeToPlayOnce = false;
    private AbstractPlayer p;
    private int energyOnUse = -1;
    private int amount = 0;
    private boolean upgraded = false;


    public LightningReflexesAction(AbstractPlayer p, boolean upgraded, boolean freeToPlayOnce, int energyOnUse) {
        this.p = p;
        this.freeToPlayOnce = freeToPlayOnce;
        this.energyOnUse = energyOnUse;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.SPECIAL;
        this.upgraded = upgraded;
    }

    @Override
    public void update() {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1) {
            effect = this.energyOnUse;
        }

        if (this.p.hasRelic("Chemical X")) {
            effect += 2;
            this.p.getRelic("Chemical X").flash();
        }

        if (this.upgraded) {
            ++effect;
        }

        for (int i = effect; i > 0; i--) {
            if (p.hasPower(AdditionalStrikePower.POWER_ID)) {
                AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX,
                        AbstractDungeon.player.dialogY,
                        3.0F,
                        "I've already used an additional strike this turn.",
                        true));
                this.isDone = true;
            } else if (i > 1) {
                addToBot(new AdditionalStrikeAction(p, new QuickJab(), false));
            } else if (i == 1) {
                addToBot(new AdditionalStrikeAction(p, new Haymaker(), true));
            } else {
                AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX,
                        AbstractDungeon.player.dialogY,
                        3.0F,
                        "Not enough energy...",
                        true));
            }

            if (!this.freeToPlayOnce) {
                this.p.energy.use(EnergyPanel.totalCount);
            }

        }

        this.isDone = true;
    }
}
