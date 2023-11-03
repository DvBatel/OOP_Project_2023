package GameObjects.Environment;
import ConstsAndMethods.Const;
import GameObjects.Animation.KeyPressStoppableAnimation;
import GameObjects.Animation.Screens.CountdownAnimation;
import GameObjects.Animation.Screens.GameOver;
import GameObjects.Animation.Screens.PauseScreen;
import GameObjects.Animation.Screens.YouWin;
import biuoop.KeyboardSensor;

/**
 * The type Animations.
 */
public class Animations {

    /**
     * Count down countdown animation.
     *
     * @param gameCollections the game collections
     * @return the countdown animation
     */
    public static CountdownAnimation countDown(
            GameCollections gameCollections) {
        return new CountdownAnimation(Const.TWO_THOUSAND,
                Const.THREE, gameCollections.getSprites());
    }

    /**
     * Pause key press stoppable animation.
     *
     * @param keyboard the keyboard
     * @return the key press stoppable animation
     */
    public static KeyPressStoppableAnimation pause(KeyboardSensor keyboard) {
        return new KeyPressStoppableAnimation(keyboard,
                KeyboardSensor.SPACE_KEY, new PauseScreen());
    }

    /**
     * Win screen key press stoppable animation.
     *
     * @param keyboard the keyboard
     * @param score    the score
     * @return the key press stoppable animation
     */
    public static KeyPressStoppableAnimation winScreen(
            KeyboardSensor keyboard, Score score) {
        return new KeyPressStoppableAnimation(keyboard,
                KeyboardSensor.SPACE_KEY, new YouWin(score));
    }

    /**
     * Lose screen key press stoppable animation.
     *
     * @param keyboard the keyboard
     * @param score    the score
     * @return the key press stoppable animation
     */
    public static KeyPressStoppableAnimation loseScreen(
            KeyboardSensor keyboard, Score score) {
        return new KeyPressStoppableAnimation(keyboard,
                KeyboardSensor.SPACE_KEY, new GameOver(score));
    }
}
