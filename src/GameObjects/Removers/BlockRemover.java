package GameObjects.Removers;

import ConstsAndMethods.Const;
import ConstsAndMethods.Counter;
import GameObjects.GameShapes.Player.Ball;
import GameObjects.Environment.Game.GameLevel;
import GameObjects.GameShapes.Attribute.Block;
import Interfaces.Hit.HitListener;

/**
 * BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {

    private final GameLevel game;
    private final Counter remainingBlocks;

    /**
     * Instantiates a new Block remover.
     *
     * @param game          the game
     * @param removedBlocks the removed blocks
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(game);
        updateScore(beingHit, hitter);
    }

    private void updateScore(Block beingHit, Ball hitter) {
        HitListener scoreListener = this.game.scoreBoard().getScoreListener();
        scoreListener.hitEvent(beingHit, hitter);
        if (this.remainingBlocks.getValue() == Const.ZERO) {
            scoreListener.hitEvent(beingHit, null);
        }
    }
}
