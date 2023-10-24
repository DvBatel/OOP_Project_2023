package GameObjects.Environment;

import ConstsAndMethods.Counter;
import GameObjects.Animation.Indicators.Score.ScoreTrackingListener;
import Interfaces.Hit.HitListener;

/**
 * Score class, handles the score objects.
 */
public class Score {
    private final HitListener scoringTrackListener;
    private final Counter score;

    /**
     * Instantiates a new Score.
     */
    public Score() {
        this.score = new Counter();
        this.scoringTrackListener = new ScoreTrackingListener(score);
    }

    /**
     * @return the score track listener.
     */
    public HitListener getScoreListener() {
        return this.scoringTrackListener;
    }

    /**
     * @return the score counter.
     */
    public Counter getScore() {
        return this.score;
    }

}
