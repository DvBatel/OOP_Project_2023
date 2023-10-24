package Interfaces;

import biuoop.DrawSurface;

/**
 * The interface Animation.
 */
public interface Animation {

    /**
     * Do one frame for the game.
     *
     * @param d the d
     */
    void doOneFrame(DrawSurface d);

    /**
     * Do one frame.
     *
     * @return true if we should stop the game
     */
    boolean shouldStop();
}
