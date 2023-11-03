package GameObjects.Animation.Screens;

import ConstsAndMethods.Const;
import GameObjects.Environment.Score;
import Interfaces.Animation;
import biuoop.DrawSurface;

/**
 * The class Game over. Initiates a game over window.
 */
public class GameOver implements Animation {

    private final Score score;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param score the score
     */
    public GameOver(Score score) {
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(Const.HUNDRED_FIFTY, d.getHeight() / Const.TWO,
                "Game Over. Your score is "
                        + this.score.getScore().getValue(), Const.FONT_SIZE);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}