/**
 * Used to retrieve quad specs
 *
 * @author Supratim Baruah smb4
 * @version 2014/3/5
 */
public class QuadData {

    private int quad;
    private int dx;
    private int dy;

    /**
     * Constructs a city wrapper class
     * @param dx change in x coord
     * @param dy change in the y coord
     * @param quad the city class
     */
    public QuadData(int quad, int dx, int dy)
    {
        this.quad = quad;
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Get quad function
     * @return quad
     */
    public int getQuad() {
        return quad;
    }

    /**
     * Get Dx function
     * @return dx
     */
    public int getDx() {
        return dx;
    }

    /**
     * Get dy function
     * @return dy
     */
    public int getDy() {
        return dy;
    }
}