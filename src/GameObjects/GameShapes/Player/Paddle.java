package GameObjects.GameShapes.Player;

import ConstsAndMethods.Angle;
import ConstsAndMethods.Const;
import ConstsAndMethods.Methods;
import ConstsAndMethods.Player;
import GameObjects.Environment.Game.GameLevel;
import Interfaces.Sprite;
import Interfaces.Collidable;
import GameObjects.GameShapes.Attribute.Point;
import GameObjects.GameShapes.Attribute.Rectangle;
import GameObjects.GameShapes.Attribute.Velocity;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
 * Class Paddle. Holds a keyboard object and a rectangle representing
 * the paddle. Controls the paddle movement and drawing surface.
 */
public class Paddle implements Sprite, Collidable {

    private final biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private final int paddleSpeed;
    private final double ballSpeed;

    /**
     * Instantiates a new Paddle.
     *
     * @param gui       the gui object for the game
     * @param rectangle the shape of the paddle
     */
    public Paddle(GUI gui, Rectangle rectangle) {
        this.keyboard = gui.getKeyboardSensor();
        this.rectangle = rectangle;
        this.paddleSpeed = Player.PADDLE_SPEED;
        this.ballSpeed = Player.DEFAULT_SPEED;
    }

    /**
     * Instantiates a new Paddle.
     *
     * @param gui       the gui object for the game
     * @param rectangle the shape of the paddle
     * @param paddleSpeed the paddle speed
     * @param ballSpeed the balls speed
     */
    public Paddle(GUI gui, Rectangle rectangle, int paddleSpeed,
                  double ballSpeed) {
        this.keyboard = gui.getKeyboardSensor();
        this.rectangle = rectangle;
        this.paddleSpeed = paddleSpeed;
        this.ballSpeed = ballSpeed;
    }

    /**
     * Change the rectangle initial start point by two to the left.
     */
    private void moveLeft() {
        this.rectangle =
                newRectangle(Methods.changeSign(this.paddleSpeed));
    }

    /**
     * Change the rectangle initial start point by two to the right.
     */
    private void moveRight() {
        this.rectangle = newRectangle(this.paddleSpeed);
    }

    /**
     * Draw the filling of the paddle.
     *
     * @param d surface object
     */
    private void drawFilling(DrawSurface d) {
        d.setColor(this.rectangle.getColor());
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
    }

    /**
     * Draw the perimeter of the paddle.
     *
     * @param d surface object
     */
    private void drawPerimeter(DrawSurface d) {
        d.setColor(java.awt.Color.BLACK);
        d.drawRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
    }

    /**
     * Method to check if there is a movement from the player was sent,
     * and act accordingly.
     */
    public void moveOneStep() {
        if (askedToMoveLeft() && possibleToMoveLeft()) {
            moveLeft();
        } else if (askedToMoveRight() && possibleToMoveRight()) {
            moveRight();
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        drawFilling(d);
        drawPerimeter(d);
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        Point[] paddleSections = returnSections();
        double[] distances = returnDistances(collisionPoint, paddleSections);
        return newAngle(smallestValInIndex(distances));
    }

    /**
     * Add the paddle object to the game.
     *
     * @param g the game object
     */
    public void addToGame(GameLevel g) {
        g.getCollections().addCollidable(this);
        g.getCollections().addSprite(this);
    }

    /**
     * Removes the paddle from the game.
     * @param g the game object
     */
    public void removeFromGame(GameLevel g) {
        g.getCollections().removeCollidable(this);
        g.getCollections().removeSprite(this);
    }

    /**
     * Return the distances between all the sections and the collision point.
     *
     * @param collisionPoint the collision point
     * @param sections       the sections of the paddle
     * @return the distances calculated
     */
    private double[] returnDistances(Point collisionPoint, Point[] sections) {
        return new double[]{collisionPoint.distance(sections[Const.ZERO]),
                collisionPoint.distance(sections[Const.ONE]),
                collisionPoint.distance(sections[Const.TWO]),
                collisionPoint.distance(sections[Const.THREE]),
                collisionPoint.distance(sections[Const.FOUR])};
    }

    /**
     * Calculate the sections of the paddle.
     *
     * @return the new points containing the sections
     */
    private Point[] returnSections() {
        Point[] sections = new Point[Const.FIVE];
        return calculatedSections(sections);
    }

    /**
     * @param sections the section array points needed for the change in angle.
     * @return the sections array after calculation.
     */
    private Point[] calculatedSections(Point[] sections) {
        for (int i = 1; i < sections.length; i++) {
            sections[i] = newPoint(sections, i);
        }
        return sections;
    }

    /**
     * @return true if the keyboard object detected a left key press.
     */
    private boolean askedToMoveLeft() {
        return this.keyboard.isPressed(KeyboardSensor.LEFT_KEY);
    }

    /**
     * @return true if the keyboard object detected a right key press.
     */
    private boolean askedToMoveRight() {
        return this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY);
    }

    /**
     * @return true if there is a possible route to the left (as to not go
     * off the screen)
     */
    private boolean possibleToMoveLeft() {
        return getUpperLeftX() - Const.TWENTY > Const.ZERO;
    }

    /**
     * @return true if there is a possible route to the right (as to not go
     * off the screen)
     */
    private boolean possibleToMoveRight() {
        return getUpperLeftX() + getPaddleWidth() + Const.TWENTY < Const.WIDTH;
    }

    /**
     * @param direction the direction of the movement asked by the player.
     * @return new rectangle containing the new start point.
     */
    private Rectangle newRectangle(int direction) {
        return new Rectangle(newStartPoint(direction),
                this.rectangle.getWidth(), this.rectangle.getHeight(),
                this.rectangle.getColor());
    }

    /**
     * Return new start point given the asked direction.
     *
     * @param direction the direction the rectangle should move
     * @return the new start point
     */
    private Point newStartPoint(int direction) {
        return new Point(getUpperLeftX() + direction, getUpperLeftY());
    }

    /**
     * @return the rectangle upper left X value.
     */
    public double getUpperLeftX() {
        return this.rectangle.getUpperLeft().getX();
    }

    /**
     * @return the rectangle upper left Y value.
     */
    public double getUpperLeftY() {
        return this.rectangle.getUpperLeft().getY();
    }

    /**
     * @return the rectangle width.
     */
    public double getPaddleWidth() {
        return this.rectangle.getWidth();
    }

    /**
     * @return the paddle width divided by how many sections needed
     */
    private double getPaddleAverage() {
        return getPaddleWidth() / Const.FIVE;
    }

    /**
     * @param index index given in the loop
     * @return true if it is the first index given by the loop
     */
    private boolean firstIndex(int index) {
        return index == Const.ONE;
    }

    /**
     * @param sections an array of sections
     * @param i        the index we add the next section to
     * @return new point containing the x section
     */
    private Point newPoint(Point[] sections, int i) {
        if (firstIndex(i)) {
            sections[Const.ZERO] = new Point(getUpperLeftX(), Const.ZERO);
        }
        return new Point(calcNextXVal(sections, i), Const.ZERO);
    }

    /**
     * @param arr an array of sections
     * @param i   the index we add the next section to
     * @return new double containing the x section
     */
    private double calcNextXVal(Point[] arr, int i) {
        return (arr[i - Const.ONE]).getX() + getPaddleAverage();
    }

    /**
     * generate new velocity according to the section it hit, which is
     * represented by the index. If the ball hit the middle section, surge
     * the velocity up.
     *
     * @param index the index of the given section
     * @return new velocity of the ball
     */
    private Velocity newAngle(int index) {
        return new Velocity(newVelocity(index));
    }

    /**
     * @param index the place of the section calculated
     * @return new velocity with a new correlated angle
     */
    private Velocity newVelocity(int index) {
        return Velocity.fromAngleAndSpeed(
                Angle.degrees()[index], this.ballSpeed);
    }

    /**
     * Calculate the smallest value in index.
     *
     * @param distances the distances calculated between the point and the
     *                  sections.
     * @return the smallest distance index
     */
    private static int smallestValInIndex(double[] distances) {
        int minSize = 0;
        for (int i = 0; i < distances.length; i++) {
            if (isSmaller(distances[i], distances[minSize])) {
                minSize = i;
            }
        }
        return minSize;
    }

    /**
     * @param currentIndex current index in loop
     * @param currMinimum  current minimum value in the array
     * @return true if the current index holds a smaller value
     */
    private static boolean isSmaller(double currentIndex, double currMinimum) {
        return currentIndex < currMinimum;
    }
}
