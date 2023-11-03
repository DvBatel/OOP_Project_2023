package GameObjects.Animation.Indicators.Score;

import ConstsAndMethods.Const;
import ConstsAndMethods.Counter;
import GameObjects.Environment.Game.GameLevel;
import Interfaces.Sprite;
import biuoop.DrawSurface;

/**
 * Score indicator, holds the properties of the score indicator.
 */
public class ScoreIndicator implements Sprite {

    private final Counter score;

    /**
     * initiates a new score indicator.
     * @param score the score counter
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawText(Const.THREE_HUNDRED_FIFTY, Const.SEVENTEEN,
                toString(), Const.SEVENTEEN);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.getCollections().addSprite(this);
    }

    @Override
    public String toString() {
        return "Score: " + this.score.getValue();
    }
}
