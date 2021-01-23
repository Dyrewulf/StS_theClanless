package theClanless.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;
import theClanless.cards.QuickJab;
import theClanless.powers.AdditionalStrikePower;
import theClanless.powers.ManeuverPower;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class AdditionalStrikeAction extends AbstractGameAction {

    private AbstractPlayer player;
    private ArrayList<AbstractCard> cardList;
    private AbstractCard card = new QuickJab();
    private boolean applyPower;


    public AdditionalStrikeAction(AbstractPlayer p, ArrayList<AbstractCard> cardList, boolean applyPower) {
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.WAIT;
        this.player = p;
        this.cardList = cardList;
        this.applyPower = applyPower;
    }

    public AdditionalStrikeAction(AbstractPlayer p, AbstractCard card, boolean applyPower) {
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.WAIT;
        this.player = p;
        this.cardList = new ArrayList<AbstractCard>();
        this.cardList.add(card);
        this.applyPower = applyPower;
    }


    @Override
    public void update() {
        if (!player.hasPower(AdditionalStrikePower.POWER_ID)) {

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
