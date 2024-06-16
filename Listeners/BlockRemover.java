// this class is in charge of removing blocks from the game.

package Listeners;

import Interfaces.HitListener;
import SetGame.Counter;
import SetGame.GameLevel;
import Sprites.Ball;
import Sprites.Block;

public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * this is the constructor.
     *
     * @param gameLevel            - the game.
     * @param remainingBlocks - the counter of the blocks.
     */
    public BlockRemover(GameLevel gameLevel, Counter remainingBlocks) {
        this.remainingBlocks = remainingBlocks;
        this.gameLevel = gameLevel;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.gameLevel);
        beingHit.removeHitListener(this);
        this.remainingBlocks.decrease(1);
    }
}
