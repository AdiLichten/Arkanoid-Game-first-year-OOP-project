// this class is responsible for the countdown animation before the game starts.

package Screens;

import Interfaces.Animation;
import SetGame.GameLevel;
import SetGame.SpriteCollection;
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;

public class CountdownAnimation implements Animation {

    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean running;
    private int fontSize = 60;
    private int tempCounter;

    /**
     * this is the constructor.
     *
     * @param numOfSeconds - the number of seconds to count from.
     * @param countFrom    - the number of seconds to count from.
     * @param gameScreen   - the game screen.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.running = false;
        this.tempCounter = countFrom;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLUE.darker().darker());
        d.fillRectangle(0, 0, GameLevel.WIDTH, GameLevel.HEIGHT);
        this.gameScreen.drawAllOn(d);
        Sleeper sleeper = new Sleeper();
        if (this.tempCounter > 0) {
            d.setColor(Color.MAGENTA);
            d.drawText(GameLevel.WIDTH / 2 - 20, GameLevel.HEIGHT / 2, Integer.toString(this.tempCounter), fontSize);
            if (this.tempCounter < numOfSeconds) {
                sleeper.sleepFor((this.countFrom * 1000L) / (long) this.numOfSeconds);
            }
        } else if (this.tempCounter > -2) {
            d.setColor(Color.MAGENTA);
            d.drawText(GameLevel.WIDTH / 2 - 45, GameLevel.HEIGHT / 2, "GO", fontSize);
            sleeper.sleepFor((this.countFrom * 1000L) / (long) this.numOfSeconds);
        } else {
            this.running = true;
        }
        this.tempCounter--;
    }

    @Override
    public boolean shouldStop() {
        return this.running;
    }
}
