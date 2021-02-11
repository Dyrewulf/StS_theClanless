package theClanless.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ScroungeAction extends AbstractGameAction {
    private final AbstractPlayer player;
    private final int look;
    private final int keep;

    public ScroungeAction(AbstractPlayer player, int numToLook, int numToKeep) {
        this.player = player;
        this.look = numToLook;
        this.keep = numToKeep;

        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = this.startDuration = Settings.ACTION_DUR_MED;
    }

    @Override
    public void update() {
        if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.isDone = true;
            return;
        }

        if (this.duration == this.startDuration) {
            if (this.player.drawPile.isEmpty() || this.look < 1) {
                this.isDone = true;
                return;
            }

            CardGroup selection = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            for (int i = 0; i < Math.min(this.look, player.drawPile.size()); ++i) {
                selection.addToTop(player.drawPile.getNCardFromTop(i));
            }
            AbstractDungeon.gridSelectScreen.open(selection, this.keep, true, "");

        } else if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                if (player.hand.size() < 10) {
                    player.drawPile.moveToHand(c);
                } else {
                    player.drawPile.moveToDiscardPile(c);
                    player.createHandIsFullDialog();
                }
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }

        this.tickDuration();
    }
}
