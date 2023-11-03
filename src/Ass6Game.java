import ConstsAndMethods.Const;
import GameObjects.Environment.Game.GameFlow;
import GameObjects.Animation.Levels.LevelThree;
import GameObjects.Animation.Levels.LevelOne;
import GameObjects.Animation.Levels.LevelTwo;
import Interfaces.LevelInformation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Batel Divinsky, ID: 211517263, Ass no.6
 * <p>
 * Class Ass6Game. Current process: Create a new game object of arkanoid,
 * initialize the game and runs the object.
 * </p>
 * @version 3.0
 */
public class Ass6Game {

    /**
     * The entry point of application.
     *
     * @param args the input arguments given by the user, currently not used.
     */
    public static void main(String[] args) {
        GameFlow game = new GameFlow();
        game.runLevels(initializeLevels(args));
    }

    private static List<LevelInformation> initializeLevels(String[] args) {
        List<LevelInformation> levels = new ArrayList<>();
        for (String arg : args) {
            try {
                Integer.parseInt(arg);
            } catch (Exception e) {
                continue;
            }
            switch (Integer.parseInt(arg)) {
                case Const.ONE -> levels.add(new LevelOne());
                case Const.TWO -> levels.add(new LevelTwo());
                case Const.THREE -> levels.add(new LevelThree());
                default -> {
                }
            }
        }
        if (levels.isEmpty()) {
            levels.add(new LevelOne());
            levels.add(new LevelTwo());
            levels.add(new LevelThree());
        }
        return levels;
    }
}