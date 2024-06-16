// The interface represents a collidable object.

package Interfaces;

import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Velocity;
import Sprites.Ball;

public interface Collidable {

    /**
     * the function return the "collision shape" of the object.
     *
     * @return "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * the function Notify the object that we collided with it at collisionPoint with a given velocity.
     *
     * @param collisionPoint  - the point we collided with
     * @param currentVelocity - the velocity of the hit.
     * @param hitter          - the ball.
     * @return new velocity expected after the hit (based on the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}