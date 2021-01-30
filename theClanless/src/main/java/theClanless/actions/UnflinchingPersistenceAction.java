package theClanless.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theClanless.cards.UnflinchingPersistence;

public class UnflinchingPersistenceAction extends AbstractGameAction {
    private final AbstractCard card;

    public UnflinchingPersistenceAction(AbstractCard card, int amount) {
        this.card = card;
        this.amount = amount;
    }

    public void update() {
        this.updateCard(this.card);
        AbstractDungeon.player.discardPile.group.forEach(this::updateCard);
        AbstractDungeon.player.drawPile.group.forEach(this::updateCard);
        AbstractDungeon.player.hand.group.forEach(this::updateCard);
        this.isDone = true;
    }

    private void updateCard(AbstractCard c) {
        if (c instanceof UnflinchingPersistence) {
            c.baseBlock += this.amount;
            c.applyPowers();
        }
    }
}
