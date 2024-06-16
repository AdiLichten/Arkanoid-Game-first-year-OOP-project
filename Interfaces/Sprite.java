// The interface of the sprite object

package Interfaces;

import biuoop.DrawSurface;

public interface Sprite {
    /**
     * the function draw the sprite to the screen.
     *
     * @param d the surface we will draw on
     */
    void drawOn(DrawSurface d);

    /**
     * the function notify the sprite that time has passed.
     */
    void timePassed();
}