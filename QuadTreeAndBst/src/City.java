

/**
 * The class to store data on the cities.
 * @author Supratim Baruah
 * @version 2014/2/1
 */
public class City
{
    private String name;
    private int population;
    //private String payload;
    private int x;
    private int y;

    /**
     * Constructs a city data class
     * @param name the name of the city
     * @param population the population of the city
     * @param x x coord
     * @param y the y coord
     */
    public City(String name, int population, int x, int y)
    {
        this.name = name;
        this.population = population;
        this.x = x;
        this.y = y;
    }

    /**
     * Set name function
     * @param name the name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Set population function
     * @param population the population
     */
    public void setPopulation(int population)
    {
        this.population = population;
    }

    /**
     * Set x function
     * @param x the x coord
     */
    public void setX(int x)
    {
        this.x = x;
    }

    /**
     * Set y function
     * @param y the y coord
     */
    public void setY(int y)
    {
        this.y = y;
    }

    /**
     * Get name function
     * @return name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Get population function
     * @return population
     */
    public int getPopulation()
    {
        return population;
    }
    /**
     * Get x function
     * @return x
     */
    public int getX()
    {
        return x;
    }

   /**
    * Get y function
    * @return y
    */
    public int getY()
    {
        return y;
    }

    /**
     * The toSting function
     * @return the string representation of the class
     */
    public String toString()
    {
        return name + " " + population + " " + x + " " + y;
    }
    /**
     * The toSting function
     * @return the string representation of the class
     */
    public String toString1()
    {
        return name + " " + population + " (" + x + ", " + y + ")";
    }
}