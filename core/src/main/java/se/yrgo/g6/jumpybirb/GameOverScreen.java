package se.yrgo.g6.jumpybirb;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameOverScreen implements Screen {
    private SpriteBatch batch;
    private Texture gameOver;
    private float worldWidth;
    private float worldHeight;
    private BirbGame game;
    public int score;
    public int highScore;


    public GameOverScreen(BirbGame game, FitViewport viewport, int score) {
        this.game = game;
        this.score = score;
        this.highScore = score;
        batch = new SpriteBatch();
        gameOver = new Texture("gameover.png");
        worldWidth = viewport.getWorldWidth();
        worldHeight = viewport.getWorldHeight();

    }


    // Här skriver vi ut alla poäng i spelet
    private void draw() {
        batch.begin();
        batch.draw(gameOver, 0, 0, worldWidth, worldHeight);
        String scoreText = String.format("You scored: %d", score);
        smallFont.draw(batch, points, 100. 50. 600. Align.center, false);

        String highScoreText = String.format("High score: %d", highScore);
        smallFont.draw(batch, highScoreText. 100.30.600. Align.center, false);

        batch.end();
    }

    private boolean newGame() {
        return Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        if (newGame()) {
            game.newGame();
            return;
        }
        draw();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
