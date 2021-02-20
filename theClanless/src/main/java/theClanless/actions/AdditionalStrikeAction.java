package theClanless.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theClanless.powers.AdditionalStrikePower;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class AdditionalStrikeAction extends AbstractGameAction {

    private final AbstractPlayer player;
    private final List<AbstractCard> cardList;
    private final boolean applyPower;

    public AdditionalStrikeAction(AbstractPlayer p, List<AbstractCard> cardList, boolean applyPower) {
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.WAIT;
        this.player = p;
        this.cardList = cardList;
        this.applyPower = applyPower;
    }

    public AdditionalStrikeAction(AbstractPlayer p, AbstractCard card, boolean applyPower) {
        this(p, Collections.singletonList(card), applyPower);
    }

    public AdditionalStrikeAction(AbstractPlayer p, AbstractCard card) {
        this(p, card, true);
    }


    @Override
    public void update() {
        if (!player.hasPower(AdditionalStrikePower.POWER_ID) || !applyPower) {

            int randomNum = 0;
            if (cardList.size() > 1) {
                randomNum = ThreadLocalRandom.current().nextInt(0, cardList.size());
            }
            AbstractDungeon.actionManager.addToBottom(
                    new MakeTempCardInHandAction(cardList.get(randomNum), 1)
            );
            if (applyPower) {
                AbstractDungeon.actionManager.addToBottom(
                        new ApplyPowerAction(player, player, new AdditionalStrikePower(player, player, 1), 1)
                );
            }
        }

        this.isDone = true;
    }
}
