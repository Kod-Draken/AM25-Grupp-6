package se.yrgo.g6.jumpybirb;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.viewport.FitViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class BirbGame extends Game {
    private FitViewport viewport = new FitViewport(800, 500);
    private int score;
    private int highScore;
    private Screen currentScreen;

    @Override
    public void create() {
        newGame();
        this.highScore = 0;
    }

    public void newGame() {
        currentScreen = new FirstScreen(this, viewport);
        setScreen(currentScreen);
        this.score = 0;
    }

    public void gameOver() {
        setScreen(new GameOverScreen(this, viewport));
    }

    public void pauseGame() {
        setScreen(new PauseScreen(this, viewport));
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



}
