package se.yrgo.g6.jumpybirb;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class Obstacle {
    private Array<Hitboxes> obstacleHitboxes;
    private Hitboxes obstacleHitbox;

    private Texture obstacle;
    private Texture obstacleUSD;

    private Array<Sprite> obstacleSprites;
    private Sprite obstacleSprite;
    private Sprite obstacleSpriteUSD;

    private float random;
    private float randomUSD;


    public Obstacle() {
        obstacle = new Texture("obstacle.png");
        obstacleUSD = new Texture("obstacleUSD.png");
        obstacleHitboxes = new Array<>();
        obstacleSprites = new Array<>();
    }

//    public void createObstacle(float x, float y) {
//        obstacleSprite = new Sprite(obstacle);
//
//        //Gives obstacle random height
//        random = 150 * MathUtils.random(0.8f, 1.15f);
//
//        //Set the obstacle size and position at right-side of screen
//        obstacleSprite.setSize(100, random);
//        obstacleSprite.setPosition(x, 0);
//
//        obstacleHitbox = new Hitboxes(obstacleSprite);
//
//        obstacleHitboxes.add(obstacleHitbox);
//        obstacleSprites.add(obstacleSprite);
//    }

    public void createObstacle(float x, float y) {
        obstacleSprite = new Sprite(obstacle);
        obstacleSpriteUSD = new Sprite(obstacleUSD);

        //Gives obstacle random height
        random = 150 * MathUtils.random(0.8f, 1.15f);
        randomUSD = 150 * MathUtils.random(0.8f, 1.15f);

        //Set the obstacle size and position at right-side of screen
        obstacleSprite.setSize(100, random);
        obstacleSpriteUSD.setSize(100, randomUSD);
        obstacleSprite.setPosition(x, 0);
        obstacleSpriteUSD.setPosition(x, y-obstacleSpriteUSD.getHeight());

        obstacleHitbox = new Hitboxes(obstacleSprite);
        obstacleHitboxes.add(obstacleHitbox);
        obstacleHitbox = new Hitboxes(obstacleSpriteUSD);
        obstacleHitboxes.add(obstacleHitbox);

        obstacleSprites.add(obstacleSprite);
        obstacleSprites.add(obstacleSpriteUSD);
    }

    public float getHeight() {
        return obstacleSprite.getHeight();
    }

    public float getWidth() {
        return obstacleSprite.getWidth();
    }

    public float getX() {
        return obstacleSprite.getX();
    }

    public float getY() {
        return obstacleSprite.getY();
    }

    public Array<Hitboxes> getObstacleHitboxes() {
        return obstacleHitboxes;
    }

    public Array<Sprite> getObstacleSprites() {
        return obstacleSprites;
    }
}
