package GameObjects.GameShapes.Attribute;
import ConstsAndMethods.Const;
import ConstsAndMethods.PConst;

/**
 * Class ObjectsForGame.PublicGeometry.Point. holds an object containing 2 integers (x and y values), and
 * it's correlated methods.
 */
public class Point {

    private final double x;
    private final double y;

    /**
     * Instantiates a new ObjectsForGame.PublicGeometry.Point.
     *
     * @param x x value
     * @param y y value
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Instantiates a new ObjectsForGame.PublicGeometry.Point with default values.
     */
    public Point() {
        this.x = Const.ZERO;
        this.y = Const.ZERO;
    }

    /**
     * returns the distance between two points.
     *
     * @param other the other dot in relation to the current object.
     * @return the distance calculated.
     */
    public double distance(Point other) {
        return Math.sqrt((Math.pow(this.x - other.getX(), Const.TWO))
                + (Math.pow(this.y - other.getY(), Const.TWO)));
    }

    /**
     * Returns the middle point between two points.
     *
     * @param other end point in relation to the current object.
     * @return the middle point.
     */
    public Point middle(Point other) {
        return new Point((this.x + other.getX()) / PConst.AVERAGE_VALUE,
                        (this.y + other.getY()) / PConst.AVERAGE_VALUE);
    }

    /**
     * Method to return if the points are equal, essentially it calculates
     * the difference between two dots and checks if said difference is
     * smaller than a tiny number (aka the threshold).
     *
     * @param other the other dot in relation to the current object.
     * @return true if the difference is smaller than the threshold
     */
    public boolean equals(Point other) {
        return smallerThanThreshold(difference(this.x, other.getX()))
                && smallerThanThreshold(difference(this.y, other.getY()));
    }

    /**
     * @return the x value of the point
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return the y value of the point
     */
    public double getY() {
        return this.y;
    }

    /**
     * @param a first value
     * @param b second value
     * @return the difference between the two in absolute value
     */
    public static double difference(double a, double b) {
        return Math.abs(a - b);
    }

    /**
     * @param difference the difference given
     * @return true if the difference is smaller than the threshold
     */
    public static boolean smallerThanThreshold(double difference) {
        return difference <= PConst.THRESHOLD;
    }

    /**
     * @return string containing the values of the point set.
     */
    public String toString() {
        return "(x: " + this.x + ", y: " + this.y + ")";
    }
}