package se.yrgo.g6.jumpybirb;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.viewport.FitViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class BirbGame extends Game {
    private FitViewport viewport = new FitViewport(800, 500);
    private int highscore;
    private int score;


    @Override
    public void create() {
        newGame();
        this.highscore = 0;
    }

    public void newGame() {
        setScreen(new FirstScreen(this, viewport));
        this.score = 0;
    }

    public void gameOver() {
        setScreen(new GameOverScreen(this, viewport));
    }

    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
