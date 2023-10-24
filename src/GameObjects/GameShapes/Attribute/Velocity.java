package GameObjects.GameShapes.Attribute;

import ConstsAndMethods.Const;
import ConstsAndMethods.Methods;

/**
 * Class ObjectsForGame.PublicGeometry.Velocity. holds an object containing two double values (for dx and
 * dy change), and it's correlated methods.
 */
public class Velocity {

    private double dx;
    private double dy;

    /**
     * Instantiates a new ObjectsForGame.PublicGeometry.Velocity.
     *
     * @param dx dx value
     * @param dy dy value
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Instantiates a new ObjectsForGame.PublicGeometry.Velocity.
     *
     * @param v the given velocity
     */
    public Velocity(Velocity v) {
        this.dx = v.getDx();
        this.dy = v.getDy();
    }

    /**
     * @return the dx value
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * @return the dy value
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Method to change the x-axis by multiplying the dx value by -1.
     */
    public void updateDx() {
        this.dx *= Const.MINUS_ONE;
    }

    /**
     * Method to change the y-axis by multiplying the dy value by -1.
     */
    public void updateDy() {
        this.dy *= Const.MINUS_ONE;
    }

    /**
     * Method that takes a point with position (x,y) and returns a new point
     * with position (x + dx, y + dy).
     *
     * @param p the point
     * @return new point with added dx and dy values
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * Method that takes a point with position (x,y) and returns a new point
     * with position (x + dx, y + dy).
     *
     * @param x x value
     * @param y y value
     * @return new point with added dx and dy values
     */
    public Point applyToPoint(double x, double y) {
        return new Point(x + this.dx, y + this.dy);
    }

    /**
     * Method to calculate the dx and dy changes using a velocity equation:
     * velocity = speed * angle.
     * The angle is calculated according to its dx and dy coordinates.
     * Because the y-axis is inverted, multiply the dy value by -1
     *
     * @param angle the angle value
     * @param speed the speed value
     * @return new velocity param
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = speed * Methods.changeSign(Math.cos(Math.toRadians(angle)));
        return new Velocity(dx, dy);
    }

    /**
     * @return string containing the values of the velocity set.
     */
    public String toString() {
        return "dx: " + this.dx + ", dy: " + this.dy;
    }
}