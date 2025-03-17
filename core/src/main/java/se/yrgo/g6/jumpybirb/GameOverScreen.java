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
    private Texture background;
    private Texture birb;
    private SpriteBatch batch;
    private Sprite birbSprite;
    private Texture gameOver;
    private float worldWidth;
    private float worldHeight;
    private BirbGame game;
    private BitmapFont font;


    public GameOverScreen(BirbGame game, FitViewport viewport) {
        this.game = game;
        background = new Texture("background.png");
        birb = new Texture("birb.png");
        batch = new SpriteBatch();
        gameOver = new Texture("gameover.png");
        worldWidth = viewport.getWorldWidth();
        worldHeight = viewport.getWorldHeight();
        font = new BitmapFont();
        birbSprite = new Sprite(birb);
        birbSprite.setSize(100, 100);
        birbSprite.setPosition(worldWidth / 2 -50f,  -30f);
    }

    private void draw() {
        ScreenUtils.clear(Color.BLACK);
        batch.begin();
        batch.draw(background, 0, 0, worldWidth, worldHeight);
        birbSprite.draw(batch);
        String score;
        if (game.getScore() > game.getHighScore()) {
            score = "New Best!: " + game.getHighScore();
        }else {
            score = "Best: " + game.getHighScore();
        }
        BitmapFont font = new BitmapFont();
        font.getData().setScale(3f);

        font.draw(batch, score, worldWidth / 2, worldHeight / 2 + 200f);
        batch.draw(gameOver, 0, 0, worldWidth, worldHeight);
        font.draw(batch, "Score: " + game.getScore(), 40, 40);
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
