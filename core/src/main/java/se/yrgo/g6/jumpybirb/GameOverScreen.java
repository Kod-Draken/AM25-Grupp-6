package se.yrgo.g6.jumpybirb;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameOverScreen implements Screen {
    private BirbGame game;
    private FitViewport viewport;
    private Obstacle obstacle;

    private float worldWidth;
    private float worldHeight;

    // logic to block input after game over
    private boolean inputBlocked = true;
    private float inputBlockTime = 0;
    private static final float BLOCK_DURATION = 1f;

    private Texture background;
    private Texture gameOver;
    private Texture floor;

    private SpriteBatch batch;
    private Sprite birbSprite;

    private BitmapFont font;

    public GameOverScreen(BirbGame game, FitViewport viewport, Birb birb, Obstacle obstacle) {
        this.game = game;
        this.viewport = viewport;
        this.obstacle = obstacle;

        worldWidth = viewport.getWorldWidth();
        worldHeight = viewport.getWorldHeight();

        initTextures();
        initBirb(birb);
        initFonts();
    }

    private void initTextures() {
        background = new Texture("textures/background-WIDER2.png");
        floor = new Texture("textures/floor.png");
        gameOver = new Texture("textures/gameover.png");
    }

    private void initBirb(Birb birb) {
        batch = new SpriteBatch();
        birbSprite = birb.getBirbSprite();
        birbSprite.setPosition(worldWidth / 2 - birbSprite.getWidth() / 2, birbSprite.getY());
    }

    private void initFonts() {
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(2f);
    }

    @Override
    public void render(float delta) {
        blockInput(delta);

        draw();

        if (startNewRound()) {
            game.newGame();
        }
    }

    private void blockInput(float delta) {
        if (inputBlocked) {
            inputBlockTime += delta;
            if (inputBlockTime >= BLOCK_DURATION) {
                inputBlocked = false;  // Unblock input after the duration
                inputBlockTime = 0;    // Reset the timer
            }
        }
    }

    private void draw() {
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        drawBackground();
        drawObstacle();
        drawFloor();
        birbSprite.draw(batch);

        String highscore = writeHighscoreString();
        drawText(highscore);

        batch.end();
    }

    private void drawFloor() {
        batch.draw(floor, -game.floorOffset,0, worldWidth * 2, floor.getHeight());
        batch.draw(floor, -game.floorOffset + (worldWidth), 0, worldWidth * 2, floor.getHeight());
    }

    private void drawObstacle() {
        for (Sprite obstacle : obstacle.getObstacleSprites()) {
            obstacle.draw(batch);
        }
    }

    private void drawBackground() {
        batch.draw(background, -game.backgroundOffset, 0, worldWidth * 2, worldHeight);
        batch.draw(background, -game.backgroundOffset + (worldWidth * 2), 0, worldWidth * 2, worldHeight);
    }

    private String writeHighscoreString() {
        String highscore;
        if (game.getScore() > game.getHighScore()) {
            highscore = "New best!: ";
            game.setHighScore(game.getScore());
        }else {
            highscore = "Best: ";
        }
        return highscore;
    }

    private void drawText(String highscore) {
        batch.draw(gameOver, 0, 0, worldWidth, worldHeight);
        font.draw(batch, "Score: " + game.getScore(), 250, 470, 300, Align.center, true );
        font.draw(batch, highscore + game.getHighScore(), 250, 440, 300, Align.center, true);
    }

    private boolean startNewRound() {
        if (inputBlocked) return false;
        return Gdx.input.isKeyJustPressed(Input.Keys.SPACE)
            || Gdx.input.isButtonJustPressed(Input.Buttons.LEFT);
    }

    @Override
    public void show() {
        viewport.apply();
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
