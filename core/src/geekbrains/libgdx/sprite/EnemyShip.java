package geekbrains.libgdx.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import geekbrains.libgdx.base.Sprite;
import geekbrains.libgdx.math.Rect;
import geekbrains.libgdx.math.Rnd;


public class EnemyShip extends Sprite {
    private static final float HEIGHT = 0.15f;
    private static final float PADDING = 0.05f;

    private static final int INVALID_POINTER = -1;
    private static final float RELOAD_INTERVAL = 0.15f;

    private Rect worldBounds;
    private  Vector2 v;
    private  Vector2 v0;


    public EnemyShip(TextureAtlas atlas) {
        super(atlas.findRegion("enemy2"),1,2,2);
        v = new Vector2();
        v0 = new Vector2(0.0f, -0.1f);
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setHeightProportion(HEIGHT);
        setTop(worldBounds.getTop() - PADDING);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        v.set(v0);
        if (getRight() < worldBounds.getLeft()) {
            setLeft(worldBounds.getRight());
        }
        if (getLeft() > worldBounds.getRight()) {
            setRight(worldBounds.getLeft());
        }
        if (getTop() < worldBounds.getBottom()) {
            setBottom(worldBounds.getTop());
        }
        if (getBottom() > worldBounds.getTop()) {
            setTop(worldBounds.getBottom());
        }
    }


}
