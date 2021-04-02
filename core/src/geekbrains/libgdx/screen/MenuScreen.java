package geekbrains.libgdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;


import geekbrains.libgdx.base.BaseScreen;

public class MenuScreen extends BaseScreen {
    private final float V_LEN = 0.5f;
    private Texture img;
    private Vector2 pos;
    private Vector2 v;

    private Vector2 tmp;

    @Override
    public void show() {
        super.show();
        img = new Texture("badlogic.jpg");
        pos = new Vector2(-0.5f,-0.5f);
        v = new Vector2();

        tmp = new Vector2();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, pos.x, pos.y, 1.0f, 1.0f);
        batch.end();
        tmp.set(t);
        if(tmp.sub(pos).len() <= v.len()) {
                pos.set(t);
        }
        else {
            pos.add(v);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        img.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        t.set(screenX, Gdx.graphics.getHeight() - screenY);
        v.set(t.cpy().sub(pos)).setLength(V_LEN);
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
                pos.y += 10;
                break;
            case Input.Keys.DOWN:
                pos.y -= 10;
                break;
            case Input.Keys.RIGHT:
                pos.x += 10;
                break;
            case Input.Keys.LEFT:
                pos.x -= 10;
                break;
        }
        return false;
    }


}
