// The class is responsible for the collision information.

package SetGame;

import Geometry.Point;
import Interfaces.Collidable;

public class CollisionInfo {

    private Point collision;

    private Collidable collidable;

    /**
     * the function initializes the Collision info.
     *
     * @param collision  - the point of the hit.
     * @param collidable - the collidable object.
     */
    public CollisionInfo(Point collision, Collidable collidable) {
        this.collision = collision;
        this.collidable = collidable;
    }

    /**
     * the function returns the point at which the collision occurs.
     *
     * @return point of collision.
     */
    public Point collisionPoint() {
        return this.collision;
    }

    /**
     * the function returns the collidable object involved in the collision.
     *
     * @return collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collidable;
    }


}
