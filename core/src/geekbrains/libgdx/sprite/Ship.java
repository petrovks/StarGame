package geekbrains.libgdx.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import geekbrains.libgdx.base.Sprite;
import geekbrains.libgdx.math.Rect;
import geekbrains.libgdx.pool.BulletPool;
import geekbrains.libgdx.pool.ExplosionPool;

public class Ship extends Sprite {
    private static final float DAMAGE_ANIMATE_INTERVAL = 0.1f;
    protected Rect worldBounds;
    protected BulletPool bulletPool;
    protected TextureRegion bulletRegion;
    protected Vector2 bulletV;
    protected int damage;
    protected float bulletHeight;
    protected Vector2 v;
    protected Vector2 v0;
    protected ExplosionPool explosionPool;
    protected float reloadTimer;
    protected float reloadInterval;
    private float damageAnimateTimer = DAMAGE_ANIMATE_INTERVAL;
    protected Sound sound;
    protected int hp;

    public Ship(){
        bulletV = new Vector2();
        v = new Vector2();
        v0 = new Vector2();
    }

    public Ship(TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames);
        v = new Vector2();
        bulletV = new Vector2();
        v0 = new Vector2();
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        reloadTimer += delta;
        if (reloadTimer > reloadInterval) {
            reloadTimer = 0f;
            shoot();
        }
        damageAnimateTimer += delta;
        if (damageAnimateTimer >= DAMAGE_ANIMATE_INTERVAL) {
            frame = 0;
        }
    }

    public void damage(int damage) {
        hp -= damage;
        if (hp <= 0) {
            hp = 0;
            destroy();
        }
        frame = 1;
        damageAnimateTimer = 0f;
    }

    @Override
    public void destroy() {
        super.destroy();
        boom();
    }

    public int getDamage() {
        return damage;
    }

    private void boom() {
        Explosion explosion = explosionPool.obtain();
        explosion.set(pos, getHeight());
    }

    private void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, this.pos, bulletV, worldBounds, damage, bulletHeight);
        sound.play(0.05f);
    }
}
