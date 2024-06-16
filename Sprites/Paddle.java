// The Paddle class is responsible for the paddle that the user controls in the game.

package Sprites;

import Geometry.Line;
import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Velocity;
import Interfaces.Collidable;
import Interfaces.Sprite;
import SetGame.GameLevel;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

public class Paddle implements Sprite, Collidable {

    private KeyboardSensor keyboard;
    private Rectangle paddle;
    private Color color;
    private Velocity velocity;
    private static final double PADDLE_HEIGHT = 20;
    private Point middlePoint;
    private Point upperLeft;

    /**
     * the function initializes the paddle.
     *
     * @param keyboard - the sensor of the keyboard.
     * @param width    - the width of the paddle.
     * @param color    - the color of the paddle.
     * @param velocity - the velocity of the paddle.
     */
    public Paddle(KeyboardSensor keyboard, double width, Color color, int velocity) {
        this.keyboard = keyboard;
        this.middlePoint = new Point((double) GameLevel.WIDTH / 2, GameLevel.HEIGHT - PADDLE_HEIGHT / 2);
        this.upperLeft = new Point(middlePoint.getX() - width / 2, middlePoint.getY() - PADDLE_HEIGHT / 2);
        this.paddle = new Rectangle(upperLeft, width, PADDLE_HEIGHT);
        this.color = color;
        this.velocity = new Velocity(velocity, 0);
    }

    /**
     * the function makes the paddle move left.
     */
    public void moveLeft() {
        Point newPlace = new Point(this.paddle.getUpperLeft().getX() - this.velocity.getDx(),
                this.paddle.getUpperLeft().getY());
        if (newPlace.getX() < GameLevel.BORDER) {
            newPlace = new Point(GameLevel.BORDER, newPlace.getY());
        }
        this.paddle = new Rectangle(newPlace, this.paddle.getWidth(), this.paddle.getHeight());
    }

    /**
     * the function makes the paddle move right.
     */
    public void moveRight() {
        Point newPlace = new Point(this.getCollisionRectangle().getUpperLeft().getX() + this.velocity.getDx(),
                this.paddle.getUpperLeft().getY());
        if (newPlace.getX() + this.paddle.getWidth() > GameLevel.WIDTH - GameLevel.BORDER) {
            newPlace = new Point(GameLevel.WIDTH - GameLevel.BORDER - this.paddle.getWidth(), newPlace.getY());
        }
        this.paddle = new Rectangle(newPlace, this.paddle.getWidth(), this.paddle.getHeight());
    }

    /**
     * the function make the paddle moves left or right according to the user will.
     */
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * the function draws the paddle on the screen.
     *
     * @param d - the surface we will draw on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
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
     * the function adds the paddle to the collidable list.
     *
     * @return the shape of the paddle.
     */
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }

    /**
     * the function calculates the speed according to the velocity vector.
     *
     * @param dx - the x value of the velocity vector.
     * @param dy - the y value of the velocity vector.
     * @return speed - the speed according to the velocity vector.
     */
    private double speed(double dx, double dy) {
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * the function calculates new velocity expected after a hit.
     *
     * @param collisionPoint  - the point of collision.
     * @param currentVelocity - the velocity before the collision.
     * @param hitter          - the ball.
     * @return the new velocity.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double speed = speed(currentVelocity.getDx(), currentVelocity.getDy());
        Line collisionLine = new Line(collisionPoint, collisionPoint);
        Point point2 = new Point(this.getCollisionRectangle().getUpperLeft().getX()
                + this.getCollisionRectangle().getWidth() / 5, this.getCollisionRectangle().getUpperLeft().getY());
        Point point3 = new Point(this.getCollisionRectangle().getUpperLeft().getX()
                + (this.getCollisionRectangle().getWidth() / 5) * 2,
                this.getCollisionRectangle().getUpperLeft().getY());
        Point point4 = new Point(this.getCollisionRectangle().getUpperLeft().getX()
                + (this.getCollisionRectangle().getWidth() / 5) * 3,
                this.getCollisionRectangle().getUpperLeft().getY());
        Point point5 = new Point(this.getCollisionRectangle().getUpperLeft().getX()
                + (this.getCollisionRectangle().getWidth() / 5) * 4,
                this.getCollisionRectangle().getUpperLeft().getY());
        Point point6 = new Point(this.getCollisionRectangle().getUpperLeft().getX()
                + this.getCollisionRectangle().getWidth(), this.getCollisionRectangle().getUpperLeft().getY());
        Line region1 = new Line(this.getCollisionRectangle().getUpperLeft(), point2);
        Line region2 = new Line(point2, point3);
        Line region3 = new Line(point3, point4);
        Line region4 = new Line(point4, point5);
        Line region5 = new Line(point5, point6);
        if (collisionLine.isIntersecting(region1)) {
            return Velocity.fromAngleAndSpeed(300, speed);
        }
        if (collisionLine.isIntersecting(region2)) {
            return Velocity.fromAngleAndSpeed(330, speed);
        }
        if (collisionLine.isIntersecting(region3)) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        if (collisionLine.isIntersecting(region4)) {
            return Velocity.fromAngleAndSpeed(30, speed);
        }
        if (collisionLine.isIntersecting(region5)) {
            return Velocity.fromAngleAndSpeed(60, speed);
        }
        if (collisionLine.isIntersecting(this.getCollisionRectangle().leftEdge())) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
    }

    /**
     * the function add the paddle to the game.
     *
     * @param g - the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
