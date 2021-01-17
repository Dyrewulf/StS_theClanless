package theClanless.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theClanless.characters.TheClanless;

import java.util.Iterator;

public class IncreasedStrengthAction extends AbstractGameAction {
    public IncreasedStrengthAction(AbstractPlayer p, int amount) {
        this.duration = Settings.ACTION_DUR_MED;
        this.actionType = ActionType.WAIT;
        this.amount = amount;
    }


    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED) {
            AbstractPlayer p = AbstractDungeon.player;
            this.upgradeAllCardsInGroup(p.hand);
            this.upgradeAllCardsInGroup(p.drawPile);
            this.upgradeAllCardsInGroup(p.discardPile);
            this.upgradeAllCardsInGroup(p.exhaustPile);
            this.isDone = true;
        }

    }



    private void upgradeAllCardsInGroup(CardGroup cardGroup) {
        Iterator var2 = cardGroup.group.iterator();

        while (var2.hasNext()) {
            AbstractCard c = (AbstractCard) var2.next();
            if (c.color.equals(TheClanless.Enums.POTENCE)) {
                if (c.type.equals(AbstractCard.CardType.ATTACK)) {
                    c.baseDamage += this.amount;
                    c.flash();
                }
            }

        }
    }
}