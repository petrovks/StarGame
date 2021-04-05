package geekbrains.libgdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import geekbrains.libgdx.base.BaseScreen;
import geekbrains.libgdx.math.Rect;
import geekbrains.libgdx.sprite.Background;
import geekbrains.libgdx.sprite.Logo;

public class MenuScreen extends BaseScreen {

    private Texture bg;
    private Texture textureLogo;
    private Background background;
    private Logo logo;

    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/bg.png");
        textureLogo = new Texture("badlogic.jpg");
        background = new Background(bg);
        logo = new Logo(textureLogo);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        logo.update(delta);
        Gdx.gl.glClearColor(0.56f, 0.81f, 0.26f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        logo.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        textureLogo.dispose();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        logo.resize(worldBounds);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        logo.touchDown(touch, pointer, button);
        return false;
    }


}
