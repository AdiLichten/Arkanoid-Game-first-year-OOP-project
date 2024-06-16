// the class represent a point in the plane.
package Geometry;

import java.util.ArrayList;
import java.util.List;


public class Rectangle {

    private Point upperLeft;

    private double width;
    private double height;

    /**
     * the function Create a new rectangle with location and width/height.
     *
     * @param upperLeft represent the upper left point of the rectangle
     * @param width     represent the width of the rectangle.
     * @param height    represent the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * the function return a (possibly empty) List of intersection points with the specified line.
     *
     * @param line the line that we need to check if it is intersects with the rectangle.
     * @return List of intersection points with the specified line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        Point lowerLeft = new Point(this.getUpperLeft().getX(), this.getUpperLeft().getY() + this.getHeight());
        Point upperRight = new Point(this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY());
        Point lowerRight = new Point(this.getUpperLeft().getX() + this.getWidth(),
                this.getUpperLeft().getY() + this.getHeight());
        List<Point> interPoints = new ArrayList<>();
        Point interLeft = line.intersectionWith(new Line(this.getUpperLeft(), lowerLeft));
        if (interLeft != null) {
            interPoints.add(interLeft);
        }
        Point interUp = line.intersectionWith(new Line(this.getUpperLeft(), upperRight));
        if (interUp != null) {
            interPoints.add(interUp);
        }
        Point interRight = line.intersectionWith(new Line(upperRight, lowerRight));
        if (interRight != null) {
            interPoints.add(interRight);
        }
        Point interLow = line.intersectionWith(new Line(lowerLeft, lowerRight));
        if (interLow != null) {
            interPoints.add(interLow);
        }
        // deals the cases that we have same intersection points
        if (interUp != null && (interUp.equals(interLeft) || interUp.equals(interRight))) {
            interPoints.remove(interUp);
            interPoints.add(interUp);
        }
        // deals the cases that we have same intersection points
        if (interLow != null && (interLow.equals(interLeft) || interLow.equals(interRight))) {
            interPoints.remove(interLow);
            interPoints.add(interLow);
        }
        return interPoints;
    }

    /**
     * the function return the width of the rectangle.
     *
     * @return double the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * the function return the height of the rectangle.
     *
     * @return double the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }


    /**
     * the function return the upper-left point of the rectangle.
     *
     * @return point the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * the function return the right edge of the rectangle.
     *
     * @return line that represent the right edge of the rectangle.
     */
    public Line rightEdge() {
        return new Line(new Point(this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY()),
                new Point(this.getUpperLeft().getX() + this.getWidth(),
                        this.getUpperLeft().getY() + this.getHeight()));
    }

    /**
     * the function return the left edge of the rectangle.
     *
     * @return line that represent the left edge of the rectangle.
     */
    public Line leftEdge() {
        return new Line(this.getUpperLeft(), new Point(this.getUpperLeft().getX(),
                this.getUpperLeft().getY() + this.getHeight()));
    }

    /**
     * the function return the top edge of the rectangle.
     *
     * @return line that represent the top edge of the rectangle.
     */
    public Line topEdge() {
        return new Line(new Point(this.getUpperLeft().getX(), this.getUpperLeft().getY() + this.getHeight()),
                new Point(this.getUpperLeft().getX() + this.getWidth(),
                        this.getUpperLeft().getY() + this.getHeight()));
    }

    /**
     * the function return the bottom edge of the rectangle.
     *
     * @return line that represent the bottom edge of the rectangle.
     */
    public Line bottomEdge() {
        return new Line(this.getUpperLeft(), new Point(this.getUpperLeft().getX() + this.getWidth(),
                this.getUpperLeft().getY()));
    }
}