import java.util.*;
import java.io.*;

/**
 * The BinaryTree class.
 * @author Supratim Baruah smb4
 * @version 2014/2/10
 * @param <T>
 */
@SuppressWarnings("unchecked")
public class BinaryTree<T extends Comparable<T>>
{
    private BtNode<T, City> root;
    private String tree = "";

    /**
     * Constructor
     */
    BinaryTree()
    {
        root = null;
    }

    /**
     * Insert a record into the tree.
     * @param key value of the record.
     * @param c The record to insert.
     */
    public void insert(T key, City c)
    {
        root = inserthelp(root, key, c);
    }

    /**
     * Remove a record from the tree.
     * @param k T value of record to remove.
     * @param c the Citi record
     * @return true if removed
     */
    public boolean remove(T k, City c)
    {
        root = removehelp(root, k, c);
        return root == null;

    }

    /**
     * Find kth position on the tree.
     * @param k the position to retrieve
     * @return the node
     */
    public BtNode<T, City> findkth(int k)
    {
        return findkthhelper(root, k);
    }

    /**
     * Retrieves the records in the range
     * @param r1 min range
     * @param r2 max range
     */
    public void findRange(T r1, T r2)
    {
        findRangehelp(root, r1, r2);
    }

    /**
     * Find a record in the tree.
     * @param k the key to the record
     * @return the record
     */
    public City find(T k)
    {
        return findhelp(root, k);
    }

    /**
     * Stores the String representation of the tree
     * @param s adding to the tree
     */
    public void setPrintTree(String s)
    {
        tree = tree + s + "\n" ;
    }

    /**
     * Empty outs the string.
     */
    public void emptyPrintTree()
    {
        tree = "";
    }

    /**
     * gets the String representation of the tree
     * @return the string
     */
    public String getPrintTree()
    {
        return tree;
    }

    /**
     * Insert a new record to the tree
     * @param rt the root
     * @param k key to the record
     * @param c the record
     * @return
     */
    private BtNode<T, City> inserthelp(BtNode<T, City> rt, T k, City c)
    {
        if (rt == null)
        {
            return new BtNode<T, City>(k, c);
        }
        if (( rt.getKey()).compareTo(k) > 0)
        {
            rt.setLeft(inserthelp(rt.left(), k, c));
        }
        else if (( rt.getKey()).compareTo(k) <= 0)
        {
            rt.setRight(inserthelp(rt.right(), k, c));
        }
        return rt;
    }

    /**
     * Finds the record on the tree
     * @param root
     * @param k the key
     * @return
     */
    private City findhelp(BtNode<T, City> rt, T k)
    {
        if (rt == null)
        {
            return null;
        }

        else if (rt.getKey().compareTo(k) > 0)
        {
            return findhelp(rt.left(), k);
        }

        else if (rt.getKey().compareTo(k) == 0)
        {
            setPrintTree(rt.getVal().toString());
            return findhelp(rt.right(), k);
        }

        else
        {
            return findhelp(rt.right(), k);
        }

    }

    /**
     * Removes a record from the tree
     * @return The tree with the node removed
     */
    private BtNode<T, City> removehelp(BtNode<T, City> rt, T k, City c)
    {
        if (rt == null)
        {
            return null;
        }
        if (rt.getKey().compareTo(k) > 0)
        {
            rt.setLeft(removehelp(rt.left(), k, c));
        }
        else if (rt.getKey().compareTo(k) < 0)
        {
            rt.setRight(removehelp(rt.right(), k, c));
        }
        else if (rt.getKey().compareTo(k) == 0 &&
                (rt.getVal().toString()).compareTo(c.toString()) != 0)
        {
            rt.setRight(removehelp(rt.right(), k, c));
        }
        else if (rt.getKey().compareTo(k) == 0 &&
                (rt.getVal().toString()).compareTo(c.toString()) == 0)
        {
            if (rt.left() == null)
            {
                return rt.right();
            }
            else if (rt.right() == null)
            {
                return rt.left();
            }
            else
            {
                BtNode<T, City> temp = getmin(rt.right());
                rt.setVal(temp.getVal());
                rt.setKey(temp.getKey());
                rt.setRight(deletemin(rt.right()));
            }
        }
        return rt;
    }

    /**
     * Gets the minimum node on the tree.
     * @param rt the root
     * @return the node
     */
    private BtNode<T, City> getmin(BtNode<T, City> rt)
    {
        if (rt.left() == null)
        {
            return rt;
        }
        else
        {
            return getmin(rt.left());
        }
    }

    /**
     * Deletes the minimum node on the tree.
     * @param rt the root
     * @return the node
     */
    private BtNode<T, City> deletemin(BtNode<T, City> rt)
    {
        if (rt.left() == null)
        {
            return rt.right();
        }
        else
        {
            rt.setLeft(deletemin(rt.left()));
        }
        return rt;
    }

    /**
     * Re-initiates the tree;
     */
    public void clear()
    {
        root = null;
    }

    /**
     * Finds the size of the left tree node.
     * @param rt the root
     * @return the size
     */
    public int findSize(BtNode<T, City> rt)
    {
        if (rt == null)
        {
            return 0;
        }
        int size = 1;
        if (rt.right() != null)
        {
            size = size + findSize(rt.right());
        }
        if (rt.left() != null)
        {
            size = size + findSize(rt.left());
        }
        return size;


    }

    /**
     * Finds the record of the kth position on the tree
     * @param rt the root
     * @param k the position
     * @return
     */
    private BtNode<T, City> findkthhelper(BtNode<T, City> rt, int k)
    {
        if (rt == null)
        {
            return null;
        }

        int findSize = findSize(rt.left());
        //            System.out.println(findSize);
        //            System.out.println(k);
        //            System.out.println(root.getVal().getName());
        if (findSize  == k)
        {
            return rt;
        }
        else if (findSize > k)
        {
            return findkthhelper(rt.left(), k);
        }
        else
        {
            return findkthhelper(rt.right(), k - findSize - 1);
        }
    }

    /**
     * Print the tree.
     */
    public void print()
    {
        printhelp(root, "");
    }

    /**
     * Print the tree
     * @param rt the root
     * @param indent The string to go before
     */
    private void printhelp(BtNode<T, City> rt, String indent)
    {
        if (rt == null) {
            return;
        }

        String newIndent;
        if (indent.equals(""))
        {
            newIndent = "    ";
        }
        else
        {
            newIndent = "    " + indent;
        }

        printhelp(rt.left(), newIndent);
        setPrintTree(indent + rt.getVal().toString());
        printhelp(rt.right(), newIndent);
    }

    /**
     * Puts the tree in ascending order
     */
    public void inOrder()
    {
        inOrderhelp(root);
    }

    /**
     * Puts the tree in ascending order
     * @param rt the root
     */
    public void inOrderhelp(BtNode<T, City> rt) {
        if (rt != null)
        {
            inOrderhelp(rt.left());
            setPrintTree(rt.getVal().toString());

            inOrderhelp(rt.right());
        }
    }


    /**
     * Retrieves the records in the range
     * @param rt the root
     * @param r1 min range
     * @param r2 max range
     */
    @SuppressWarnings("unchecked")
    private void findRangehelp(BtNode<T, City> rt, T r1, T r2)
    {
        if ( rt != null )
        {
            if (rt.getKey().compareTo(r1) > 0)
            {
                findRangehelp(rt.left(), r1, r2);
            }

            if ((rt.getKey().compareTo(r1) >= 0 &&
                    rt.getKey().compareTo(r2) <= 0))
            {
                setPrintTree(rt.getVal().toString());
            }

            if (rt.getKey().compareTo(r2) <= 0)
            {
                findRangehelp(rt.right(), r1, r2);
            }


        }

    }
}

