package geekbrains.libgdx.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import geekbrains.libgdx.base.Sprite;
import geekbrains.libgdx.math.Rect;
import geekbrains.libgdx.math.Rnd;
import geekbrains.libgdx.pool.BulletPool;
import geekbrains.libgdx.pool.ExplosionPool;


public class EnemyShip extends Ship {

    public EnemyShip(BulletPool bulletPool, ExplosionPool explosionPool, Rect worldBounds, Sound sound) {
        this.bulletPool = bulletPool;
        this.sound = sound;
        this.worldBounds = worldBounds;
        this.explosionPool = explosionPool;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (getTop() < worldBounds.getTop()) {
            v.set(v0);
        } else {
            reloadTimer = reloadInterval * 0.8f;
        }
        if (getBottom() < worldBounds.getBottom()) {
            destroy();
    }
    }

    public void set(TextureRegion[] regions,
                    Vector2 v,
                    TextureRegion bulletRegion,
                    float bulletHeight,
                    float bulletVY,
                    int damage,
                    float reloadInterval,
                    float height,
                    int hp
    ) {
        this.regions = regions;
        this.v0.set(v);
        this.v.set(0, -0.3f);
        this.bulletRegion = bulletRegion;
        this.bulletHeight = bulletHeight;
        this.bulletV.set(0, bulletVY);
        this.damage = damage;
        this.reloadInterval = reloadInterval;
        setHeightProportion(height);
        this.hp = hp;
    }

    public boolean isBulletCollision(Rect bullet) {
        return !(bullet.getRight() < getLeft()
                || bullet.getLeft() > getRight()
                || bullet.getBottom() > getTop()
                || bullet.getTop() < pos.y);
    }
}
