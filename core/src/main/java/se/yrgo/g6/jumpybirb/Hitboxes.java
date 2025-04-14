package se.yrgo.g6.jumpybirb;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;

public class Hitboxes {
    private Sprite sprite;
    private Polygon hitbox;

    public Hitboxes() {
        this.hitbox = new Polygon();
    }

    public Hitboxes(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setPosition(float x, float y) {
        this.hitbox.setPosition(x, y);
    }

    public Rectangle getRect() {
        return this.hitbox.getBoundingRectangle();
    }

    public boolean overlaps(Hitboxes other) {
        return Intersector.overlapConvexPolygons(this.hitbox, other.hitbox);
    }

    public void createBirb() {
        float[] vertices = {
            5f, 15f, //tail bot
            5f, 30f, //tail top
            65f, 48f, //head
            85f, 20f, //beak
            60f, 18f, //belly
        };

        hitbox = new Polygon(vertices);
        hitbox.setPosition(sprite.getX(), sprite.getY());
    }

    public void createTree() {
        float[] vertices = {
            sprite.getWidth()*0.4f, 0f, //root start
            sprite.getWidth()*0.4f, sprite.getHeight()*0.66f, //crown start
            sprite.getWidth()*0.1f, sprite.getHeight()*0.66f, //crown bottom left
            sprite.getWidth()*0.1f, sprite.getHeight()*0.75f, //crown mid left
            sprite.getWidth()*0.55f, sprite.getHeight()*0.9f, //crown top
            sprite.getWidth()*0.9f, sprite.getHeight()*0.75f, //crown mid right
            sprite.getWidth()*0.9f, sprite.getHeight()*0.66f, //crown bottom right
            sprite.getWidth()*0.7f, sprite.getHeight()*0.66f, //crown end
            sprite.getWidth()*0.7f, 0f //root end
        };

        hitbox = new Polygon(vertices);
        hitbox.setPosition(sprite.getX(), sprite.getY());
    }

    public void createCloud() {
        float[] vertices = {
            10f, 10f,
            sprite.getWidth()/3, (sprite.getHeight()/3)*2,
            (sprite.getWidth()/3)*2, (sprite.getHeight()/3)*2,
            sprite.getWidth()-10f, 10f
        };

        hitbox = new Polygon(vertices);
        hitbox.setPosition(sprite.getX(), sprite.getY());
    }
}
