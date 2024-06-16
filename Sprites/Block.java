// The Block class is a rectangle with color and frame color.

package Sprites;

import Geometry.Line;
import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Velocity;
import Interfaces.Collidable;
import Interfaces.HitListener;
import Interfaces.HitNotifier;
import Interfaces.Sprite;
import SetGame.GameLevel;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Block implements Collidable, Sprite, HitNotifier {

    private Rectangle collisionRectangle;
    private Color color;

    private Color frameColor;

    private static final double EPSILON = Math.pow(10, -10);

    private List<HitListener> hitListeners;

    /**
     * the function initialized the "collision shape" of the object and the color of it.
     *
     * @param collisionRectangle - the rectangle.
     * @param color              - the color of the block
     * @param frameColor         - the frame color (of the block).
     */
    public Block(Rectangle collisionRectangle, Color color, Color frameColor) {
        this.collisionRectangle = collisionRectangle;
        this.color = color;
        this.frameColor = frameColor;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * the function returns the "collision shape" of the object - rectangle.
     *
     * @return collisionRectangle - the rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.collisionRectangle;
    }

    /**
     * the function Notify the block that we collided with it at collisionPoint with a given velocity.
     *
     * @param collisionPoint  - the point we collided with
     * @param currentVelocity - the velocity of the hit.
     * @param hitter          - the ball.
     * @return new velocity expected after the hit (based on the force the block inflicted on us).
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity newVelocity = currentVelocity;
        Line collisionLine = new Line(collisionPoint, collisionPoint);
        Line leftEdge = this.getCollisionRectangle().leftEdge();
        Line rightEdge = this.getCollisionRectangle().rightEdge();
        Line topEdge = this.getCollisionRectangle().topEdge();
        Line bottomEdge = this.getCollisionRectangle().bottomEdge();
        if ((collisionPoint.getX() >= GameLevel.WIDTH && collisionPoint.getY() <= GameLevel.BORDER + 2)
                || (collisionPoint.getX() <= GameLevel.BORDER + 2 && collisionPoint.getY() <= GameLevel.BORDER)
                || (collisionPoint.getX() >= GameLevel.WIDTH - 2 && collisionPoint.getY() <= GameLevel.BORDER)
                || (collisionPoint.getX() >= GameLevel.BORDER && collisionPoint.getY() <= GameLevel.BORDER + 2)) {
            this.notifyHit(hitter);
            return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
        }
        // the collision point hits one of the corners
        if ((collisionLine.isIntersecting(topEdge) && collisionLine.isIntersecting(leftEdge))
                || (collisionLine.isIntersecting(leftEdge) && collisionLine.isIntersecting(bottomEdge))
                || (collisionLine.isIntersecting(bottomEdge) && collisionLine.isIntersecting(rightEdge))
                || (collisionLine.isIntersecting(rightEdge) && collisionLine.isIntersecting(topEdge))) {
            if (currentVelocity.getDx() > currentVelocity.getDy()) {
                this.notifyHit(hitter);
                return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            }
            if (currentVelocity.getDx() < currentVelocity.getDy()) {
                this.notifyHit(hitter);
                return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
            }
            this.notifyHit(hitter);
            return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
        }
        //the collision point hits bottom or top edges
        if ((collisionLine.isIntersecting(topEdge)) || (collisionLine.isIntersecting(bottomEdge))) {
            newVelocity = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        //the collision point hits left or right edges
        if ((collisionLine.isIntersecting(leftEdge)) || (collisionLine.isIntersecting(rightEdge))) {
            newVelocity = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        this.notifyHit(hitter);
        //there is no hit
        return newVelocity;
    }

    /**
     * the function draw the block on the given DrawSurface.
     *
     * @param d - the surface we want to draw on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(frameColor);
        d.drawRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.getCollisionRectangle().getUpperLeft().getY(),
                (int) this.getCollisionRectangle().getWidth(),
                (int) this.getCollisionRectangle().getHeight());
        d.setColor(this.color);
        d.fillRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.getCollisionRectangle().getUpperLeft().getY(),
                (int) this.getCollisionRectangle().getWidth(),
                (int) this.getCollisionRectangle().getHeight());
    }

    /**
     * if we wanted to have animated blocks we could use the timePassed method to implement this behavior.
     */
    public void timePassed() {
    }

    /**
     * the function initializes the block to the game.
     *
     * @param gameLevel - the game
     */
    public void addBlockToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }

    /**
     * the function removes the block from the game.
     *
     * @param gameLevel - the game.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
