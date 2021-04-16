package geekbrains.libgdx.pool;

import com.badlogic.gdx.audio.Sound;

import geekbrains.libgdx.base.SpritesPool;
import geekbrains.libgdx.math.Rect;
import geekbrains.libgdx.sprite.EnemyShip;
import geekbrains.libgdx.sprite.Explosion;

public class EnemyPool extends SpritesPool<EnemyShip> {

    private final BulletPool bulletPool;
    private final Rect worldBounds;
    private final Sound sound;
    private final ExplosionPool explosionPool;
    public EnemyPool(BulletPool bulletPool, ExplosionPool explosionPool, Rect worldBounds, Sound sound) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
        this.sound = sound;
        this.explosionPool = explosionPool;
    }

       @Override
    protected EnemyShip newSprite() {
        return new EnemyShip(bulletPool, explosionPool, worldBounds,sound);
    }
}
