/**
 * Class to store record to be stored at the node
 *
 * @author Supratim Baruah smb4
 * @version 2014/3/5
 * @param <T>
 */
public class CityRecord<T> {

    private int x;
    private int y;
    private T city;

    /**
     * Constructs a city wrapper class
     * @param x x coord
     * @param y the y coord
     * @param city the city class
     */
    public CityRecord(int x, int y, T city)
    {
        this.x = x;
        this.y = y;
        this.city = city;
    }

    /**
     * Get x function
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * Get y function
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * Get city function
     * @return city
     */
    public T getCity() {
        return city;
    }

    /**
     * String representation of the class
     * @return string
     */
    public String toString() {
        return ((City) getCity()).getName() + " " +
        ((City) getCity()).getPopulation() + " (" + getX() + ", " +
        getY() + ")";
    }
}