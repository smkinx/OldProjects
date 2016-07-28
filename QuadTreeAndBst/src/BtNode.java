import java.util.Comparator;

/**
 * The node for the BST.
 * @author Supratim Baruah
 * @version 2014/2/1
 * @param <T>
 * @param <City>
 */
public class BtNode<T extends Comparable<T>, City> implements Comparator
{
    private BtNode<T, City> left;
    private BtNode<T, City> right;
    private City val;
    private T key;

    /**
     * Constructs a city data class
     * @param k the key to the node
     * @param val City to store at the node
     */
    public BtNode(T k, City val)
    {
        key = k;
        this.left = null;
        this.right = null;
        this.val = val;
    }

    /**
     * The toSting function
     * @return the String representation of the class
     */
    @Override
    public String toString()
    {
        return val.toString();
    }

    /**
     * Set key function
     * @param k key
     */
    public void setKey(T k)
    {
        key = k;
    }

    /**
     * Set City function
     * @param k city value
     */
    public void setVal(City k)
    {
        val = k;
    }

    /**
     * Set left node function
     * @param a the record
     */
    public void setLeft(BtNode<T, City> a)
    {
        left = a;
    }

    /**
     * Set right node function
     * @param a the record
     */
    public void setRight(BtNode<T, City> a)
    {
        right = a;
    }

    /**
     * Returns the Left node
     * @return left node
     */
    public BtNode<T, City> left()
    {
        return left;
    }

    /**
     * Returns the right node
     * @return right node
     */
    public BtNode<T, City> right()
    {
        return right;
    }

    /**
     * Returns the key to the node
     * @return key
     */
    public T getKey()
    {
        return key;
    }

    /**
     * Returns the City class where the data is stored
     * @return City class
     */
    public City getVal()
    {
        return val;
    }



    /**
     * The compare btNode function
     * @param n1 btNode
     * @param n2 btNode
     * @return 0 if same
     */
    @SuppressWarnings("unchecked")
    @Override
    public int compare(Object n1, Object n2) {
        int a = 0;
        if (((BtNode<T, City>) n1).getKey() == ((BtNode<T, City>) n2).getKey())
        {
            City t1 = (City) ((BtNode<T, City>) n1).getVal();
            City t2 = (City) ((BtNode<T, City>) n2).getVal();
            if (t1.toString().compareTo(t2.toString()) == 0)
            {
                return 0;
            }
        }
        else
        {
            a = (((BtNode<T, City>) n1).getKey()).compareTo(
                    ((BtNode<T, City>) n2).getKey());
        }

        if (a > 0)
        {
            return -1; //n1 < n2
        }
        else
        {
            return 1;  //n1 > n2
        }


    }


}
