package se.yrgo.g6.jumpybirb;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.viewport.FitViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class BirbGame extends Game {
    private FitViewport viewport = new FitViewport(800, 500);

    @Override
    public void create() {
        newGame();
    }

    public void newGame() {
        setScreen(new FirstScreen(this, viewport));
    }

    public void gameOver() {
        setScreen(new GameOverScreen(this, viewport));
    }


}
