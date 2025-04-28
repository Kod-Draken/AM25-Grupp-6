package se.yrgo.g6.jumpybirb;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.viewport.FitViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class BirbGame extends Game {
    private FitViewport viewport = new FitViewport(800, 480);
    private Screen currentScreen;

    private Birb birb;
    private Obstacle obstacle;

    private ScoreKeeper scoreKeeper; // keeps track of high score
    private int score;

    float backgroundOffset;
    float floorOffset;

    private Music backgroundMusic;

    @Override
    public void create() {
        mainMenu();
        initScoreKeeper();

    }

    public void mainMenu() {
        currentScreen = new MenuScreen(this, viewport);
        setScreen(currentScreen);
    }

    public void newGame() {
        birb = new Birb();
        obstacle = new Obstacle();
        currentScreen = new GameScreen(this, viewport, birb, obstacle);
        setScreen(currentScreen);
        this.score = 0;

        initAndPlayMusic();
    }

    private void initAndPlayMusic() {
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/BirbGameTheme.mp3"));
        backgroundMusic.setVolume(.4f);
        backgroundMusic.setLooping(true);
        backgroundMusic.play();
    }

    private void stopAndResetMusic() {
        backgroundMusic.stop();
        backgroundMusic.dispose();
    }

    public void initScoreKeeper() {
        scoreKeeper = new ScoreKeeper();
    }

    public void gameOver() {

        stopAndResetMusic();

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
        return scoreKeeper.getHighscore();
    }
    public void setHighScore(int highScore) {
        scoreKeeper.setHighscore(highScore);
    }

    public float getBackgroundOffset() {
        return backgroundOffset;
    }

    public float getFloorOffset() {
        return floorOffset;
    }
}
