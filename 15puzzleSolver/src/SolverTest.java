
import student.TestCase;

/**
 *  The test class for solver class
 *
 *  @author  Supratim Baruah (smb4)
 *  @version 2014/3/10
 */
public class SolverTest
    extends TestCase
{
    private Solver solver;

    /**
     * sets up the variables;
     */
    public void setUp()
    {


        solver = new Solver();

    }

    /**
     * Tests out the inserts.
     * @throws FileNotFoundException
     */
    public void testInsert()
    {
//        setSystemIn("V P Q 1 2 3 4 5 6 7 8 9 10 11 12 13 14 0 15");
    	String s = "V P Q 1 2 3 4 5 6 7 8 9 10 11 12 13 0 14 15";
    	String[] val = s.split("\\s+");
        solver.main(val);
        assertEquals("Visited States:\n" +
        		"1 (4,2)\n" +
        		"2 (3,2)\n" +
        		"3 (4,3)\n" +
        		"4 (4,1)\n" +
        		"5 (3,3)\n" +
        		"6 (4,4)\n",
                systemOut().getHistory());

        systemOut().clearHistory();
    	String s1 = "V F Q 1 2 3 4 5 6 7 8 9 10 11 12 13 0 14 15";
    	String[] val1 = s1.split("\\s+");
        solver.main(val1);
        assertEquals("Visited States:\n" +
        		"1 (4,2)\n" +
        		"2 (3,2)\n" +
        		"3 (4,3)\n" +
        		"4 (4,1)\n" +
        		"5 (2,2)\n" +
        		"6 (3,3)\n" +
        		"7 (3,1)\n" +
        		"8 (3,3)\n" +
        		"9 (4,4)\n",
                systemOut().getHistory());

        systemOut().clearHistory();
    	String s2 = "S F Q 1 2 3 4 5 6 7 8 9 10 11 12 13 0 14 15";
    	String[] val2 = s2.split("\\s+");
        solver.main(val2);
        assertEquals("Solution path:\n" +
        		"1 (4,2) right 14\n" +
        		"2 (4,3) right 15\n" +
        		"3 (4,4) \n",
        		systemOut().getHistory());

        systemOut().clearHistory();
    	String s3 = "S F V 1 2 3 4 5 6 7 8 9 10 11 12 13 14 0 15";
    	String[] val3 = s3.split("\\s+");
        solver.main(val3);

        assertEquals("Solution path:\n" +
        		"1 (4,3) right 15\n\n" +
        		"1 2 3 4 \n" +
        		"5 6 7 8 \n" +
        		"9 10 11 12 \n" +
        		"13 14    15 \n\n" +
        		"2 (4,4) \n\n" +
        		"1 2 3 4 \n" +
        		"5 6 7 8 \n" +
        		"9 10 11 12 \n" +
        		"13 14 15    \n",
        		systemOut().getHistory());

        systemOut().clearHistory();
        String s4 = "V P V 1 2 3 4 5 6 7 8 9 10 11 12 13 0 14 15";
    	String[] val4 = s4.split("\\s+");
        solver.main(val4);

        assertEquals("Visited States:\n" +
        		"1 (4,2)\n\n" +

        		"1 2 3 4 \n" +
        		"5 6 7 8 \n" +
        		"9 10 11 12 \n" +
        		"13    14 15 \n\n" +

        		"2 (3,2)\n\n" +

        		"1 2 3 4 \n" +
        		"5 6 7 8 \n" +
        		"9    11 12 \n" +
        		"13 10 14 15 \n\n" +

        		"3 (4,3)\n\n" +

        		"1 2 3 4 \n" +
        		"5 6 7 8 \n" +
        		"9 10 11 12 \n" +
        		"13 14    15 \n\n" +

        		"4 (4,1)\n\n" +

        		"1 2 3 4 \n" +
        		"5 6 7 8 \n" +
        		"9 10 11 12 \n" +
        		"   13 14 15 \n\n" +

        		"5 (3,3)\n\n" +

        		"1 2 3 4 \n" +
        		"5 6 7 8 \n" +
        		"9 10    12 \n" +
        		"13 14 11 15 \n\n" +

        		"6 (4,4)\n\n" +

        		"1 2 3 4 \n" +
        		"5 6 7 8 \n" +
        		"9 10 11 12 \n" +
        		"13 14 15    \n\n",

        		systemOut().getHistory());


        systemOut().clearHistory();
    	String s5 = "S F Q 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 0";
    	String[] val5 = s5.split("\\s+");
        solver.main(val5);
        assertEquals("Solution path:\n" +
        		"1 (4,4) \n",
        		systemOut().getHistory());


        systemOut().clearHistory();
    	String s6 = "S P Q 1 2 3 4 5 6 15 8 9 10 11 7 13 14 12 0";
    	String[] val6 = s6.split("\\s+");
        solver.main(val6);
        assertEquals("Solution path:\n" +
        		"1 (4,4) left 12\n" +
        		"2 (4,3) up 11\n" +
        		"3 (3,3) up 15\n" +
        		"4 (2,3) right 8\n" +
        		"5 (2,4) down 7\n" +
        		"6 (3,4) down 12\n" +
        		"7 (4,4) left 11\n" +
        		"8 (4,3) up 15\n" +
        		"9 (3,3) up 8\n" +
        		"10 (2,3) right 7\n" +
        		"11 (2,4) down 12\n" +
        		"12 (3,4) down 11\n" +
        		"13 (4,4) left 15\n" +
        		"14 (4,3) up 8\n" +
        		"15 (3,3) right 11\n" +
        		"16 (3,4) up 12\n" +
        		"17 (2,4) left 7\n" +
        		"18 (2,3) down 11\n" +
        		"19 (3,3) down 8\n" +
        		"20 (4,3) right 15\n" +
        		"21 (4,4) up 12\n" +
        		"22 (3,4) left 8\n" +
        		"23 (3,3) up 11\n" +
        		"24 (2,3) right 7\n" +
        		"25 (2,4) down 8\n" +
        		"26 (3,4) down 12\n" +
        		"27 (4,4) \n",
        		systemOut().getHistory());

        systemOut().clearHistory();
    	String s7 = "S P Q 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 0";
    	String[] val7 = s7.split("\\s+");
        solver.main(val7);
        assertEquals("Solution path:\n" +
        		"1 (4,4) \n",
        		systemOut().getHistory());

        systemOut().clearHistory();
    	String s8 = "V F V 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 0";
    	String[] val8 = s8.split("\\s+");
        solver.main(val8);
        assertEquals("Visited States:\n" +
        		"1 (4,4)\n\n" +

        		"1 2 3 4 \n" +
        		"5 6 7 8 \n" +
        		"9 10 11 12 \n" +
        		"13 14 15    \n\n",
        		systemOut().getHistory());

        systemOut().clearHistory();
        String s9 = "V F V 1 2 3 4 5 6 7 8 9 10 11 12 13 14 0 15";
        String[] val9 = s9.split("\\s+");
        solver.main(val9);
        assertEquals("Visited States:\n" +
        		"1 (4,3)\n\n" +

        		"1 2 3 4 \n" +
        		"5 6 7 8 \n" +
        		"9 10 11 12 \n" +
        		"13 14    15 \n\n" +

        		"2 (3,3)\n\n" +

        		"1 2 3 4 \n" +
        		"5 6 7 8 \n" +
        		"9 10    12 \n" +
        		"13 14 11 15 \n\n" +

        		"3 (4,4)\n\n" +

        		"1 2 3 4 \n" +
        		"5 6 7 8 \n" +
        		"9 10 11 12 \n" +
        		"13 14 15    \n\n",
        		systemOut().getHistory());
    }


}
