package structure.math;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import structure.constants.Constants;


/**
 * Simple class for handling Cartesian coordinates in 3 dimensional space with
 * double numbers.
 * @author Abdullah Kahraman
 * @version 3.0
 * @since 3.0
 * @see Point3i
 */
public class Point3d {
    /**
     * Cartesian X-coordinates.
     */
    private double xCoordinate;
    //--------------------------------------------------------------------------
    /**
     * Cartesian Y-coordinates.
     */
    private double yCoordinate;
    //--------------------------------------------------------------------------
    /**
     * Cartesian Z-coordinates.
     */
    private double zCoordinate;
    //--------------------------------------------------------------------------
    /**
     * Size of point.
     */
    private double pointRadius;
    //--------------------------------------------------------------------------
    /**
     * Constructor.
     * @param x
     *        - Cartesian X-coordinates.
     * @param y
     *        - Cartesian Y-coordinates.
     * @param z
     *        - Cartesian Z-coordinates.
     */
    public Point3d(final double x, final double y, final double z) {
        this(x, y, z, 0);
    }
    //--------------------------------------------------------------------------
    /**
     * Constructor.
     * @param x
     *        - Cartesian X-coordinates.
     * @param y
     *        - Cartesian Y-coordinates.
     * @param z
     *        - Cartesian Z-coordinates.
     * @param radius
     *        - Radius of this point.
     */
    public Point3d(final double x, final double y, final double z,
                   final double radius) {
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.zCoordinate = z;
        this.pointRadius = radius;
    }
    //--------------------------------------------------------------------------
    /**
     * Returns the Cartesian X-coordinate of this point.
     * @return double number representing the Cartesian X-coordinate of this
     *         point.
     */
    public final double getX() {
        return xCoordinate;
    }
    //--------------------------------------------------------------------------
    /**
     * Returns the Cartesian Y-coordinate of this point.
     * @return double number representing the Cartesian Y-coordinate of this
     *         point.
     */
    public final double getY() {
        return yCoordinate;
    }
    //--------------------------------------------------------------------------
    /**
     * Returns the Cartesian Z-coordinate of this point.
     * @return double number representing the Cartesian Z-coordinate of this
     *         point.
     */
    public final double getZ() {
        return zCoordinate;
    }
    //--------------------------------------------------------------------------
    /**
     * Returns the radius of this point.
     * @return double number representing the radius of this point.
     */
    public final double getRadius() {
        return this.pointRadius;
    }
    //--------------------------------------------------------------------------
    /**
     * Performs addition to the Cartesian coordinates of this point.
     * @param x
     *        - double to add to X-coordinate
     * @param y
     *        - double to add to Y-coordinate
     * @param z
     *        - double to add to Z-coordinate
     * @return Point3d object with new Cartesian coordinates
     */
    public final Point3d add(final double x, final double y, final double z) {
        return new Point3d(this.getX() + x,
                           this.getY() + y,
                           this.getZ() + z,
                           this.getRadius()
                          );
    }
    //--------------------------------------------------------------------------
    /**
     * Checks whether two points have the same Cartesian coordinates, within
     * an error of 0.001 Angstroem.
     * @param  point3dObject
     *              - Point3d object
     * @return {@code TRUE} if both points have the same coordinates,
     *         {@code FALSE} otherwise.
     * @throws ClassCastException if point3dObject is not of type Point3d.
     */
    public final boolean equals(final Object point3dObject) 
                                                     throws ClassCastException {
        final double errorMargin = 0.001;
        if (point3dObject instanceof Point3d) {
            Point3d point3d = (Point3d) point3dObject;

            if (Math.abs(this.xCoordinate - point3d.getX()) <= errorMargin
                &&
                Math.abs(this.yCoordinate - point3d.getY()) <= errorMargin
                &&
                Math.abs(this.zCoordinate - point3d.getZ()) <= errorMargin) {
                return true;
            }
        } else {
            throw new ClassCastException("ERROR: Object " + point3dObject
                                       + " is not of type Point3d"
                                       + Constants.LINE_SEPERATOR);
        }
    return false;
    }
    //--------------------------------------------------------------------------
    /**
     * Creates a copy of this Point3d object.
     * @return Copy of this Point3d object
     */
    public final Point3d copy() {
        return new Point3d(this.getX(),
                           this.getY(),
                           this.getZ(),
                           this.getRadius()
                          );
    }
    //--------------------------------------------------------------------------
    /**
     * Returns a String representation of this point.
     * @return String object holding the comma separated X,Y,Z coordinates of
     * this point.
     */
    public final String toString() {
        NumberFormat decFormat = new DecimalFormat("0.00");
        return "X:" + decFormat.format(this.getX()) + ", "
             + "Y:" + decFormat.format(this.getY()) + ", "
             + "Z:" + decFormat.format(this.getZ())
             + Constants.LINE_SEPERATOR;
    }
}
