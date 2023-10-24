package Interfaces;

import GameObjects.GameShapes.Attribute.Block;
import GameObjects.GameShapes.Attribute.Point;
import GameObjects.GameShapes.Attribute.Velocity;

import java.util.List;

/**
 * The interface Level information.
 */
public interface LevelInformation {

    /**
     * @return the number of balls in a level.
     */
    int numberOfBalls();

    /**
     * @return the initial velocity of each ball.
     *      *
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return the paddle speed.
     */
    int paddleSpeed();

    /**
     * @return the paddle width.
     */
    int paddleWidth();

    /**
     * @return the paddle height.
     */
    int paddleHeight();

    /**
     * @return the paddle start point
     */
    Point paddleStartPoint();

    /**
     * @return the ball start point
     */
    Point ballStartPoint();

    /**
     * @return the level name.
     */
    String levelName();

    /**
     * @return the sprite with the background of the level.
     */
    Sprite getBackground();

    /**
     * @return the list of blocks that make up this level, each block contains
     * its size, color and location.
     */
    List<Block> blocks();

    /**
     * @return the number of blocks to remove
     */
    int numberOfBlocksToRemove();

    /**
     * @return the balls speed.
     */
    double ballSpeed();
}
