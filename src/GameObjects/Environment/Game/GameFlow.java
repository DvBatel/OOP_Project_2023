package GameObjects.Environment.Game;

import ConstsAndMethods.Const;
import GameObjects.Environment.LifeTrack;
import GameObjects.Environment.Score;
import GameObjects.Animation.AnimationRunner;
import Interfaces.LevelInformation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.util.List;

/**
 * The class Game flow. Holds the properties to run the game as a whole.
 */
public class GameFlow {

    private final GUI gui;
    private final AnimationRunner animationRunner;
    private final KeyboardSensor keyboardSensor;
    private final LifeTrack lives;
    private final Score score;

    /**
     * Instantiates a new Game flow.
     */
    public GameFlow() {
        this.gui = new GUI(Const.GAME_NAME, Const.WIDTH, Const.HEIGHT);
        this.keyboardSensor = gui.getKeyboardSensor();
        this.animationRunner = new AnimationRunner(this.gui);
        this.lives = new LifeTrack();
        this.score = new Score();
    }

    /**
     * Run levels.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        boolean winParam = true;
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.gui,
                    this.animationRunner, this.keyboardSensor, this.score,
                    this.lives);
            level.initialize();
            while (!level.shouldStop()) {
                level.run();
            }
            if (level.noLivesRemaining()) {
                winParam = false;
                break;
            }
        }
        EndScreen.endScreen(winParam, score, animationRunner, keyboardSensor);
        this.gui.close();
    }
}
