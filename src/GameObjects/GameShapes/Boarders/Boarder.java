package GameObjects.GameShapes.Boarders;

import ConstsAndMethods.Const;
import GameObjects.GameShapes.Player.Ball;
import GameObjects.Environment.Game.GameLevel;
import GameObjects.GameShapes.Attribute.Velocity;
import GameObjects.GameShapes.Attribute.Point;
import GameObjects.GameShapes.Attribute.Rectangle;
import Interfaces.Collidable;
import Interfaces.Sprite;
import biuoop.DrawSurface;

/**
 * Class Boarder, implements collidable and sprite option.
 */
public class Boarder implements Collidable, Sprite {

    private final Rectangle rectangle;

    /**
     * Instantiates a new Boarder.
     *
     * @param rectangle the rectangle received
     */
    public Boarder(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        double[] distances = returnDistances(collisionPoint);
        return changedVelocity(distances, currentVelocity);
    }

    @Override
    public void drawOn(DrawSurface d) {
        drawFilling(d);
        drawPerimeter(d);
    }

    /**
     * Draw the filling of the block.
     *
     * @param d surface object
     */
    private void drawFilling(DrawSurface d) {
        d.setColor(this.rectangle.getColor());
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
    }

    /**
     * Draw the perimeter of the block.
     *
     * @param d surface object
     */
    private void drawPerimeter(DrawSurface d) {
        d.setColor(java.awt.Color.BLACK);
        d.drawRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.getCollections().addCollidable(this);
        g.getCollections().addSprite(this);
    }

    private double[] returnDistances(Point collisionPoint) {
        return this.rectangle.returnDistances(collisionPoint);
    }

    /**
     * Change the current velocity given the different distances.
     *
     * @param distances the distances calculated
     * @param velocity  the current velocity of the ball
     * @return the velocity
     */
    private Velocity changedVelocity(double[] distances, Velocity velocity) {
        updateDx(distances, velocity);
        updateDy(distances, velocity);
        return velocity;
    }

    /**
     * Change the current velocity if the collision point is from below or
     * above.
     *
     * @param distances the distances calculated
     * @param velocity  the current velocity of the ball
     */
    public void updateDx(double[] distances, Velocity velocity) {
        if (isInRange(distances[Const.ZERO])
                || isInRange(distances[Const.ONE])) {
            velocity.updateDx();
        }
    }

    /**
     * Change the current velocity if the collision point is from one right or
     * left.
     *
     * @param distances the distances calculated
     * @param velocity  the current velocity of the ball
     */
    public void updateDy(double[] distances, Velocity velocity) {
        if (isInRange(distances[Const.TWO])
                || isInRange(distances[Const.THREE])) {
            velocity.updateDy();
        }
    }

    /**
     * @param givenDistance given distance from the array
     * @return true if the distance is smaller than the threshold
     */
    private boolean isInRange(double givenDistance) {
        return Point.smallerThanThreshold(givenDistance);
    }
}

