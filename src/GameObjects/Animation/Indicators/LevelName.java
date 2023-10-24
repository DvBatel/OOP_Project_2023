package GameObjects.Animation.Indicators;

import ConstsAndMethods.Const;
import GameObjects.Environment.Game.GameLevel;
import Interfaces.Sprite;
import biuoop.DrawSurface;

/**
 * The class Level name. holds a sprite reference to the level name.
 */
public class LevelName implements Sprite {

    private final String levelName;

    /**
     * Instantiates a new Level name.
     *
     * @param game the game
     */
    public LevelName(GameLevel game) {
        this.levelName = game.levelInformation().levelName();
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawText(Const.FIVE_HUNDRED_FIFTY, Const.SEVENTEEN,
                "Level Name: " + this.levelName, Const.SEVENTEEN);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.getCollections().addSprite(this);
    }
}
