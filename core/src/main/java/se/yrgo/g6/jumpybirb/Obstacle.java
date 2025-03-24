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

    public Obstacle() {
        obstacle = new Texture("obstacle.png");
        obstacleUSD = new Texture("obstacleUSD.png");
        obstacleSprites = new Array<>();
    }

    public void createObstacle(float x) {
        obstacleSprite = new Sprite(obstacle);
        obstacleSprite.setSize(100, 200 * MathUtils.random(0.9f, 1.2f));
        obstacleSprite.setPosition(x, 0);
        obstacleSprites.add(obstacleSprite);
    }

    public void createObstacleUSD(float x, float y) {
        obstacleSprite = new Sprite(obstacleUSD);
        obstacleSprite.setSize(100, 300 * MathUtils.random(0.9f, 1.2f));
        obstacleSprite.setPosition(x, y - obstacleSprite.getHeight());
        obstacleSprites.add(obstacleSprite);
    }

    public Array<Sprite> getObstacleSprites() {
        return obstacleSprites;
    }
}
