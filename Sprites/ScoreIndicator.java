// this class is responsible for the score indicator in the game.

package Sprites;

import Interfaces.Sprite;
import SetGame.Counter;
import SetGame.GameLevel;
import biuoop.DrawSurface;

import java.awt.Color;

public class ScoreIndicator implements Sprite {

    private GameLevel gameLevel;
    private Counter currentScore;

    /**
     * this is the constructor.
     *
     * @param currentScore - the score counter.
     * @param gameLevel    - the game.
     */
    public ScoreIndicator(Counter currentScore, GameLevel gameLevel) {
        this.currentScore = currentScore;
        this.gameLevel = gameLevel;
        gameLevel.addSprite(this);
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.LIGHT_GRAY);
        d.fillRectangle(0, 0, GameLevel.WIDTH, GameLevel.BORDER);
        d.setColor(Color.BLACK);
        d.drawText(GameLevel.WIDTH / 2 - 50, GameLevel.BORDER - 3, "Score:" + " " + currentScore.getValue(),
                15);
        d.drawText(GameLevel.WIDTH - 200, GameLevel.BORDER - 3,
                "Level Name:" + " " + this.gameLevel.getLevelInformation().levelName(), 15);
    }

    @Override
    public void timePassed() {
    }
}
