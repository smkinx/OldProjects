import java.util.ArrayList;


/**
 * Fly Weight leaf node
 *
 * @author Supratim Baruah smb4
 * @version 2014/3/5
 * @param <T>
 */
public class PRQuadTreeFlyWeight<T> extends PRQuadTreeLeaf<T> {
    @SuppressWarnings("rawtypes")
    private static PRQuadTreeFlyWeight fly = new PRQuadTreeFlyWeight();

    /**
     * Get a reference to the flyweight.
     * @return Get a reference to the flyweight
     * @param <T> the stored data
     */
    @SuppressWarnings("unchecked")
    public static <T> PRQuadTreeFlyWeight<T> getFlyWeight()
    {
        return (PRQuadTreeFlyWeight<T>)fly;
    }

    /**
     * Insertions to a Fly Weight return a newly instantiated leaf node.
     * @param x x coordinate of the data
     * @param y y coordinate of the data
     * @param data the data to store
     * @param xMin xmin boundary
     * @param yMin ymin boundary
     * @param xMax xmax boundary
     * @param yMax ymax boundary
     * @return a new leaf node with the data inserted.
     */
    @Override
    public PRQuadTreeNode<T> insert(int x, int y, T data, int xMin, int yMin,
            int xMax, int yMax)
    {
        PRQuadTreeNode<T> leaf = new PRQuadTreeLeaf<T>();
        return leaf.insert(x, y, data, xMax, yMax, xMax, yMax);
    }


    /**
     * Return the string representation of flyweight
     * @return empty
     */
    public String toString()
    {
        return "Empty\n";
    }

    /**
     * Return the string representation of flyweight for tree
     * @param indent indent
     * @param xMin xmin boundary
     * @param yMin ymin boundary
     * @param xMax xmax boundary
     * @param yMax ymax boundary
     * @return empty
     */
    public String tree(String indent, int xMin, int yMin, int xMax, int yMax)
    {
        return indent + "Empty" + "\n";
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
            int w, int h, ArrayList<T> list)
    {
        //Nothing to add from FlyWeight
    }


}