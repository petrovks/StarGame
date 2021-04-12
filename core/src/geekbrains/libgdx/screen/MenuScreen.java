package geekbrains.libgdx.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import geekbrains.libgdx.base.BaseScreen;
import geekbrains.libgdx.math.Rect;
import geekbrains.libgdx.sprite.Background;
import geekbrains.libgdx.sprite.ExitButton;
import geekbrains.libgdx.sprite.PlayButton;
import geekbrains.libgdx.sprite.Star;

public class MenuScreen extends BaseScreen {

    private static final int STAR_COUNT = 256;
    private final Game game;
    private Texture bg;
   // private Vector2 pos;
    private Background background;

    private TextureAtlas atlas;
    private Star star[];
    private ExitButton exitButton;
    private PlayButton playButton;

    public MenuScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/bg.png");
        //pos = new Vector2(-0.5f,-0.5f);
        background = new Background(bg);
        atlas = new TextureAtlas("textures/menuAtlas.tpack");
        star = new Star[STAR_COUNT];
        for (int i = 0; i < STAR_COUNT; i++) {
            star[i] = new Star(atlas);
        }
        exitButton = new ExitButton(atlas);
        playButton = new PlayButton(atlas, game);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        for (Star stars : star) {
            stars.update(delta);
        }
        Gdx.gl.glClearColor(0.56f, 0.81f, 0.26f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        for (Star stars : star) {
            stars.draw(batch);
        }
        exitButton.draw(batch);
        playButton.draw(batch);

        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        atlas.dispose();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        background.resize(worldBounds);
        for (Star stars : star) {
            stars.resize(worldBounds);
        }
        exitButton.resize(worldBounds);
        playButton.resize(worldBounds);


    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        exitButton.touchDown(touch, pointer, button);
        playButton.touchDown(touch, pointer, button);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        exitButton.touchUp(touch, pointer, button);
        playButton.touchUp(touch, pointer, button);
        return false;
    }

}
