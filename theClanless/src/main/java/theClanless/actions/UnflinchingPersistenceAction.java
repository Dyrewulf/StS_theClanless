package theClanless.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theClanless.cards.UnflinchingPersistence;

import java.util.Iterator;

public class UnflinchingPersistenceAction extends AbstractGameAction {
    private AbstractCard card;

    public UnflinchingPersistenceAction(AbstractCard card, int amount) {
        this.card = card;
        this.amount = amount;
    }

    public void update() {
        AbstractCard var10000 = this.card;
        var10000.baseBlock += this.amount;
        this.card.applyPowers();
        Iterator var1 = AbstractDungeon.player.discardPile.group.iterator();

        AbstractCard c;
        while(var1.hasNext()) {
            c = (AbstractCard)var1.next();
            if (c instanceof UnflinchingPersistence) {
                c.baseBlock += this.amount;
                c.applyPowers();
            }
        }

        var1 = AbstractDungeon.player.drawPile.group.iterator();

        while(var1.hasNext()) {
            c = (AbstractCard)var1.next();
            if (c instanceof UnflinchingPersistence) {
                c.baseBlock += this.amount;
                c.applyPowers();
            }
        }

        var1 = AbstractDungeon.player.hand.group.iterator();

        while(var1.hasNext()) {
            c = (AbstractCard)var1.next();
            if (c instanceof UnflinchingPersistence) {
                c.baseBlock += this.amount;
                c.applyPowers();
            }
        }

        this.isDone = true;
    }
}
