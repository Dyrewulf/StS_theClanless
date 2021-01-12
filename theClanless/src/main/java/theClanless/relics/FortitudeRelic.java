package theClanless.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theClanless.characters.TheClanless;
import theClanless.theClanlessMod;
import theClanless.util.TextureLoader;

import static theClanless.theClanlessMod.makeRelicOutlinePath;
import static theClanless.theClanlessMod.makeRelicPath;

public class FortitudeRelic extends CustomRelic {
    public static final String ID = theClanlessMod.makeID("FortitudeRelic");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("fortitude.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("discipline.png"));

    public static final AbstractCard.CardColor COLOR = TheClanless.Enums.FORTITUDE;

    public FortitudeRelic() {
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
