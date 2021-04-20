package geekbrains.libgdx.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import geekbrains.libgdx.base.Sprite;
import geekbrains.libgdx.math.Rect;

public class GameOver extends Sprite {

    public GameOver(TextureAtlas atlas) {
        super(atlas.findRegion("message_game_over"));
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.08f);
    }
}