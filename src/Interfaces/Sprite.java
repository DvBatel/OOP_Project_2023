package Interfaces;

import GameObjects.Environment.Game.GameLevel;
import biuoop.DrawSurface;

/**
 * The interface Sprite.
 */
public interface Sprite {

    /**
     * draw the sprite to the screen.
     *
     * @param d the surface
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * add interface object to the game.
     *
     * @param g the game object
     */
    void addToGame(GameLevel g);
}