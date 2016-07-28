
import student.TestCase;

/**
 *  The test class for quad class
 *
 *  @author  Supratim Baruah (smb4)
 *  @version 2014/3/5
 */
public class QuadTest
    extends TestCase
{
    private Quad d1;
    private Quad  d2;
    private Quad  d3;
    private Quad  d4;
    private Quad  d5;
    /**
     * sets up the variables;
     */
    public void setUp()
    {
        d1 = new Quad();
        d2 = new Quad();
        d3 = new Quad();
        d4 = new Quad();
        d5 = new Quad();
    }

    /**
     * Tests out the inserts.
     */
    public void testInsert()
    {
        setSystemIn(
//                "insert Home 5 0 0\n" +
//                "insert Away 10 64 64\n" +
//                "tree location\n"
                "insert Balwin 30404 256 256\n" +
                "insert Alexandria 139966 770 770\n" +
                "insert Danville 43055 256 770\n" +
                "insert Lynchburg 75568 770 256\n" +
                "insert Portsmouth 95535 500 500\n" +
                "insert Suffolk 84585 700 700\n" +
                "insert Radford 16408 725 725\n" +
                "insert Williamsburg 14068 1023 1023\n" +
                "insert Fredericksburg 24286 344 355\n" +
                "insert Norfolk 242803 200 700\n" +
                "insert Baltimore 620961 700 200\n" +
                "tree location\n" +
                "rfind 0 0 1000 1000\n" +
                "find location 256 770\n" +
                "delete location 256 770\n" +
                "makenull\n" +
                "tree location\n" +
                "rfind 0 0 1000 1000\n" +
                "find location 256 770\n" +
               "rfind 100 100 900 900\n" +
                "delete location 256 770\n");
        d1.main(null);

        assertEquals("insert Balwin 30404 256 256\n" +
                "insert Alexandria 139966 770 770\n" +
                "insert Danville 43055 256 770\n" +
                "insert Lynchburg 75568 770 256\n" +
                "insert Portsmouth 95535 500 500\n" +
                "insert Suffolk 84585 700 700\n" +
                "insert Radford 16408 725 725\n" +
                "insert Williamsburg 14068 1023 1023\n" +
                "insert Fredericksburg 24286 344 355\n" +
                "insert Norfolk 242803 200 700\n" +
                "insert Baltimore 620961 700 200\n" +
                "tree location\n" +
                "Internal (512, 512)\n" +
                "....Internal (256, 256)\n" +
                "........Empty\n" +
                "........Empty\n" +
                "........Empty\n" +
                "........Internal (384, 384)\n" +
                "............Internal (320, 320)\n" +
                "................Balwin 30404 (256, 256)\n" +
                "................Empty\n" +
                "................Empty\n" +
                "................Fredericksburg 24286 (344, 355)\n" +
                "............Empty\n" +
                "............Empty\n" +
                "............Portsmouth 95535 (500, 500)\n" +
                "....Internal (768, 256)\n" +
                "........Baltimore 620961 (700, 200)\n" +
                "........Empty\n" +
                "........Empty\n" +
                "........Lynchburg 75568 (770, 256)\n" +
                "....Internal (256, 768)\n" +
                "........Norfolk 242803 (200, 700)\n" +
                "........Empty\n" +
                "........Empty\n" +
                "........Danville 43055 (256, 770)\n" +
                "....Internal (768, 768)\n" +
                "........Internal (640, 640)\n" +
                "............Empty\n" +
                "............Empty\n" +
                "............Empty\n" +
                "............Internal (704, 704)\n" +
                "................Suffolk 84585 (700, 700)\n" +
                "................Empty\n" +
                "................Empty\n" +
                "................Radford 16408 (725, 725)\n" +
                "........Empty\n" +
                "........Empty\n" +
                "........Internal (896, 896)\n" +
                "............Alexandria 139966 (770, 770)\n" +
                "............Empty\n" +
                "............Empty\n" +
                "............Williamsburg 14068 (1023, 1023)\n" +
                "rfind 0 0 1000 1000\n" +
                "Balwin 30404 (256, 256)\n" +
                "Fredericksburg 24286 (344, 355)\n" +
                "Portsmouth 95535 (500, 500)\n" +
                "Baltimore 620961 (700, 200)\n" +
                "Lynchburg 75568 (770, 256)\n" +
                "Norfolk 242803 (200, 700)\n" +
                "Danville 43055 (256, 770)\n" +
                "Suffolk 84585 (700, 700)\n" +
                "Radford 16408 (725, 725)\n" +
                "Alexandria 139966 (770, 770)\n" +
                "find location 256 770\n" +
                "Danville 43055 (256, 770)\n" +
                "delete location 256 770\n" +
                "makenull\n" +
                "tree location\n" +
                "Empty\n" +
                "rfind 0 0 1000 1000\n" +
                "Not found\n" +
                "find location 256 770\n" +
                "Not found\n" +
                "rfind 100 100 900 900\n" +
                "Not found\n" +
                "delete location 256 770\n" +
                "Not found\n",
                systemOut().getHistory());

    }

    /**
     * Tests out the finds
     */
    public void testFind()
    {
//        System.out.println("C".compareTo("A"));
//        setSystemIn(
//                "insert A 3  stuff\n" +
//                "insert B 5 stuff\n" +
//                "insert C 7778 stuff\n" +
//                "insert D 7 stuff\n" +
//                "insert E 8 stuff\n" +
//                "insert F 8 stuff\n" +
//                "insert F 8 stuff\n" +
//                "tree name\n" +
//                "findRange name A G\n");
        setSystemIn("insert Balwin 30404 5 6\n" +
                "insert Alexandria 139966 7 8\n" +
                "insert Danville 43055 100 100\n" +
                "insert Lynchburg 75568 100 120\n" +
                "insert Portsmouth 95535 500 500\n" +
                "insert Suffolk 84585 700 700\n" +
                "insert Radford 16408 725 725\n" +
                "insert Williamsburg 14068 1023 1023\n" +
                "insert Fredericksburg 24286 344 355\n" +
                "insert Norfolk 242803 200 700\n" +
                "insert Baltimore 620961 700 200\n" +
                "find location 100 120\n" +
                "find location 100 1000\n");

        d2.main(null);

        assertEquals("insert Balwin 30404 5 6\n" +
                "insert Alexandria 139966 7 8\n" +
                "insert Danville 43055 100 100\n" +
                "insert Lynchburg 75568 100 120\n" +
                "insert Portsmouth 95535 500 500\n" +
                "insert Suffolk 84585 700 700\n" +
                "insert Radford 16408 725 725\n" +
                "insert Williamsburg 14068 1023 1023\n" +
                "insert Fredericksburg 24286 344 355\n" +
                "insert Norfolk 242803 200 700\n" +
                "insert Baltimore 620961 700 200\n" +
                "find location 100 120\n" +
                "Lynchburg 75568 (100, 120)\n" +
                "find location 100 1000\n" +
                "Not found\n" ,
                systemOut().getHistory());
    }

    /**
     * Tests the delete
     */
    public void testDelete()
    {
        setSystemIn("insert Balwin 30404 5 6\n" +
                "insert Alexandria 139966 7 8\n" +
                "insert Danville 43055 100 100\n" +
                "insert Lynchburg 75568 100 120\n" +
                "insert Portsmouth 95535 500 500\n" +
                "insert Suffolk 84585 700 700\n" +
                "insert Radford 16408 725 725\n" +
                "insert Williamsburg 14068 1023 1023\n" +
                "insert Fredericksburg 24286 344 355\n" +
                "insert Norfolk 242803 200 700\n" +
                "insert Baltimore 620961 700 200\n" +
                "delete location 700 700\n" +
                "find location 700 700 \n" +
                "makenull\n" +
                "tree name\n" +
                "tree population\n");

        d3.main(null);

        assertEquals("insert Balwin 30404 5 6\n" +
                "insert Alexandria 139966 7 8\n" +
                "insert Danville 43055 100 100\n" +
                "insert Lynchburg 75568 100 120\n" +
                "insert Portsmouth 95535 500 500\n" +
                "insert Suffolk 84585 700 700\n" +
                "insert Radford 16408 725 725\n" +
                "insert Williamsburg 14068 1023 1023\n" +
                "insert Fredericksburg 24286 344 355\n" +
                "insert Norfolk 242803 200 700\n" +
                "insert Baltimore 620961 700 200\n" +
                "delete location 700 700\n" +
                "find location 700 700 \n" +
                "Not found\n" +
                "makenull\n" +
                "tree name\n" +
                "Database empty\n" +
                "tree population\n" +
                "Database empty\n",
                systemOut().getHistory());
    }

    /**
     * Tests for duplicates
     */
    public void testDuplicate()
    {
        setSystemIn("insert Balwin 30404 5 6\n" +
                 "insert Alexandria 139966 7 8\n" +
                 "insert Danville 43055 100 100\n" +
                 "insert Lynchburg 75568 100 120\n" +
                 "insert Portsmouth 95535 500 500\n" +
                 "insert Suffolk 84585 700 700\n" +
                 "insert Radford 16408 725 725\n" +
                 "insert Williamsburg 14068 1023 1023\n" +
                 "insert Williamsburg 1212 1165 150\n" +
                 "insert Suffolk 123 156 100\n" +
                 "insert Fredericksburg 24286 344 355\n" +
                 "insert Fredericksburg 24286 344 355\n" +
                 "insert Norfolk 242803 200 700\n" +
                 "insert Baltimore 620961 700 200\n" +
                 "insert Baltimore 620961 700 200\n" +
                 "delete location 800 800\n" +
                 "makenull\n");
        d5.main(null);
        assertEquals("insert Balwin 30404 5 6\n" +
                "insert Alexandria 139966 7 8\n" +
                "insert Danville 43055 100 100\n" +
                "insert Lynchburg 75568 100 120\n" +
                "insert Portsmouth 95535 500 500\n" +
                "insert Suffolk 84585 700 700\n" +
                "insert Radford 16408 725 725\n" +
                "insert Williamsburg 14068 1023 1023\n" +
                "insert Williamsburg 1212 1165 150\n" +
                "Out of bounds\n" +
                "insert Suffolk 123 156 100\n" +
                "insert Fredericksburg 24286 344 355\n" +
                "insert Fredericksburg 24286 344 355\n" +
                "Duplicate\n" +
                "insert Norfolk 242803 200 700\n" +
                "insert Baltimore 620961 700 200\n" +
                "insert Baltimore 620961 700 200\n" +
                "Duplicate\n" +
                "delete location 800 800\n" +
                "Not found\n" +
                "makenull\n",
                systemOut().getHistory());
    }

    /**
     * Tests the inorder
     */
    public void testInOrdder()
    {
        setSystemIn(
                "insert Balwin 30404 0 0\n" +
                "insert Balwin 30404 0 9\n" +
                "insert Balwin 30404 213 32\n" +
                "insert Balwin 30404 213 234\n" +
                "insert Alexandria 139966 1024 1024\n" +
                "insert Danville 43055 1023 1023\n" +
                "insert Lynchburg 75568 0 1024\n" +
                "insert Portsmouth 95535 -1 -1\n" +
                "toString\n" +
                "insert Balwin 30404 555 555\n" );

        d4.main(null);

        assertEquals("insert Balwin 30404 0 0\n" +
                "insert Balwin 30404 0 9\n" +
                "insert Balwin 30404 213 32\n" +
                "insert Balwin 30404 213 234\n" +
                "insert Alexandria 139966 1024 1024\n" +
                "Out of bounds\n" +
                "insert Danville 43055 1023 1023\n" +
                "insert Lynchburg 75568 0 1024\n" +
                "Out of bounds\n" +
                "insert Portsmouth 95535 -1 -1\n" +
                "Out of bounds\n" +
                "toString\n" +
                "Internal\n" +
                "Internal\n" +
                "Internal\n" +
                "Internal\n" +
                "Internal\n" +
                "Internal\n" +
                "Internal\n" +
                "Balwin 30404 (0, 0)\n" +
                "Empty\n" +
                "Balwin 30404 (0, 9)\n" +
                "Empty\n" +
                "Empty\n" +
                "Empty\n" +
                "Empty\n" +
                "Empty\n" +
                "Empty\n" +
                "Empty\n" +
                "Empty\n" +
                "Empty\n" +
                "Empty\n" +
                "Balwin 30404 (213, 32)\n" +
                "Empty\n" +
                "Balwin 30404 (213, 234)\n" +
                "Empty\n" +
                "Empty\n" +
                "Empty\n" +
                "Empty\n" +
                "Empty\n" +
                "Danville 43055 (1023, 1023)\n" +
                "insert Balwin 30404 555 555\n",
                systemOut().getHistory());
    }
}
