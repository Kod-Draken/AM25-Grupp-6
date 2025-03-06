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
    private Texture gameOver;
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

    public FirstScreen() {
        background = new Texture("placeholder_background.jpg");
        birb = new Texture("placeholder_birb.jpg");
        gameOver = new Texture("gameover.png");
        //obstacle = new Texture("placeholder_obstacle.jpg");
        batch = new SpriteBatch();
        viewport = new FitViewport(800, 500);
        birbSprite = new Sprite(birb);
        birbSprite.setSize(100, 100);
        gameOverSprite = new Sprite(gameOver);
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
        jump();
        draw();
        logic();
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
        batch.draw(background, 0, 0, worldWidth, worldHeight);
        birbSprite.draw(batch);
        batch.end();
    }

    private void jump() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            velocity = jumpStrenght;
        }
    }

    /**
     * Checks if the player hits the lowest allowed coordinate.
     * @return true if coordinate is reached, otherwise false.
     */
    private boolean isGameOver() {
        return birbSprite.getY() < -50;
    }

    /**
     * For now only draws an ugly "GAME OVER" sprite
     */
    private void gameOver() {
        batch.begin();
        batch.draw(gameOverSprite,0, 0, worldWidth, worldHeight);
        batch.end();
    }

    private void logic() {
        delta = Gdx.graphics.getDeltaTime();

        velocity += gravity;
        birbSprite.translateY(velocity * delta);

        float birdWidth = birb.getWidth();
        float birdHeight = birb.getHeight();

        birbSprite.setY(MathUtils.clamp(birbSprite.getY(), -100, worldHeight + 100));

        if (isGameOver()) {
            gameOver();
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
