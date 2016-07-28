import java.util.ArrayList;


/**
 * An implementation of the Point Range Quad Tree Data Structure.
 *
 * @author Supratim Baruah smb4
 * @version 2014/3/5
 * @param <T> quadtree type
 */
public class PRQuadTree<T>
{
    private int size;
    private PRQuadTreeNode<T> root;

    /**
     * Initializes the quad tree
     * @param size The dimensions of tthe Quad Tree
     */
    public PRQuadTree(int size)
    {
        this.size = size;
        this.root = PRQuadTreeFlyWeight.getFlyWeight();
    }

    /**
     * Insert data into the QuadTree
     * @param x The X coordinate.
     * @param y The Y coordinat.
     * @param data The data to insert.
     * @return The true if successful or false otherwise.
     */
    public boolean insert(int x, int y, T data) {

        try
        {
            root = root.insert(x, y, data, 0, 0, size, size);
            return true;
        }
        catch (DuplicateEntryException d)
        {
            return false;
        }


    }

    /**
     * Remove data on the Quadtree
     * @param x The X coordinate of the data.
     * @param y The Y coordinate of the data.
     * @return The removed data or null.
     */

    public T remove(int x, int y) {
        @SuppressWarnings("unchecked")
        T[] data = (T[])(new Object[1]);
        root = root.remove(x, y, data, 0, 0, size, size);
        return data[0];
    }

    /**
     * Find data on the quadtree
     * @param x The X coordinate of the data.
     * @param y The Y coordinate of the data.
     * @return The data or null.
     */
    public T find(int x, int y) {
        @SuppressWarnings("unchecked")
        T[] data = (T[])(new Object[1]);
        data[0] = null;
        root = root.find(x, y, data, 0, 0, size, size, "find");
        return data[0];
    }

    /**
     *rfind function
     * @param x xmin of the range
     * @param y ymin of the range
     * @param w xmax of the range
     * @param h ymax of the range
     * @param list to store the points
     * @return
     */
    public void rangeSearch(int x, int y, int w, int h, ArrayList<T> list)
    {
        root.rangeSearch(0, 0, size, x, y, w, h, list);
    }

    /**
     * Get a string representation of the quad tree.
     * @return A string representation of the quadtree.
     */
    public String toString()
    {
        return root.toString();
    }

    /**
     * Get a string representation of the quad tree.
     * @return A string representation of the quadtree.
     */
    public String tree()
    {
        return root.tree("", 0, 0, size, size);
    }

    /**
     * reInitialize the quadtree
     */
    public void clear()
    {
        root = PRQuadTreeFlyWeight.getFlyWeight();
    }

}