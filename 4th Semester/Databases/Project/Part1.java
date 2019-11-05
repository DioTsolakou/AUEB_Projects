/*
Main that creates a new object Utils, asks how many CSV files you would like to edit.
And depending on the number you typed, asks about the Nth file each time and calls through the Utils object
the editCSV method.
*/
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class Part1
{
    public static void main (String args[]) throws IOException
    {
        Utils newUtils = new Utils();
        Scanner in = new Scanner(System.in);
        String path1;
        String path2;
        String path3;
        String path4;
        String path5;
        int files;
        int counter = 1;

        System.out.println("How many .csv files would you like to edit? ");
        files = in.nextInt();

        for (int i = 0; i < files; i++)
        {
            System.out.println("Please give the .csv file number " +counter+ " : ");
            if (i == 0)
            {
                path1 = in.next();
                Reader reader1 = Files.newBufferedReader(Paths.get(path1));

                File file1 = new File("..\\newFile1.csv");
                FileWriter f1 = new FileWriter(file1);
                Writer writer1 = Files.newBufferedWriter(Paths.get("..\\newFile1.csv"), StandardOpenOption.APPEND);
                newUtils.editCSV(reader1, writer1);
                reader1.close();
                f1.close();
                writer1.close();
            }
            else if (i == 1)
            {
                path2 = in.next();
                Reader reader2 = Files.newBufferedReader(Paths.get(path2));

                File file2 = new File("..\\newFile2.csv");
                FileWriter f2 = new FileWriter(file2);
                Writer writer2 = Files.newBufferedWriter(Paths.get("..\\newFile2.csv"), StandardOpenOption.APPEND);
                newUtils.editCSV(reader2, writer2);
                reader2.close();
                f2.close();
                writer2.close();
            }
            else if (i == 2)
            {
                path3 = in.next();
                Reader reader3 = Files.newBufferedReader(Paths.get(path3));

                File file3 = new File("..\\newFile3.csv");
                FileWriter f3 = new FileWriter(file3);
                Writer writer3 = Files.newBufferedWriter(Paths.get("..\\newFile3.csv"), StandardOpenOption.APPEND);
                newUtils.editCSV(reader3, writer3);
                reader3.close();
                f3.close();
                writer3.close();
            }
            else if (i == 3)
            {
                path4 = in.next();
                Reader reader4 = Files.newBufferedReader(Paths.get(path4));

                File file4 = new File("..\\newFile4.csv");
                FileWriter f4 = new FileWriter(file4);
                Writer writer4 = Files.newBufferedWriter(Paths.get("..\\newFile4.csv"), StandardOpenOption.APPEND);
                newUtils.editCSV(reader4, writer4);
                reader4.close();
                f4.close();
                writer4.close();
            }
            else if (i == 4)
            {
                path5 = in.next();
                Reader reader5 = Files.newBufferedReader(Paths.get(path5));

                File file5 = new File("..\\newFile5.csv");
                FileWriter f5 = new FileWriter(file5);
                Writer writer5 = Files.newBufferedWriter(Paths.get("..\\newFile5.csv"), StandardOpenOption.APPEND);
                newUtils.editCSV(reader5, writer5);
                reader5.close();
                f5.close();
                writer5.close();
            }
            counter++;
        }
    }
}


