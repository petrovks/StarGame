package geekbrains.libgdx.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import geekbrains.libgdx.base.Sprite;
import geekbrains.libgdx.math.Rect;

public class Logo extends Sprite {
    private static final float LEN = 0.01f;
    private Vector2 t;
    private Vector2 v;
    private Vector2 tmp;

    public Logo(Texture texture) {
        super(new TextureRegion(texture));
        t = new Vector2();
        v = new Vector2();
        tmp = new Vector2();
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.1f);
    }

    @Override
    public void update(float delta) {
        tmp.set(t);
        if(tmp.sub(pos).len() > LEN) {
            pos.add(v);
        }
        else {
            pos.set(t);
        }
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        this.t.set(touch);
        v.set(touch.sub(pos)).setLength(LEN);
        return false;
    }
}
