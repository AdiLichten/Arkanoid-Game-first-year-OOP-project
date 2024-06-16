// the interface represents the information of the level.

package Interfaces;

import Geometry.Point;
import Geometry.Velocity;
import Sprites.Block;

import java.util.List;

public interface LevelInformation {

    /**
     * the function returns the number of the balls in a specific level.
     *
     * @return number of balls.
     */
    int numberOfBalls();

    /**
     * the function initializes the velocity of each ball.
     *
     * @return the list of the velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * the function returns the paddle seed of this level.
     *
     * @return the paddle speed.
     */
    int paddleSpeed();

    /**
     * the function returns the paddle width of this level.
     *
     * @return the paddle width.
     */
    int paddleWidth();

    /**
     * the function returns the level name.
     *
     * @return the level name.
     */
    String levelName();

    /**
     * the function returns background of this level.
     *
     * @return the level background.
     */
    Sprite getBackground();

    /**
     * the function returns the blocks of this level.
     *
     * @return a list of blocks..
     */
    List<Block> blocks();

    /**
     * the function returns number of blocks that should be removed.
     *
     * @return number of blocks.
     */
    int numberOfBlocksToRemove();

    /**
     * the function returns the center points of each ball.
     *
     * @return list of points.
     */
    List<Point> centerPoints();
}
