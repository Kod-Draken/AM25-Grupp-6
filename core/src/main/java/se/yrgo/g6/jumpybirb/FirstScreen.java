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
    private Texture background;
    private Texture birb;
    private Texture obstacle;
    private SpriteBatch batch;
    private Sprite birbSprite;
    private FitViewport viewport;
    private BirbGame game;
    private BitmapFont font = new BitmapFont();
    private float velocity = 0f;
    final private float gravity = -30f;
    final private float jumpStrenght = 500f;
    private float delta;
    private float worldWidth;
    private float worldHeight;
    private String score;


    public FirstScreen(BirbGame game, FitViewport viewport) {
        background = new Texture("background.png");
        birb = new Texture("birb.png");
        //obstacle = new Texture("placeholder_obstacle.jpg");
        batch = new SpriteBatch();
        birbSprite = new Sprite(birb);
        birbSprite.setSize(100, 100);
        this.viewport = viewport;
        worldWidth = viewport.getWorldWidth();
        worldHeight = viewport.getWorldHeight();
        birbSprite.setPosition(worldWidth / 2 -50f, worldHeight / 2 -50f);
        this.game = game;
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
            if (game.getScore() > game.getHighScore()) {
                game.setNewHighScore(true);
                game.setHighScore(game.getScore());
            } else {
                game.setNewHighScore(false);
            }
            game.gameOver();
            return;
        }
        jump();
        draw();
        logic();
    }

    private void draw() {
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        batch.draw(background, 0, 0, worldWidth, worldHeight);
        birbSprite.draw(batch);
        score = "" + game.getScore();
        font.draw(batch, score, worldWidth / 2 - font.getScaleX(), worldHeight / 2 + 100f);
        batch.end();
    }

    private void jump() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            velocity = jumpStrenght;
            //temp score
            game.setScore(game.getScore() + 1);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.pauseGame();
        }
    }

    /**
     * Checks if the player hits the lowest allowed coordinate.
     * @return true if coordinate is reached, otherwise false.
     */
    private boolean isGameOver() {
        return birbSprite.getY() < -50f;
    }

    private void logic() {
        delta = Gdx.graphics.getDeltaTime();

        velocity += gravity;
        birbSprite.translateY(velocity * delta);

        birbSprite.setY(MathUtils.clamp(birbSprite.getY(), -51,worldHeight - 100));
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // When resuming game automatically jump once
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
