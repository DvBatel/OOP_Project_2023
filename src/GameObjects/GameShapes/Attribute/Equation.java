package GameObjects.GameShapes.Attribute;

import ConstsAndMethods.Const;
import ConstsAndMethods.PConst;

/**
 * @author Batel Divinsky, ID: 211517263, Ass no.2
 * <p>
 * Class Equation. holds an object containing 2 doubles and the type of the
 * equation.
 * </p>
 */
public class Equation {

    private final double slope;
    private double bVal;
    private char type;

    /**
     * Method to calculate the equation values.
     *
     * @param line given line to return equation values
     */
    public Equation(Line line) {
        this.slope = slope(line);
        if (this.slope != Const.ZERO) {
            this.bVal = bVal(line);
            this.type = PConst.NEUTRAL;
        }
    }

    /**
     * @return the slope
     */
    public double getSlope() {
        return this.slope;
    }

    /**
     * @return the bVal
     */
    public double bVal() {
        return this.bVal;
    }

    /**
     * @return the type of the equation.
     */
    public char type() {
        return this.type;
    }

    /**
     * Method to calculate the slope of the line. If the line is vertical or
     * horizontal, set the equation type to X or Y (axis) and set the bVal to
     * the value.
     *
     * @param line given line.
     * @return slope value according to the line values.
     */
    private double slope(Line line) {
        if (line.start().getX() == line.end().getX()) {
            this.type = PConst.X_AXIS;
            this.bVal = line.start().getX();
            return Const.ZERO;
        } else if (line.start().getY() == line.end().getY()) {
            this.type = PConst.Y_AXIS;
            this.bVal = line.start().getY();
            return Const.ZERO;
        }
        this.type = PConst.NEUTRAL;
        return (line.start().getY() - line.end().getY())
                / (line.start().getX() - line.end().getX());
    }

    /**
     * Method to calculate the b value of the equation.
     *
     * @param line the line given
     * @return the b value
     */
    private double bVal(Line line) {
        return line.start().getY() - (this.slope * line.start().getX());
    }

    /**
     * @param other the second equation in relation to the current one
     * @param a     the current equation type in question
     * @param b     the other equation type in question
     * @return true if current given type
     */
    public boolean eqType(Equation other, char a, char b) {
        return this.type == a && other.type() == b;
    }

    /**
     * @return true if the equation is of x kind
     */
    public boolean isXType() {
        return this.type() == PConst.X_AXIS;
    }

    /**
     * @return true if the equation is of y kind
     */
    public boolean isYType() {
        return this.type() == PConst.X_AXIS;
    }

    /**
     * @param other second equation
     * @return the x value of the intersection
     */
    public double calcXVal(Equation other) {
        return (this.bVal() - other.bVal()) / (other.getSlope() - this.getSlope());
    }

    /**
     * @param other second equation
     * @param xIntersect the x value of the intersection
     * @return the y value of the intersection
     */
    public double calcYVal(Equation other, double xIntersect) {
        return other.getSlope() * xIntersect + other.bVal();
    }

    /**
     * @return string containing the values of the velocity settings.
     */
    public String toString() {
        return "slope: " + this.slope + ", bVal: " + this.bVal;
    }
}
