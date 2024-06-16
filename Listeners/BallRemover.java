// this class is in charge of removing balls from the game.

package Listeners;

import Interfaces.HitListener;
import SetGame.Counter;
import SetGame.GameLevel;
import Sprites.Ball;
import Sprites.Block;

public class BallRemover implements HitListener {

    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * this is the constructor.
     *
     * @param gameLevel           - the game.
     * @param remainingBalls - the counter of the balls.
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        hitter.removeHitListener(this);
        this.remainingBalls.decrease(1);
    }
}
