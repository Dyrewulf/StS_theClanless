package theClanless.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theClanless.characters.TheClanless;
import theClanless.theClanlessMod;
import theClanless.util.TextureLoader;

import static theClanless.theClanlessMod.makeRelicOutlinePath;
import static theClanless.theClanlessMod.makeRelicPath;

public class PotenceRelic extends CustomRelic {
    public static final String ID = theClanlessMod.makeID("PotenceRelic");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("potence.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("discipline.png"));

    public static final AbstractCard.CardColor COLOR = TheClanless.Enums.POTENCE;

    public PotenceRelic() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.FLAT);
    }


    @Override
    public void onEquip() {
    }


    @Override
    public void onUnequip() {
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
