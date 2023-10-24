package GameObjects.Animation.Levels;

import ConstsAndMethods.Angle;
import ConstsAndMethods.Const;
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
import java.util.List;
import java.util.Random;

/**
 * The class Level three. Holds the first level details.
 */
public class LevelThree implements LevelInformation, Sprite {

    //point indexes to generate the blocks the ball is supposed to hit
    private static final int[] POINTS = {1, 7, 60, 760, 120, 20};
    private static final double BLOCK_WIDTH = 60;
    private static final double BLOCK_HEIGHT = 20;

    private static final int PADDLE_SPEED = 12;
    private static final int BALL_SPEED = 7;
    private static final int B_START_X = 395;
    private static final int B_START_Y = 540;
    private static final int P_START_X = 300;
    private static final int P_START_Y = 560;
    private static final int PADDLE_WIDTH = 150;
    private static final int PADDLE_HEIGHT = 15;

    private final List<Velocity> initialBallVelocities = new ArrayList<>();
    private final List<Block> blocks = new ArrayList<>();
    private final int paddleSpeed;
    private final int paddleWidth;
    private final int paddleHeight;
    private final Sprite screenBackground;
    private int[][] starPositions; //stars array for the third level


    /**
     * Instantiates a new Level three.
     */
    public LevelThree() {
        this.paddleSpeed = PADDLE_SPEED;
        this.paddleWidth = PADDLE_WIDTH;
        this.paddleHeight = PADDLE_HEIGHT;
        setVelocities();
        setBlocks();
        this.screenBackground = background();
        setStars();
    }

    private void setVelocities() {
        for (int i = 0; i < Const.TWO; i++) {
            this.initialBallVelocities.add(Velocity.fromAngleAndSpeed(
                    Angle.degrees()[new Random().nextInt(Const.FOUR)],
                    BALL_SPEED));
        }
    }

    private void setBlocks() {
        for (int i = POINTS[Const.ZERO]; i < POINTS[Const.ONE]; i++) {
            java.awt.Color color = Const.BLOCKS[i - Const.ONE];
            for (int j = i * POINTS[Const.TWO];
                 j <= POINTS[Const.THREE]; j += POINTS[Const.TWO]) {
                this.blocks.add(new Block(newRectBlock(i, j, color)));
            }
        }
    }

    private Background background() {
        return new Background(new Rectangle(new Point(), Const.WIDTH,
                Const.HEIGHT * Const.TWO,
                Color.GREEN.darker().darker().darker()), this);
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
        return new Rectangle(newBlockPoint(i, j), BLOCK_WIDTH, BLOCK_HEIGHT, color);
    }

    /**
     * Return new start block point.
     *
     * @param i the row index (or y value)
     * @param j the block start x value
     * @return new start point of the block
     */
    public static Point newBlockPoint(int i, int j) {
        return new Point(j, POINTS[Const.FOUR] + i * POINTS[Const.FIVE]);
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
        return "Green 3";
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

    private void setStars() {
        // Create an array to store the star positions
        this.starPositions = new int[Const.HUNDRED][Const.TWO];

        // Generate and store the star positions
        for (int i = 0; i < Const.HUNDRED; i++) {
            int x = (int) (Math.random() * Const.WIDTH);
            int y = (int) (Math.random() * Const.HEIGHT);
            starPositions[i][Const.ZERO] = x;
            starPositions[i][Const.ONE] = y;
        }

    }

    private static void drawStar(DrawSurface d, int x, int y, int size) {
        d.setColor(Color.WHITE);

        // Draw star shape
        double innerRadius = (double) size / Const.TWO;
        double angle = Math.PI / Const.FIVE;

        for (int i = 0; i < Const.TEN; i++) {
            double radius = (i % Const.TWO == Const.ZERO)
                    ? (double) size : innerRadius;
            int x1 = x + (int) (radius * Math.sin(i * angle));
            int y1 = y + (int) (radius * Math.cos(i * angle));
            int x2 = x + (int) (radius * Math.sin((i + Const.ONE) * angle));
            int y2 = y + (int) (radius * Math.cos((i + Const.ONE) * angle));
            d.drawLine(x1, y1, x2, y2);
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        // Draw stars
        for (int i = 0; i < Const.HUNDRED; i++) {
            int x = starPositions[i][Const.ZERO];
            int y = starPositions[i][Const.ONE];

            if (i % Const.THREE == Const.ZERO) {
                drawStar(d, x, y, Const.FOUR);
            } else if (i % Const.THREE == Const.ONE) {
                drawStar(d, x, y, Const.FOUR * Const.TWO);
            } else {
                drawStar(d, x, y, Const.FOUR * Const.THREE);
            }
        }
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {

    }
}
