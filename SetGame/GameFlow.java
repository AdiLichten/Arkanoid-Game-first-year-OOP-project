// The GameFlow class is responsible for running the levels, and moving from one level to the next.

package SetGame;

import Interfaces.LevelInformation;
import Screens.EndScreen;
import Screens.KeyPressStoppableAnimation;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.List;

public class GameFlow {
    private GUI gui;
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter lives;
    private Counter score;
    private Counter ballsCounter;
    private Counter blocksCounter;
    private boolean isWin;

    /**
     * this is the constructor.
     *
     * @param gui - the gui.
     * @param ar  - the animation runner.
     * @param ks  - the keyboard sensor.
     */
    public GameFlow(GUI gui, AnimationRunner ar, KeyboardSensor ks) {
        this.gui = gui;
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.score = new Counter();
        this.lives = new Counter(7);
        this.ballsCounter = new Counter();
        this.blocksCounter = new Counter();
        this.isWin = true;
    }

    /**
     * the function runs the levels.
     *
     * @param levels - the list of the levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.animationRunner, this.score,
                    this.lives, this.blocksCounter, this.ballsCounter, this.gui);
            level.initialize();
            while (this.blocksCounter.getValue() > 0 && this.ballsCounter.getValue() > 0) {
                level.run();
            }
            if (this.blocksCounter.getValue() == 0) {
                this.score.increase(100);
            }
            while (this.ballsCounter.getValue() == 0 && this.lives.getValue() > 0) {
                level.setBalls();
                level.run();
            }
            if (this.lives.getValue() == 0) {
                isWin = false;
                break;
            }
        }
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space",
                new EndScreen(this.score, isWin)));
        gui.close();
    }
}
