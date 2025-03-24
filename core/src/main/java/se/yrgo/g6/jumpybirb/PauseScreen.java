package se.yrgo.g6.jumpybirb;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class PauseScreen implements Screen {
    private BirbGame game;
    private FitViewport viewport;
    private Birb birb;

    private SpriteBatch batch;
    BitmapFont font;
    private Sprite birbSprite;

    private float worldWidth;
    private float worldHeight;

    public PauseScreen(BirbGame game, FitViewport viewport, Birb birb) {
        this.game = game;
        this.viewport = viewport;
        this.birb = birb;

        batch = new SpriteBatch();
        font = new BitmapFont();
        birbSprite = birb.getBirbSprite();
        // Doesn't work
        //birbSprite.setPosition(worldWidth / 2 -50f, birbSprite.getY());

        worldWidth = viewport.getWorldWidth();
        worldHeight = viewport.getWorldHeight();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        viewport.apply();

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            game.resumeGame();
            dispose();
            return;
        }
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        font.getData().setScale(3f);
        font.draw(batch, "Game Paused", worldWidth / 2, worldHeight / 2 + 30f,1 , Align.center, false);
        batch.end();
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
        batch.dispose();
    }
}
