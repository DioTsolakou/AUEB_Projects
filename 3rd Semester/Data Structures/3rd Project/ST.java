import java.io.*;
import java.util.StringTokenizer;

public class ST
{
    private class TreeNode
    {
        WordFreq item;
        TreeNode l;
        TreeNode r;
        int number;

        public TreeNode(WordFreq item)
        {
            if (item == null) throw new IllegalArgumentException();
            this.item = item;
            number += number;
        }
    }

    public ST()
    {
        this.size = 0;
    }

    protected TreeNode root;
    protected int size;
    List stopwords = new List();

    WordFreq search(String w)
    {
        WordFreq temp = searchR(root, w);
        if (temp.getCount() > getMeanFrequency())
        {
            remove(temp.key());
            insertRoot(temp.key());
        }
        return temp;
    }

    WordFreq searchR(TreeNode start, String w)
    {
        if (start == null) return null;
        int result = start.item.key().compareToIgnoreCase(w);

        if (result == 0) return start.item;
        else if (result < 0) return searchR(start.l, w);
        else return searchR(start.r, w);
    }

    TreeNode insertRootR(TreeNode a, String w)
    {
        if (a == null)
        {
            TreeNode node = new TreeNode(new WordFreq(w));
            size++;
            return node;
        }
        int result = a.item.key().compareToIgnoreCase(w);
        if (result == 0) return a;
        else if (result < 0)
        {
            a.l = insertRootR(a.l, w);
            a = rotateR(a);
        }
        else
        {
            a.r = insertRootR(a.r, w);
            a = rotateL(a);
        }
        return a;
    }

    void insertRoot(String w)
    {
        root = insertRootR(root, w);
    }

    void insert(WordFreq item)
    {
        String key = item.key();
        item = new WordFreq(key);
        if (root == null) {
            root = new TreeNode(item);
            return;
        }
        TreeNode r = root;
        TreeNode t = r;
        int result = key.compareToIgnoreCase(t.item.key());
        while (t != null)
        {
            if (result > 0)
            {
                r = t;
                t = t.l;
            }
            else
            {
                r = t;
                t = t.r;
            }
        }
        int result2 = key.compareToIgnoreCase(r.item.key());
        if (result2 > 0)
        {
            r.l = new TreeNode(item);
        }
        else r.r = new TreeNode(item);

        size++;
    }

    void update(String w)
    {
        WordFreq temp = search(w);
        if (temp != null)
        {
            temp.increment();
        }
        else
        {
            insert(temp);
        }
    }

    void remove(String w)
    {
        removeR(root, w);
        size--;
    }

    TreeNode removeR(TreeNode start, String w)
    {
        if (start == null) return null;
        int result = start.item.key().compareToIgnoreCase(w);
        if (result < 0)
        {
            start.l = removeR(start.l, w);
        }
        else if (result == 0)
        {
            start = join(start.l, start.r);
        }
        else{
            start.r = removeR(start.r, w);
        }
        return start;
    }

    TreeNode join(TreeNode a, TreeNode b)
    {
        if (a == null) return b;
        if (b == null) return a;
        b = partition(b, 0);
        b.l = a;
        return b;
    }

    TreeNode partition(TreeNode a, int i)
    {
        int temp = (a.l == null) ? 0 : a.l.number;
        if (temp > i)
        {
            a.l = partition(a.l, i);
            a = rotateR(a);
        }
        if (temp < i)
        {
            a.r = partition(a.r, i);
            a = rotateL(a);
        }
        return a;
    }

    void load(String filename)
    {
        File txt = null;
        BufferedReader reader = null;

        try
        {
            txt = new File(filename); // try to find the file
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

        try
        {
            String line;
            String word;
            String token;
            StringTokenizer st;

            while ((line = reader.readLine()) != null)
            {
                st = new StringTokenizer(line);
                while (st.hasMoreTokens())
                {
                    word = "";
                    token = st.nextToken();
                    for (int i =0; i < token.length(); i++)
                    {
                        if (Character.isLetter(token.charAt(i)) || token.charAt(i) == "'".charAt(0))
                        {
                            word += token.charAt(i);
                        }
                    }
                    if (!stopwords.isStopWord(word))
                    {
                        update(word);
                        size++;
                    }
                }
            }
        }
        catch (IOException e)
        {
            System.err.println("Error reading file.");
        }
    }

    int getTotalWords()
    {
        return getTotalWordsR(root);
    }

    int getTotalWordsR(TreeNode a)
    {
        if (a == null) return 0;
        return 1 + getTotalWordsR(a.l) + getTotalWordsR(a.r);
    }

    int getDistinctWords()
    {
        return size;
    }

    int getFrequency(String w)
    {
        WordFreq word = search(w);
        if (word != null)
        {
            return word.getCount();
        }
        return 0;
    }

    WordFreq getMaximumFrequency()
    {
        return getMaximumFrequencyR(root);
    }

    WordFreq getMaximumFrequencyR(TreeNode a)
    {
        if (a == null) return null;

        WordFreq maxfreq = a.item;
        WordFreq rfreq = getMaximumFrequencyR(a.r);
        WordFreq lfreq = getMaximumFrequencyR(a.l);

        if (maxfreq.getCount() < lfreq.getCount())
        {
            maxfreq = lfreq;
        }
        if (maxfreq.getCount() < rfreq.getCount())
        {
            maxfreq = rfreq;
        }
        return maxfreq;
    }

    double getMeanFrequency()
    {
        double freq = getMeanFrequencyR(root);
        int count = 1 + root.l.number + root.r.number;
        double mean =  freq/(double) count;
        return mean;
    }

    double getMeanFrequencyR(TreeNode a)
    {
        if (a == null) return 0;
        return a.item.getCount() + getMeanFrequencyR(a.l) + getMeanFrequencyR(a.r);
    }

    void addStopWord(String w)
    {
        if (!stopwords.isStopWord(w))
        {
            stopwords.insert(w);
        }
    }

    void removeStopWord(String w)
    {
        if (stopwords.isStopWord(w))
        {
            stopwords.remove(w);
        }
    }

    void printTreeAlphabetically(PrintStream stream)
    {
        printTreeAlphabeticallyR(stream, root);
    }

    void printTreeAlphabeticallyR(PrintStream stream, TreeNode a)
    {
        if (a == null) return;
        printTreeAlphabeticallyR(stream, a.l);
        stream.println("Word : " +a.item.key()+ " " + "Frequency : " + a.item.getCount() + " ");
        printTreeAlphabeticallyR(stream, a.r);
    }

    void printTreeByFrequency(PrintStream stream)
    {

    }

    private String toStringR(TreeNode a)
    {
        if (a == null) return null;
        String s = toStringR(a.l);
        s += a.item.toString() + "\n";
        s += toStringR(a.r);
        return s;
    }

    public String toString()
    {
        return toStringR(root);
    }

    TreeNode rotateR(TreeNode a)
    {
        TreeNode b = a.l;
        a.l = b.r;
        b.r = a;
        return b;
    }

    TreeNode rotateL(TreeNode a)
    {
        TreeNode b = a.r;
        a.r = b.l;
        b.l = a;
        return b;
    }
}