package se.yrgo.g6.jumpybirb;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MenuScreen implements Screen {
    private BirbGame game;
    private FitViewport viewport;

    private float worldWidth;
    private float worldHeight;

    private Texture background;

    private SpriteBatch batch;
    private BitmapFont font;

    public MenuScreen(BirbGame birbGame, FitViewport viewport) {
        game = birbGame;
        this.viewport = viewport;

        worldWidth = viewport.getWorldWidth();
        worldHeight = viewport.getWorldHeight();

        background = new Texture("textures/background-WIDER2.png");

        batch = new SpriteBatch();

        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(2f);
    }

    @Override
    public void show() {
        viewport.apply();
        draw();
    }

    @Override
    public void render(float v) {
        draw();
        input();
    }

    private void draw() {

        batch.setProjectionMatrix(viewport.getCamera().combined);

        batch.begin();

        batch.draw(background, -game.backgroundOffset, 0, worldWidth * 2, worldHeight);

        font.draw(batch, "Welcome to Jumpy Birb!", 250, 470);
        font.draw(batch, "Press space to begin playing", 250, 440);

        batch.end();
    }

    public void input() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            game.newGame();
        }
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
