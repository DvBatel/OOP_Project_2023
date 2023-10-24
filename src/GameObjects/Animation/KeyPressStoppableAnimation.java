package GameObjects.Animation;

import Interfaces.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The class Key press stoppable animation. Gives the screens the option to
 * exit the windows.
 */
public class KeyPressStoppableAnimation implements Animation {

    private final KeyboardSensor keyboard;
    private final String key;
    private final Animation animation;
    private boolean isAlreadyPressed;
    private boolean stop;

    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param sensor    the sensor object
     * @param key       the key object
     * @param animation the animation object
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key,
                                      Animation animation) {
        this.keyboard = sensor;
        this.key = key;
        this.isAlreadyPressed = true;
        this.animation = animation;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (this.keyboard.isPressed(this.key) && !this.isAlreadyPressed) {
            this.stop = true;
        } else if (!this.keyboard.isPressed(this.key)) {
            this.isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}