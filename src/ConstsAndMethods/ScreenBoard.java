package ConstsAndMethods;

import GameObjects.Animation.Indicators.Lives.LifeIndicator;
import GameObjects.GameShapes.Attribute.Block;
import GameObjects.Environment.Game.GameLevel;
import GameObjects.GameShapes.Boarders.Boarder;
import GameObjects.GameShapes.Boarders.DeathRegion;
import GameObjects.GameShapes.Attribute.Point;
import GameObjects.GameShapes.Attribute.Rectangle;
import GameObjects.Animation.Indicators.Score.ScoreIndicator;
import Interfaces.Sprite;

/**
 * Class Screen board. Responsible for the boarders and the blocks generating
 * process.
 */
public class ScreenBoard {

    //points for the boarders
    public static final double START_X = -100;
    private static final double START_Y = -100;
    public static final double END_HEIGHT = 580;
    public static final double END_WIDTH = 780;

    /**
     * Create boarders.
     *
     * @param game the game object
     */
    public static void createBoarders(GameLevel game) {
        generateGameBoarders(game);
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
     * return new death region.
     *
     * @return the new death region
     */
    public static DeathRegion newDeathRegion() {
        return new DeathRegion(newBlock(Const.ZERO,
                (int) (END_HEIGHT + Const.TWENTY)));
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
