package GameObjects.GameShapes.Attribute;

import ConstsAndMethods.Const;
import GameObjects.Environment.Game.GameLevel;
import GameObjects.GameShapes.Player.Ball;
import Interfaces.Collidable;
import Interfaces.Hit.HitListener;
import Interfaces.Hit.HitNotifier;
import Interfaces.Sprite;
import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Block, implements collidable and sprite option.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private final Rectangle rectangle;
    private final List<HitListener> hitListeners;

    /**
     * Instantiates a new Block.
     *
     * @param rectangle the rectangle received
     */
    public Block(Rectangle rectangle) {
        this.rectangle = rectangle;
        this.hitListeners = new ArrayList<>();
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        double[] distances = returnDistances(collisionPoint);
        this.notifyHit(hitter);
        return changedVelocity(distances, currentVelocity);
    }

    @Override
    public void drawOn(DrawSurface d) {
        this.rectangle.drawOn(d);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.getCollections().addCollidable(this);
        g.getCollections().addSprite(this);
        addHitListener(g.getListeners().getBlockRemover());
        g.getListeners().remainingBlocks().increase(Const.ONE);
    }

    /**
     * Removes the block from the game.
     *
     * @param g the game object
     */
    public void removeFromGame(GameLevel g) {
        g.getCollections().removeCollidable(this);
        g.getCollections().removeSprite(this);
        g.levelInformation().blocks().remove(this);
        removeHitListener(g.getListeners().getBlockRemover());
        g.getListeners().remainingBlocks().decrease(Const.ONE);
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

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
