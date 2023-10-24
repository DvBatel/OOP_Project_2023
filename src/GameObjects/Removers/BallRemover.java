package GameObjects.Removers;

import ConstsAndMethods.Const;
import ConstsAndMethods.Counter;
import GameObjects.GameShapes.Player.Ball;
import GameObjects.GameShapes.Attribute.Block;
import GameObjects.Environment.Game.GameLevel;
import Interfaces.Hit.HitListener;

/**
 * BallRemover is in charge of removing balls from the game, as well as
 * keeping count of the number of balls that remain.
 */
public class BallRemover implements HitListener {

    private final GameLevel game;
    private final Counter remainingBalls;

    /**
     * Instantiates a new Block remover.
     *
     * @param game         the game
     * @param removedBalls the removed blocks
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        remainingBalls.decrease(Const.ONE);
    }
}
