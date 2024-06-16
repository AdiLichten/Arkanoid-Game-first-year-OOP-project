// this is the main class of the game

import Interfaces.LevelInformation;
import Levels.Level1;
import Levels.Level2;
import Levels.Level3;
import Levels.Level4;
import SetGame.AnimationRunner;
import SetGame.Counter;
import SetGame.GameFlow;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.ArrayList;
import java.util.List;

public class Ass6Game {


    /**
     * this is the main function.
     *
     * @param args the user input
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        List<LevelInformation> levels = new ArrayList<>();
        List<Integer> levelNum = new ArrayList<>();
        AnimationRunner animationRunner = new AnimationRunner(gui);
        Counter lives = new Counter(10);
        for (int i = 0; i < args.length; i++) {
            try {
                levelNum.add(Integer.parseInt(args[i]));
            } catch (Exception exception) {
                System.out.println("ERROR! incorrect level input");
            }
        }
        for (Integer levelNUm : levelNum) {
            if (levelNUm == 1) {
                levels.add(new Level1());
            } else if (levelNUm == 2) {
                levels.add(new Level2());
            } else if (levelNUm == 3) {
                levels.add(new Level3());
            } else if (levelNUm == 4) {
                levels.add(new Level4());
            }
        }
        if (levelNum.size() == 0) {
            levels.add(new Level1());
            levels.add(new Level2());
            levels.add(new Level3());
            levels.add(new Level4());
        }
        GameFlow gameFlow = new GameFlow(gui, animationRunner, keyboard);
        gameFlow.runLevels(levels);
    }
}
