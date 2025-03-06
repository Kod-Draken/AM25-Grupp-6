package se.yrgo.g6.jumpybirb;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class BirbGame extends Game {

    @Override
    public void create() {
        setScreen(new FirstScreen());
    }
}
