package geekbrains.libgdx.pool;

import geekbrains.libgdx.base.SpritesPool;
import geekbrains.libgdx.sprite.Bullet;

public class BulletPool extends SpritesPool<Bullet> {

    @Override
    protected Bullet newSprite() {
        return new Bullet();
    }

}
