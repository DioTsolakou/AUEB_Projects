public class WordFreq
{
    private String word;
    private int count;

    public WordFreq(String word)
    {
        this.word = word;
        count = 1;
    }

    public String key()
    {
        return word;
    }

    public void increment()
    {
        count++;
    }

    public int getCount()
    {
        return count;
    }
}