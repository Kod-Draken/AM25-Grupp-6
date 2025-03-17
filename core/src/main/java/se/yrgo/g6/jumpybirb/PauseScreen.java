package se.yrgo.g6.jumpybirb;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class PauseScreen implements Screen {
    private BirbGame game;
    private SpriteBatch batch;
    private float worldWidth;
    private float worldHeight;

    public PauseScreen(BirbGame game, FitViewport viewport) {
        this.game = game;
        batch = new SpriteBatch();
        worldWidth = viewport.getWorldWidth();
        worldHeight = viewport.getWorldHeight();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            game.resumeGame();
            dispose();
            return;
        }
        batch.begin();
        BitmapFont font = new BitmapFont();
        font.getData().setScale(3f);
        font.draw(batch, "Game Paused", worldWidth / 2, worldHeight / 2);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

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
