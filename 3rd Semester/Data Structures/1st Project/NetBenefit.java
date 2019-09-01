import java.io.*;
import java.util.*;

public class NetBenefit
{
    public static void main(String args[])
    {
/*        String path = null;

        try {
            System.out.println(args[0]);
            String current = new java.io.File( "." ).getCanonicalPath();
            System.out.println("Current dir:"+current);
            File f = new File(current + "\\" + args[0]);
            path = f.getCanonicalPath();
            System.out.println(path);
            path = path.replace("\\", "/");
            System.out.println(path);
        }
        catch (IOException e)
        {
            System.err.println("No file was specified");
        }
        F.readFile(path);*/
        ReadFile F = new ReadFile();
        F.readFile("D:\\Users\\Diogenis\\Uni\\Data Structures\\First_Exercise\\src\\logistic.txt");
    }
}