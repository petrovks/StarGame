package geekbrains.libgdx.sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import geekbrains.libgdx.base.BaseButton;
import geekbrains.libgdx.math.Rect;
import geekbrains.libgdx.screen.GameScreen;
import geekbrains.libgdx.screen.MenuScreen;

public class NewGameButton extends BaseButton {
    private static final float HEIGHT = 0.08f;
    private static final float PADDING = 0.04f;

    private final Game game;

    public NewGameButton(TextureAtlas atlas, Game game) {
        super(atlas.findRegion("button_new_game"));
        this.game = game;
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(HEIGHT);
        setBottom(worldBounds.getBottom() + PADDING);
        setLeft(worldBounds.getLeft() + PADDING);
    }

    @Override
    public void action() {
        game.setScreen(new MenuScreen(game));
    }
}
