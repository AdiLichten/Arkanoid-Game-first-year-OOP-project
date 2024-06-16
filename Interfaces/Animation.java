// The Animation interface is responsible for the animation of the game.
package Interfaces;

import biuoop.DrawSurface;

public interface Animation {

    /**
     * the function draws the frame of the game.
     *
     * @param d - the draw surface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * the function returns if the level should be stopped.
     * @return boolean value, rather the level should stop or not.
     */
    boolean shouldStop();
}
