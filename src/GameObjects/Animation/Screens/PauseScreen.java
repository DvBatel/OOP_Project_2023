package GameObjects.Animation.Screens;

import ConstsAndMethods.Const;
import Interfaces.Animation;
import biuoop.DrawSurface;

/**
 * The class pause screen. Initiates a screen pause window.
 */
public class PauseScreen implements Animation {

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(Const.HUNDRED_FIFTY, d.getHeight() / Const.TWO,
                "paused -- press space to continue", Const.FONT_SIZE);
    }

    @Override
    public boolean shouldStop() {
        return true;
    }
}
