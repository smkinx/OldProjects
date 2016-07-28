import java.awt.Rectangle;

/**
 *  The class to help with rfind
 *
 *  @author  Supratim Baruah (smb4)
 *  @version 2014/03/7
 */
public class Geometry {


    /**
     * Check if the range intersects with the quad
     * @param xMin quad xMin
     * @param yMin quad yMin
     * @param size quad xMax, yMax
     * @param x min of range
     * @param y min of range
     * @param w max of range
     * @param h max of range
     * @return true if intersect
     */
    public static boolean intersects(int xMin, int yMin, int size , int x,
            int y, int w, int h)
    {
        Rectangle rect = new Rectangle(xMin, yMin, size, size);
        Rectangle rect1 = new Rectangle(x, y, w, h);
        return rect.intersects(rect1);
    }

    /**
     * Checks if the range contains the point
     * @param xR x min of range
     * @param yR y min of range
     * @param w max of range
     * @param h max of range
     * @param x x of the point
     * @param y y of the point
     * @return true if x, y in the range
     */
    public static boolean contains(int xR, int yR, int w, int h, int x, int y)
    {
        Rectangle rect1 = new Rectangle(xR, yR, w, h);
        return rect1.contains(x, y);
    }
}

