package theClanless.actions;

import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class FastHandsDiscardToHand extends BetterDiscardPileToHandAction {
    private final AbstractPlayer player;
    private final int numberOfCards;
    private final boolean optional;
    private final boolean setCost;
    private final int costReduct;

    public FastHandsDiscardToHand(int numberOfCards, int costChange) {
        super(numberOfCards, costChange);
        this.costReduct = costChange;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.player = AbstractDungeon.player;
        this.numberOfCards = numberOfCards;
        this.optional = true;
        this.setCost = true;
    }

    @Override
    public void update() {
        if (this.duration == this.startDuration) {
            if (!this.player.discardPile.isEmpty() && this.numberOfCards > 0) {
                if (this.player.discardPile.size() <= this.numberOfCards && !this.optional) {
                    ArrayList<AbstractCard> cardsToMove = new ArrayList<>(this.player.discardPile.group);
                    for (AbstractCard c : cardsToMove) {
                        if (this.player.hand.size() < 10) {
                            this.player.hand.addToHand(c);
                            if (this.setCost) {
                                c.setCostForTurn(c.costForTurn - this.costReduct);
                                c.exhaust = true;
                            }
                            this.player.discardPile.removeCard(c);
                        }
                        c.lighten(false);
                        c.applyPowers();
                    }

                    this.isDone = true;
                } else {
                    if (this.numberOfCards == 1) {
                        if (this.optional) {
                            AbstractDungeon.gridSelectScreen.open(this.player.discardPile, this.numberOfCards, true, TEXT[0]);
                        } else {
                            AbstractDungeon.gridSelectScreen.open(this.player.discardPile, this.numberOfCards, TEXT[0], false);
                        }
                    } else if (this.optional) {
                        AbstractDungeon.gridSelectScreen.open(this.player.discardPile, this.numberOfCards, true, TEXT[1] + this.numberOfCards + TEXT[2]);
                    } else {
                        AbstractDungeon.gridSelectScreen.open(this.player.discardPile, this.numberOfCards, TEXT[1] + this.numberOfCards + TEXT[2], false);
                    }

                    this.tickDuration();
                }
            } else {
                this.isDone = true;
            }
        } else {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                    if (this.player.hand.size() < 10) {
                        this.player.hand.addToHand(c);
                        if (this.setCost) {
                            c.setCostForTurn(c.costForTurn - this.costReduct);
                            c.exhaust = true;
                        }

                        this.player.discardPile.removeCard(c);
                    }
                    c.lighten(false);
                    c.unhover();
                    c.applyPowers();
                }

                for (AbstractCard c : this.player.discardPile.group) {
                    c.unhover();
                    c.target_x = (float) CardGroup.DISCARD_PILE_X;
                    c.target_y = 0.0F;
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                AbstractDungeon.player.hand.refreshHandLayout();
            }

            this.tickDuration();

            if (this.isDone) {
                this.player.hand.group.forEach(AbstractCard::applyPowers);
            }
        }
    }
}
