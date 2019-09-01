import java.util.Comparator;

public class SongComparator implements Comparator<Song> {
    public int compare(Song a, Song b)
    {
        if (a.getLikes() > b.getLikes())
        {
            return 1;
        }
        else if (a.getLikes() < b.getLikes())
        {
            return -1;
        }
        else
        {
            if (a.getTitle().compareToIgnoreCase(b.getTitle()) == 0)
            {
                return 0;
            }
            return -a.getTitle().compareToIgnoreCase(b.getTitle());
        }
    }
}