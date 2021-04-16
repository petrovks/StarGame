package geekbrains.libgdx.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import geekbrains.libgdx.base.Sprite;
import geekbrains.libgdx.math.Rect;
import geekbrains.libgdx.math.Rnd;
import geekbrains.libgdx.pool.BulletPool;


public class EnemyShip extends Ship {

    public EnemyShip(BulletPool bulletPool, Rect worldBounds, Sound sound) {
        this.bulletPool = bulletPool;
        this.sound = sound;
        this.worldBounds = worldBounds;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
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
        this.v.set(v);
        this.bulletRegion = bulletRegion;
        this.bulletHeight = bulletHeight;
        this.bulletV.set(0, bulletVY);
        this.damage = damage;
        this.reloadInterval = reloadInterval;
        setHeightProportion(height);
        this.hp = hp;

    }
}
