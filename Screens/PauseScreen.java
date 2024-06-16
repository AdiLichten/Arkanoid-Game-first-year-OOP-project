// this class is responsible for the pause screen.
package Screens;

import Interfaces.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * this is the constructor.
     *
     * @param sensor - the keyboard sensor.
     */
    public PauseScreen(KeyboardSensor sensor) {
        this.keyboard = sensor;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}