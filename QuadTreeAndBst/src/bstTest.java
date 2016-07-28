
import student.TestCase;

/**
 *  The test class for bst class
 *
 *  @author  Supratim Baruah (smb4)
 *  @version 2014/02/10
 */
public class bstTest
    extends TestCase
{
    private bst d1;
    private bst d2;
    private bst d3;
    private bst d4;
    private bst d5;
    /**
     * sets up the variables;
     */
    public void setUp()
    {


        d1 = new bst();
        d2 = new bst();
        d3 = new bst();
        d4 = new bst();
        d5 = new bst();
    }

    /**
     * Tests out the inserts.
     */
    public void testInsert()
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
                "tree name\n" +
                "tree population\n");
        d1.main(null);

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
                "tree name\n" +
                "    Alexandria 139966 7 8\n" +
                "        Baltimore 620961 700 200\n" +
                "Balwin 30404 5 6\n" +
                "    Danville 43055 100 100\n" +
                "            Fredericksburg 24286 344 355\n" +
                "        Lynchburg 75568 100 120\n" +
                "                Norfolk 242803 200 700\n" +
                "            Portsmouth 95535 500 500\n" +
                "                    Radford 16408 725 725\n" +
                "                Suffolk 84585 700 700\n" +
                "                    Williamsburg 14068 1023 1023\n" +
                "tree population\n" +
                "        Williamsburg 14068 1023 1023\n" +
                "    Radford 16408 725 725\n" +
                "        Fredericksburg 24286 344 355\n" +
                "Balwin 30404 5 6\n" +
                "        Danville 43055 100 100\n" +
                "            Lynchburg 75568 100 120\n" +
                "                    Suffolk 84585 700 700\n" +
                "                Portsmouth 95535 500 500\n" +
                "    Alexandria 139966 7 8\n" +
                "        Norfolk 242803 200 700\n" +
                "            Baltimore 620961 700 200\n" ,
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
                "find name Danville\n" +
                "find name Blacksburg\n" +
                "find population 24286\n" +
                "find population 1000000\n" +
                "findKth name 9\n" +
                "findKth population 5\n" +
                "findKth name 20\n" +
                "findRange name B E\n" +
                "findRange population 20000 40000\n" +
                "findKth population 20\n" +
                "findRange name C Can\n" +
            "findRange population 20000 20001\n");
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
                "find name Danville\n" +
                "Danville 43055 100 100\n" +
                "find name Blacksburg\n" +
                "Not found\n" +
                "find population 24286\n" +
                "Fredericksburg 24286 344 355\n" +
                "find population 1000000\n" +
                "Not found\n" +
                "findKth name 9\n" +
                "Suffolk 84585 700 700\n" +
                "findKth population 5\n" +
                "Lynchburg 75568 100 120\n" +
                "findKth name 20\n" +
                "Not found\n" +
                "findRange name B E\n" +
                "Baltimore 620961 700 200\n" +
                "Balwin 30404 5 6\n" +
                "Danville 43055 100 100\n" +
                "findRange population 20000 40000\n" +
                "Fredericksburg 24286 344 355\n" +
                "Balwin 30404 5 6\n" +
                "findKth population 20\n" +
                "Not found\n" +
                "findRange name C Can\n" +
                "Not found\n" +
                "findRange population 20000 20001\n" +
                "Not found\n",
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
                "delete  name Alexandria\n" +
                "delete population 620961\n" +
                "delete population 1\n" +
                "delete  name Blacksburg\n" +
                "makenull\n" +
                "tree name\n" +
                "tree population\n" +
            "delete population 0");

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
                "delete name Alexandria\n" +
                "delete population 620961\n" +
                "delete population 1\n" +
                "Not found\n" +
                "delete name Blacksburg\n" +
                "Not found\n" +
                "makenull\n" +
                "tree name\n" +
                "Database empty\n" +
                "tree population\n" +
                "Database empty\n" +
                "delete population 0\n" +
                "Not found\n",
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
                 "delete  name Alexandria\n" +
                 "delete  name Portsmouth\n" +
                 "delete  name Norfolk\n" +
                 "delete  name Balwin\n" +
                 "delete name Fredericksburg\n" +
                 "delete population 1212\n" +
                 "delete population 620961\n" +
                 "delete population 1\n" +
                 "delete  name Williamsburg\n" +
                 "makenull\n" +
                 "delete name Hello");
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
                "insert Suffolk 123 156 100\n" +
                "insert Fredericksburg 24286 344 355\n" +
                "insert Fredericksburg 24286 344 355\n" +
                "insert Norfolk 242803 200 700\n" +
                "insert Baltimore 620961 700 200\n" +
                "delete name Alexandria\n" +
                "delete name Portsmouth\n" +
                "delete name Norfolk\n" +
                "delete name Balwin\n" +
                "delete name Fredericksburg\n" +
                "delete population 1212\n" +
                "delete population 620961\n" +
                "delete population 1\n" +
                "Not found\n" +
                "delete name Williamsburg\n" +
                "makenull\n" +
                "delete name Hello\n" +
                "Not found\n",
                systemOut().getHistory());
    }

    /**
     * Tests the inorder
     */
    public void testInOrdder()
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
                "sort name\n" +
                "sort population\n" +
                "makenull\n" +
                "sort name\n" +
            "sort population");

        d4.main(null);

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
                "sort name\n" +
                "Alexandria 139966 7 8\n" +
                "Baltimore 620961 700 200\n" +
                "Balwin 30404 5 6\n" +
                "Danville 43055 100 100\n" +
                "Fredericksburg 24286 344 355\n" +
                "Lynchburg 75568 100 120\n" +
                "Norfolk 242803 200 700\n" +
                "Portsmouth 95535 500 500\n" +
                "Radford 16408 725 725\n" +
                "Suffolk 84585 700 700\n" +
                "Williamsburg 14068 1023 1023\n" +
                "sort population\n" +
                "Williamsburg 14068 1023 1023\n" +
                "Radford 16408 725 725\n" +
                "Fredericksburg 24286 344 355\n" +
                "Balwin 30404 5 6\n" +
                "Danville 43055 100 100\n" +
                "Lynchburg 75568 100 120\n" +
                "Suffolk 84585 700 700\n" +
                "Portsmouth 95535 500 500\n" +
                "Alexandria 139966 7 8\n" +
                "Norfolk 242803 200 700\n" +
                "Baltimore 620961 700 200\n" +
                "makenull\n" +
                "sort name\n" +
                "Database empty\n" +
                "sort population\n" +
                "Database empty\n",
                systemOut().getHistory());
    }
}
