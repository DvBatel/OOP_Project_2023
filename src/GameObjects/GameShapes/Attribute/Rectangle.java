package GameObjects.GameShapes.Attribute;

import ConstsAndMethods.Const;
import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

/**
 * Class Rectangle. holds variables for the upper left start of the
 * rectangle, as well as the width and the height and color.
 */
public class Rectangle {

    private final Point upperLeft;
    private final double width;
    private final double height;
    private final Line[] lines;
    private java.awt.Color color;

    /**
     * Instantiates a new Rectangle.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.lines = getRectangleLines();
        this.color = Const.COLOR_PALETTE[Const.ZERO];
    }

    /**
     * Instantiates a new Rectangle.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     * @param color     the color
     */
    public Rectangle(Point upperLeft, double width,
                     double height, Color color) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.color = color;
        this.lines = getRectangleLines();
    }

    /**
     * Calculate intersection points with the rectangle.
     *
     * @param line the line
     * @return the java . util . list
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> pointList = new ArrayList<>();
        for (int i = 0; i < Const.FOUR; i++) {
            if (lines[i].isIntersecting(line)
                    && lines[i].intersectionWith(line) != null) {
                pointList.add(lines[i].intersectionWith(line));
            }
        }
        return pointList;
    }

    /**
     * Return distances of each line of the rectangle with the collision
     * point.
     *
     * @param collisionPoint the collision point
     * @return the distances calculated
     */
    public double[] returnDistances(Point collisionPoint) {
        return new double[]{Math.abs(collisionPoint.getX()
                - this.getUpperLeft().getX()),
                Math.abs(collisionPoint.getX()
                        - (this.getUpperLeft().getX()
                        + this.getWidth())),
                Math.abs(collisionPoint.getY()
                        - this.getUpperLeft().getY()),
                Math.abs(collisionPoint.getY()
                        - (this.getUpperLeft().getY()
                        + this.getHeight()))};
    }

    /**
     * Get rectangle lines line [ ].
     *
     * @return the line [ ]
     */
    private Line[] getRectangleLines() {
        Point[] points = getRectanglePoints();
        return new Line[]{new Line(points[Const.ZERO], points[Const.ONE]),
                new Line(points[Const.ZERO], points[Const.TWO]),
                new Line(points[Const.ONE], points[Const.THREE]),
                new Line(points[Const.TWO], points[Const.THREE])};
    }

    /**
     * Get rectangle points point [ ].
     *
     * @return the point [ ]
     */
    private Point[] getRectanglePoints() {
        return new Point[]{this.upperLeft, getUpperRight(),
                getUnderLeft(), getUnderRight()};
    }

    /**
     * @return upper right rectangle point.
     */
    public Point getUpperRight() {
        return new Point(this.upperLeft.getX() + this.width,
                this.upperLeft.getY());

    }

    /**
     * @return under left rectangle point.
     */
    public Point getUnderLeft() {
        return new Point(this.upperLeft.getX(),
                this.upperLeft.getY() + this.height);
    }

    /**
     * @return under right rectangle point.
     */
    public Point getUnderRight() {
        return new Point(this.upperLeft.getX() + getWidth(),
                this.upperLeft.getY() + this.height);
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Gets color.
     *
     * @return the color value of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Draw the rectangle.
     *
     * @param d surface object
     */
    public void drawOn(DrawSurface d) {
        drawFilling(d);
        drawPerimeter(d);
    }

    private void drawFilling(DrawSurface d) {
        d.setColor(this.getColor());
        d.fillRectangle((int) this.getUpperLeft().getX(),
                (int) this.getUpperLeft().getY(),
                (int) this.getWidth(),
                (int) this.getHeight());
    }

    private void drawPerimeter(DrawSurface d) {
        d.setColor(java.awt.Color.BLACK);
        d.drawRectangle((int) this.getUpperLeft().getX(),
                (int) this.getUpperLeft().getY(),
                (int) this.getWidth(),
                (int) this.getHeight());
    }

    /**
     * Sets color to default setting.
     */
    public void deathRegionColor() {
        this.color = Const.COLOR_PALETTE[Const.SIX];
    }

    /**
     * Gets upper left.
     *
     * @return the upper left
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

}