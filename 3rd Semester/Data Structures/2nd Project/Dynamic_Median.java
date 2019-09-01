import java.io.*;
import java.util.*;

public class Dynamic_Median
{
    public static void main(String args[])
    {
        PQ<Integer> lowPQ = new PQ<Integer>(10, new MinLikeComparator());
        PQ<Integer> highPQ = new PQ<Integer>(10, new MaxLikeComparator());

        String path;

        Scanner in = new Scanner(System.in);

        System.out.println("Please give the path to the file: ");
        path = in.nextLine();

        if (!path.endsWith(".txt"))
        {
            System.out.println("Wrong file format given!");
            return;
        }
        else
        {
            File txt = null;
            BufferedReader reader = null;
            String line;
            int likes;
            int space =  0;
            int position = 0;
            double median = 0;

            try
            {
                txt = new File(path); // try to find the file
            }
            catch (NullPointerException e)
            {
                System.err.println("File not found.");
            }

            try {
                reader = new BufferedReader(new FileReader(path));
            }
            catch (FileNotFoundException e)
            {
                System.err.println("Error opening file!");
            }

            try
            {
                int i = 0;
                while ((line = reader.readLine()) != null)
                {
                    space = line.lastIndexOf(' ');  //takes the index position of the first space

                    position = space;   //makes the index of the first space the starting position for the next element

                    likes = Integer.parseInt(line.substring(position).trim()); //takes the element between the last space until the end of the line

                    System.out.println(likes);

                    highPQ.insert(likes);

                    if (Math.abs(highPQ.size() - lowPQ.size()) > 1)
                    {
                        if (highPQ.size() > lowPQ.size())
                        {
                            lowPQ.insert(highPQ.getMax());
                        }
                        else highPQ.insert(lowPQ.getMax());
                    }

                    if (i%5 == 0)
                    {
                        if (highPQ.size() == lowPQ.size())
                        {
                            median = (double) highPQ.max();
                            System.out.println("Median is : " +median);
                        }
                        else if (highPQ.size() > lowPQ.size())
                        {
                            median = (double) highPQ.max();
                            System.out.println("Median is : " +median);
                        }
                        else if (highPQ.size() < lowPQ.size())
                        {
                            median = (double) lowPQ.max();
                            System.out.println("Median is : " +median);
                        }
                    }

                    System.out.println();
                    i++;
                }
            }
            catch (IOException e)
            {
                System.err.println("Error reading file!");
            }
            finally {
                System.out.println("Max of high : " +highPQ.max());
                System.out.println();
                System.out.println("Max of low : " +lowPQ.max());
                System.out.println();
                System.out.println("Low elements");
                lowPQ.printTopHeapElements(5);
                System.out.println();
                System.out.println("Max elements");
                highPQ.printTopHeapElements(5);
            }
        }
    }
}