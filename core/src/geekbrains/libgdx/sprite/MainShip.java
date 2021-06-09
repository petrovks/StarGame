package geekbrains.libgdx.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import geekbrains.libgdx.base.Sprite;
import geekbrains.libgdx.math.Rect;
import geekbrains.libgdx.pool.BulletPool;
import geekbrains.libgdx.pool.ExplosionPool;
import geekbrains.libgdx.utils.EnemyEmitter;

public class MainShip extends Ship {

    private static final float HEIGHT = 0.15f;
    private static final float PADDING = 0.05f;
    private static final int HP = 100;
    private static final int INVALID_POINTER = -1;

    private boolean pressedLeft;
    private boolean pressedRight;

    private int leftPointer = INVALID_POINTER;
    private int rightPointer = INVALID_POINTER;

    private int level = 1;
    private boolean isChangeLevel = false;

    public MainShip(TextureAtlas atlas, BulletPool bulletPool, ExplosionPool explosionPool, Sound sound) {
        super(atlas.findRegion("main_ship"), 1, 2, 2);
        this.bulletPool = bulletPool;
        this.sound = sound;
        this.explosionPool = explosionPool;
        this.bulletRegion = atlas.findRegion("bulletMainShip");
        bulletV.set(0, 0.5f);
        v0.set(0.5f, 0);
        reloadInterval = 0.2f;
        damage = 1;
        bulletHeight = 0.01f;
        hp = HP;
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setHeightProportion(HEIGHT);
        setBottom(worldBounds.getBottom() + PADDING);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (getRight() > worldBounds.getRight()) {
            setRight(worldBounds.getRight());
            stop();
        }
        if (getLeft() < worldBounds.getLeft()) {
            setLeft(worldBounds.getLeft());
            stop();
        }
        if (level % 2 == 0 && isChangeLevel) {
            reloadInterval -= 0.02f;
            isChangeLevel = false;
        }
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        if (touch.x < worldBounds.pos.x) {
            if (leftPointer != INVALID_POINTER) {
                return false;
            }
            leftPointer = pointer;
            moveLeft();
        } else {
            if (rightPointer != INVALID_POINTER) {
                return false;
            }
            rightPointer = pointer;
            moveRight();
        }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        if (pointer == leftPointer) {
            leftPointer = INVALID_POINTER;
            if (rightPointer != INVALID_POINTER) {
                moveRight();
            } else {
                stop();
            }
        } else if (pointer == rightPointer) {
            rightPointer = INVALID_POINTER;
            if (leftPointer != INVALID_POINTER) {
                moveLeft();
            } else {
                stop();
            }
        }
        return false;
    }

    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.RIGHT:
            case Input.Keys.D:
                moveRight();
                pressedRight = true;
                break;
            case Input.Keys.LEFT:
            case Input.Keys.A:
                moveLeft();
                pressedLeft = true;
                break;
        }
        return false;
    }

    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.RIGHT:
            case Input.Keys.D:
                pressedRight = false;
                if (pressedLeft) {
                    moveLeft();
                } else {
                    stop();
                }
                break;
            case Input.Keys.LEFT:
            case Input.Keys.A:
                pressedLeft = false;
                if (pressedRight) {
                    moveRight();
                } else {
                    stop();
                }
                break;
        }
        return false;
    }

    private void moveRight() {
        v.set(v0);
    }

    private void moveLeft() {
        v.set(v0).rotateDeg(180);
    }

    private void stop() {
        v.setZero();
    }

    public boolean isBulletCollision(Rect bullet) {
        return !(bullet.getRight() < getLeft()
                || bullet.getLeft() > getRight()
                || bullet.getBottom() > pos.y
                || bullet.getTop() < getBottom());
    }

    public void startNewGame() {
        pressedLeft = false;
        pressedRight = false;
        leftPointer = INVALID_POINTER;
        rightPointer = INVALID_POINTER;
        stop();
        this.pos.x = worldBounds.pos.x;
        hp = HP;
        flushDestroy();
    }

    @Override
    protected void shoot() {
        Vector2 v1 = new Vector2();
        v1.set(bulletV.x, bulletV.y+ (float)level/10);

        if (level > 5){
            Bullet bullet = bulletPool.obtain();
            Bullet bullet1 = bulletPool.obtain();
            Vector2 m = new Vector2();
            m.set(this.pos.x + 0.035f , this.pos.y);
            bullet.set(this, bulletRegion, m, v1, worldBounds, damage, bulletHeight);
            m.set(this.pos.x - 0.035f , this.pos.y);
            bullet1.set(this, bulletRegion, m, v1, worldBounds, damage, bulletHeight);
        }
        else {
            Bullet bullet = bulletPool.obtain();
            bullet.set(this, bulletRegion, this.pos, v1, worldBounds, damage, bulletHeight);
        }
        sound.play(0.05f);
    }

    public void setLevel(int level) {
        if (this.level != level){
            this.level = level;
            isChangeLevel = true;
        }
    }
}

