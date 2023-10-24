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

/**
 * The class Level two. Holds the second level details.
 */
public class LevelTwo implements LevelInformation, Sprite {

    //point indexes to generate the blocks the ball is supposed to hit
    private static final int[] POINTS = {1, 7, 40, 760, 120, 20};
    private static final double BLOCK_WIDTH = 40;
    private static final double BLOCK_HEIGHT = 30;

    private static final int PADDLE_SPEED = 1;
    private static final int BALL_SPEED = 8;
    private static final int B_START_X = 395;
    private static final int B_START_Y = 480;
    private static final int P_START_X = 50;
    private static final int P_START_Y = 500;
    private static final int PADDLE_WIDTH = 700;
    private static final int PADDLE_HEIGHT = 20;

    private final List<Velocity> initialBallVelocities = new ArrayList<>();
    private final List<Block> blocks = new ArrayList<>();
    private final int paddleSpeed;
    private final int paddleWidth;
    private final Sprite screenBackground;
    private final int paddleHeight;

    /**
     * Instantiates a new Level two.
     */
    public LevelTwo() {
        this.paddleSpeed = PADDLE_SPEED;
        this.paddleWidth = PADDLE_WIDTH;
        this.paddleHeight = PADDLE_HEIGHT;
        setVelocities();
        setBlocks();
        this.screenBackground = background();
    }

    private void setVelocities() {
        for (int i = 0; i < Const.TEN; i++) {
            if (i > Const.FOUR) {
                this.initialBallVelocities.add(Velocity.fromAngleAndSpeed(
                        Angle.degrees()[Const.NINE - i] - Const.FIFTEEN,
                        BALL_SPEED));
            } else {
                this.initialBallVelocities.add(Velocity.fromAngleAndSpeed(
                        Angle.degrees()[i], BALL_SPEED));
            }
        }
    }

    private void setBlocks() {
        int counter = Const.ZERO;
        for (int i = POINTS[Const.FIVE];
             i <= POINTS[Const.THREE]; i += POINTS[Const.TWO]) {
            Color color = getColorPalette(counter);
            this.blocks.add(new Block(newRectBlock(i, color)));
            counter++;
        }
    }

    private Background background() {
        return new Background(new Rectangle(
                new Point(), Const.WIDTH, Const.HEIGHT * Const.TWO,
                Const.COLOR_PALETTE[Const.FIVE]), this);
    }

    /**
     * Return new rectangle for the block shape.
     *
     * @param j     the block start x value
     * @param color the color given to the block
     * @return new rectangle representing the block
     */
    private static Rectangle newRectBlock(int j, Color color) {
        return new Rectangle(newBlockPoint(j),
                BLOCK_WIDTH, BLOCK_HEIGHT, color);
    }

    /**
     * Return new start block point.
     *
     * @param j the block start x value
     * @return new start point of the block
     */
    private static Point newBlockPoint(int j) {
        return new Point(j, POINTS[Const.FOUR]
                + Const.FOUR * POINTS[Const.FIVE]);
    }

    private static Color getColorPalette(int counter) {
        if (counter >= Const.ZERO && counter <= Const.ONE) {
            return Const.LEVEL_TWO[Const.ZERO];
        } else if (counter >= Const.TWO && counter <= Const.FOUR) {
            return Const.LEVEL_TWO[Const.ONE];
        } else if (counter >= Const.FIVE && counter <= Const.SEVEN) {
            return Const.LEVEL_TWO[Const.TWO];
        } else if (counter >= Const.EIGHT && counter <= Const.ELEVEN) {
            return Const.LEVEL_TWO[Const.THREE];
        } else if (counter >= Const.TWELVE && counter <= Const.FIFTEEN) {
            return Const.LEVEL_TWO[Const.FOUR];
        } else {
            return Const.LEVEL_TWO[Const.FIVE];
        }
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
        return "Wide Easy";
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
        d.setColor(Const.LEVEL_ONE[Const.ZERO]);

        int centerX = Const.TWO_HUNDRED; // X-coordinate of the center point
        int centerY = Const.HUNDRED_FIFTY; // Y-coordinate of the center point
        int radius = Const.THREE_HUNDRED; // Radius of the circle
        int numLines = Const.SEVENTY_TWO; // Number of lines to be printed
        int lineLength = Const.HUNDRED_FIFTY;
        int gapSize = Const.TEN;
        // Angle between each line
        double angleIncrement = Const.TWO * Math.PI / numLines;

        for (int i = 0; i < numLines; i++) {
            double angle = i * angleIncrement;
            int x1 = centerX + (int) ((radius - lineLength) * Math.cos(angle));
            int y1 = centerY + (int) ((radius - lineLength) * Math.sin(angle));
            int x2 = centerX + (int) ((radius + gapSize) * Math.cos(angle));
            int y2 = centerY + (int) ((radius + gapSize) * Math.sin(angle));
            d.drawLine(x1, y1, x2, y2);
        }

        d.fillCircle(Const.TWO_HUNDRED, Const.HUNDRED_FIFTY,
                Const.HUNDRED_TWENTY);
        d.setColor(Const.LEVEL_ONE[Const.ONE]);
        d.fillCircle(Const.TWO_HUNDRED, Const.HUNDRED_FIFTY, Const.HUNDRED);
        d.setColor(Const.LEVEL_ONE[Const.TWO]);
        d.fillCircle(Const.TWO_HUNDRED, Const.HUNDRED_FIFTY, Const.EIGHTY);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
    }
}
