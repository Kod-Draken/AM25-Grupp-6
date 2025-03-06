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

    private SpriteBatch batch;
    private FitViewport viewport;
    private Sprite birbSprite;
     float velocity = 0;
    final float gravity = -30;
    final private float jumpStrenght = 100f;
    private float delta;
    private float worldWidth;
    private float worldHeight;

    public FirstScreen() {
        background = new Texture("placeholder_background.jpg");
        birb = new Texture("placeholder_birb.jpg");
        //obstacle = new Texture("placeholder_obstacle.jpg");
        batch = new SpriteBatch();
        viewport = new FitViewport(800, 500);
        birbSprite = new Sprite(birb);
        birbSprite.setSize(100, 100);
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
        
        delta = Gdx.graphics.getDeltaTime();

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            velocity = jumpStrenght;
            birbSprite.translateY(jumpStrenght * delta);
        }
    }

    private void logic() {
        float birdWidth = birb.getWidth();
        float birdHeight = birb.getHeight();
        birbSprite.setX(MathUtils.clamp(birbSprite.getX(), 0, worldWidth-100));
        birbSprite.setY(MathUtils.clamp(birbSprite.getY(), 0, worldHeight-100));

        birbSprite.translateY(gravity * delta);
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
