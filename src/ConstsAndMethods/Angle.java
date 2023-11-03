package ConstsAndMethods;

/**
 * Method that stores all the degrees needed for the ball.
 */
public class Angle {

    //constants for the change in ball according to where it hit the paddle.
    public static final int SECTION_ONE = 300;
    public static final int SECTION_TWO = 330;
    public static final int SECTION_THREE = 360;
    public static final int SECTION_FOUR = 30;
    public static final int SECTION_FIVE = 60;

    /**
     * @return an array containing the degrees.
     */
    public static double[] degrees() {
        return new double[]{SECTION_ONE, SECTION_TWO,
                SECTION_THREE, SECTION_FOUR, SECTION_FIVE};
    }
}
