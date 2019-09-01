import java.util.*;

public class Top_k_withPQ
{
    public static void main(String[] args)
    {
        int k = 0;
        int capacity = 0;
        String path;

        Scanner in = new Scanner(System.in);

        System.out.println("Please give the capacity of the queue: ");
        capacity = Integer.parseInt(in.nextLine());

        PQ<Song> pQueue = new PQ<Song>(capacity, new SongComparator());

        System.out.println("Please give the path to the file: ");
        path = in.nextLine();

        if (!path.endsWith(".txt"))
        {
            System.out.println("Wrong file format given!");
            return;
        }

        System.out.println("Please give the amount of top songs you want to see: ");
        k = Integer.parseInt(in.nextLine());

        if (k < 0 || k > capacity)
        {
            System.out.println("Top number of songs cannot be a negative or larger than the listed songs");
            return;
        }
        pQueue.readTextHeap(path, k);
    }
}
