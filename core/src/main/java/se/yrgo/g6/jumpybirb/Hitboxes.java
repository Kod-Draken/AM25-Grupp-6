package se.yrgo.g6.jumpybirb;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

public class Hitboxes {
    private Rectangle hitbox;
    private Sprite sprite;

    public Hitboxes() {
        hitbox = new Rectangle();
    }

    public Hitboxes(Sprite sprite) {
        this.sprite = sprite;
        hitbox = new Rectangle(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight() * 0.95f);
    }

    public void setHitbox(Sprite sprite) {
        this.sprite = sprite;
        hitbox.set(sprite.getX() + 10f, sprite.getY() + 10f, sprite.getWidth() -20f, sprite.getHeight() * 0.9f);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public float getHeight() {
        return hitbox.getHeight();
    }

    public float getWidth() {
        return hitbox.getWidth();
    }

    public float getX() {
        return hitbox.getX();
    }

    public float getY() {
        return hitbox.getY();
    }

    public boolean overlaps(Hitboxes e) {
        return hitbox.overlaps(e.hitbox);
    }
}
