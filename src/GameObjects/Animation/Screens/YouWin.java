package GameObjects.Animation.Screens;

import ConstsAndMethods.Const;
import GameObjects.Environment.Score;
import Interfaces.Animation;
import biuoop.DrawSurface;

/**
 * The class you win. Initiates a "you win" window.
 */
public class YouWin implements Animation {

    private final Score score;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param score the keyboard reference
     */
    public YouWin(Score score) {
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(Const.HUNDRED_FIFTY, d.getHeight() / Const.TWO,
                "You win! Your score is "
                        + this.score.getScore().getValue(), Const.FONT_SIZE);
    }

    @Override
    public boolean shouldStop() {
        return true;
    }
}