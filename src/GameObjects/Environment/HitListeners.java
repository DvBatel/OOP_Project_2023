package GameObjects.Environment;

import ConstsAndMethods.Counter;
import GameObjects.Environment.Game.GameLevel;
import GameObjects.Removers.BallRemover;
import GameObjects.Removers.BlockRemover;
import Interfaces.Hit.HitListener;

/**
 * Class HitListeners, handles the blocks and balls listeners of the game.
 */
public class HitListeners {

    private HitListener blockRemover;
    private HitListener ballRemover;
    private Counter remainingBalls;
    private Counter remainingBlocks;

    /**
     * Instantiates a new Hit listeners.
     *
     * @param game the game
     */
    public HitListeners(GameLevel game) {
        initRemaining();
        initRemovers(game);
    }

    private void initRemaining() {
        this.remainingBlocks = new Counter();
        this.remainingBalls = new Counter();
    }

    private void initRemovers(GameLevel game) {
        this.blockRemover = new BlockRemover(game, remainingBlocks);
        this.ballRemover = new BallRemover(game, remainingBalls);
    }

    /**
     * Gets block remover.
     *
     * @return the Block remover hit listener.
     */
    public HitListener getBlockRemover() {
        return this.blockRemover;
    }

    /**
     * Gets ball remover.
     *
     * @return the Ball Remover hit listener.
     */
    public HitListener getBallRemover() {
        return this.ballRemover;
    }

    /**
     * Remaining blocks counter.
     *
     * @return the blocks counter.
     */
    public Counter remainingBlocks() {
        return this.remainingBlocks;
    }

    /**
     * Remaining balls counter.
     *
     * @return the balls counter.
     */
    public Counter remainingBalls() {
        return this.remainingBalls;
    }


}
