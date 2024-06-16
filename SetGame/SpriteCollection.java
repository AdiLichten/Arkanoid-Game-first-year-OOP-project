// The SpriteCollection class holds a collection of sprites.

package SetGame;

import Interfaces.Sprite;
import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

public class SpriteCollection {

    private List<Sprite> sprites;

    /**
     * the function initializes a new list of sprites.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * the function adds a new sprite to the sprites list.
     *
     * @param s - the new sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * the function returns a list of the sprites.
     *
     * @return list of sprites.
     */
    public List<Sprite> getSprites() {
        return this.sprites;
    }

    /**
     * the function call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> temp = new ArrayList<>(this.sprites);
        for (Sprite sprite : temp) {
            sprite.timePassed();
        }
    }

    /**
     * the function call drawOn(d) on all sprites.
     *
     * @param d - the surface we will draw on.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : this.sprites) {
            sprite.drawOn(d);
        }
    }
}

