import java.io.*;
import java.util.*;

/**
 * The BST class handing system.in and system.out.
 * @author Supratim Baruah smb4
 * @version 2014/2/10
 * @param <T>
 */
@SuppressWarnings("unchecked")
class bst<T extends Comparable<T>>
{
    /**
     * main method
     * @param args input
     */
    public static void main(String[] args)
    {
        BinaryTree nameBst = new BinaryTree();
        BinaryTree popBst = new BinaryTree();

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
                else
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
            }

            //Find Command
            else if (val[0].equals("find"))
            {
                System.out.println(inst);
                if (val[1].compareTo("name") == 0)
                {
                    nameBst.emptyPrintTree();
                    nameBst.find(val[2]);
                    if (nameBst.getPrintTree().equals(""))
                    {
                        System.out.println("Not found");
                    }
                    else
                    {
                        System.out.print(nameBst.getPrintTree());
                    }
                }
                else
                {
                    popBst.emptyPrintTree();
                    popBst.find(Integer.parseInt(val[2]));
                    if (popBst.getPrintTree().equals(""))
                    {
                        System.out.println("Not found");
                    }
                    else
                    {
                        System.out.print(popBst.getPrintTree());
                    }
                }
            }

            //Findkth Command
            else if (val[0].compareTo("findKth") == 0)
            {
                System.out.println(inst);

                if (val[1].compareTo("name") == 0)
                {
                    BtNode temp = nameBst.findkth(Integer.parseInt(val[2]));
                    if (temp == null)
                    {
                        System.out.println("Not found");
                    }
                    else
                    {
                        System.out.println(temp.toString());
                    }
                }
                else
                {
                    BtNode temp = popBst.findkth(Integer.parseInt(val[2]));
                    if (temp == null)
                    {
                        System.out.println("Not found");
                    }
                    else
                    {
                        System.out.println(temp.toString());
                    }
                }
            }

            //FindRange Command
            else if (val[0].compareTo("findRange") == 0)
            {
                System.out.println(inst);
                if (val[1].compareTo("name") == 0)
                {
                    nameBst.emptyPrintTree();
                    nameBst.findRange(val[2], val[3]);
                    if (nameBst.getPrintTree().equals(""))
                    {
                        System.out.println("Not found");
                    }
                    else
                    {
                        System.out.print(nameBst.getPrintTree());
                    }
                }
                else
                {
                    popBst.emptyPrintTree();
                    popBst.findRange(Integer.parseInt(val[2]),
                            Integer.parseInt(val[3]));
                    if (popBst.getPrintTree().equals(""))
                    {
                        System.out.println("Not found");
                    }
                    else
                    {
                        System.out.print(popBst.getPrintTree());
                    }
                }
            }

            //Delete Command
            else if (val[0].compareTo("delete") == 0)
            {
                System.out.println(inst);
                if (val[1].compareTo("name") == 0)
                {
                    nameBst.emptyPrintTree();
                    nameBst.find(val[2]);
                    if (!nameBst.getPrintTree().equals(""))
                    {
                        Scanner d = new Scanner(nameBst.getPrintTree());
                        while (d.hasNextLine())
                        {
                            String c1 = d.nextLine();
                            String[] c2 = c1.split("\\s+");
                            City temp1 = new City(c2[0],
                                    Integer.parseInt(c2[1]),
                                    Integer.parseInt(c2[2]),
                                    Integer.parseInt(c2[3]));
                            nameBst.remove(c2[0], temp1);
                            popBst.remove(Integer.parseInt(c2[1]), temp1);
                        }
                    }
                    else
                    {
                        System.out.println("Not found");
                    }
                }
                else
                {
                    popBst.emptyPrintTree();
                    popBst.find(Integer.parseInt(val[2]));
                    if (!popBst.getPrintTree().equals(""))
                    {
                        Scanner d = new Scanner(popBst.getPrintTree());
                        while (d.hasNextLine())
                        {
                            String c1 = d.nextLine();
                            String[] c2 = c1.split("\\s+");
                            City temp1 = new City(c2[0],
                                    Integer.parseInt(c2[1]),
                                    Integer.parseInt(c2[2]),
                                    Integer.parseInt(c2[3]));
                            nameBst.remove(c2[0], temp1);
                            popBst.remove(Integer.parseInt(c2[1]), temp1);
                        }
                    }
                    else
                    {
                        System.out.println("Not found");
                    }
                }
            }

            //Makenull Command
            else if (val[0].equals("makenull"))
            {
                System.out.println(inst);
                popBst.clear();
                nameBst.clear();
            }

            //Sort Command
            else if (val[0].compareTo("sort") == 0)
            {
                System.out.println(inst);
                if (val[1].compareTo("name") == 0)
                {
                    nameBst.emptyPrintTree();
                    nameBst.inOrder();
                    System.out.print(nameBst.getPrintTree());
                    if (nameBst.getPrintTree().equals(""))
                    {
                        System.out.println("Database empty");
                    }
                }
                else
                {
                    popBst.emptyPrintTree();
                    popBst.inOrder();
                    System.out.print(popBst.getPrintTree());
                    if (popBst.getPrintTree().equals(""))
                    {
                        System.out.println("Database empty");
                    }
                }
            }
        }
        s.close();
    }
}