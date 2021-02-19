package theClanless.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theClanless.cards.AbstractClanlessCard;
import theClanless.theClanlessMod;
import theClanless.util.TextureLoader;

import static theClanless.theClanlessMod.makePowerPath;

public class PrecisionPower extends AbstractPower {

    public static final String POWER_ID = theClanlessMod.makeID("PrecisionPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("placeholder_power32.png"));
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("placeholder_power84.png"));

    public PrecisionPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.description = DESCRIPTIONS[0];
        this.type = PowerType.BUFF;

        this.owner = owner;
        this.amount = amount;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        float d = super.atDamageGive(damage, type, card);
        if (card instanceof AbstractClanlessCard) {
            AbstractClanlessCard clanlessCard = (AbstractClanlessCard) card;
            if (clanlessCard.hasClanlessTag(AbstractClanlessCard.clanlessCardTags.RANGED)) {
                d += this.amount;
            }
        }
        return d;
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
