package GameObjects.Animation;

import ConstsAndMethods.Timing;
import Interfaces.Animation;
import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * The type Animation runner.
 */
public class AnimationRunner {

    private final GUI gui;

    /**
     * Instantiates a new Animation runner.
     *
     * @param gui the gui
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
    }

    /**
     * Run the given animation.
     *
     * @param animation the animation
     */
    public void run(Animation animation) {
        while (!animation.shouldStop()) {
            long startTime = Timing.currentTimeMillis();
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            Timing.calcTiming(startTime, Timing.millisecondsPerFrame());
        }
    }
}