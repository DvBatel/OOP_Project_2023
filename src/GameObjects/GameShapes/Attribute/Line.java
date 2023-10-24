package GameObjects.GameShapes.Attribute;

import ConstsAndMethods.Const;
import ConstsAndMethods.PConst;
import GameObjects.Collections.CollisionInfo;
import java.util.List;

/**
 * Class ObjectsForGame.PublicGeometry.Line.
 * Holds an object containing a start and end point, and it's
 * correlated methods.
 */
public class Line {

    private final Point start;
    private final Point end;

    /**
     * Instantiates a new line.
     *
     * @param start start point given
     * @param end   end point given
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Instantiates a new line using x and y values.
     *
     * @param x1 first x index given
     * @param y1 first y index given
     * @param x2 second x index given
     * @param y2 second y index given
     */
    public Line(double x1, double y1, double x2, double y2) {
        start = new Point(x1, y1);
        end = new Point(x2, y2);
    }

    /**
     * @return the length of the line.
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * @return the middle point of the line.
     */
    public Point middle() {
        return this.start.middle(this.end);
    }

    /**
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * Method to check if there is an intersection point between two lines.
     *
     * @param other the other line in the relation to the current line object.
     * @return true if the lines are intersecting.
     */
    public boolean isIntersecting(Line other) {
        if (equals(other)) {
            return true;
        } else if (slopeBCollide(other)
                && isColliding(other) && other.isColliding(this)) {
            return true;
        }
        return pointExits(intersectionWith(other));
    }

    /**
     * Returns an intersection point between two lines. if the lines are
     * colliding in some type of way or don't touch at all, return null.
     *
     * @param other the other line in relation to the current line object.
     * @return intersection point.
     */
    public Point intersectionWith(Line other) {
        if (this.equals(other)) {
            return null;
        } else if (start().equals(other.start())
                || start().equals(other.end())
                || end().equals(other.end())
                || end().equals(other.start())) {
            return ifOnlyPointIntersect(other);
        }
        return intersectionPoint(other);
    }

    /**
     * return line array containing lines with middle points in order to
     * check the distance.
     *
     * @param first  first point
     * @param second second point
     * @param other  the second line in relation to the current one
     * @return line arrays full of lines in order to test them
     */
    private Line[] getLines(Point first, Point second, Line other) {
        return new Line[]{new Line(start(), first),
                new Line(first, end()),
                new Line(other.start(), second),
                new Line(second, other.end())};
    }

    /**
     * Method to check if the lines intersect in a single point and not more.
     *
     * @param other the other line in relation to the current one
     * @return suspect of single intersection
     */
    private Point ifOnlyPointIntersect(Line other) {
        if (touchInOne(this.start, other.start())) {
            return ifNotAnywhereElse(getLines(other.end(),
                    this.end, other), this.start, other);
        } else if (touchInOne(this.start, other.end())) {
            return ifNotAnywhereElse(getLines(other.end(),
                    this.end, other), this.start, other);
        } else if (touchInOne(this.end, other.end())) {
            return ifNotAnywhereElse(getLines(other.end(),
                    this.start, other), this.end, other);
        } else if (touchInOne(this.end, other.start())) {
            return ifNotAnywhereElse(getLines(other.end(),
                    this.start, other), this.end, other);
        }
        return null;
    }

    /**
     * Method to check if the lines intersect in the second point as well as
     * the first checked.
     *
     * @param lines generated line for the test
     * @param point the suspect for the only collision point
     * @param other the other line in relation to the current one
     * @return the suspect point in case the lines intersect in one point
     */
    private Point ifNotAnywhereElse(Line[] lines, Point point, Line other) {
        if (isEitherRange(lines, other)) {
            return null;
        }
        return point;
    }

    /**
     * Method to check if either of the generated line range within the
     * other line.
     *
     * @param lines the generated lines
     * @param other the other line in relation to the current one
     * @return true if they range within, which means more than one collision
     */
    private boolean isEitherRange(Line[] lines, Line other) {
        return (isInRange(lines[Const.ZERO], lines[Const.ONE])
                || other.isInRange(lines[Const.TWO], lines[Const.THREE]));
    }

    /**
     * Method to check if both of the generated line range within the
     * other line.
     *
     * @param lines the generated lines
     * @param other the other line in relation to the current one
     * @return true if they range within, which means the point is within
     * the range.
     */
    private boolean isBothRange(Line[] lines, Line other) {
        return (isInRange(lines[Const.ZERO], lines[Const.ONE])
                && other.isInRange(lines[Const.TWO], lines[Const.THREE]));
    }

    /**
     * @param one first point
     * @param two second point
     * @return true if they are equal
     */
    private boolean touchInOne(Point one, Point two) {
        return one.equals(two);
    }

    /**
     * Method to calculate the intersection point. calls method to calculate
     * the intersection (if there is one), then check if the point is in range.
     *
     * @param other the other line in relation to the current one
     * @return an intersection point/ null
     */
    private Point intersectionPoint(Line other) {
        if (!pointExits(intersection(other))) {
            return null;
        }
        return afterCheck(other);
    }

    /**
     * Put the intersection point found and check if it aligns with both
     * lines ranges.
     *
     * @param other the other line in relation to the current line
     * @return the intersection point/ null
     */
    private Point afterCheck(Line other) {
        if (isBothRange(getLines(intersection(other),
                intersection(other), other), other)) {
            return intersection(other);
        }
        return null;
    }

    /**
     * @param susForIntersect suspect intersection
     * @return true if an intersection point was found
     */
    public static boolean pointExits(Point susForIntersect) {
        return susForIntersect != null;
    }

    /**
     * Method to return if the line are intersecting in more than one point.
     * <p>
     * Current process: set each "start" and "end" value of both lines
     * "between" the start and end point of the other line, then check if
     * the lengths between are equal to the whole line itself.
     * </p>
     *
     * @param other the other line in relation to the current line.
     * @return true if colliding in some way, false otherwise
     */
    private boolean isColliding(Line other) {
        Line[] lines = lines(other);
        return isInRange(lines[Const.ZERO], lines[Const.ONE])
                || isInRange(lines[Const.TWO], lines[Const.THREE]);
    }

    /**
     * Method to create and return an array of 4 new lines containing the
     * lines given.
     *
     * @param other the other line in relation to the current line.
     * @return array of lines.
     */
    private Line[] lines(Line other) {
        Line[] lines = new Line[Const.FOUR];
        lines[Const.ZERO] = new Line(this.start, other.start());
        lines[Const.ONE] = new Line(other.start(), this.end);
        lines[Const.TWO] = new Line(this.start, other.end());
        lines[Const.THREE] = new Line(other.end(), this.end);
        return lines;
    }

    /**
     * Method to calculate the intersection point according to both lines
     * coordinates.
     * <p>
     * Current process: Calculates the equation of the linear lines, then
     * check if they are parallel to X or Y axis. if not, calculate as usual
     * the intersection point (even if it's not between said lines).
     * </p>
     *
     * @param other the other line in relation to the current object
     * @return calculated intersection point
     */
    private Point intersection(Line other) {
        Equation one = new Equation(this);
        Equation two = new Equation(other);
        double xIntersect, yIntersect;

        //X and Y axis edge cases
        if (one.eqType(two, PConst.X_AXIS, PConst.Y_AXIS)) {
            return new Point(one.bVal(), two.bVal());
        } else if (one.eqType(two, PConst.Y_AXIS, PConst.X_AXIS)) {
            return new Point(two.bVal(), one.bVal());
        } else if (one.eqType(two, PConst.X_AXIS, PConst.X_AXIS)
                || one.eqType(two, PConst.Y_AXIS, PConst.Y_AXIS)) {
            return null;
        } else if (one.isXType()) {
            xIntersect = one.bVal();
            yIntersect = two.getSlope() * xIntersect + two.bVal();
            return new Point(xIntersect, yIntersect);
        } else if (one.isYType()) {
            yIntersect = one.bVal();
            xIntersect = (yIntersect - two.bVal()) / two.getSlope();
            return new Point(xIntersect, yIntersect);
        } else if (two.isXType()) {
            xIntersect = two.bVal();
            yIntersect = one.getSlope() * xIntersect + one.bVal();
            return new Point(xIntersect, yIntersect);
        } else if (two.isYType()) {
            yIntersect = two.bVal();
            xIntersect = (yIntersect - one.bVal()) / one.getSlope();
            return new Point(xIntersect, yIntersect);
        }

        return new Point(one.calcXVal(two),
                one.calcYVal(one, one.calcXVal(two)));
    }

    /**
     * Method to return if a point is found between two lines
     * <p>
     * Current process: the method receives two lines in relation to the
     * current line object. return true if the difference between the
     * current line and the sum of both lines received is smaller than the
     * threshold.
     * </p>
     *
     * @param lineOne first line
     * @param lineTwo second line
     * @return true if the line and the sum of lineOne+lineTwo are equal
     */
    private boolean isInRange(Line lineOne, Line lineTwo) {
        return Point.smallerThanThreshold(Point.difference(this.length(),
                (lineOne.length() + lineTwo.length())));
    }

    /**
     * Method to return if the current line is equal to another.
     * compares with equal by points
     *
     * @param other the other line in relation to the current object.
     * @return true if indeed equal
     */
    public boolean equals(Line other) {
        return (this.start.equals(other.start())
                && this.end.equals(other.end()))
                || (this.end.equals(other.start())
                && this.start.equals(other.end()));
    }

    /**
     * Method to check if the lines share the same equation.
     *
     * @param other the other line
     * @return true if the slope and the bVal are equal
     */
    private boolean slopeBCollide(Line other) {
        return new Equation(this).getSlope()
                == new Equation(other).getSlope()
                && new Equation(this).bVal()
                == new Equation(other).bVal();
    }

    /**
     * Closest intersection to start of line.
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     *
     * @param rect the rect
     * @return the point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> pointList = rect.intersectionPoints(this);
        if (pointList.isEmpty()) {
            return null;
        }
        return closestPoint(pointList);
    }

    /**
     * Method to return closest point.
     *
     * @param pointList the point list
     * @return the closest point to the start of the line
     */
    private Point closestPoint(List<Point> pointList) {
        Point closest = null;
        for (Point point : pointList) {
            if (closest == null) {
                closest = point;
            } else if (point.distance(this.start)
                    < closest.distance(this.start)) {
                closest = point;
            }
        }
        return closest;
    }

    /**
     * Method to return closest collision info point.
     *
     * @param collisionInfo the list containing all the collision points
     *                      and their collidable info.
     * @return the closest collision point to the start of the line
     */
    public CollisionInfo closestCollisionInfo(List<CollisionInfo> collisionInfo) {
        CollisionInfo closest = null;
        for (CollisionInfo point : collisionInfo) {
            if (isEmpty(closest)) {
                closest = point;
            } else if (isSmaller(point.collisionPoint(),
                    closest.collisionPoint())) {
                closest = point;
            }
        }
        return closest;
    }

    /**
     * Check if the collision info is empty.
     * @param point current collision point.
     * @return true if not null
     */
    private boolean isEmpty(CollisionInfo point) {
        return point == null;
    }

    /**
     * Check which one of the points is closer to the start.
     * @param point curren point in loop
     * @param closest current closest point
     * @return true if current point is smaller in distance than the current
     * closest.
     */
    private boolean isSmaller(Point point, Point closest) {
        return point.distance(start()) < closest.distance(start());
    }

    /**
     * @return string containing the values of the line set.
     */
    public String toString() {
        return "start value: (" + this.start.toString()
                + "), end value: (" + this.end.toString() + ")";
    }
}