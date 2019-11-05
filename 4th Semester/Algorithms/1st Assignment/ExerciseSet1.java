import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ExerciseSet1
{
    public static void main(String args[]) throws IOException {
        Scanner in = new Scanner(System.in);

        Exercise1 newTest = new Exercise1();

        File txt = new File("src\\1.1-sm.txt");

        List<Integer> list = new ArrayList<>();
        list = Utilities.convertFileSequenceToList(txt);
        Integer array[] = new Integer[list.size()];
        array = list.toArray(array);

        int element;

        System.out.println("Please input the integer you would like to search : ");
        element = in.nextInt();

        newTest.find_first_and_last(array, element);

        for (int i = 0; i < 3; i++)
        {
            System.out.println();
        }

        Exercise2 newTest01 = new Exercise2();

        File txt01 = new File("src\\1.2-sm.txt");

        List<Integer> newList = new ArrayList<>();
        newList = Utilities.convertFileSequenceToList(txt01);
        Integer array01[] = new Integer[newList.size()];
        array01 = newList.toArray(array01);

        newTest01.quicksort_variation(array01, 0, array01.length - 1);
        System.out.println("Sorted Array : ");
        System.out.println(Arrays.toString(array01));
    }
}
