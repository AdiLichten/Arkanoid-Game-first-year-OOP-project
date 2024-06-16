// this class is responsible for the background of the game.

package Sprites;

import Geometry.Point;
import Interfaces.Sprite;
import SetGame.GameLevel;
import biuoop.DrawSurface;

import java.awt.Color;

public class Background implements Sprite {

    private Color color;
    private String levelName;

    /**
     * this is the constructor.
     *
     * @param color     - the background color.
     * @param levelName - the level name.
     */
    public Background(Color color, String levelName) {
        this.color = color;
        this.levelName = levelName;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle(0, 0, GameLevel.WIDTH, GameLevel.HEIGHT);
        switch (this.levelName) {
            case "Direct Hit":
                int radius = 50;
                Point center = new Point((double) GameLevel.WIDTH / 2, (double) GameLevel.HEIGHT / 4);
                d.setColor(Color.BLUE);
                for (int i = 1; i <= 3; i++) {
                    d.drawCircle((int) center.getX(), (int) center.getY(), radius * i);
                }
                d.drawLine(GameLevel.WIDTH / 2, 0, GameLevel.WIDTH / 2, GameLevel.HEIGHT / 2 + 50);
                d.drawLine(GameLevel.WIDTH / 2 - 200, GameLevel.HEIGHT / 4, GameLevel.WIDTH - 200,
                        GameLevel.HEIGHT / 4);
                break;
            case "Wide Easy":
                Point sun = new Point(100, 200);
                d.setColor(new Color(255, 254, 145));
                for (int i = 25; i < GameLevel.WIDTH - GameLevel.BORDER; i += 10) {
                    d.drawLine((int) sun.getX(), (int) sun.getY(), i, GameLevel.HEIGHT / 2);
                }
                d.fillCircle((int) sun.getX(), (int) sun.getY(), 50);
                d.setColor(new Color(255, 220, 62));
                d.fillCircle((int) sun.getX(), (int) sun.getY(), 45);
                break;
            case "Green 3":
                d.setColor(Color.gray.darker().darker().darker());
                d.fillRectangle(135, 400, 30, 50);
                d.setColor(Color.gray.darker().darker());
                d.fillRectangle(145, 200, 10, 200);
                d.setColor(Color.black.brighter());
                d.fillRectangle(100, 450, 100, 250);
                d.setColor(Color.orange);
                d.fillCircle(150, 200, 10);
                d.setColor(Color.red);
                d.fillCircle(150, 200, 6);
                d.setColor(Color.white);
                d.fillCircle(150, 200, 2);
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        d.fillRectangle(105 + j * 20, 460 + i * 30, 10, 20);
                    }
                }
                break;
            case "Final Four":
                d.setColor(Color.gray.brighter());
                d.fillCircle(150, 400, 20);
                d.fillCircle(180, 395, 30);
                d.fillCircle(210, 400, 30);
                for (int i = 0; i < 10; i++) {
                    d.drawLine(140 + i * 10, 410, 80 + i * 10, 800);
                }
                d.setColor(Color.gray);
                d.fillCircle(600, 500, 20);
                d.fillCircle(630, 495, 30);
                d.fillCircle(660, 500, 30);
                for (int i = 0; i < 10; i++) {
                    d.drawLine(590 + i * 10, 510, 510 + i * 10, 800);
                }
                break;
            default:
                System.out.println("ERROR!");
                break;
        }
    }

    @Override
    public void timePassed() {

    }
}
