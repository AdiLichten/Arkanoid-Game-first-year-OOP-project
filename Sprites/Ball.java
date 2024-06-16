// this class represents a ball in the game.

package Sprites;

import Interfaces.HitListener;
import Interfaces.HitNotifier;
import SetGame.CollisionInfo;
import SetGame.GameLevel;
import SetGame.GameEnvironment;
import Geometry.Line;
import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Velocity;
import Interfaces.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Ball implements Sprite, HitNotifier {

    private Point max;
    private Point min;
    private Point center;
    private int radius;
    private Color color;
    private Velocity v;
    private GameEnvironment gameEnvironment;

    private List<HitListener> hitListeners;

    /**
     * this is a constructor, that initialize the values of the center point, the radius and the color of the ball.
     *
     * @param x     - the x value of the center.
     * @param y     - the y value of the center.
     * @param r     - the radius of the ball.
     * @param color - the color of the ball.
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.v = new Velocity(0, 0);
        this.max = new Point(200, 200);
        this.min = new Point(0, 0);
        this.hitListeners = new ArrayList<>();
    }

    /**
     * the function sets max value of the screen.
     *
     * @param max - the max point
     */
    public void setMax(Point max) {
        this.max = max;
    }

    /**
     * the function sets min value of the screen.
     *
     * @param min - the min point
     */
    public void setMin(Point min) {
        this.min = min;
    }

    /**
     * the function returns the x value of the center.
     *
     * @return x value of the center.
     */
    public int getX() {
        return (int) Math.round(this.center.getX());
    }

    /**
     * the function returns the y value of the center.
     *
     * @return y value of the center.
     */
    public int getY() {
        return (int) Math.round(this.center.getY());
    }

    /**
     * the function sets center point.
     *
     * @param center - the center.
     */
    public void setCenter(Point center) {
        this.center = center;
    }

    /**
     * the function returns the radius of the ball.
     *
     * @return radius of the ball.
     */
    public int getSize() {
        return this.radius;

    }

    /**
     * the function returns the color of the ball.
     *
     * @return the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * the function draw the ball on the given DrawSurface.
     *
     * @param surface - the surface we want to draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.getSize());
        surface.setColor(this.getColor());
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * the function sets the velocity.
     *
     * @param v - the velocity.
     */
    public void setVelocity(Velocity v) {
        this.v = v;
    }

    /**
     * the function sets the velocity values.
     *
     * @param dx - the x value of the speed vector.
     * @param dy - the y value of the speed vector.
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * the function returns the velocity .
     *
     * @return velocity.
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * the function initializes the game environment.
     *
     * @param gameEnvironment - the new game environment.
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * the function compute the ball trajectory and checks if moving on this trajectory will hit anything.
     * if there is no hit, it will move the ball to the end of the trajectory.
     * else, it will move the ball to the hitting point and updates the velocity of the ball.
     */
    public void moveOneStep() {
        Point oldPoint = this.center;
        Line trajectory = new Line(oldPoint, this.getVelocity().applyToPoint(this.center));
        CollisionInfo collisionInfo = this.gameEnvironment.getClosestCollision(trajectory);
        Line center = new Line(this.center.getX(), this.center.getY(), this.center.getX(), this.center.getY());
        // checking if the ball will hit the paddle
        for (int i = 0; i < this.gameEnvironment.getCollidables().size(); i++) {
            if (this.gameEnvironment.getCollidables().get(i).getClass() == Paddle.class) {
                Rectangle rectangle = this.gameEnvironment.getCollidables().get(i).getCollisionRectangle();
                if (!rectangle.intersectionPoints(center).isEmpty()) {
                    this.setCenter(new Point(this.center.getX(), rectangle.getUpperLeft().getY() - 1));
                }
            }
        }
        if (collisionInfo != null) {
            for (int i = 0; i < this.gameEnvironment.getCollidables().size(); i++) {
                if (this.gameEnvironment.getCollidables().get(i).getClass() == Block.class) {
                    Rectangle rectangle = this.gameEnvironment.getCollidables().get(i).getCollisionRectangle();
                    if (!rectangle.intersectionPoints(center).isEmpty()) {
                        this.setCenter(new Point(this.center.getX(), rectangle.getUpperLeft().getY()));
                    }
                } else {
                    this.setVelocity(collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(),
                            this.getVelocity()));
                    this.setCenter(new Point(trajectory.middle().getX(), trajectory.middle().getY()));
                }
            }

        }
        this.setCenter(this.getVelocity().applyToPoint(this.center));
    }

    /**
     * the function updates the ball that it should move one step.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * the function initializes the ball to the game.
     *
     * @param gameLevel - the game
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        this.gameEnvironment = gameLevel.getEnvironment();
    }

    /**
     * the function removes the ball from the game.
     *
     * @param gameLevel - the game
     */
    public void removeFromGame(GameLevel gameLevel) {
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
}
