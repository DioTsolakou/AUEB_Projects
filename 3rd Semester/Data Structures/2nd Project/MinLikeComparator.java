import java.util.Comparator;

public class MinLikeComparator implements Comparator<Integer>
{
    public int compare(Integer a, Integer b)
    {
        return a.compareTo(b);
    }
}
