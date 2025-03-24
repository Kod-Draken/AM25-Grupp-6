package se.yrgo.g6.jumpybirb;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Birb {
    private Texture birb;
    private Sprite birbSprite;

    public Birb() {
        birb = new Texture("Birb.png");
        birbSprite = new Sprite(birb);
        birbSprite.setSize(100, 100);
    }

    public Sprite getBirbSprite() {
        return birbSprite;
    }

}
