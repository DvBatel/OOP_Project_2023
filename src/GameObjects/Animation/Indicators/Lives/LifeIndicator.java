package GameObjects.Animation.Indicators.Lives;

import ConstsAndMethods.Const;
import ConstsAndMethods.Counter;
import GameObjects.Environment.Game.GameLevel;
import Interfaces.Sprite;
import biuoop.DrawSurface;

/**
 * The class Life indicator. Holds a reference to the lives counter.
 */
public class LifeIndicator implements Sprite {

    private final Counter lives;

    /**
     * Instantiates a new Life indicator.
     *
     * @param lives the lives
     */
    public LifeIndicator(Counter lives) {
        this.lives = lives;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawText(Const.HUNDRED, Const.SEVENTEEN,
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
        return "Lives: " + this.lives.getValue();
    }

}
