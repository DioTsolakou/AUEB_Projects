public class Exercise1
{
    Integer find_first_occurence(Integer[] array, Integer x)
    {
        Integer left = 0;
        Integer right = array.length;
        Integer mid;

        while (left < right)
        {
            mid = (left+right)/2;
            if (array[mid] < x)
            {
                left = mid + 1;
            }
            else right = mid;
        }

        return left;
    }

    Integer find_last_occurence(Integer[] array, Integer x)
    {
        Integer left = 0;
        Integer right = array.length;
        Integer mid;

        while (left < right)
        {
            mid = (left+right)/2;
            if (array[mid] <= x)
            {
                left = mid + 1;
            }
            else right = mid;
        }

        return left - 1;
    }

    void find_first_and_last(Integer[] array, Integer x)
    {
        Integer first = find_first_occurence(array, x);
        Integer last = find_last_occurence(array, x);

        if (first > last)
        {
            System.out.println("The value given doesn't exist inside the given array.");
        }
        else
        {
            System.out.println("First occurence of " +x+ " : " +find_first_occurence(array, x));
            System.out.println("Last occurence of " +x+ " : " +find_last_occurence(array, x));
        }
    }
}
