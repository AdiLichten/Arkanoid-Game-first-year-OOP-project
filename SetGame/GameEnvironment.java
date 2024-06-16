// The GameEnvironment class is a collection of collidables.
// It is responsible for managing the collidables and detecting collisions between them.

package SetGame;

import Geometry.Line;
import Geometry.Point;
import Interfaces.Collidable;

import java.util.ArrayList;
import java.util.List;

public class GameEnvironment {

    private List<Collidable> collidables;

    /**
     * the function initialized a new list of collidables.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * the function adds a given collidable to the environment.
     *
     * @param c the new Collidable.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * the function returns the list of the collidables.
     *
     * @return list of the collidables.
     */
    public List<Collidable> getCollidables() {
        return this.collidables;
    }

    //

    /**
     * Assume an object moving from line.start() to line.end(), if this object will not collide with any of the
     * collidables in this collection, the function returns null. Else, the function returns the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory - the trajectory the ball will follow till it collide with an object.
     * @return the collision info with the trajectory.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<Point> interPoints = new ArrayList<>();
        int index = 0;
        for (int i = 0; i < this.collidables.size(); i++) {
            if (trajectory.closestIntersectionToStartOfLine(this.collidables.get(i).getCollisionRectangle()) != null) {
                interPoints.add(trajectory.closestIntersectionToStartOfLine(this.collidables.get(i)
                        .getCollisionRectangle()));
            }
        }
        if (interPoints.size() == 0) {
            return null;
        }
        Point min = interPoints.get(0);
        for (int i = 0; i < interPoints.size(); i++) {
            if (interPoints.get(i).distance(trajectory.start()) < min.distance(trajectory.start())) {
                min = interPoints.get(i);
            }
        }
        for (int i = 0; i < this.getCollidables().size(); i++) {
            if (min.equals(trajectory.closestIntersectionToStartOfLine(this.collidables
                    .get(i).getCollisionRectangle()))) {
                index = i;
            }
        }
        return new CollisionInfo(min, this.getCollidables().get(index));

    }


}
