package GameObjects.GameShapes.Boarders.Background;

import GameObjects.Environment.Game.GameLevel;
import GameObjects.GameShapes.Attribute.Point;
import GameObjects.GameShapes.Attribute.Rectangle;
import GameObjects.GameShapes.Attribute.Velocity;
import GameObjects.GameShapes.Player.Ball;
import Interfaces.Collidable;
import Interfaces.Sprite;
import biuoop.DrawSurface;

/**
 * The class Background. holds the background properties for each level.
 */
public class Background implements Sprite, Collidable {

    private final Rectangle rectangle;
    private final Sprite level;

    /**
     * Instantiates a new Background.
     *
     * @param rectangle   the rectangle
     * @param level the level
     */
    public Background(Rectangle rectangle, Sprite level) {
        this.rectangle = rectangle;
        this.level = level;
    }

    @Override
    public void drawOn(DrawSurface d) {
        this.rectangle.drawOn(d);
        this.level.drawOn(d);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.getCollections().addCollidable(this);
        g.getCollections().addSprite(this);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Ball hitter,
                        Point collisionPoint, Velocity currentVelocity) {
        return currentVelocity;
    }

//    private void setStars() {
//        // Create an array to store the star positions
//        this.starPositions = new int[Const.HUNDRED][Const.TWO];
//
//        // Generate and store the star positions
//        for (int i = 0; i < Const.HUNDRED; i++) {
//            int x = (int) (Math.random() * Const.WIDTH);
//            int y = (int) (Math.random() * Const.HEIGHT);
//            starPositions[i][Const.ZERO] = x;
//            starPositions[i][Const.ONE] = y;
//        }
//
//    }
//
//    private static void drawStar(DrawSurface d, int x, int y, int size) {
//        d.setColor(Color.WHITE);
//
//        // Draw star shape
//        double innerRadius = (double) size / Const.TWO;
//        double angle = Math.PI / Const.FIVE;
//
//        for (int i = 0; i < Const.TEN; i++) {
//            double radius = (i % Const.TWO == Const.ZERO)
//                    ? (double) size : innerRadius;
//            int x1 = x + (int) (radius * Math.sin(i * angle));
//            int y1 = y + (int) (radius * Math.cos(i * angle));
//            int x2 = x + (int) (radius * Math.sin((i + Const.ONE) * angle));
//            int y2 = y + (int) (radius * Math.cos((i + Const.ONE) * angle));
//            d.drawLine(x1, y1, x2, y2);
//        }
//    }
}
