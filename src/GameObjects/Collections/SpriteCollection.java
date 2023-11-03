package GameObjects.Collections;

import ConstsAndMethods.Const;
import Interfaces.Sprite;
import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Sprite collection. holds the sprite of the game in a list.
 */
public class SpriteCollection {

    private final List<Sprite> spriteList;

    /**
     * Instantiates a new Sprite collection.
     */
    public SpriteCollection() {
        this.spriteList = new ArrayList<>();
    }

    /**
     * Adds sprite.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        spriteList.add(s);
    }

    /**
     * Remove sprite from the sprite collection.
     *
     * @param s the sprite object needed to be removed from the sprite
     *          collection.
     */
    public void removeSprite(Sprite s) {
        spriteList.remove(s);
    }

    /**
     * @return the first sprite in the list
     */
    public Sprite firstSprite() {
        return this.spriteList.get(Const.ZERO);
    }

    /**
     * Notify all time passed by calling timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < this.spriteList.size(); i++) {
            this.spriteList.get(i).timePassed();
        }
    }

    /**
     * Draw all time passed by calling drawOn() on all sprites.
     *
     * @param d the surface object
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < this.spriteList.size(); i++) {
            this.spriteList.get(i).drawOn(d);
        }
    }
}