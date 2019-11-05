/*
Reads line by line the CSV file that was given by the user.
If it's on the firs line, the header, adds/replaces each date with a -01 as a day,
replaces " with an empty string, splits the string whenever it detects a comma and assigns that value into an array.
Then for elements 6 until the last element, assigns them to a different array so that it will contain the dates.
It then writes the header.
When we are not on the first line, we again replace the ", and again split the line like above.
Writing the first 6 values of each line/first 6 elements of the split array and then write the price and date.
*/
import java.io.*;

public class Utils
{
    public void editCSV(Reader reader, Writer writer) throws IOException
    {
        BufferedReader newReader = new BufferedReader(reader);
        BufferedWriter newWriter = new BufferedWriter(writer);
        String line;
        String splits[];
        String dates[] = new String[150];
        int currentLine = 0;

        while ((line = newReader.readLine()) != null)
        {
            if (currentLine == 0)
            {
                line = line.replaceAll("(\\w{4}-\\w{2})", "$1-01");
                line = line.replaceAll("\"", "");
                splits = line.split(",");
                for (int i = 6; i < splits.length; i++)
                {
                    dates[i-6] = splits[i];
                }

                newWriter.write("zipcode,City,State,Metro,CountyName,SizeRank,Price,Date \n");
            }
            else
            {
                line = line.replaceAll("\"", "");
                splits = line.split(",");
                for (int j = 6; j < splits.length; j++)
                {
                    for (int k = 0; k < 6; k++)
                    {
                        newWriter.append(splits[k] + ",");
                    }
                    newWriter.append(splits[j] + "," + dates[j-6]+ "\n");
                }
            }
            currentLine++;
        }

        newReader.close();
        newWriter.close();
    }
}