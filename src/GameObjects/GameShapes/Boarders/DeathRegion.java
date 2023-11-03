package GameObjects.GameShapes.Boarders;

import ConstsAndMethods.Const;
import GameObjects.GameShapes.Player.Ball;
import GameObjects.Environment.Game.GameLevel;
import GameObjects.GameShapes.Attribute.Point;
import GameObjects.GameShapes.Attribute.Rectangle;
import GameObjects.GameShapes.Attribute.Velocity;
import GameObjects.GameShapes.Attribute.Block;
import Interfaces.Collidable;
import Interfaces.Hit.HitListener;
import Interfaces.Hit.HitNotifier;
import Interfaces.Sprite;
import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * Class DeathRegion, holds the properties of the lower block in the game to
 * symbolize the floor has been hit by a ball.
 */
public class DeathRegion implements Collidable, Sprite, HitNotifier {

    private final Block block;
    private final List<HitListener> hitListeners;

    /**
     * Instantiates a new Boarder.
     *
     * @param block the rectangle received
     */
    public DeathRegion(Block block) {
        this.block = block;
        this.hitListeners = new ArrayList<>();
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.block.getCollisionRectangle();
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        this.notifyHit(hitter);
        return currentVelocity;
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
        d.setColor(getCollisionRectangle().getColor());
        d.fillRectangle((int) getCollisionRectangle().getUpperLeft().getX(),
                (int) getCollisionRectangle().getUpperLeft().getY(),
                (int) getCollisionRectangle().getWidth(),
                (int) getCollisionRectangle().getHeight());
    }

    /**
     * Draw the perimeter of the block.
     *
     * @param d surface object
     */
    private void drawPerimeter(DrawSurface d) {
        d.setColor(java.awt.Color.BLACK);
        d.drawRectangle((int) getCollisionRectangle().getUpperLeft().getX(),
                (int) getCollisionRectangle().getUpperLeft().getY(),
                (int) getCollisionRectangle().getWidth(),
                (int) getCollisionRectangle().getHeight());
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.getCollections().addCollidable(this);
        g.getCollections().addSprite(this);
    }

    /**
     * Method to add how many balls are in a level.
     *
     * @param g the game object
     */
    public void addHowManyBalls(GameLevel g) {
        for (int i = 0; i < g.getListeners().remainingBalls().getValue(); i++) {
            addHitListener(g.getListeners().getBallRemover());
        }
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
        this.hitListeners.get(Const.ZERO).hitEvent(this.block, hitter);
        this.hitListeners.remove(this.hitListeners.get(Const.ZERO));
    }
}