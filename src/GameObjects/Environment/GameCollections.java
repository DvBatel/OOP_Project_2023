package GameObjects.Environment;

import GameObjects.Collections.GameEnvironment;
import GameObjects.Collections.SpriteCollection;
import GameObjects.Environment.Game.GameLevel;
import Interfaces.Collidable;
import Interfaces.Sprite;

/**
 * Class Collections, handles both the collidables and sprite collection.
 */
public class GameCollections {

    private final SpriteCollection sprites;
    private final GameEnvironment environment;

    /**
     * Instantiates a new Collections.
     */
    public GameCollections() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
    }

    /**
     * @return the game environment.
     */
    public GameEnvironment getEnv() {
        return this.environment;
    }

    /**
     * @return the game environment.
     */
    public SpriteCollection getSprites() {
        return this.sprites;
    }

    /**
     * Add collidable to the game environment.
     * @param c the collidable object needed to be added to the game
     *          environment.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Add sprite to the sprite collection.
     * @param s the sprite object needed to be added to the sprite collection.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Remove collidable from the sprite collection.
     * @param c the collidable object needed to be removed from the
     *          collidables collection.
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * Remove sprite from the sprite collection.
     * @param s the sprite object needed to be removed from the sprite
     *          collection.
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * add the blocks given by the level on the screen.
     * @param game the level information
     */
    public void createBlocks(GameLevel game) {
        for (int i = 0; i < game.levelInformation().numberOfBlocksToRemove(); i++) {
            game.levelInformation().blocks().get(i).addToGame(game);
        }
    }
}
