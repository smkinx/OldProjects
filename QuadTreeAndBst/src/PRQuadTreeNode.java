import java.util.ArrayList;

/**
 * The Quadtree Node
 *
 * @author Supratim Baruah smb4
 * @version 2014/3/5
 * @param <T>
 */

public abstract class PRQuadTreeNode<T> {



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
    public abstract PRQuadTreeNode<T> insert(int x, int y, T data, int xMin,
            int yMin, int xMax, int yMax);



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

    public abstract PRQuadTreeNode<T> remove(int x, int y, T[] data, int xMin,

            int yMin, int xMax, int yMax);

    /**
     * Return a string representation of the internal node.
     * @param indent indent
     * @param xMin xmin boundary
     * @param yMin ymin boundary
     * @param xMax xmax boundary
     * @param yMax ymax boundary
     * @return a string representation of the internal node.
     */
    public abstract String tree(String indent, int xMin,
            int yMin, int xMax, int yMax);

    /**
     * The find function.
     * @param x x coordinate of the data
     * @param y y coordinate of the data
     * @param data the data to store
     * @param xMin xmin boundary
     * @param yMin ymin boundary
     * @param xMax xmax boundary
     * @param yMax ymax boundary
     * @param command command
     * @return the node found or not
     */
    public abstract PRQuadTreeNode<T> find(int x, int y, T[] data, int xMin,
            int yMin, int xMax, int yMax, String command);

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
    public abstract void rangeSearch(int xMin, int yMin, int size,
            int x, int y, int w, int h, ArrayList<T> list);

}