package GameObjects.GameShapes.Player;

import ConstsAndMethods.Const;
import GameObjects.Collections.CollisionInfo;
import GameObjects.Collections.GameEnvironment;
import GameObjects.Environment.Game.GameLevel;
import GameObjects.GameShapes.Attribute.Velocity;
import GameObjects.GameShapes.Attribute.Line;
import GameObjects.GameShapes.Attribute.Point;
import Interfaces.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Class ObjectsForGame.Ball.
 * <p> Class ObjectsForGame.Ball. holds an object containing
 * center point, radius integer, awt color, velocity point and the game
 * collidable objects, and it's correlated methods.
 * </p>
 */
public class Ball implements Sprite {

    private Point center;
    private final int radius;
    private final Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;

    /**
     * Instantiates a new ball.
     *
     * @param center center value
     * @param r      the radius
     * @param color  the color of the ball
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(Const.ZERO, Const.ZERO);
    }

    /**
     * Instantiates a new ball.
     *
     * @param center          center value
     * @param r               the radius
     * @param color           the color of the ball
     * @param gameEnvironment environment object given by the game scope
     */
    public Ball(Point center, int r,
                Color color, GameEnvironment gameEnvironment) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(Const.ZERO, Const.ZERO);
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Instantiates a new ball using 2 values as center point.
     *
     * @param x     the x value of the ball center
     * @param y     the y value of the ball center
     * @param r     the radius
     * @param color the color
     */
    public Ball(double x, double y, int r, Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(Const.ZERO, Const.ZERO);
    }

    /**
     * @return the x value of the center
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * @return the y value of the center
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * @return the radius value of the ball
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * @return the color value of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * @return a collision info on the closest collision point.
     */
    public CollisionInfo getClosestCollision() {
        return this.gameEnvironment.getClosestCollision(trajectory());
    }

    @Override
    public void drawOn(DrawSurface d) {
        drawFilling(d);
        drawPerimeter(d);
    }

    /**
     * Draw the filling of the circle.
     *
     * @param d surface object
     */
    private void drawFilling(DrawSurface d) {
        d.setColor(getColor());
        d.fillCircle(getX(), getY(), getSize());
    }

    /**
     * Draw the perimeter of the circle.
     *
     * @param d surface object
     */
    private void drawPerimeter(DrawSurface d) {
        d.setColor(java.awt.Color.BLACK);
        d.drawCircle(getX(), getY(), getSize());
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    @Override
    public void addToGame(GameLevel g) {
        g.getCollections().addSprite(this);
    }

    /**
     * Removes the ball from the game.
     *
     * @param g the game object
     */
    public void removeFromGame(GameLevel g) {
        g.getCollections().removeSprite(this);
        g.getListeners().remainingBalls().decrease(Const.ONE);
    }

    /**
     * Sets velocity.
     *
     * @param v the velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets velocity.
     *
     * @param dx the dx value of the velocity
     * @param dy the dy value of the velocity
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * @return the velocity of the current ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Method to change the center spot on screen using the velocity
     * coordinates.
     * <p>
     * Current process: check if the ball is about to cross one of the
     * blocks in the game environment, change center close enough to the
     * collision, then call methods to change accordingly. else, apply
     * velocity as usual.
     * </p>
     */
    public void moveOneStep() {
        if (isColliding()) {
            changePointAndVelocity();
            return;
        }
        this.center = newCenter();
    }

    /**
     * @return true if there is a collision with the block
     */
    private boolean isColliding() {
        return getClosestCollision() != null;
    }

    /**
     * Change velocity after hit of the object.
     *
     * @param c collision info about the collision point
     * @return new velocity after the object had changed the given velocity
     */
    private Velocity changeVelocityAfterHit(CollisionInfo c) {
        return c.collisionObject().hit(this, c.collisionPoint(),
                this.velocity);
    }

    /**
     * @return new line containing the trajectory, which is how the ball
     * will move without any obstacles.
     */
    private Line trajectory() {
        return new Line(this.center, this.velocity.applyToPoint(this.center));
    }

    /**
     * @return new center point after the center had been applied its velocity.
     */
    private Point newCenter() {
        return this.getVelocity().applyToPoint(this.center);
    }

    /**
     * return a point close to collision point.
     *
     * @param collisionPoint the collision point
     * @return the closest point calculated
     */
    private Point pointCloseToCollision(Point collisionPoint) {
        return new Point(newValue(collisionPoint.getX(), this.center.getX()),
                newValue(collisionPoint.getY(), this.center.getY()));
    }

    /**
     * @param collision the collision point (x or y value)
     * @param centerVal the center point (x or y value)
     * @return the new value calculated
     */
    private double newValue(double collision, double centerVal) {
        if (collision < centerVal
                || isInRange(collision, centerVal)) {
            return collision + Const.ONE;
        }
        return collision - Const.ONE;
    }

    /**
     * @param one first value
     * @param two seconds value
     * @return the difference between the values
     */
    private double difference(double one, double two) {
        return Point.difference(one, two);
    }

    /**
     * @param one first value
     * @param two seconds value
     * @return true if the difference is smaller than the threshold
     */
    private boolean isInRange(double one, double two) {
        return Point.smallerThanThreshold(difference(one, two));
    }

    /**
     * @return closest new point for the new center.
     */
    private Point returnClosePoint() {
        return pointCloseToCollision(getClosestCollision().collisionPoint());
    }

    /**
     * Method to change the center and velocity to fit the collision info.
     */
    private void changePointAndVelocity() {
        CollisionInfo c = getClosestCollision();
        this.center = returnClosePoint();
        this.velocity = changeVelocityAfterHit(c);
        this.center = newCenter();
    }

    /**
     * @return string containing the values of the ball settings.
     */
    public String toString() {
        return "center: " + this.center.toString()
                + ", radius: " + this.radius + ",\n color: " + this.color
                + ", velocity: (" + this.velocity.toString() + ")";
    }
}