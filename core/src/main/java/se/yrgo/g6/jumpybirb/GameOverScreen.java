package se.yrgo.g6.jumpybirb;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameOverScreen implements Screen {
    private SpriteBatch batch;
    private Texture gameOver;
    private float worldWidth;
    private float worldHeight;
    private BirbGame game;
    int score;



    public GameOverScreen(BirbGame game, FitViewport viewport, int score) {
        this.game = game;
        batch = new SpriteBatch();
        gameOver = new Texture("gameover.png");
        worldWidth = viewport.getWorldWidth();
        worldHeight = viewport.getWorldHeight();
        score = score;

    }

    private void draw() {
        batch.begin();
        batch.draw(gameOver, 0, 0, worldWidth, worldHeight);
        bigFont.draw(batch, "Game Over!", 100, 100, 600, Align.center, false);

        String points = String.format("You scored: %d", alienGame.getPoints());
        smallFont.draw(batch, points, 100, 50, 600, Align.center, false);

        alienHead.draw(batch, elapsedTime)
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
