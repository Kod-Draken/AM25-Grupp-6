package se.yrgo.g6.jumpybirb;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

/** First screen of the application. Displayed after the application is created. */
public class FirstScreen implements Screen {
    private Texture background;
    private Texture birb;
    private Texture obstacle;
    private Texture gameOverTxt;
    private SpriteBatch batch;
    private FitViewport viewport;
    private Sprite birbSprite;
    private Sprite gameOverSprite;
     float velocity = 0;
    final float gravity = -30;
    final private float jumpStrenght = 500f;
    private float delta;
    private float worldWidth;
    private float worldHeight;
    boolean gameOver = false;

    public FirstScreen() {
        background = new Texture("placeholder_background.jpg");
        birb = new Texture("placeholder_birb.jpg");
        gameOverTxt = new Texture("gameover.png");
        //obstacle = new Texture("placeholder_obstacle.jpg");
        batch = new SpriteBatch();
        viewport = new FitViewport(800, 500);
        birbSprite = new Sprite(birb);
        birbSprite.setSize(100, 100);
        gameOverSprite = new Sprite(gameOverTxt);
        gameOverSprite.setSize(10, 10);
        worldWidth = viewport.getWorldWidth();
        worldHeight = viewport.getWorldHeight();
        birbSprite.setPosition(worldWidth / 2 -50, worldHeight / 2 -50);
    }

    @Override
    public void show() {
        // Prepare your screen here.
    }

    @Override
    public void render(float delta) {

        // Draw your screen here. "delta" is the time since last render in seconds.
        if (!gameOver) {
            logic();
        }
            draw();
            jump();
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
    private void draw() {
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        if (gameOver) {
            batch.draw(gameOverSprite,0, 0, worldWidth, worldHeight);
        }
        else {
        batch.draw(background, 0, 0, worldWidth, worldHeight);
        birbSprite.draw(batch);
        }
        batch.end();
    }

    /**
     * Press space to jump, simple as that
     */
    private void jump() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            velocity = jumpStrenght;
        }
    }

    /**
     * Checks if player goes beyond allowed Y-coordinate.
     * @return true if coordinate is reached.
     */
    private boolean isGameOver() {
        return birbSprite.getY() < -50;

    }

    /**
     * Unused for now, instead relies on the "boolean gameOver"
     * which is set if isGameOver() returns true
     */
    private void gameOver() {
    }

    private void logic() {
        delta = Gdx.graphics.getDeltaTime(); // speed doesn't scale with framerate

        velocity += gravity; // gravity affects speed
        birbSprite.translateY(velocity * delta); // bird moves Y-axis according to speed

        float birdWidth = birb.getWidth(); // unused?
        float birdHeight = birb.getHeight(); // unused?

        birbSprite.setY(MathUtils.clamp(birbSprite.getY(), -100, worldHeight + 100));

        if (isGameOver()) {
            gameOver = true;
        };
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        // Destroy screen's assets here.
    }
}
