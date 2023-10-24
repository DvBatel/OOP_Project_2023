package ConstsAndMethods;

import java.util.Random;

/**
 * Class Methods. holds all the static methods needed for several tasks.
 */
public class Methods {

    private static final Random RANDOM = new Random();

    /**
     * Generate color for a ball using r.g.b. values
     *
     * @return the color generated
     */
    public static java.awt.Color generateColor() {
        float r = RANDOM.nextFloat();
        float g = RANDOM.nextFloat();
        float b = RANDOM.nextFloat();

        return new java.awt.Color(r, g, b);
    }

    /**
     * @param number number given to change sign
     * @return negative sign of the number
     */
    public static int changeSign(int number) {
        return Const.MINUS_ONE * number;
    }

    /**
     * @param number number given to change sign
     * @return negative sign of the number
     */
    public static double changeSign(double number) {
        return Const.MINUS_ONE * number;
    }
}
