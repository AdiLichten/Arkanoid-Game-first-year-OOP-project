// this class is responsible for the key press stoppable animation.

package Screens;

import Interfaces.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class KeyPressStoppableAnimation implements Animation {

    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean running;
    private boolean isAlreadyPressed;

    /**
     * this is the constructor.
     *
     * @param sensor    - the keyboard sensor.
     * @param key       - the key that was pressed.
     * @param animation - the screen that we want to show after the key is pressed.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.running = true;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (this.sensor.isPressed(key) && !isAlreadyPressed) {
            this.running = false;
        } else {
            this.isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}
