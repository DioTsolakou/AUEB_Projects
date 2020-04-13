package ML;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Dictionary
{
    List<Word> dictionary;

    public Dictionary()
    {
        dictionary = new ArrayList<>();
    }


    public List<Word> getDictionary()
    {
        return dictionary;
    }

    public void setDictionary(List<Word> dictionary)
    {
        this.dictionary = dictionary;
    }

    public void readEmails(String path)
    {
        File directory = new File(path);

        for (File file : directory.listFiles())
        {
            if (file.isFile())
            {
                try
                {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String line;
                    String token;
                    int index;

                    while ((line = reader.readLine()) != null)
                    {
                        line = line.replaceAll("[^a-zA-Z0-9$%.:\\s]", "");
                        line = line.replaceAll("[0-9]", "d");
                        StringTokenizer tokenizer = new StringTokenizer(line, " ");
                        while (tokenizer.hasMoreTokens())
                        {
                            token = tokenizer.nextToken();

                            if (dictionary.contains(token))
                            {
                                index = dictionary.indexOf(token);
                                dictionary.get(index).increase();
                            }
                            else
                            {
                                Word newWord = new Word(token);
                                dictionary.add(newWord);
                            }
                        }
                    }

                   reader.close();
                }
                catch (IOException exception)
                {
                    System.out.println("An error has occurred!");
                }
            }
        }
    }
}