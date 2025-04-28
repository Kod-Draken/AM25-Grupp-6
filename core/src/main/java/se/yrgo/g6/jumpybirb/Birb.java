package se.yrgo.g6.jumpybirb;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Birb {
    private Texture birb;
    private Sprite birbSprite;

    public Birb() {
        birb = new Texture("textures/birb.png");
        birbSprite = new Sprite(birb);
        birbSprite.setSize(93, 58  );
    }

    public Sprite getBirbSprite() {
        return birbSprite;
    }

    public void setBirbSprite(Sprite birbSprite) {
        this.birbSprite = birbSprite;
    }

}
