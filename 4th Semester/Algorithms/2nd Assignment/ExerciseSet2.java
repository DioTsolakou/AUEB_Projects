import java.io.File;
import java.io.IOException;
import java.util.*;

public class ExerciseSet2
{
    public static void main(String args[]) throws IOException
    {
        Scanner in = new Scanner(System.in);
        Integer wantedCalories;

       Exercise1 testEx1 = new Exercise1();

        File txt = new File("2.1-sm.txt");

        List<Integer> list = new ArrayList<>();
        list = Utilities.convertFileSequenceToList(txt);
        Integer array[] = new Integer[list.size()];
        array = list.toArray(array);

        System.out.println(testEx1.shortestRoute(array));

        for (int i = 0; i < 3; i++)
        {
            System.out.println();
        }

        Exercise2 testEx2 = new Exercise2();

        File txt1 = new File("src\\calories.txt");
        File txt2 = new File("src\\fat.txt");

        List<Integer> list1 = new ArrayList<>();
        list1 = Utilities.convertFileSequenceToList(txt1);
        Integer array1[] = new Integer[list1.size()];
        array1 = list1.toArray(array1);

        List<Integer> list2 = new ArrayList<>();
        list2 = Utilities.convertFileSequenceToList(txt2);

        Integer array2[] = new Integer[list2.size()];
        array2 = list2.toArray(array2);

        System.out.println("Please input the amount of wanted calories : ");
        wantedCalories = in.nextInt();

        System.out.println(testEx2.caloriesAndFatDiet(array1, array2, wantedCalories));

    }
}
