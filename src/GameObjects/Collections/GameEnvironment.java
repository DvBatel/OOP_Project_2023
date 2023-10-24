package GameObjects.Collections;

import Interfaces.Collidable;
import GameObjects.GameShapes.Attribute.Line;
import GameObjects.GameShapes.Attribute.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Game environment. holds all the collidable object in a list.
 */
public class GameEnvironment {

    private final List<Collidable> collidableList;

    /**
     * Instantiates a new Game environment.
     */
    public GameEnvironment() {
        collidableList = new ArrayList<>();
    }

    /**
     * @return the size of the collidables list
     */
    public int size() {
        return this.collidableList.size();
    }

    /**
     * @param i the index wanted from the list
     * @return the collidable from the list
     */
    public Collidable getCollidable(int i) {
        return this.collidableList.get(i);
    }

    /**
     * Add the given collidable to the environment.
     *
     * @param c the collidable object
     */
    public void addCollidable(Collidable c) {
        collidableList.add(c);
    }

    /**
     * Remove collidable from the sprite collection.
     *
     * @param c the collidable object needed to be removed from the collidables
     *          collection.
     */
    public void removeCollidable(Collidable c) {
        collidableList.remove(c);
    }

    /**
     * Method to return closest collision.
     * <p>
     * Current process: Assume an object moving from line.start() to
     * line.end(). If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     * </p>
     *
     * @param trajectory the trajectory
     * @return the closest collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<CollisionInfo> collisionInfo = collisionInfoList(trajectory);
        return trajectory.closestCollisionInfo(collisionInfo);
    }

    /**
     * Method to return the intersection point by calling the function.
     *
     * @param c          the collidable object
     * @param trajectory the line given
     * @return intersection point/ null
     */
    public Point interPoint(Collidable c, Line trajectory) {
        return trajectory.closestIntersectionToStartOfLine(
                c.getCollisionRectangle());
    }

    /**
     * Method to gather all of existing collisions with the trajectory.
     *
     * @param trajectory the line given by the task
     * @return a list containing given collisions
     */
    public List<CollisionInfo> collisionInfoList(Line trajectory) {
        List<CollisionInfo> collisionInfo = new ArrayList<>();
        for (Collidable collide : this.collidableList) {
            addIfCollisionExists(collisionInfo, collide, trajectory);
        }
        return collisionInfo;
    }

    /**
     * Method that adds the collision points to the collision info list.
     *
     * @param collisionInfo the list the collision point is being added to
     * @param c             the collidable object
     * @param trajectory    the given line
     */
    public void addIfCollisionExists(List<CollisionInfo> collisionInfo,
                                     Collidable c, Line trajectory) {
        if (Line.pointExits(interPoint(c, trajectory))) {
            collisionInfo.add(new CollisionInfo(interPoint(c, trajectory), c));
        }
    }
}