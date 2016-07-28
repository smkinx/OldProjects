import java.util.ArrayList;

/**
 * Quadtree internal node
 *
 * @author Supratim Baruah smb4
 * @version 2014/3/5
 * @param <T>
 */
public class PRQuadTreeInternal<T> extends PRQuadTreeNode<T> {
    private PRQuadTreeNode[] region;

    //
    private static final int NW = 0;
    private static final int NE = 1;
    private static final int SW = 2;
    private static final int SE = 3;


    /**
     * Instantiate a new Internal node.
     */
    @SuppressWarnings("unchecked")
    public PRQuadTreeInternal()
    {
        region = (PRQuadTreeNode<T>[])(new PRQuadTreeNode[4]);
        region[NW] = PRQuadTreeFlyWeight.getFlyWeight();
        region[NE] = PRQuadTreeFlyWeight.getFlyWeight();
        region[SW] = PRQuadTreeFlyWeight.getFlyWeight();
        region[SE] = PRQuadTreeFlyWeight.getFlyWeight();
    }


    /**
     * The insert function.
     *
     * @param x x coordinate of the data
     * @param y y coordinate of the data
     * @param data the data to store
     * @param xMin xmin boundary
     * @param yMin ymin boundary
     * @param xMax xmax boundary
     * @param yMax ymax boundary
     * @return the node inserted
     */
    @SuppressWarnings("unchecked")
    public PRQuadTreeNode<T> insert(int x, int y, T data, int xMin, int yMin,
            int xMax, int yMax)
    {
        int quad;
        int dx;
        int dy;

        // Determine the quadrant to insert into.
        QuadData qData = split(x, y, xMin, yMin, xMax);
        quad = qData.getQuad();
        dx = qData.getDx();
        dy = qData.getDy();

        // Do the insert
        region[quad] = region[quad].insert(x, y, data, xMin + dx, yMin + dy,
                xMax / 2, yMax / 2);
        return this;
    }

    /**
     * The remove function.
     *
     * @param x x coordinate of the data
     * @param y y coordinate of the data
     * @param data the data to store
     * @param xMin xmin boundary
     * @param yMin ymin boundary
     * @param xMax xmax boundary
     * @param yMax ymax boundary
     * @return the node removed
     */
    @SuppressWarnings("unchecked")
    public PRQuadTreeNode<T> remove(int x, int y, T[] data, int xMin, int yMin,
            int xMax, int yMax)
    {
        int quad;
        int dx;
        int dy;

        // Determine the quadrant to insert into.
        QuadData qData = split(x, y, xMin, yMin, xMax);
        quad = qData.getQuad();
        dx = qData.getDx();
        dy = qData.getDy();

        // Do the remove
        region[quad] = region[quad].remove(x, y, data, xMin + dx, yMin + dy,
                xMax / 2, yMax / 2);

        if (canCon())
        {
            return con();
        }
        else
        {
            return this;
        }

    }


    /**
     * Can contract funtion
     * @return true if internal can contract
     */
    @SuppressWarnings("unchecked")
    public boolean canCon()
    {
        int i = 0;
        int j = 0;
        for (int a = 0; a < 4; a++)
        {
            if (region[a] instanceof PRQuadTreeLeaf)
            {
//                String str = region[a].toString();
                if (!region[a].toString().equals("Empty\n"))
                {
                    i++;
                }
            }
            if (region[a] instanceof PRQuadTreeFlyWeight)
            {
                j++;
            }

        }
        return (i == 1 && j == 3);
    }

    /**
     * Contract the internal
     * @return the lead node to set it equal to
     */
    @SuppressWarnings("unchecked")
    public PRQuadTreeNode<T> con()
    {

        for (int a = 0; a < 4; a++)
        {
            if (region[a] instanceof PRQuadTreeLeaf)
            {
//                String str = region[a].toString();
                if (!region[a].toString().equals("Empty\n"))
                {
                    return region[a];
                }
            }
        }
        return this;
    }

    /**
     * Splits the quadrant in 4 different region
     *
     * @param x x coordinate of the data
     * @param y y coordinate of the data
     * @param xMin xmin boundary
     * @param yMin ymin boundary
     * @param size the size
     * @return quadrant
     */
    private QuadData split(int x, int y, int xMin, int yMin, int size)
    {
        int quad;
        int dx;
        int dy;

        if (x < (xMin + (size) / 2 )) {
            dx = 0;
            if (y < (yMin + (size) / 2)) {
                dy = 0;
                quad = NW;
            }
            else
            {
                dy = (size / 2);
                quad = SW;
            }
        }
        else
        {
            dx = (size / 2);
            if (y < (yMin + (size) / 2)) {
                dy = 0;
                quad = NE;
            }
            else {
                dy = (size / 2);
                quad = SE;
            }
        }

        return new QuadData(quad, dx, dy);
    }



    /**
     * Return a string representation of the internal node.
     * @return a string representation of the internal node.
     */
    public String toString()
    {
        String str = "Internal\n";
        for (int i = 0; i < 4; i++)
        {
            str += region[i].toString();
        }
        return str;
    }

    /**
     * Return a string representation of the internal node.
     * @param indent indent
     * @param xMin xmin boundary
     * @param yMin ymin boundary
     * @param xMax xmax boundary
     * @param yMax ymax boundary
     * @return a string representation of the internal node.
     */
    public String tree(String indent, int xMin, int yMin, int xMax, int yMax)
    {
        int dx;
        int dy;
        int xMid = xMin + (xMax) / 2;
        int yMid = yMin + (yMax) / 2;
        String str = indent + "Internal (" + xMid + ", " + yMid + ")" + "\n";
        if (indent.equals(""))
        {

            indent = "....";
        }
        else
        {
            indent = "...." + indent;
        }

        for (int i = 0; i < 4; i++) {
            if (i == 0)
            {
                dx = 0;
                dy = 0;
            }
            else if (i == 1)
            {
                dx = (xMax / 2);
                dy = 0;

            }
            else if (i == 2)
            {
                dx = 0;
                dy = (yMax / 2);

            }
            else
            {
                dx = (xMax / 2);
                dy = (yMax / 2);

            }

            str += region[i].tree(indent, xMin + dx , yMin + dy,
                    xMax / 2, yMax / 2);

        }
        return str;

    }


    /**
     * The find function.
     *
     * @param x x coordinate of the data
     * @param y y coordinate of the data
     * @param data the data to store
     * @param xMin xmin boundary
     * @param yMin ymin boundary
     * @param xMax xmax boundary
     * @param yMax ymax boundary
     * @param command the command
     * @return the node found or not
     */
    @SuppressWarnings("unchecked")
    public PRQuadTreeNode<T> find(int x, int y, T[] data, int xMin, int yMin,
            int xMax, int yMax, String command)
    {
        int quad;
        int dx;
        int dy;

        QuadData qData = split(x, y, xMin, yMin, xMax);
        quad = qData.getQuad();
        dx = qData.getDx();
        dy = qData.getDy();

        region[quad] =  region[quad].find(x, y, data, xMin + dx, yMin + dy,
                xMax / 2, yMax / 2, "find");

        return this;

    }

    /**
     * The rfind function.
     *
     * @param xMin xmin boundary
     * @param yMin ymin boundary
     * @param size the dimention of the quad
     * @param x range search x
     * @param y range search y
     * @param w range search width
     * @param h range search height
     * @param list to store the points
     */
    @SuppressWarnings("unchecked")
    public void rangeSearch(int xMin, int yMin, int size,
            int x, int y, int w, int h, ArrayList<T> list)
    {
        if (Geometry.intersects(xMin, yMin, size / 2, x, y, w, h)) {
            region[NW].rangeSearch(xMin, yMin, size / 2, x, y, w, h, list);
        }
        if (Geometry.intersects(xMin + size / 2, yMin, size / 2, x, y, w, h)) {
            region[NE].rangeSearch(xMin + size / 2, yMin, size / 2, x, y, w, h,
                    list);
        }
        if (Geometry.intersects(xMin, yMin + size / 2, size / 2, x, y, w, h)) {
            region[SW].rangeSearch(xMin, yMin + size / 2, size / 2, x, y, w, h,
                    list);
        }
        if (Geometry.intersects(xMin + size / 2, yMin + size / 2, size / 2, x,
                y, w, h)) {
            region[SE].rangeSearch(xMin + size / 2, yMin + size / 2, size / 2,
                    x, y, w, h, list);
        }

    }


}