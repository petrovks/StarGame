package geekbrains.libgdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import geekbrains.libgdx.base.BaseScreen;
import geekbrains.libgdx.math.Rect;
import geekbrains.libgdx.sprite.Background;

public class MenuScreen extends BaseScreen {

    private Texture bg;
   // private Vector2 pos;
    private Background background;

    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/bg.png");
        //pos = new Vector2(-0.5f,-0.5f);
        background = new Background(bg);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        return false;
    }
}
