package GameObjects.Environment.Game;

import GameObjects.Animation.AnimationRunner;
import GameObjects.Animation.Screens.GameOver;
import GameObjects.Animation.KeyPressStoppableAnimation;
import GameObjects.Animation.Screens.YouWin;
import GameObjects.Environment.Score;
import Interfaces.Animation;
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
        Animation winner = new KeyPressStoppableAnimation(keyboardSensor,
                KeyboardSensor.SPACE_KEY, new YouWin(score));
        Animation loser = new KeyPressStoppableAnimation(keyboardSensor,
                KeyboardSensor.SPACE_KEY, new GameOver(score));
        if (winParam) {
            animationRunner.run(winner);
        } else {
            animationRunner.run(loser);
        }
    }
}
