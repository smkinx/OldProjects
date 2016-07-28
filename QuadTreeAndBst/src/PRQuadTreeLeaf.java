import java.util.ArrayList;


/**
 * QuadTree leaf node to store the data
 *
 * @author Supratim Baruah smb4
 * @version 2014/3/5
 * @param <T>
 */

public class PRQuadTreeLeaf<T> extends PRQuadTreeNode<T> {

    private CityRecord<T> rec;

    /**
     * Instantiate a new empty PRQuadTree Leaf.
     */
    public PRQuadTreeLeaf()
    {
        rec = null;
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
     * @return the inserted value
     */
    @SuppressWarnings("unchecked")
    public PRQuadTreeNode<T> insert(int x, int y, T data, int xMin, int yMin,
            int xMax, int yMax)
    {
        if (rec != null)
        {
            if (rec.getX() == x && rec.getY() == y)
            {
                throw new DuplicateEntryException();
            }
        }
        if (rec != null)
        {

            PRQuadTreeNode<T> internal = new PRQuadTreeInternal<T>();

            internal = internal.insert(rec.getX(), rec.getY(), rec.getCity(),
                    xMin, yMin, xMax, yMax);

            internal = internal.insert(x, y, data, xMin, yMin, xMax, yMax);

            return internal;
        }
        else
        {

            rec = new CityRecord(x, y , data);
            return this;
        }
    }

    /**
     * the remove function
     * @param x x coordinate of the data
     * @param y y coordinate of the data
     * @param data the data to store
     * @param xMin xmin boundary
     * @param yMin ymin boundary
     * @param xMax xmax boundary
     * @param yMax ymax boundary
     * @return the removed node
     */
    public PRQuadTreeNode<T> remove(int x, int y, T[] data, int xMin, int yMin,
            int xMax, int yMax)
    {
        data[0] = null;

        if (rec != null)
        {
            if ((rec.getX() == x) && (rec.getY() == y))
            {
                data[0] = rec.getCity();
                data[0] = null;
            }
        }
        if (data[0] == null)
        {
            return PRQuadTreeFlyWeight.getFlyWeight();
        }
        else
        {
            return this;
        }

    }

    /**
     * Return the string representation of the points in this leaf.
     * @return This node as a string.
     */
    public String toString()
    {
        String str = "";
        str = rec.toString() + "\n";
        return str;
    }

    /**
     * Return the string representation of the points in this leaf.
     * @param indent indent
     * @param xMin xmin boundary
     * @param yMin ymin boundary
     * @param xMax xmax boundary
     * @param yMax ymax boundary
     * @return This node as a string.
     */
    @Override
    public String tree(String indent, int xMin, int yMin, int xMax, int yMax)
    {
        String str = "";
        str = indent + rec.toString() + "\n";
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
     * @param command command
     * @return the found node or not
     */
    public PRQuadTreeNode<T> find(int x, int y, T[] data, int xMin, int yMin,
            int xMax, int yMax, String command)
    {
        if (command.equals("find"))
        {
            {
                if (rec != null)
                {
                    if ((rec.getX() == x) && (rec.getY() == y))
                    {
                        data[0] = rec.getCity();
                        return this;
                    }
                }
            }
        }

        return PRQuadTreeFlyWeight.getFlyWeight();
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
    @Override
    public void rangeSearch(int xMin, int yMin, int size, int x, int y,
            int w, int h, ArrayList<T> list) {
        if (Geometry.contains(x, y, w, h, rec.getX(), rec.getY()))
        {
            list.add(rec.getCity());
        }

    }

}