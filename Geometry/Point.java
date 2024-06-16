
package Geometry;

public class Point {

    private static final double EPSILON = Math.pow(10, -10);

    private double x;

    private double y;

    /**
     * the function initializes the point.
     *
     * @param x represent the x value of the point.
     * @param y represent the y value of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * the function return the distance between this point and the other point.
     *
     * @param other represent other point.
     * @return double that represent the distance.
     */
    public double distance(Point other) {
        return Math.sqrt((this.getX() - other.getX()) * (this.getX() - other.getX())
                + (this.getY() - other.getY()) * (this.getY() - other.getY()));
    }

    /**
     * the function checks if the points are equal.
     *
     * @param other represent other point.
     * @return boolean - true if the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        if (this == null || other == null) {
            return false;
        }
        return (Math.abs(this.getX() - other.getX()) < EPSILON && Math.abs(this.getY() - other.getY()) < EPSILON);
    }

    /**
     * the function return the x value of this point.
     *
     * @return double x that represent the x value.
     */
    public double getX() {
        return x;
    }

    /**
     * the function return the y value of this point.
     *
     * @return double y that represent the y value.
     */
    public double getY() {
        return y;
    }
}