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

    private float delta;
    private float worldWidth;
    private float worldHeight;

    private Texture background;
    private Texture birb;
    private Texture obstacle;

    private SpriteBatch batch;
    private Sprite birbSprite;

    float velocity = 0f;
    final private float gravity = -30f;
    final private float jumpStrenght = 500f;

    // Obstacles
    final private float obstacleSpeed = -5f;
    private Array<Sprite> obstacleSprites;
    private float obstacleTimer;

    private BitmapFont font;

    public FirstScreen(BirbGame game, FitViewport viewport) {
        this.game = game;
        this.viewport = viewport;

        worldWidth = viewport.getWorldWidth();
        worldHeight = viewport.getWorldHeight();

        background = new Texture("background.png");
        birb = new Texture("birb.png");
        obstacle = new Texture("obstacle.png");
        obstacleSprites = new Array<>();

        batch = new SpriteBatch();
        birbSprite = new Sprite(birb);
        birbSprite.setSize(100, 100);
        birbSprite.setPosition(worldWidth / 2 -50, worldHeight / 2 -50);

        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(2f);
    }

    public void createObstacle() {
        Sprite obstacleSprite = new Sprite(obstacle);
        obstacleSprite.setSize(obstacle.getWidth(), obstacle.getHeight());
        obstacleSprite.setPosition(worldWidth, 0);
        obstacleSprites.add(obstacleSprite);

    };

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
            game.pauseGame();
            birbSprite.setSize(0,0);
        }
    }

    /**
     * Checks if the player hits the lowest allowed coordinate.
     * @return true if coordinate is reached, otherwise false.
     */
    private boolean isGameOver() {
        return birbSprite.getY() < -30f;
    }

    private void logic() {
        delta = Gdx.graphics.getDeltaTime();

        if (game.getScore() > game.getHighScore()) {
            game.setHighScore(game.getScore());
        }

        velocity += gravity;
        birbSprite.translateY(velocity * delta);

        for (int i = obstacleSprites.size -1; i >= 0; i--) {
            Sprite obstacleSprite = obstacleSprites.get(i);
            float obstacleWidth = obstacleSprite.getWidth();
            float obstacleHeight = obstacleSprite.getHeight();

            obstacleSprite.translateX(-200 * delta);

            if (obstacleSprite.getX() < -obstacleWidth) {
                obstacleSprites.removeIndex(i);
            }
        }

        obstacleTimer += delta;
        if (obstacleTimer > 4f) {
            obstacleTimer = 0f;
            createObstacle();
        }

        float birdWidth = birb.getWidth();
        float birdHeight = birb.getHeight();

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
