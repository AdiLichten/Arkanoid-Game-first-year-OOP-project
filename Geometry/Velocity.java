// the class represents the speed of the ball.
package Geometry;

public class Velocity {
    private double dx;
    private double dy;

    /**
     * the function initializes the velocity.
     *
     * @param dx - the x value of the speed vector.
     * @param dy - the y value of the speed vector.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * the function returns the x value of the speed vector.
     *
     * @return double - x value of the speed vector.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * the function returns the y value of the speed vector.
     *
     * @return double - y value of the speed vector.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * the function takes a point with position (x,y) and returns a new point with position (x+dx, y+dy).
     *
     * @param p - the added values to the point.
     * @return point  - with the new position.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * the function takes change the velocity of the ball by calculating the x and y vectors of speed.
     *
     * @param angle - the angle of the vector
     * @param speed the speed vector.
     * @return velocity - the x and y vectors of speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.cos(angle);
        double dy = speed * Math.sin(angle);
        return new Velocity(dx, dy);
    }
}