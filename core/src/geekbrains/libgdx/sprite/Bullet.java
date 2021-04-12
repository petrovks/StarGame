package geekbrains.libgdx.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import geekbrains.libgdx.base.Sprite;
import geekbrains.libgdx.math.Rect;

public class Bullet extends Sprite {
    Sound sound = Gdx.audio.newSound(Gdx.files.internal("sounds/bullet.wav"));
    private Rect worldBounds;
    private Vector2 v;
    private int damage;
    private Sprite owner;

    public Bullet() {
        regions = new TextureRegion[1];
        v = new Vector2();
    }

    public void set(
            Sprite owner,
            TextureRegion region,
            Vector2 pos,
            Vector2 v,
            Rect worldBounds,
            int damage,
            float height
    ) {
        this.owner = owner;
        this.regions[0] = region;
        this.pos.set(pos);
        this.v.set(v);
        this.worldBounds = worldBounds;
        this.damage = damage;
        setHeightProportion(height);
        sound.play(0.05f);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        if (isOutside(worldBounds)) {
            destroy();
        }
    }

    public int getDamage() {
        return damage;
    }

    public Sprite getOwner() {
        return owner;
    }
}

