// The interface represents a hit listener.

package Interfaces;

import Sprites.Ball;
import Sprites.Block;


public interface HitListener {

    /**
     * the function is called whenever the beingHit object is hit.
     *
     * @param beingHit - the block that was hit.
     * @param hitter   - the ball.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
