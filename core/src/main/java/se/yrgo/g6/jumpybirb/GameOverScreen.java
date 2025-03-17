package se.yrgo.g6.jumpybirb;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameOverScreen implements Screen {
    private SpriteBatch batch;
    private Texture gameOver;
    private float worldWidth;
    private float worldHeight;
    private BirbGame game;


    public GameOverScreen(BirbGame game, FitViewport viewport) {
        this.game = game;
        batch = new SpriteBatch();
        gameOver = new Texture("gameover.png");
        worldWidth = viewport.getWorldWidth();
        worldHeight = viewport.getWorldHeight();
    }

    private void draw() {
        batch.begin();
        String score;
        if (game.getNewHighScore()) {
            score = "New Best!: " + game.getHighScore();
        }else {
            score = "Best: " + game.getHighScore();
        }
        BitmapFont font = new BitmapFont();
        font.getData().setScale(3f);

        font.draw(batch, score, worldWidth / 2, worldHeight / 2 + 200f);
        batch.draw(gameOver, 0, 0, worldWidth, worldHeight);
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
