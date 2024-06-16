// the class represents a line in the plane.
package Geometry;

import java.util.List;

public class Line {
    private Point start;
    private Point end;
    private double x1;
    private double y1;
    private double x2;
    private double y2;

    /**
     * the function initializes the line.
     *
     * @param start represent the value of the starting point.
     * @param end   represent the value of the ending point.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * the function initializes the values of the line.
     *
     * @param x1 represent the x value of the starting point.
     * @param y1 represent the y value of the starting point.
     * @param x2 represent the x value of the ending point.
     * @param y2 represent the y value of the ending point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * the function return the length of the line.
     *
     * @return double that represent the length of the line.
     */
    public double length() {
        return Math.sqrt((this.start.getX() - this.end.getX()) * (this.start.getX() - this.end.getX())
                + (this.start.getY() - this.end.getY()) * (this.start.getY() - this.end.getY()));
    }

    /**
     * the function returns the middle point of the line.
     *
     * @return double that represent the middle of the line.
     */
    public Point middle() {
        return new Point((this.start.getX() + this.end.getX()) / 2, (this.start.getY() + this.end.getY()) / 2);
    }

    /**
     * the function returns the starting point of the line.
     *
     * @return Point that represent the starting point of the line.
     */
    public Point start() {
        return start;
    }

    /**
     * the function returns the ending point of the line.
     *
     * @return Point that represent the ending point of the line.
     */
    public Point end() {
        return end;
    }

    /**
     * the function checks if the lines are intersecting.
     *
     * @param other represent the other line.
     * @return boolean - true if the lines intersects, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        if (this.start.getX() == this.end.getX() || other.start.getX() == other.end.getX()) {
            if (this.verticalAndParallel(other) || other.verticalAndParallel(this)) {
                return true;
            }
            if (this.start.getX() == this.end.getX() && other.start.getX() == other.end.getX()
                    && this.start.getX() == other.start.getX() && this.linesAreVertical(other)) {
                return true;
            }
            if (other.start.getX() != other.end.getX() && this.oneLineIsVertical(other)) {
                return true;
            }
            return this.start.getX() != this.end.getX() && other.oneLineIsVertical(this);
        }
        if (this.start.getY() == this.end.getY() || other.start.getY() == other.end.getY()) {
            if (this.start.getY() == this.end.getY() && other.start.getY() == other.end.getY()
                    && this.start.getY() == other.start.getY() && this.linesAreParallel(other)) {
                return true;
            }
            if (other.start.getY() != other.end.getY() && this.oneLineIsParallel(other)) {
                return true;
            }
            return (this.start.getY() != this.end.getY() && other.oneLineIsParallel(this));
        }
        if (this.slope() == other.slope() && this.linesHaveTheSameSlope(other)) {
            return true;
        }
        return this.slope() != other.slope() && this.linesHaveDifferentSlope(other);
    }

    /**
     * the function checks if the lines have only 1 intersecting point.
     *
     * @param other represent the other line.
     * @return returns the intersection point if the lines intersect, and null otherwise .
     */
    public Point intersectionWith(Line other) {
        if (this.isIntersecting(other) && this.start.getX() == this.end.getX()
                && other.start.getY() == other.end.getY()) {
            return new Point(this.start.getX(), other.start.getY());
        } else if (this.isIntersecting(other) && other.start.getX() == other.end.getX()
                && this.start.getY() == this.end.getY()) {
            return new Point(other.start.getX(), this.start.getY());
        } else if (this.isIntersecting(other) && this.start.getX() == this.end.getX()
                && other.start.getX() == other.end.getX()) {
            if ((this.start.getY() == other.start.getY()
                    && (this.end.getY() < Math.min(other.start.getY(), other.end.getY())
                    || this.end.getY() > Math.max(other.start.getY(), other.end.getY())))
                    || this.start.getY() == other.end.getY()
                    && (this.end.getY() < Math.min(other.start.getY(), other.end.getY())
                    || this.end.getY() > Math.max(other.start.getY(), other.end.getY()))) {
                return new Point(this.start.getX(), this.start.getY());
            }
            if (this.end.getY() == other.start.getY()
                    && (this.start.getY() < Math.min(other.start.getY(), other.end.getY())
                    || this.end.getY() > Math.max(other.start.getY(), other.end.getY()))
                    || this.end.getY() == other.end.getY()
                    && (this.start.getY() < Math.min(other.start.getY(), other.end.getY())
                    || this.end.getY() > Math.max(other.start.getY(), other.end.getY())))  {
                return new Point(this.end.getX(), this.end.getY());
            }
        } else if (this.isIntersecting(other) && this.start.getX() == this.end.getX()
                && other.start.getX() != other.end.getX()) {
            return new Point(this.start.getX(), other.slope() * this.start.getX()
                    - other.slope() * other.start.getX() + other.start.getY());
        } else if (this.isIntersecting(other) && other.start.getX() == other.end.getX()
                && this.start.getX() != this.end.getX()) {
            return new Point(other.start.getX(), this.slope() * other.start.getX()
                    - this.slope() * this.start.getX() + this.start.getY());
        } else if (this.isIntersecting(other) && this.start.getY() == this.end.getY()
                && other.start.getY() == other.end.getY()) {
            if (this.start.getX() == other.start.getX() && this.end.getX() == other.end.getX()) {
                return null;
            }
            if (this.start.getX() == other.end.getX() && this.end.getX() == other.start.getX()) {
                return null;
            }
            if (this.start.getX() == other.start.getX() && (this.start.getX()
                    <= Math.min(other.start.getX(), other.end.getX()) && this.end.getX()
                    <= Math.min(other.start.getX(), other.end.getX()))) {
                return new Point(this.start.getX(), this.start.getY());
            }
            if (this.start.getX() == other.end.getX() && (this.start.getX()
                    <= Math.min(other.start.getX(), other.end.getX()) && this.end.getX()
                    <= Math.min(other.start.getX(), other.end.getX()))) {
                return new Point(this.start.getX(), this.start.getY());
            }
            if (this.end.getX() == other.start.getX() && (this.start.getX()
                    <= Math.min(other.start.getX(), other.end.getX()) && this.end.getX()
                    <= Math.min(other.start.getX(), other.end.getX()))) {
                return new Point(this.end.getX(), this.end.getY());
            }
            if (this.end.getX() == other.end.getX() && (this.start.getX()
                    <= Math.min(other.start.getX(), other.end.getX()) && this.end.getX()
                    <= Math.min(other.start.getX(), other.end.getX()))) {
                return new Point(this.end.getX(), this.end.getY());
            }
            if (this.start.getX() == other.start.getX() && (this.start.getX()
                    >= Math.max(other.start.getX(), other.end.getX()) && this.end.getX()
                    >= Math.max(other.start.getX(), other.end.getX()))) {
                return new Point(this.start.getX(), this.start.getY());
            }
            if (this.start.getX() == other.end.getX() && (this.start.getX()
                    >= Math.max(other.start.getX(), other.end.getX()) && this.end.getX()
                    >= Math.max(other.start.getX(), other.end.getX()))) {
                return new Point(this.start.getX(), this.start.getY());
            }
            if (this.end.getX() == other.start.getX() && (this.start.getX()
                    >= Math.max(other.start.getX(), other.end.getX()) && this.end.getX()
                    >= Math.max(other.start.getX(), other.end.getX()))) {
                return new Point(this.end.getX(), this.end.getY());
            }
            if (this.end.getX() == other.end.getX() && (this.start.getX()
                    >= Math.max(other.start.getX(), other.end.getX()) && this.end.getX()
                    >= Math.max(other.start.getX(), other.end.getX()))) {
                return new Point(this.end.getX(), this.end.getY());
            }
        } else if (this.isIntersecting(other) && this.start.getY() == this.end.getY()
                && other.start.getY() != other.end.getY()) {
            return new Point((this.start.getY() - other.start.getY()) / other.slope()
                    + other.start.getX(), this.start.getY());
        } else if (this.isIntersecting(other) && other.start.getY() == other.end.getY()
                && this.start.getY() != this.end.getY()) {
            return new Point((other.start.getY() - this.start.getY()) / this.slope()
                    + this.start.getX(), other.start.getY());
        } else if (this.isIntersecting(other) && this.slope() == other.slope()) {
            if (this.start.getX() == other.start.getX() && this.end.getX() == other.end.getX()) {
                return null;
            }
            if (this.start.getX() == other.end.getX() && this.end.getX() == other.start.getX()) {
                return null;
            }
        } else if (this.isIntersecting(other) && this.slope() == other.slope()) {
            if (this.start.getX() == other.start.getX() || this.start.getX() == other.end.getX()) {
                return new Point(this.start.getX(), this.start.getY());
            }
            if (this.end.getX() == other.start.getX() || this.end.getX() == other.end.getX()) {
                return new Point(this.end.getX(), this.end.getY());
            }
        } else if (this.isIntersecting(other) && this.slope() != other.slope()) {
            double interX = (this.slope() * this.start.getX() - other.slope() * other.start.getX() - this.start.getY()
                    + other.start.getY()) / (this.slope() - other.slope());
            double interY = this.slope() * interX - this.slope() * this.start.getX() + this.start.getY();
            return new Point(interX, interY);
        }
        return null;
    }

    /**
     * the function checks if the lines are the same.
     *
     * @param other represent the other line.
     * @return boolean - true if the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        return (this.start.getX() == other.start.getX() && this.start.getY() == other.start.getY())
                || (this.start.getX() == other.end.getX() && this.start.getY() == other.end.getY())
                || (this.end.getX() == other.start.getX() && this.end.getY() == other.start.getY())
                || (this.end.getX() == other.end.getX() && this.end.getY() == other.end.getY());
    }

    /**
     * the function calculate the slope of the line.
     *
     * @return double that represent the slope.
     */
    private double slope() {
        return (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());

    }

    /**
     * the function checks if the lines have intersection point and one line is parallel and the other is vertical.
     *
     * @param other represent the other line.
     * @return boolean - true if they intersect and one is parallel and the other is vertical, false otherwise.
     */
    private boolean verticalAndParallel(Line other) { //the lines look something like this: | -
        if (other.start.getY() == other.end.getY()) {
            return this.start.getX() >= Math.min(other.start.getX(), other.end.getX())
                    && this.start.getX() <= Math.max(other.start.getX(), other.end.getX())
                    && other.start.getY() >= Math.min(this.start.getY(), this.end.getY())
                    && other.start.getY() <= Math.max(this.start.getY(), this.end.getY());
        }
        return false;
    }

    /**
     * the function checks if the lines have intersection point and 2 of them are vertical.
     *
     * @param other represent the other line.
     * @return boolean - true if they intersect and 2 of them are vertical, false otherwise.
     */
    private boolean linesAreVertical(Line other) { // the lines look something like this: | |
        return (this.start.getY() >= Math.min(other.start.getY(), other.end.getY())
                && this.start.getY() <= Math.max(other.start.getY(), other.end.getY()))
                || (this.end.getY() >= Math.min(other.start.getY(), other.end.getY())
                && this.end.getY() <= Math.max(other.start.getY(), other.end.getY()))
                || (other.start.getY() >= Math.min(this.start.getY(), this.end.getY())
                && other.start.getY() <= Math.max(this.start.getY(), this.end.getY()))
                || (other.end.getY() >= Math.min(this.start.getY(), this.end.getY())
                && other.end.getY() <= Math.max(this.start.getY(), this.end.getY()));
    }

    /**
     * the function checks if the lines have intersection point and 1 of them is vertical.
     *
     * @param other represent the other line.
     * @return boolean - true if they intersect and 1 of them is vertical, false otherwise.
     */
    private boolean oneLineIsVertical(Line other) { // the lines look something like this: | \
        return other.slope() * this.start.getX() - other.slope() * other.start.getX() + other.start.getY()
                >= Math.min(this.start.getY(), this.end.getY())
                && other.slope() * this.start.getX() - other.slope() * other.start.getX() + other.start.getY()
                <= Math.max(this.start.getY(), this.end.getY())
                && this.start.getX() >= Math.min(other.start.getX(), other.end.getX())
                && this.start.getX() <= Math.max(other.start.getX(), other.end.getX());
    }

    /**
     * the function checks if the lines have intersection point and 2 of them are parallel.
     *
     * @param other represent the other line.
     * @return boolean - true if they intersect, and they are parallel, false otherwise.
     */
    private boolean linesAreParallel(Line other) { // the lines look something like this: - -
        return ((this.start.getX() >= Math.min(other.start.getX(), other.end.getX())
                && (this.start.getX() <= Math.max(other.start.getX(), other.end.getX())))
                || (this.end.getX() >= Math.min(other.start.getX(), other.end.getX())
                && this.end.getX() <= Math.max(other.start.getX(), other.end.getX()))
                || (other.start.getX() >= Math.min(this.start.getX(), this.end.getX())
                && other.start.getX() <= Math.max(this.start.getX(), this.end.getX()))
                || (other.end.getX() >= Math.min(this.start.getX(), this.end.getX())
                && other.end.getX() <= Math.max(this.start.getX(), this.end.getX())));
    }

    /**
     * the function checks if the lines have intersection point and 1 of them is parallel.
     *
     * @param other represent the other line.
     * @return boolean - true if they intersect, and 1 of them is parallel, false otherwise.
     */
    private boolean oneLineIsParallel(Line other) { //the lines look something like this: - \
        return (this.start.getY() - other.start.getY()) / other.slope() + other.start.getX()
                >= Math.min(this.start.getX(), this.end.getX())
                && (this.start.getY() - other.start.getY()) / other.slope() + other.start.getX()
                <= Math.max(this.start.getX(), this.end.getX())
                && this.start.getY() >= Math.min(other.start.getY(), other.end.getY())
                && this.start.getY() <= Math.max(other.start.getY(), other.end.getY());
    }

    /**
     * the function checks if the lines have intersection point and if they have the same slope.
     *
     * @param other represent the other line.
     * @return boolean - true if they intersect, and they have the same slope, false otherwise.
     */
    private boolean linesHaveTheSameSlope(Line other) { // the lines look something like this: \ \
        return this.start.getY() == (other.slope() * this.start.getX() - other.slope() * other.start.getX()
                + other.start.getY()) && this.linesAreParallel(other);
    }

    /**
     * the function checks if the lines have intersection point, and they don't have the same slope.
     *
     * @param other represent the other line.
     * @return boolean - true if they intersect, and they don't have the same slope, false otherwise.
     */
    private boolean linesHaveDifferentSlope(Line other) { // the lines look something like this: \/
        double interX = (this.slope() * this.start.getX() - other.slope() * other.start.getX()
                - this.start.getY() + other.start.getY()) / (this.slope() - other.slope());
        double interY = this.slope() * interX - this.slope() * this.start.getX() + this.start.getY();
        return interX >= Math.min(this.start.getX(), this.end().getX())
                && interX <= Math.max(this.start.getX(), this.end().getX())
                && interX >= Math.min(other.start.getX(), other.end().getX())
                && interX <= Math.max(other.start.getX(), other.end().getX())
                && interY >= Math.min(this.start.getY(), this.end.getY())
                && interY <= Math.max(this.start.getY(), this.end.getY())
                && interY >= Math.min(other.start.getY(), other.end.getY())
                && interY <= Math.max(other.start.getY(), other.end.getY());
    }

    /**
     * the function checks if the rectangle intersects this line.
     *
     * @param rect represents the rectangle that the line might intersect with.
     * @return point - of the closest intersection point to the start of the line,
     * or null if this line does not intersect with the rectangle.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> interPoints = rect.intersectionPoints(this);
        if (interPoints.isEmpty()) {
            return null;
        }
        if (interPoints.size() == 1) {
            return interPoints.get(0);
        }
        // the distance between the starting point of this line to the first intersection point.
        Double dist0 = this.start.distance(interPoints.get(0));
        // the distance between the starting point of this line to the second intersection point.
        Double dist1 = this.start.distance(interPoints.get(1));
        if (dist0 < dist1) {
            return interPoints.get(0);
        }
        return interPoints.get(1);
    }

}