package geekbrains.libgdx.pool;

import com.badlogic.gdx.audio.Sound;

import geekbrains.libgdx.base.SpritesPool;
import geekbrains.libgdx.math.Rect;
import geekbrains.libgdx.sprite.EnemyShip;

public class EnemyPool extends SpritesPool<EnemyShip> {

    private final BulletPool bulletPool;
    private final Rect worldBounds;
    private final Sound sound;

    public EnemyPool(BulletPool bulletPool, Rect worldBounds, Sound sound) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
        this.sound = sound;
    }

       @Override
    protected EnemyShip newSprite() {
        return new EnemyShip(bulletPool,worldBounds,sound);
    }
}
