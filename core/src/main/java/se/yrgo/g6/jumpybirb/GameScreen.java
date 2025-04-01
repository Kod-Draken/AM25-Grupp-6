package se.yrgo.g6.jumpybirb;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

/** First screen of the application. Displayed after the application is created. */
public class GameScreen implements Screen {
    private BirbGame game;
    private FitViewport viewport;
    private Birb birb;
    private Obstacle obstacle;

    private float delta;
    private float worldWidth;
    private float worldHeight;

    private Texture background;
    private Texture floor;

    private SpriteBatch batch;
    private Sprite birbSprite;
    private Sprite floorSprite;

    private Hitboxes birbHitbox;
    private Array<Hitboxes> obstacleHitboxes;
    private Hitboxes obstacleHitbox;

    private float backgroundOffset;
    private final float BACKGROUND_SPEED = 1;
    private float floorOffset;
    private final float FLOOR_SPEED = 2;

    float velocity = 0f;
    final private float GRAVITY = -30f;
    final private float JUMP_STRENGTH = 500f;

    // Obstacles
    final private float obstacleSpeed = -5f;
    Array<Sprite> obstacleSprites;
    private float obstacleTimer;

    private BitmapFont font;

    //Animation
    Texture birbFlapSheet;
    Animation<TextureRegion> flapAnimation;
    float jumpAnimationTimer;
    private static final int COLS = 5;
    private boolean isJumping;

    private boolean gameOver;

    public GameScreen(BirbGame game, FitViewport viewport, Birb birb, Obstacle obstacle) {
        this.game = game;
        this.viewport = viewport;
        this.birb = birb;
        this.obstacle = obstacle;

        worldWidth = viewport.getWorldWidth();
        worldHeight = viewport.getWorldHeight();

        gameOver = false;
        obstacleSprites = obstacle.getObstacleSprites();
        obstacleHitboxes = obstacle.getObstacleHitboxes();
        initTextures();
        initSprites(birb);
        initHitboxes();
        initFonts();
        initAnimation();
    }

    private void initAnimation() {
        TextureRegion[][] tmp = TextureRegion.split(
            birbFlapSheet,birbFlapSheet.getWidth() / COLS,
            birbFlapSheet.getHeight());
        isJumping = false;
        TextureRegion[] animationFrames = new TextureRegion[COLS];
        int index = 0;
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < COLS; j++) {
                animationFrames[index++] = tmp[i][j];
            }
        }
        flapAnimation = new Animation<>(0.05f, animationFrames);
        jumpAnimationTimer = 0f;
    }

    private void initFonts() {
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(2f);
    }

    private void initHitboxes() {
        obstacleHitbox = new Hitboxes();
        birbHitbox = new Hitboxes();
    }
    private void initTextures() {
        background = new Texture("background-WIDER2.png");
        floor = new Texture("floor.png");
        birbFlapSheet = new Texture("birbAnimationSheet.png");
    }

    private void initSprites(Birb birb) {
        batch = new SpriteBatch();

        birbSprite = birb.getBirbSprite();
        birbSprite.setPosition(
            worldWidth / 2 - birbSprite.getWidth() / 2,
            worldHeight / 2 - birbSprite.getWidth() / 2
        );

        floorSprite = new Sprite(floor);
        floorSprite.setSize(floor.getWidth(), floor.getHeight() );
        floorSprite.setPosition(0, 0);

        obstacleSprites = obstacle.getObstacleSprites();
    }


    @Override
    public void render(float delta) {
        // Time for animation
        jumpAnimationTimer += delta;

        if (isGameOver()) {
            dispose();
            game.gameOver();
            return;
        }
        logic();
        draw();
        input();
    }

    private void draw() {
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();

        batch.setProjectionMatrix(viewport.getCamera().combined);

        batch.begin();

        drawBackground();
        drawObstacle();
        drawFloor();
        drawScore();
        drawBirb();

        batch.end();
    }

    private void drawBirb() {
        if (isJumping){
        TextureRegion currentFrame = flapAnimation.getKeyFrame(jumpAnimationTimer, true);
        batch.draw(currentFrame, birbSprite.getX(), birbSprite.getY());
        }
        else birbSprite.draw(batch);
    }

    private void drawScore() {
        font.draw(batch, "Score: " + game.getScore(), 250, 470, 300, Align.center, true);
    }

    private void drawFloor() {
        batch.draw(floor, -floorOffset,0, worldWidth * 2, floor.getHeight());
        batch.draw(floor, -floorOffset + (worldWidth), 0, worldWidth * 2, floor.getHeight());
    }

    //Draw obstacles
    private void drawObstacle() {
        for (Sprite obstacle : obstacleSprites) {
            obstacle.draw(batch);
        }
    }

    private void drawBackground() {
        batch.draw(background, -backgroundOffset, 0, worldWidth * 2, worldHeight);
        batch.draw(background, -backgroundOffset + (worldWidth * 2), 0, worldWidth * 2, worldHeight);
    }

    private void input() {
        // SPACE-BAR to jump.
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            velocity = JUMP_STRENGTH;
            game.setScore(game.getScore() + 1);
            isJumping = true;
            jumpAnimationTimer = 0f;
        }
        // ESC to pause.
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.pauseGame();
        }
    }

    /**
     * @return True if coordinate is reached or obstacle collision.
     */
    private boolean isGameOver() {
        if (obstacleHitboxes == null) {
            return false;
        }
        if (gameOver) {
            return true;
        }
        return birbSprite.getY() < -30f;
    }

    private void logic() {
        delta = Gdx.graphics.getDeltaTime();

        velocity += GRAVITY;

        isNewHighscore();

        scrollBackground();
        scrollFloor();

        birbSprite.translateY(velocity * delta);
        birbHitbox = new Hitboxes(birbSprite);

        if (isJumping && flapAnimation.isAnimationFinished(jumpAnimationTimer)) {
            isJumping = false;
        }

        moveObstacles();
        spawnObstacle();

        // Lock area in which the player can move.
        birbSprite.setY(MathUtils.clamp(birbSprite.getY(), 0,worldHeight - birbSprite.getHeight()));

    }

    private void spawnObstacle() {
        obstacleTimer += delta;
        if (obstacleTimer > 2f) {
            obstacleTimer = 0f;
            obstacle.createObstacle(worldWidth, worldHeight);
        }
    }

    private void moveObstacles() {
        for (int i = obstacleSprites.size -1; i >= 0; i--) {
            Sprite obstacleSprite = obstacleSprites.get(i);
            Hitboxes hitbox = obstacleHitboxes.get(i);
            float obstacleWidth = obstacleSprite.getWidth();
            float obstacleHeight = obstacleSprite.getHeight();

            // Move obstacle left
            hitbox.setHitbox(obstacleSprite);
            obstacleSprite.translateX(-200f * delta);

            if (obstacleSprite.getX() < -obstacleWidth) {
                obstacleSprites.removeIndex(i);
                obstacleHitboxes.removeIndex(i);
            }
        }
        for (Hitboxes hitbox : obstacleHitboxes) {
            if (birbHitbox.overlaps(hitbox)) {
                gameOver = true;
            }
        }
    }

    private void scrollFloor() {
        floorOffset += FLOOR_SPEED;
        if (floorOffset % (worldWidth) == 0) {
            floorOffset = 0;
        }
    }

    private void scrollBackground() {
        backgroundOffset += BACKGROUND_SPEED;
        if (backgroundOffset % (worldWidth * 2) == 0) {
            backgroundOffset = 0;
        }
    }


    private void isNewHighscore() {
        if (game.getScore() > game.getHighScore()) {
            game.setHighScore(game.getScore());
        }
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
        birbSprite.setSize(93, 58);
        velocity = JUMP_STRENGTH;
    }

    @Override
    public void show() {
        // Prepare your screen here.
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


