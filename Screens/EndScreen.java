// this class is responsible for the end screen of the game.

package Screens;

import Interfaces.Animation;
import SetGame.Counter;
import biuoop.DrawSurface;

public class EndScreen implements Animation {

    private Counter score;
    private boolean isWin;

    /**
     * this is the constructor.
     *
     * @param score - the game score.
     * @param isWin - a boolean value indicating win or loss.
     */
    public EndScreen(Counter score, boolean isWin) {
        this.score = score;
        this.isWin = isWin;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (!isWin) {
            d.drawText(100, 400, "Game Over. Your score is " + this.score.getValue(), 30);
        } else {
            d.drawText(100, 400, "You Win! Your score is " + this.score.getValue(), 30);
        }
    }

    @Override
    public boolean shouldStop() {
        return true;
    }
}
