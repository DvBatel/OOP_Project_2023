package GameObjects.Animation.Screens;

import ConstsAndMethods.Const;
import GameObjects.Collections.SpriteCollection;
import GameObjects.GameShapes.Boarders.Background.Background;
import Interfaces.Animation;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import java.awt.Color;

/**
 * The class The CountdownAnimation. Displays the given gameScreen,
 * for numOfSeconds seconds, and on top of them it shows
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) seconds, before
 * it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {

    private final long timeOfSleep;
    private int counter;
    private final SpriteCollection gameScreen;
    private final Sleeper sleeper;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds to count from
     * @param countFrom    the counter asked for
     * @param gameScreen   the sprites of the game
     */
    public CountdownAnimation(double numOfSeconds, int countFrom,
                              SpriteCollection gameScreen) {
        this.timeOfSleep = (long) numOfSeconds / countFrom;
        this.counter = countFrom;
        this.gameScreen = gameScreen;
        this.sleeper = new Sleeper();
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        gameScreen.drawAllOn(d);
        setCountColor(d);
        if (counter != Const.ZERO) {
            d.drawText(Const.THREE_HUNDRED_FIFTY, d.getHeight() / Const.TWO,
                    String.valueOf(counter), Const.FONT_SIZE);
        }
        sleeper.sleepFor(timeOfSleep);
        counter--;
    }

    private void setCountColor(DrawSurface d) {
        if (((Background) this.gameScreen.firstSprite()).
                getCollisionRectangle().getColor().equals(Color.BLACK)) {
            d.setColor(Color.WHITE);
        } else {
            d.setColor(Color.BLACK);
        }
    }

    @Override
    public boolean shouldStop() {
        return counter < Const.ZERO;
    }
}
