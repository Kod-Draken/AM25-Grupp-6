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
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

/** First screen of the application. Displayed after the application is created. */
public class FirstScreen implements Screen {
    private BirbGame game;
    private FitViewport viewport;
    private Birb birb;
    private Obstacle obstacle;

    private float delta;
    private float worldWidth;
    private float worldHeight;

    private Texture background;

    private SpriteBatch batch;
    private Sprite birbSprite;

    private Hitboxes birbHitbox;
    private Hitboxes obstacleHitbox;

    float velocity = 0f;
    final private float gravity = -30f;
    final private float jumpStrenght = 500f;

    // Obstacles
    final private float obstacleSpeed = -5f;
    Array<Sprite> obstacleSprites;
    private float obstacleTimer;

    private BitmapFont font;

    public FirstScreen(BirbGame game, FitViewport viewport, Birb birb, Obstacle obstacle) {
        this.game = game;
        this.viewport = viewport;
        this.birb = birb;
        this.obstacle = obstacle;

        worldWidth = viewport.getWorldWidth();
        worldHeight = viewport.getWorldHeight();

        background = new Texture("background.png");
        obstacleSprites = obstacle.getObstacleSprites();
        obstacleHitbox = new Hitboxes();

        batch = new SpriteBatch();
        birbSprite = birb.getBirbSprite();
        birbSprite.setPosition(worldWidth / 2 -50, worldHeight / 2 -50);
        birbHitbox = new Hitboxes();


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
    }

    private void draw() {
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();

        batch.setProjectionMatrix(viewport.getCamera().combined);

        batch.begin();

        batch.draw(background, 0, 0, worldWidth, worldHeight);

        birbSprite.draw(batch);

        for (Sprite obstacle : obstacleSprites) {
            obstacle.draw(batch);
        }

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
        if (birbHitbox.overlaps(obstacleHitbox)) {
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
        birbHitbox = new Hitboxes(birbSprite);

        for (int i = obstacleSprites.size -1; i >= 0; i--) {
            Sprite obstacleSprite = obstacleSprites.get(i);
            float obstacleWidth = obstacleSprite.getWidth();
            float obstacleHeight = obstacleSprite.getHeight();

            obstacleSprite.translateX(-200 * delta);
            obstacleHitbox.setHitbox(obstacleSprite);

            if (obstacleSprite.getX() < -obstacleWidth) {
                obstacleSprites.removeIndex(i);
            }
        }

        obstacleTimer += delta;
        if (obstacleTimer > 2f) {
            obstacleTimer = 0f;
            // Creates upside down obstacles
            obstacle.createObstacleUSD(worldWidth, worldHeight);
        }

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
