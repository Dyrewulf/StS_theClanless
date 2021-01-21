//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package theClanless.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;

import java.util.ArrayList;
import java.util.Iterator;

public class SkinOfSteelAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private boolean isThisUpgraded = false;
    private AbstractMonster targetMonster;
    private AbstractPlayer player;

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
            ArrayList<AbstractMonster> theMonsters = AbstractDungeon.getMonsters().monsters;
            Iterator iter234345 = theMonsters.iterator();
            while (iter234345.hasNext()) {
                AbstractMonster mon = (AbstractMonster) iter234345.next();
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
