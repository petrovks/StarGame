package geekbrains.libgdx.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;

import geekbrains.libgdx.math.MatrixUtils;
import geekbrains.libgdx.math.Rect;

public class BaseScreen implements Screen, InputProcessor {
    protected SpriteBatch batch;
    private Rect screenBounds;
    private Rect worldBounds;
    private Rect glBounds;
    private Vector2 t;
    private Matrix4 worldToGl;
    private Matrix3 screenToWorld;

    @Override
    public void show() {
        System.out.println("show");
        batch = new SpriteBatch();
        screenBounds = new Rect();
        worldBounds = new Rect();
        glBounds = new Rect(0,0,1f,1f);
        worldToGl = new Matrix4();
        screenToWorld = new Matrix3();
        t = new Vector2();
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) { }

    @Override
    public void resize(int width, int height) {
        System.out.println("resize width = " + width + " height = " + height);
        screenBounds.setSize(width, height);
        screenBounds.setLeft(0);
        screenBounds.setBottom(0);
        float aspect = width/ (float) height;
        worldBounds.setHeight(1f);
        worldBounds.setWidth(1f*aspect);
        MatrixUtils.calcTransitionMatrix(worldToGl,worldBounds,glBounds);
        MatrixUtils.calcTransitionMatrix(screenToWorld,screenBounds,worldBounds);
        batch.setProjectionMatrix(worldToGl);
        resize(worldBounds);
    }

    public void resize(Rect worldBounds) {
        System.out.println("resize width = " + worldBounds.getWidth() + " height = " + worldBounds.getHeight());
    }

    @Override
    public void pause() {
        System.out.println("pause");// срабатывает когда сворачиваем экран
    }

    @Override
    public void resume() {
        System.out.println("resume");// срабатывает когда разворачиваем экран
    }

    @Override
    public void hide() {
        System.out.println("hide");
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        System.out.println("keyDown keycode = " + keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        System.out.println("keyUp keycode = " + keycode);
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        System.out.println("KeyTyped character = " + character);
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("TouchDown screenX = " + screenX + " screenY = " + screenY);
        t.set(screenX,screenBounds.getHeight() - screenY).mul(screenToWorld);
        touchDown(t,pointer,button);
        return false;
    }

    public boolean touchDown(Vector2 touch, int pointer, int button) {
        System.out.println("TouchDown touchX = " + touch.x + " touchY = " + touch.y);
        t.set(touch.x,screenBounds.getHeight() - touch.y).mul(screenToWorld);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        System.out.println("TouchUp screenX = " + screenX + " screenY = " + screenY);
        t.set(screenX,screenBounds.getHeight() - screenY).mul(screenToWorld);
        touchUp(t,pointer,button);
        return false;
    }

    public boolean touchUp(Vector2 touch, int pointer, int button) {
        System.out.println("TouchUp screenX = " + touch.x + " touchY = " + touch.y);
        t.set(touch.x,screenBounds.getHeight() - touch.y).mul(screenToWorld);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        System.out.println("TouchDragged screenX = " + screenX + " screenY = " + screenY);
        t.set(screenX,screenBounds.getHeight() - screenY).mul(screenToWorld);
        touchDragged(t,pointer);
        return false;
    }

    public boolean touchDragged(Vector2 touch, int pointer) {
        System.out.println("TouchDragged screenX = " + touch.x + " touchY = " + touch.y);
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        System.out.println("scrolled amountX = " + amountX + " amountY = " + amountY);
        return false;
    }
}
