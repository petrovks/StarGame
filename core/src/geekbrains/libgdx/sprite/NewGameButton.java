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
    private static final float HEIGHT = 0.05f;

    private final GameScreen gameScreen;

    public NewGameButton(TextureAtlas atlas, GameScreen gameScreen) {
        super(atlas.findRegion("button_new_game"));
        this.gameScreen = gameScreen;
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(HEIGHT);
    }

    @Override
    public void action() {
            gameScreen.startNewGame();
    }
}
