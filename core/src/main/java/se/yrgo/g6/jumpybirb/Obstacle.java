package se.yrgo.g6.jumpybirb;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class Obstacle {
    private Array<Hitboxes> obstacleHitboxes;
    private Hitboxes obstacleHitbox;

    private Texture cloud;
    private Texture tree;

    private Array<Sprite> obstacleSprites;
    private Sprite groundedObstacleSprite;
    private Sprite elevatedObstacleSprite;

    private float randomScale;
    private float randomPosition;


    public Obstacle() {

        cloud = new Texture("textures/cloud.png");
        tree = new Texture("textures/tree1.png");
        obstacleHitboxes = new Array<>();
        obstacleSprites = new Array<>();
    }

    public void createTree(float x) {
        groundedObstacleSprite = new Sprite(tree);
        //Gives obstacle random scale
        randomScale = 1 * MathUtils.random(0.7f, 0.75f);
        randomPosition = 1 * MathUtils.random(1f, 1.2f);
        groundedObstacleSprite.setSize(groundedObstacleSprite.getWidth() * randomScale, groundedObstacleSprite.getHeight() * randomScale);
        groundedObstacleSprite.setPosition(x * randomPosition, 0);

        obstacleHitbox = new Hitboxes(groundedObstacleSprite);
        obstacleHitbox.createTree();
        obstacleHitboxes.add(obstacleHitbox);

        obstacleSprites.add(groundedObstacleSprite);
    }

    public void createCloud(float x, float y) {
        elevatedObstacleSprite = new Sprite(cloud);

        //Gives obstacle random scale
        randomScale = 1 * MathUtils.random(0.65f, 0.8f);
        randomPosition = 1 * MathUtils.random(1f, 1.2f);

        //Set the obstacle size and position at right-side of screen
        elevatedObstacleSprite.setSize(elevatedObstacleSprite.getWidth() * randomScale, elevatedObstacleSprite.getHeight() * randomScale);
        //High cloud
        if (MathUtils.randomBoolean()) {
            elevatedObstacleSprite.setPosition(x * randomPosition, y - elevatedObstacleSprite.getHeight());
        }
        //Low cloud
        else {
            elevatedObstacleSprite.setPosition(x * randomPosition, y - elevatedObstacleSprite.getHeight() * 2);
        }

        obstacleHitbox = new Hitboxes(elevatedObstacleSprite);
        obstacleHitbox.createCloud();
        obstacleHitboxes.add(obstacleHitbox);

        obstacleSprites.add(elevatedObstacleSprite);
    }

    public Array<Hitboxes> getObstacleHitboxes() {
        return obstacleHitboxes;
    }

    public Array<Sprite> getObstacleSprites() {
        return obstacleSprites;
    }
}
