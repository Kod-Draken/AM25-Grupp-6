package se.yrgo.g6.jumpybirb;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class Obstacle {
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
        obstacleSprites = new Array<>();
    }

    public void createObstacle(float x, float y) {
        obstacleSprite = new Sprite(obstacle);
        obstacleSpriteUSD = new Sprite(obstacleUSD);
        random = 150 * MathUtils.random(0.8f, 1.15f);
        randomUSD = 150 * MathUtils.random(0.8f, 1.15f);
        obstacleSprite.setSize(100, random);
        obstacleSpriteUSD.setSize(100, randomUSD);
        obstacleSprite.setPosition(x, 0);
        obstacleSpriteUSD.setPosition(x, y-obstacleSpriteUSD.getHeight());
        obstacleSprites.add(obstacleSprite);
        obstacleSprites.add(obstacleSpriteUSD);
    }

    public Array<Sprite> getObstacleSprites() {
        return obstacleSprites;
    }
}
