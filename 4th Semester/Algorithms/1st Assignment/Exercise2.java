import java.util.Random;

public class Exercise2
{
    void swap(Integer[] array, Integer a, Integer b)
    {
        Integer temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    void quicksort_variation(Integer[] array, Integer left, Integer right)
    {
        if (left >= right) return;

        Partition_Pivot p = partition(array, left, right);

        quicksort_variation(array, left, p.getA());
        quicksort_variation(array, p.getB(), right);
    }

    Partition_Pivot partition(Integer[] array, Integer left, Integer right)
    {
        Integer current = left;
        Random random = new Random();
        Integer pivot = random.nextInt(right);
        //System.out.println(pivot);

        while (current <= right)
        {
            if (array[current] < array[pivot])
            {
                swap(array, left, current);
                ++left;
                ++current;
            }
            else if (array[current] > array[pivot])
            {
                swap(array, current, right);
                --right;
            }
            else ++current;
        }
        Partition_Pivot p = new Partition_Pivot(left, current);

        return p;
    }
}
