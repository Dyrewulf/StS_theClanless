package theClanless.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theClanless.characters.TheClanless;
import theClanless.theClanlessMod;
import theClanless.util.TextureLoader;

import static theClanless.theClanlessMod.*;

public class CelerityRelic extends CustomRelic {
    public static final String ID = theClanlessMod.makeID("CelerityRelic");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("celerity.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("discipline.png"));

    public static final AbstractCard.CardColor COLOR = TheClanless.Enums.CELERITY;

    public CelerityRelic() {
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

    public static String getID() {return ID;}

}
