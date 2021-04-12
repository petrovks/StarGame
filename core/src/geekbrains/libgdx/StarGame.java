package geekbrains.libgdx;

import com.badlogic.gdx.Game;
import geekbrains.libgdx.screen.MenuScreen;

public class StarGame extends Game {
	@Override
	public void create () {
		setScreen(new MenuScreen(this));
	}
}
