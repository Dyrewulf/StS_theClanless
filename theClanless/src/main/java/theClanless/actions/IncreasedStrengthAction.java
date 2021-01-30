package theClanless.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import theClanless.characters.TheClanless;

public class IncreasedStrengthAction extends AbstractGameAction {
    private final AbstractPlayer player;

    public IncreasedStrengthAction(AbstractPlayer p, int amount) {
        this.duration = Settings.ACTION_DUR_MED;
        this.actionType = ActionType.WAIT;
        this.player = p;
        this.amount = amount;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED) {
            this.upgradeAllCardsInGroup(this.player.hand);
            this.upgradeAllCardsInGroup(this.player.drawPile);
            this.upgradeAllCardsInGroup(this.player.discardPile);
            this.upgradeAllCardsInGroup(this.player.exhaustPile);
            this.isDone = true;
        }
    }

    private void upgradeAllCardsInGroup(CardGroup cardGroup) {
        for (AbstractCard c : cardGroup.group) {
            if (c.color.equals(TheClanless.Enums.POTENCE)) {
                if (c.type.equals(AbstractCard.CardType.ATTACK)) {
                    c.baseDamage += this.amount;
                    c.flash();
                }
            }
        }
    }
}