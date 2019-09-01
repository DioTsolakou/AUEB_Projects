import java.util.Comparator;

public class MaxLikeComparator implements Comparator<Integer>
{
    public int compare(Integer a, Integer b)
    {
        return -1*a.compareTo(b);
    }
}
