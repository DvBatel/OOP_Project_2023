package GameObjects.Environment.Game;

import GameObjects.Animation.AnimationRunner;
import GameObjects.Environment.Animations;
import GameObjects.Environment.Score;
import biuoop.KeyboardSensor;

/**
 * The class End screen. holds the properties to the end screen.
 */
public class EndScreen {

    /**
     * End screen animation. Runs the current end screen according to given
     * parameters.
     *
     * @param winParam        the win boolean parameter
     * @param score           the score
     * @param animationRunner the animation runner
     * @param keyboardSensor  the keyboard sensor
     */
    public static void endScreen(boolean winParam, Score score,
                                 AnimationRunner animationRunner,
                                 KeyboardSensor keyboardSensor) {
        if (winParam) {
            animationRunner.run(Animations.winScreen(keyboardSensor, score));
        } else {
            animationRunner.run(Animations.loseScreen(keyboardSensor, score));
        }
    }
}
