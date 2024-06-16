// this class is responsible for the lives indicator in the game.

package Sprites;

import Interfaces.Sprite;
import SetGame.Counter;
import SetGame.GameLevel;
import biuoop.DrawSurface;

import java.awt.Color;

public class LivesIndicator implements Sprite {
    private GameLevel gameLevel;
    private Counter lives;

    /**
     * this is the constructor.
     *
     * @param lives     - the lives counter.
     * @param gameLevel - the game.
     */
    public LivesIndicator(Counter lives, GameLevel gameLevel) {
        this.lives = lives;
        this.gameLevel = gameLevel;
        gameLevel.addSprite(this);
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(30, GameLevel.BORDER - 3,
                "Lives:" + " " + this.lives.getValue(), 15);
    }

    @Override
    public void timePassed() {
    }
}
