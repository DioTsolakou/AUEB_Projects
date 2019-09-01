import java.io.*;

public class Song
{
    Song songs[];

    private int id;
    private String title;
    private int likes;
    protected SongComparator cmp;

    public Song()
    {
        SongComparator cmp = new SongComparator();
    }

    public Song(int id, String title, int likes, SongComparator cmp)
    {
        this.id = id;
        this.title = title;
        this.likes = likes;
        this.cmp = cmp;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public int getLikes()
    {
        return likes;
    }

    public void setLikes(int likes)
    {
        this.likes = likes;
    }

    private void quicksort(Song[] s, int first, int last)
    {
        if (last <= first) return;
        int i = partition(s, first, last);
        quicksort(s, first, i - 1);
        quicksort(s, i + 1, last);
    }

    int partition(Song s[], int first, int last)
    {
        Song pivot = s[(first + last)/2];
        int i = first - 1;
        for (int j = first; j < last; j++)
        {
            if (cmp.compare(s[j], pivot) < 0)
            {
                i++;
                exch(s, s[i], s[j]);
            }
        }
        exch(s, s[i + 1], pivot);
        return i + 1;
    }

    private static void exch(Song[] s, Song i, Song j)
    {
        Song tmp = i;
        i = j;
        j = tmp;
    }

    public void readText(String path)
    {
        File txt = null;
        BufferedReader reader = null;
        BufferedReader reader01 = null;
        String line;
        String line01;
        int id;
        String title;
        int likes;
        int counterS = 0;
        int i = 0;
        int space =  0;
        int position = 0;
        int counter = 0;

        try
        {
            txt = new File(path); // try to find the file
        }
        catch (NullPointerException e)
        {
            System.err.println("File not found.");
        }

        try
        {
            reader = new BufferedReader(new FileReader(txt)); // try to open the file
        }
        catch (FileNotFoundException e)
        {
            System.err.println("Error opening file!");
        }

        try {
            line = reader.readLine();
            while (line != null)
            {
                counterS++;
                line = reader.readLine();
            }
            System.out.println(counterS);

            songs = new Song[counterS];
        }
        catch (IOException e)
        {
            System.err.println("Error reading file.");
        }

        try {
            reader01 = new BufferedReader(new FileReader(txt));
        }
        catch (FileNotFoundException e)
        {
            System.err.println("Error opening file!");
        }

        try
        {
            while ((line01 = reader01.readLine()) != null)
            {
                space = line01.indexOf(' ');  //takes the index position of the first space
                id = Integer.parseInt(line01.substring(0, space).trim());     //takes the first element of the line until the first space and trims any spaces

                position = space;   //makes the index of the first space the starting position for the next element
                i = line01.length();
                for (space = position; space < i; space++)
                {
                    if (Character.isDigit(line01.charAt(space))) break; //reads until it finds the next digit to surpass any spaces in the song's title
                }

                title = line01.substring(position, space - 1).trim(); //takes the string between the first space(which is now position) until
                                                                    //the space before the first digit after the title

                position = space;
                likes = Integer.parseInt(line01.substring(position).trim()); //takes the element between the space after the title until
                                                                            //the end of the line

                Song newSong = new Song(id, title, likes, cmp);
                songs[counter] = newSong;
                counter++;
            }
        }
        catch (IOException e)
        {
            System.err.println("Error reading file!");
        }
        finally {
            printArrayElement(songs, counter);
        }
    }

    public void top_K(int k)
    {
        if (k > songs.length)
        {
            System.err.println("Top songs cannot be more than the listed songs!");
            return;
        }
        quicksort(songs, 0, songs.length);
        printArrayElement(songs, k);
    }

    static void printArrayElement(Song s[], int elements)
    {
        if (elements > s.length) return;
        else
        {
            for (int i=0; i<elements; ++i)
                System.out.println(s[i]+" ");
            System.out.println();
        }
    }

    @Override
    public String toString()
    {
        return "ID: " +getId()+ " " + "Title: " +getTitle()+ " " + "Likes: " +getLikes();
    }
}