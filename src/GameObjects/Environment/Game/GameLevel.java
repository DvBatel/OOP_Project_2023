package GameObjects.Environment.Game;

import ConstsAndMethods.Const;
import ConstsAndMethods.Player;
import ConstsAndMethods.ScreenBoard;
import GameObjects.Animation.Screens.CountdownAnimation;
import GameObjects.Environment.Animations;
import GameObjects.Environment.GameCollections;
import GameObjects.Environment.HitListeners;
import GameObjects.Environment.LifeTrack;
import GameObjects.Environment.Score;
import GameObjects.Animation.Indicators.LevelName;
import GameObjects.GameShapes.Boarders.DeathRegion;
import GameObjects.GameShapes.Player.Paddle;
import Interfaces.Animation;
import GameObjects.Animation.AnimationRunner;
import Interfaces.LevelInformation;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
 * Class Game. used to initialize and run the game.
 */
public class GameLevel implements Animation {

    private final GUI gui;
    private final GameCollections collections;
    private DeathRegion deathRegion;
    private Paddle paddle;
    private final HitListeners listeners;
    private final Score score;
    private final LevelInformation levelInformation;
    private final LifeTrack lives;
    private final AnimationRunner runner;
    private final KeyboardSensor keyboard;

    /**
     * Instantiates a new Game.
     *
     * @param levelInformation the level information
     * @param gui              the game object
     * @param animationRunner  the animation runner
     * @param keyboardSensor   the keyboard sensor
     * @param score            the score indicator
     */
    public GameLevel(LevelInformation levelInformation, GUI gui,
                     AnimationRunner animationRunner,
                     KeyboardSensor keyboardSensor, Score score,
                     LifeTrack lives) {
        this.gui = gui;
        this.levelInformation = levelInformation;
        this.keyboard = keyboardSensor;
        this.runner = animationRunner;
        this.collections = new GameCollections();
        this.listeners = new HitListeners(this);
        this.lives = lives;
        this.score = score;
    }

    /**
     * @return the gui object needed for the game screen.
     */
    public GUI getGUI() {
        return this.gui;
    }

    /**
     * @return the game collections of the game.
     */
    public GameCollections getCollections() {
        return this.collections;
    }

    /**
     * @return the listeners of the game.
     */
    public HitListeners getListeners() {
        return this.listeners;
    }

    /**
     * @return the lives board.
     */
    public LifeTrack livesBoard() {
        return this.lives;
    }

    /**
     * @return the level information of the game.
     */
    public LevelInformation levelInformation() {
        return this.levelInformation;
    }

    /**
     * @return the scoreboard.
     */
    public Score scoreBoard() {
        return this.score;
    }

    /**
     * Initialize a new game: creates the Blocks, Ball and the Paddle,
     * then adds them to their respective game collections.
     */
    public void initialize() {
        this.levelInformation.getBackground().addToGame(this);
        ScreenBoard.createBoarders(this);
        this.deathRegion = ScreenBoard.newDeathRegion();
        this.deathRegion.addToGame(this);
        this.collections.createBlocks(this);
        resetBalls();
        ScreenBoard.createScoreBoard(this);
        ScreenBoard.createLivesBoard(this);
        new LevelName(this).addToGame(this);
    }

    private void resetBalls() {
        if (this.paddle != null) {
            this.paddle.removeFromGame(this);
        }
        this.paddle = Player.newPaddle(this);
        this.paddle.addToGame(this);
        Player.generateBalls(this);
        this.deathRegion.addHowManyBalls(this);
    }

    /**
     * Run the game -- start the animation loop.
     * <p>
     * Current process: calculate the initial timing, run the animation with
     * the sprites, then calculate the timing to sleep if needed.
     * </p>
     */
    public void run() {
        runAnimation(Animations.countDown(getCollections()));
        this.runner.run(this);
    }

    private void runAnimation(Animation animation) {
        this.runner.run(animation);
    }

    private void runCountDown() {
        this.runner.run(new CountdownAnimation(Const.TWO_THOUSAND,
                Const.THREE, getCollections().getSprites()));
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.collections.getSprites().drawAllOn(d);
        this.collections.getSprites().notifyAllTimePassed();
        if (this.keyboard.isPressed("p")) {
            runAnimation(Animations.pause(this.gui.getKeyboardSensor()));
        }
    }

    @Override
    public boolean shouldStop() {
        if (this.listeners.remainingBlocks().getValue() == Const.ZERO
                || (this.listeners.remainingBalls().getValue() == Const.ZERO
                && this.lives.getNumLives() == Const.ONE)) {
            return true;
        } else if (this.listeners.remainingBalls().getValue() == Const.ZERO) {
            this.lives.decreaseLife();
            resetBalls();
            runCountDown();
        }
        return false;
    }

    /**
     * @return true if the number of lives is greater than zero.
     */
    public boolean noLivesRemaining() {
        return this.listeners.remainingBalls().getValue() == Const.ZERO
                && this.lives.getNumLives() == Const.ONE;
    }
}