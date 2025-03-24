package se.yrgo.g6.jumpybirb;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

/** First screen of the application. Displayed after the application is created. */
public class FirstScreen implements Screen {
    private BirbGame game;
    private FitViewport viewport;
    private Birb birb;

    private float delta;
    private float worldWidth;
    private float worldHeight;

    private Texture background;
    private Texture obstacle;

    private SpriteBatch batch;
    private Sprite birbSprite;

    private BitmapFont font;

    float velocity = 0f;
    final private float gravity = -30f;
    final private float jumpStrenght = 500f;

    public FirstScreen(BirbGame game, FitViewport viewport, Birb birb) {
        this.game = game;
        this.viewport = viewport;
        this.birb = birb;

        worldWidth = viewport.getWorldWidth();
        worldHeight = viewport.getWorldHeight();

        background = new Texture("background.png");
        //obstacle = new Texture("placeholder_obstacle.jpg");

        batch = new SpriteBatch();
        birbSprite = birb.getBirbSprite();
        birbSprite.setPosition(worldWidth / 2 -50, worldHeight / 2 -50);

        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(2f);
    }

    @Override
    public void show() {
        // Prepare your screen here.
    }

    @Override
    public void render(float delta) {
        // Draw your screen here. "delta" is the time since last render in seconds.
        if (isGameOver()) {
            dispose();
            game.gameOver();
            return;
        }
        jump();
        logic();
        draw();
        System.out.println("Birb Y: " + birbSprite.getY());
    }

    private void draw() {
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        batch.draw(background, 0, 0, worldWidth, worldHeight);
        birbSprite.draw(batch);
        font.draw(batch, "Score: " + game.getScore(), 10, 470);
        batch.end();
    }

    private void jump() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            velocity = jumpStrenght;
            // For now score is increased when jumping.
            game.setScore(game.getScore() + 1);
        }
        // When pressing ESC, pause game and hide birb
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            birbSprite.setSize(0, 0);
            game.pauseGame();
        }
    }

    /**
     * Checks if the player hits the lowest allowed coordinate.
     * @return true if coordinate is reached, otherwise false.
     */
    private boolean isGameOver() {
        //die on ceiling *wip
        if (birbSprite.getY() > 400f) {
            return true;
        }
        return birbSprite.getY() < -30f;
    }

    private void logic() {
        delta = Gdx.graphics.getDeltaTime();

        if (game.getScore() > game.getHighScore()) {
            game.setHighScore(game.getScore());
        }

        velocity += gravity;
        birbSprite.translateY(velocity * delta);

        birbSprite.setY(MathUtils.clamp(birbSprite.getY(), -51,worldHeight - 100));

    }

    @Override
    public void resize(int width, int height) {
        // Resize your screen here. The parameters represent the new window size.
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // When resuming game, show birb and jump once
        birbSprite.setSize(100, 100);
        velocity = jumpStrenght;
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
