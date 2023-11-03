package GameObjects.Environment;

import ConstsAndMethods.Const;
import ConstsAndMethods.Counter;

/**
 * The class Life track. Keeps track on the number of lives the player has.
 */
public class LifeTrack {

    private final Counter lives;

    /**
     * Instantiates a new Score.
     */
    public LifeTrack() {
        this.lives = new Counter(Const.SEVEN);
    }

    /**
     * Decrease life.
     */
    public void decreaseLife() {
        this.lives.decrease(Const.ONE);
    }

    /**
     * @return the lives counter.
     */
    public Counter getLives() {
        return this.lives;
    }

    /**
     * @return the lives counter.
     */
    public int getNumLives() {
        return this.lives.getValue();
    }
}
