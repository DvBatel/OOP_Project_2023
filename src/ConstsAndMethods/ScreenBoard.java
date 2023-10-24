package ConstsAndMethods;

import GameObjects.Collections.GameEnvironment;
import GameObjects.GameShapes.Attribute.Block;
import GameObjects.Environment.Game.GameLevel;
import GameObjects.GameShapes.Boarders.Boarder;
import GameObjects.GameShapes.Boarders.DeathRegion;
import GameObjects.GameShapes.Attribute.Point;
import GameObjects.GameShapes.Attribute.Rectangle;
import GameObjects.Animation.Indicators.Lives.LifeIndicator;
import GameObjects.Animation.Indicators.Score.ScoreIndicator;
import Interfaces.Sprite;

import java.awt.Color;
import java.util.Random;

/**
 * Class Screen board. Responsible for the boarders and the blocks generating
 * process.
 */
public class ScreenBoard {

    //point indexes to generate the blocks the ball is supposed to hit
    private static final int[] POINTS = {1, 7, 60, 760, 120, 20};
    private static final double BLOCK_WIDTH = 60;
    private static final double BLOCK_HEIGHT = 20;

    //points for the boarders
    public static final double START_X = -100;
    private static final double START_Y = -100;
    public static final double END_HEIGHT = 580;
    public static final double END_WIDTH = 780;

    private static final Random RAND = new Random();

    /**
     * Create boarders.
     *
     * @param game the game object
     */
    public static void createBoarders(GameLevel game) {
        generateGameBoarders(game);
    }

    /**
     * Creates blocks.
     *
     * @param game the game object
     */
    public static void createBlocks(GameLevel game) {
        generateBlocks(game);
        createRandomDeathBlocks(game);
    }

    private static void createRandomDeathBlocks(GameLevel game) {
        //get the collidables array size and generate a random index in the
        // size minus the walls
        int collSize = game.getCollections().getEnv().size();
        for (int i = 0; i < Const.TWO; i++) {
            int random = RAND.nextInt(max(collSize, i)) + min(i);
            GameEnvironment gEnv = game.getCollections().getEnv();
            //add the death region to the game and remove the block properties
            // of listening to a hit as to not give the player points.
            newDeathBlock(gEnv, random).addToGame(game);
            ((Block) gEnv.getCollidable(random)).removeFromGame(game);
        }

    }

    private static int max(int collSize, int i) {
        return collSize - Const.THREE - i;
    }

    private static int min(int i) {
        return Const.THREE + i;

    }

    private static DeathRegion newDeathBlock(GameEnvironment gEnv, int i) {
        gEnv.getCollidable(i).getCollisionRectangle().deathRegionColor();
        return new DeathRegion((Block) gEnv.getCollidable(i));
    }

    /**
     * Create scoreboard.
     *
     * @param game the game object
     */
    public static void createScoreBoard(GameLevel game) {
        Sprite scoreIndicator =
                new ScoreIndicator(game.scoreBoard().getScore());
        scoreIndicator.addToGame(game);
    }

    /**
     * Create lives board.
     *
     * @param game the game object
     */
    public static void createLivesBoard(GameLevel game) {
        Sprite livesIndicator =
                new LifeIndicator(game.livesBoard().getLives());
        livesIndicator.addToGame(game);
    }

    /**
     * return new death region.
     *
     * @return the new death region
     */
    public static DeathRegion newDeathRegion() {
        return new DeathRegion(newBlock(Const.ZERO,
                (int) (END_HEIGHT + Const.TWENTY)));
    }

    /**
     * Generate blocks.
     *
     * @param game the game object
     */
    public static void generateBlocks(GameLevel game) {
        for (int i = POINTS[Const.ZERO]; i < POINTS[Const.ONE]; i++) {
            generateRow(i, game);
        }
    }

    /**
     * Generate row, while each row has a unique color.
     *
     * @param i    the row index
     * @param game the game object
     */
    public static void generateRow(int i, GameLevel game) {
        java.awt.Color color = Const.BLOCKS[i - Const.ONE];
        for (int j = i * POINTS[Const.TWO]; j <= POINTS[Const.THREE]; j += POINTS[Const.TWO]) {
            generateBlock(i, j, color, game);
        }
    }

    /**
     * Generate a single block and add it to the game.
     *
     * @param i     the row index (or y value)
     * @param j     the block start x value
     * @param color the color given to the block
     * @param game  the game object
     */
    public static void generateBlock(int i, int j, Color color, GameLevel game) {
        newBlock(i, j, color).addToGame(game);
    }

    /**
     * return new block object.
     *
     * @param i     the row index (or y value)
     * @param j     the block start x value
     * @param color the color given to the block
     * @return the new block generated
     */
    public static Block newBlock(int i, int j, Color color) {
        return new Block(newRectBlock(i, j, color));
    }

    /**
     * return new block object.
     *
     * @param i the row index (or y value)
     * @param j the block start x value
     * @return the new block generated
     */
    public static Block newBlock(int i, int j) {
        return new Block(newRectBlock(i, j));
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
     * Return new rectangle for the block shape.
     *
     * @param i the row index (or y value)
     * @param j the block start x value
     * @return new rectangle representing the block
     */
    public static Rectangle newRectBlock(int i, int j) {
        return new Rectangle(new Point(i, j), Const.THOUSAND,
                Const.HUNDRED_TWENTY);
    }

    /**
     * Generate game boarders.
     * Current process: generate the blocks objects. then, run an object in
     * a loop in order to add each block boarder to the game.
     *
     * @param game the game object
     */
    public static void generateGameBoarders(GameLevel game) {
        for (Boarder block : generateBoarders()) {
            block.addToGame(game);
        }
    }

    /**
     * generate points, then the rectangle dimensions, then the blocks objects.
     *
     * @return new block object of the boarders
     */
    public static Boarder[] generateBoarders() {
        return blockBoarders(blockRects(blockStartPoints()));
    }

    /**
     * Return set values of the game object boarders.
     *
     * @return the start points of the boarders
     */
    public static Point[] blockStartPoints() {
        return new Point[]{new Point(START_X, Const.ZERO),
                new Point(Const.ZERO, START_Y),
                new Point(END_WIDTH, Const.ZERO)};
    }

    /**
     * Return set values of the game object shapes.
     *
     * @param points the start upper points
     * @return the start points of the boarders
     */
    public static Rectangle[] blockRects(Point[] points) {
        return new Rectangle[]{new Rectangle(points[Const.ZERO],
                Const.HUNDRED_TWENTY, Const.THOUSAND),
                new Rectangle(points[Const.ONE], Const.THOUSAND,
                        Const.HUNDRED_TWENTY), new Rectangle(points[Const.TWO],
                Const.HUNDRED_TWENTY, Const.THOUSAND)};
    }

    /**
     * Return set values of the game object blocks.
     *
     * @param rectangles the shapes of the boarders
     * @return the block boarders
     */
    public static Boarder[] blockBoarders(Rectangle[] rectangles) {
        return new Boarder[]{new Boarder(rectangles[Const.ZERO]),
                new Boarder(rectangles[Const.ONE]),
                new Boarder(rectangles[Const.TWO])};
    }
}
