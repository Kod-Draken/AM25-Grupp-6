package se.yrgo.g6.jumpybirb;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameOverScreen implements Screen {
    private BirbGame game;
    private FitViewport viewport;

    private float worldWidth;
    private float worldHeight;

    private Texture background;
    private Texture birb;
    private Texture gameOver;

    private SpriteBatch batch;
    private Sprite birbSprite;

    private BitmapFont font;

    public GameOverScreen(BirbGame game, FitViewport viewport) {
        this.game = game;
        this.viewport = viewport;

        worldWidth = viewport.getWorldWidth();
        worldHeight = viewport.getWorldHeight();

        background = new Texture("background.png");
        birb = new Texture("birb.png");
        gameOver = new Texture("gameover.png");

        batch = new SpriteBatch();
        birbSprite = new Sprite(birb);
        birbSprite.setSize(100, 100);
        birbSprite.setPosition(worldWidth / 2 -50f,  -30f);

        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(2f);
    }

    @Override
    public void render(float v) {
        if (newGame()) {
            game.newGame();
            return;
        }
        draw();
    }

    private void draw() {
        viewport.apply();

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        batch.draw(background, 0, 0, worldWidth, worldHeight);
        birbSprite.draw(batch);

        // Depending on
        String highscore;
        if (game.getScore() > game.getHighScore()) {
            highscore = "New best!: ";
            game.setHighScore(game.getScore());
        }else {
            highscore = "Best: ";
        }

        batch.draw(gameOver, 0, 0, worldWidth, worldHeight);
        font.draw(batch, "Score: " + game.getScore(), 10, 470);
        font.draw(batch, highscore + game.getHighScore(), 10, 440);
        batch.end();
    }

    private boolean newGame() {
        return Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
    }

    @Override
    public void show() {

    }


    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
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
