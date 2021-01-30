package theClanless.actions;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.NightmarePower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import org.graalvm.compiler.core.common.type.ArithmeticOpTable;
import theClanless.powers.ManeuverPower;

public class DOMCAction extends AbstractGameAction {

    private AbstractPlayer player;
    private static final float DURATION = 0.5F;

    public DOMCAction(AbstractPlayer p, int amount) {
        this.duration = 0.5F;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.player = p;
        this.amount = amount;
    }



    @Override
    public void update() {
        if (this.duration == DURATION) {
            if (this.player.hand.isEmpty()) {
                this.isDone = true;
            } else {
                AbstractDungeon.handCardSelectScreen.open("Exhaust a card for strength.", 1, false, true);
                this.tickDuration();
            }
        } else {
            if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
                AbstractCard tmpCard = AbstractDungeon.handCardSelectScreen.selectedCards.getBottomCard();
                AbstractDungeon.player.hand.addToHand(tmpCard);

                AbstractDungeon.actionManager.addToTop(
                        new ApplyPowerAction(player, player, new StrengthPower(player, this.amount), this.amount)
                );
                AbstractDungeon.actionManager.addToTop(
                        new ExhaustSpecificCardAction(tmpCard, player.hand)
                );


                AbstractDungeon.handCardSelectScreen.selectedCards.clear();
                AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            }

            this.tickDuration();

        }
    }
}
