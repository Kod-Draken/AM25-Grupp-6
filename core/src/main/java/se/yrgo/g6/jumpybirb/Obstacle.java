package se.yrgo.g6.jumpybirb;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;

public class Obstacle {
    private Texture obstacle;
    private Array<Sprite> obstacleSprites;

    public Obstacle() {
        obstacle = new Texture("obstacle.png");
        obstacleSprites = new Array<>();
    }

    public void createObstacle(float x, float y) {
        Sprite obstacleSprite = new Sprite(obstacle);
        obstacleSprite.setSize(100, 200);
        obstacleSprite.setPosition(x, y);
        obstacleSprites.add(obstacleSprite);
    };

    public Array<Sprite> getObstacleSprites() {
        return obstacleSprites;
    }
}
