package ConstsAndMethods;

import GameObjects.Environment.Game.GameLevel;
import GameObjects.GameShapes.Player.Paddle;
import GameObjects.GameShapes.Player.Ball;
import GameObjects.GameShapes.Attribute.Point;
import GameObjects.GameShapes.Attribute.Rectangle;

/**
 * Class Player. Responsible for the paddle the ball generating process.
 */
public class Player {

    //Ball constants
    public static final int RADIUS = 6;
    public static final double DEFAULT_SPEED = 5;
    public static final double DEFAULT_ANGLE = -180;
    //Paddle constants
    public static final int PADDLE_SPEED = 3;

    /**
     * Generate a new ball.
     *
     * @param game the game object
     */
    public static void generateBalls(GameLevel game) {
        for (int i = 0; i < game.levelInformation().numberOfBalls(); i++) {
            generateBall(game, i);
            game.getListeners().remainingBalls().increase(Const.ONE);
        }
    }

    private static void generateBall(GameLevel game, int i) {
        Ball ball = newBall(game);
        ball.setVelocity(game.levelInformation().initialBallVelocities().get(i));
        ball.addToGame(game);
    }

    /**
     * Generate a new paddle.
     *
     * @param game the game object
     */
    public static void generatePaddle(GameLevel game) {
        newPaddle(game).addToGame(game);
    }

    /**
     * generate new ball object.
     *
     * @param game the game object
     * @return new ball object
     */
    private static Ball newBall(GameLevel game) {
        return new Ball(defaultCenter(game), RADIUS,
                Methods.generateColor(), game.getCollections().getEnv());
    }

    /**
     * Generate a new center point.
     *
     * @param game the game object
     * @return the new random center point
     */
    private static Point defaultCenter(GameLevel game) {
        return game.levelInformation().ballStartPoint();
    }

    /**
     * Generate new paddle object.
     *
     * @param game the game object
     * @return the new generated paddle
     */
    public static Paddle newPaddle(GameLevel game) {
        return new Paddle(game.getGUI(),
                new Rectangle(game.levelInformation().paddleStartPoint(),
                        game.levelInformation().paddleWidth(),
                        game.levelInformation().paddleHeight(),
                        Methods.generateColor()),
                game.levelInformation().paddleSpeed(),
                game.levelInformation().ballSpeed());
    }
}
