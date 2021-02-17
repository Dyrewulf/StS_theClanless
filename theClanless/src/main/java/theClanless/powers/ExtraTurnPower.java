package theClanless.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.watcher.SkipEnemiesTurnAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theClanless.theClanlessMod;
import theClanless.util.TextureLoader;

import static theClanless.theClanlessMod.makePowerPath;

public class ExtraTurnPower extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = theClanlessMod.makeID("ExtraTurnPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("placeholder_power32.png"));
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("placeholder_power84.png"));

    public ExtraTurnPower(AbstractCreature owner) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.description = DESCRIPTIONS[0];
        this.isTurnBased = true;
        this.type = PowerType.BUFF;

        this.owner = owner;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            addToBot(new SkipEnemiesTurnAction());
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new ExtraTurnPower(owner);
    }
}
