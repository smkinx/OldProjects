import java.io.*;
import java.util.*;

/**
 * The BST class handing system.in and system.out.
 * @author Supratim Baruah smb4
 * @version 2014/3/5
 * @param <T>
 */
@SuppressWarnings("unchecked")
class Quad<T extends Comparable<T>>
{
    /**
     * main method
     * @param args input
     */
    public static void main(String[] args)
    {
        BinaryTree nameBst = new BinaryTree();
        BinaryTree popBst = new BinaryTree();
        PRQuadTree<City> qt = new PRQuadTree<City>(1024);

        Scanner s = new Scanner(System.in);

        //Processes the instructions from the file
        while (s.hasNextLine())
        {
            String name = null;
            int pop = 0;
            int x = 0;
            int y = 0;
            String line = s.nextLine();
            String[] val = line.split("\\s+");
            String inst = line.replaceAll("\\s+", " ");

            //Insert command
            if (val[0].compareTo("insert") == 0)
            {
                System.out.println(inst);
                name = val[1];
                pop = Integer.parseInt(val[2]);
                x = Integer.parseInt(val[3]);
                y = Integer.parseInt(val[4]);
                City c = new City(name, pop, x, y);
                nameBst.insert(name, c);
                popBst.insert(pop, c);
                if (x < 0 || x >= 1024 || y < 0 || y >= 1024)
                {
                    System.out.println("Out of bounds");
                }
                else if (!qt.insert(x, y, c)) {
                    System.out.println("Duplicate");
                }
            }

            //Tree Command
            else if (val[0].compareTo("tree") == 0)
            {
                System.out.println(inst);
                if (val[1].compareTo("name") == 0)
                {
                    nameBst.emptyPrintTree();
                    nameBst.print();
                    if (nameBst.getPrintTree().equals(""))
                    {
                        System.out.println("Database empty");
                    }
                    else
                    {
                        System.out.print(nameBst.getPrintTree());
                    }
                }
                else if (val[1].compareTo("population") == 0)
                {
                    popBst.emptyPrintTree();
                    popBst.print();
                    if (popBst.getPrintTree().equals(""))
                    {
                        System.out.println("Database empty");
                    }
                    else
                    {
                        System.out.print(popBst.getPrintTree());
                    }
                }
                else
                {
                    System.out.print(qt.tree());
                }
            }

            //Find Command
            else if (val[0].equals("find"))
            {
                System.out.println(inst);
                if (val[1].equals("location"))
                {
                    x = Integer.parseInt(val[2]);
                    y = Integer.parseInt(val[3]);

                    if ( qt.find(x, y) == null)
                    {
                        System.out.println("Not found");
                    }
                    else
                    {
                        System.out.println(qt.find(x, y).toString1());
                    }
                }
            }


            //Delete Command
            else if (val[0].compareTo("delete") == 0)
            {
                System.out.println(inst);
                if (val[1].compareTo("location") == 0)
                {
                    x = Integer.parseInt(val[2]);
                    y = Integer.parseInt(val[3]);

                    if (qt.find(x, y) == null)
                    {
                        System.out.println("Not found");
                    }
                    else
                    {

                        City temp1 = new City(qt.find(x, y).getName(),
                                qt.find(x, y).getPopulation(),
                                qt.find(x, y).getX(),
                                qt.find(x, y).getY());
                        nameBst.remove(qt.find(x, y).getName(), temp1);
                        popBst.remove(qt.find(x, y).getPopulation(), temp1);
                        qt.remove(x, y);

                    }
                }
            }

            //Makenull Command
            else if (val[0].equals("makenull"))
            {
                System.out.println(inst);
                popBst.clear();
                nameBst.clear();
                qt.clear();
            }

            //rfind Command
            else if (val[0].equals("rfind"))
            {
                System.out.println(inst);
                x = Integer.parseInt(val[1]);
                y = Integer.parseInt(val[2]);
                int w = Integer.parseInt(val[3]);
                int h = Integer.parseInt(val[4]);
                ArrayList<City> list = new ArrayList<City>();
                qt.rangeSearch(x, y, w, h, list);
                if (list.size() == 0)
                {
                    System.out.println("Not found");
                }
                else
                {
                    for (int i = 0; i < list.size(); i++)
                    {
                        System.out.print(list.get(i).toString1() + "\n");
                    }
                }
            }

            //toString
            else if (val[0].equals("toString"))
            {
                System.out.println(inst);
                System.out.print(qt.toString());
            }
        }
        s.close();
    }
}