import java.io.*;
import java.util.*;

public class Top_k
{
    public static void main(String[] args)
    {
        int k = 0;
        String path;

        Scanner in = new Scanner(System.in);

        System.out.println("Please give the path to the file: ");
        path = in.nextLine();

        if (!path.endsWith(".txt"))
        {
            System.out.println("Wrong file format given!");
            return;
        }

        System.out.println("Please give the amount of top songs you want to see: ");
        k = Integer.parseInt(in.nextLine());

        if (k < 0)
        {
            System.out.println("Top number of songs cannot be a negative!");
            return;
        }

        Song song = new Song();
        song.readText(path);
        song.top_K(k);
    }
}
