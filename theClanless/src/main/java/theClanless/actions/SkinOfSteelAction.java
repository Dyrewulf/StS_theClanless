//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package theClanless.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;

public class SkinOfSteelAction extends AbstractGameAction {
    public static final String[] TEXT;

    private static final UIStrings uiStrings;
    private final boolean isThisUpgraded;
    private final AbstractMonster targetMonster;
    private final AbstractPlayer player;

    public SkinOfSteelAction(AbstractMonster m, boolean isThisUpgraded) {
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
        this.isThisUpgraded = isThisUpgraded;
        this.targetMonster = m;
        this.player = AbstractDungeon.player;
    }

    public void update() {
        if (this.targetMonster != null && this.targetMonster.getIntentBaseDmg() >= 0 && !this.isThisUpgraded) {
            AbstractDungeon.actionManager.addToBottom(
                    new GainBlockAction(player, player, this.targetMonster.getIntentBaseDmg())
            );
        } else if (isThisUpgraded) {
            for (AbstractMonster mon : AbstractDungeon.getMonsters().monsters) {
                if (!mon.isDead && mon.getIntentBaseDmg() >= 0) {
                    AbstractDungeon.actionManager.addToBottom(
                            new GainBlockAction(player, player, mon.getIntentBaseDmg())
                    );
                }
            }
        } else {
            AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, TEXT[0], true));
        }

        this.isDone = true;
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("OpeningAction");
        TEXT = uiStrings.TEXT;
    }
}
