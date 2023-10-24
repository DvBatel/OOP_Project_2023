package GameObjects.Animation.Levels;

import ConstsAndMethods.Angle;
import ConstsAndMethods.Const;
import ConstsAndMethods.Player;
import GameObjects.Environment.Game.GameLevel;
import GameObjects.GameShapes.Boarders.Background.Background;
import GameObjects.GameShapes.Attribute.Block;
import GameObjects.GameShapes.Attribute.Point;
import GameObjects.GameShapes.Attribute.Rectangle;
import GameObjects.GameShapes.Attribute.Velocity;
import Interfaces.LevelInformation;
import Interfaces.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * The class Level one. Holds the first level details.
 */
public class LevelOne implements LevelInformation, Sprite {

    //point indexes to generate the blocks the ball is supposed to hit
    private static final double BLOCK_WIDTH = 40;
    private static final double BLOCK_HEIGHT = 40;

    private static final int PADDLE_SPEED = 7;
    private static final int BALL_SPEED = 8;
    private static final int B_START_X = 395;
    private static final int B_START_Y = 540;
    private static final int P_START_X = 320;
    private static final int P_START_Y = 560;
    private static final int PADDLE_WIDTH = 150;
    private static final int PADDLE_HEIGHT = 20;

    private final List<Velocity> initialBallVelocities = new ArrayList<>();
    private final List<Block> blocks = new ArrayList<>();
    private final int paddleSpeed;
    private final int paddleWidth;
    private final Sprite screenBackground;
    private final int paddleHeight;

    /**
     * Instantiates a new Level one.
     */
    public LevelOne() {
        this.paddleSpeed = PADDLE_SPEED;
        this.paddleWidth = PADDLE_WIDTH;
        this.paddleHeight = PADDLE_HEIGHT;
        setVelocities();
        setBlocks();
        this.screenBackground = background();
    }

    private void setVelocities() {
        this.initialBallVelocities.add(Velocity.fromAngleAndSpeed(
                Player.DEFAULT_ANGLE, BALL_SPEED));
    }

    private void setBlocks() {
        this.blocks.add(new Block(newRectBlock(Const.SINGLE_BLOCK_X,
                Const.HUNDRED_TWENTY_FIVE, Color.RED)));
    }

    private Background background() {
        return new Background(new Rectangle(
                new Point(), Const.WIDTH, Const.HEIGHT * Const.TWO,
                Color.BLACK), this);
    }

    /**
     * Return new rectangle for the block shape.
     *
     * @param i     the row index (or y value)
     * @param j     the block start x value
     * @param color the color given to the block
     * @return new rectangle representing the block
     */
    public static Rectangle newRectBlock(int i, int j, Color color) {
        return new Rectangle(newBlockPoint(i, j),
                BLOCK_WIDTH, BLOCK_HEIGHT, color);
    }

    /**
     * Return new start block point.
     *
     * @param i the row index (or y value)
     * @param j the block start x value
     * @return new start point of the block
     */
    public static Point newBlockPoint(int i, int j) {
        return new Point(i, j);
    }

    @Override
    public int numberOfBalls() {
        return this.initialBallVelocities.size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return this.initialBallVelocities;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    public int paddleHeight() {
        return this.paddleHeight;
    }

    @Override
    public Point paddleStartPoint() {
        return new Point(P_START_X, P_START_Y);
    }

    @Override
    public Point ballStartPoint() {
        return new Point(B_START_X, B_START_Y);
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return this.screenBackground;
    }

    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks.size();
    }

    @Override
    public double ballSpeed() {
        return BALL_SPEED;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLUE);
        for (int i = 0; i <= Const.HUNDRED_FIFTY; i += Const.FIFTY) {
            d.drawCircle(Const.BULLSEYE_X, Const.HUNDRED_FIFTY,
                    Const.HUNDRED_FIFTY - i);
        }
        d.drawLine(Const.BULLSEYE_X, Const.ZERO, Const.BULLSEYE_X,
                Const.HUNDRED_FIFTEEN);
        d.drawLine(Const.BULLSEYE_X, Const.HUNDRED_SEVENTY_FIVE,
                Const.BULLSEYE_X, Const.THREE_TWENTY_FIVE);
        d.drawLine(Const.TWO_HUNDRED, Const.HUNDRED_FORTY_FIVE,
                Const.THREE_SIXTY_FIVE, Const.HUNDRED_FORTY_FIVE);
        d.drawLine(Const.FOUR_TWENTY_FIVE, Const.HUNDRED_FORTY_FIVE,
                Const.FIVE_EIGHTY, Const.HUNDRED_FORTY_FIVE);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
    }
}