package GameObjects.Animation.Indicators.Score;

import ConstsAndMethods.Const;
import ConstsAndMethods.Counter;
import GameObjects.GameShapes.Player.Ball;
import GameObjects.GameShapes.Attribute.Block;
import Interfaces.Hit.HitListener;

/**
 * The type Score tracking listener.
 */
public class ScoreTrackingListener implements HitListener {

    private final Counter currentScore;

    /**
     * Instantiates a new Score tracking listener.
     *
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (hitter == null) {
            this.currentScore.increase(Const.HUNDRED);
        } else {
            this.currentScore.increase(Const.FIVE);
        }
    }
}
