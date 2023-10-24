package ConstsAndMethods;

import biuoop.Sleeper;

/**
 * Class Timing. manages the timing calculations for the loop.
 */
public class Timing {

    private static final Sleeper SLEEPER = new Sleeper();

    /**
     * Check if the difference is greater than zero and sleep if true.
     *
     * @param startTime           the start time
     * @param millisecondPerFrame the milliseconds per frame
     */
    public static void calcTiming(long startTime, int millisecondPerFrame) {
        if (isNegligibleTime(startTime, millisecondPerFrame)) {
            sleepFor(milliSecLeftToSleep(startTime, millisecondPerFrame));
        }
    }

    /**
     * calculate the used time.
     *
     * @param startTime the start time
     * @return the difference between start and the current time
     */
    public static long usedTime(long startTime) {
        return currentTimeMillis() - startTime;
    }

    /**
     * calculates the milliseconds left to sleep.
     *
     * @param startTime           the start time
     * @param millisecondPerFrame the millis per frame
     * @return the difference between the milliseconds per frame and the
     * used time.
     */
    public static long milliSecLeftToSleep(
            long startTime, int millisecondPerFrame) {
        return millisecondPerFrame - usedTime(startTime);
    }

    /**
     * Activate the sleeper object, sleep for how many milli Seconds
     * Left To Sleep.
     *
     * @param milliSecondLeftToSleep the milliseconds left to sleep
     */
    public static void sleepFor(long milliSecondLeftToSleep) {
        SLEEPER.sleepFor(milliSecondLeftToSleep);
    }

    /**
     * Query method to check if the difference is negligible.
     *
     * @param startTime              the start time
     * @param millisecondPerFrame the milliseconds per frame
     * @return the true if the difference is greater than zero
     */
    public static boolean isNegligibleTime(
            long startTime, int millisecondPerFrame) {
        return millisecondPerFrame - usedTime(startTime) > Const.ZERO;
    }

    /**
     * Command method to return the current time milliseconds.
     *
     * @return the current time milliseconds
     */
    public static long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * Command method to return the Frames per second.
     *
     * @return the Frames per second
     */
    public static int framesPerSecond() {
        return Const.SIXTY_SECONDS;
    }

    /**
     * Calculate and return the milliseconds per frame.
     *
     * @return the milliseconds per frame
     */
    public static int millisecondsPerFrame() {
        return Const.THOUSAND / framesPerSecond();
    }
}
