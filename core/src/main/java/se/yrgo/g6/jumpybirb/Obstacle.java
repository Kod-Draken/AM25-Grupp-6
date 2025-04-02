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
    private Texture cloud;
    private Texture tree;

    private Array<Sprite> obstacleSprites;
    private Sprite groundedObstacleSprite;
    private Sprite elevatedObstacleSprite;

    private float randomScale;
    private float randomPosition;


    public Obstacle() {
        obstacle = new Texture("obstacle.png");
        obstacleUSD = new Texture("obstacleUSD.png");
        cloud = new Texture("cloud.png");
        tree = new Texture("tree1.png");
        obstacleHitboxes = new Array<>();
        obstacleSprites = new Array<>();
    }

    public void createObstacle(float x, float y) {
        groundedObstacleSprite = new Sprite(tree);
        elevatedObstacleSprite = new Sprite(cloud);

        //Gives obstacle random scale
        randomScale = 1 * MathUtils.random(0.6f, 0.8f);
        randomPosition = 1 * MathUtils.random(1f, 1.5f);

        //Set the obstacle size and position at right-side of screen
        groundedObstacleSprite.setSize(171 * randomScale, 300 * randomScale);
        elevatedObstacleSprite.setSize(elevatedObstacleSprite.getWidth(), elevatedObstacleSprite.getHeight());
        groundedObstacleSprite.setPosition(x * randomPosition, 0);
        elevatedObstacleSprite.setPosition(x * randomPosition, y - elevatedObstacleSprite.getHeight() * randomPosition);

        obstacleHitbox = new Hitboxes(groundedObstacleSprite);
        obstacleHitboxes.add(obstacleHitbox);
        obstacleHitbox = new Hitboxes(elevatedObstacleSprite);
        obstacleHitboxes.add(obstacleHitbox);

        obstacleSprites.add(groundedObstacleSprite);
        obstacleSprites.add(elevatedObstacleSprite);
    }

    public Array<Hitboxes> getObstacleHitboxes() {
        return obstacleHitboxes;
    }

    public Array<Sprite> getObstacleSprites() {
        return obstacleSprites;
    }
}
