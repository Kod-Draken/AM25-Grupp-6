package se.yrgo.g6.jumpybirb;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class ScoreKeeper {
    public Preferences prefs;

    public ScoreKeeper() {
        prefs = Gdx.app.getPreferences("g6ScoreKeeper");
    }

    public int getHighscore() {
        return prefs.getInteger("highscore");
    }

    public void setHighscore(int highscore) {
        prefs.putInteger("highscore", highscore);
        prefs.flush();
    }
}
