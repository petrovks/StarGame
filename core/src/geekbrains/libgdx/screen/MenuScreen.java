package geekbrains.libgdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;


import geekbrains.libgdx.base.BaseScreen;

public class MenuScreen extends BaseScreen {
    private Texture img;
    private Vector2 pos;
    private Vector2 v;
    private Vector2 t;
    private float positionX;
    private float positionY;
    private enum STATE {
        NONE, MOVE
    }
    STATE state = STATE.NONE;

    @Override
    public void show() {
        super.show();
        img = new Texture("badlogic.jpg");
        pos = new Vector2();
        v = new Vector2(1,1);

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, pos.x, pos.y);
        batch.end();
        if(state != STATE.NONE) {

                pos.add(t.nor());

            if (Math.round(pos.x) == positionX && Math.round(pos.y) == positionY) {
                state = STATE.NONE;
            }
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        img.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        positionY = Gdx.graphics.getHeight() - screenY;
        positionX = screenX;
        t = new Vector2(positionX, positionY);
        t.sub(pos.x, pos.y);

        //  positionX = screenX;

        //pos.set(screenX, Gdx.graphics.getHeight() - screenY);
        state = STATE.MOVE;

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
