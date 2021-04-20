package geekbrains.libgdx.pool;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import geekbrains.libgdx.base.SpritesPool;
import geekbrains.libgdx.sprite.Explosion;

public class ExplosionPool extends SpritesPool<Explosion> {

    private TextureAtlas atlas;
    private Sound explosionSound;

    public ExplosionPool(TextureAtlas atlas, Sound explosionSound) {
        this.atlas = atlas;
        this.explosionSound = explosionSound;
    }

    @Override
    protected Explosion newSprite() {
        return new Explosion(atlas, explosionSound);
    }
}
