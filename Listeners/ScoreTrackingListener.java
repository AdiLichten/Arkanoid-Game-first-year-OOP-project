// this class is in charge of updating the score of the game.

package Listeners;

import Interfaces.HitListener;
import SetGame.Counter;
import Sprites.Ball;
import Sprites.Block;

public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * this is the constructor.
     *
     * @param scoreCounter - the counter of the points.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}
