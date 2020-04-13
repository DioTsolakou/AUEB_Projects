package ML;

public class Word
{
    private String word;
    private int frequency;

    public Word(){}

    public Word(String word)
    {
        this.word = word;
        frequency = 1;
    }

    public Word(String word, int frequency)
    {
        this.word = word;
        this.frequency = frequency;
    }

    public String getWord()
    {
        return word;
    }

    public void setWord(String word)
    {
        this.word = word;
    }

    public int getFrequency()
    {
        return frequency;
    }

    public void setFrequency(int frequency)
    {
        this.frequency = frequency;
    }

    public void increase()
    {
        frequency++;
    }

    @Override
    public boolean equals(Object object)
    {
        boolean isEqual= false;

        if (object != null && object instanceof Word)
        {
            isEqual = (this.word == ((Word) object).word);
        }

        return isEqual;
    }

    @Override
    public int hashCode()
    {
        return 31 + (word == null ? 0 : word.hashCode());
    }
}
