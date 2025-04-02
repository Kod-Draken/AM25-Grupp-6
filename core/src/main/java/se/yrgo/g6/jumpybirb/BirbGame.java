package se.yrgo.g6.jumpybirb;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.viewport.FitViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class BirbGame extends Game {
    private FitViewport viewport = new FitViewport(800, 480);
    private Screen currentScreen;

    private Birb birb;
    private Obstacle obstacle;

    private int score;
    private int highScore;

    float backgroundOffset;
    float floorOffset;

    @Override
    public void create() {
        newGame();
        this.highScore = 0;
    }

    public void newGame() {
        birb = new Birb();
        obstacle = new Obstacle();
        currentScreen = new GameScreen(this, viewport, birb, obstacle);
        setScreen(currentScreen);
        this.score = 0;
    }

    public void gameOver() {
        GameOverScreen gameOverScreen = new GameOverScreen(this, viewport, birb, obstacle);
        setScreen(gameOverScreen);
        gameOverScreen.render(0);
    }

    public void pauseGame() {
        setScreen(new PauseScreen(this, viewport, birb));
    }

    public void resumeGame() {
        setScreen(currentScreen);
        currentScreen.resume();
    }

    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public int getHighScore() {
        return highScore;
    }
    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public float getBackgroundOffset() {
        return backgroundOffset;
    }

    public float getFloorOffset() {
        return floorOffset;
    }
}
