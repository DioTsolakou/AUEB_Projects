import java.io.*;
import java.util.*;

public class ReadHtml
{
    StringStackImpl newStack = new StringStackImpl();

    public void readHtml(String path)
    {
        File html_file = null;
        BufferedReader fr = null;
        String line;
        //Scanner in = new Scanner(System.in);
        //System.out.println("Plesase give the file's path : ");
        //path = in.nextLine();

        try
        {
            html_file = new File(path); // try to find the file
        }
        catch (NullPointerException e)
        {
            System.err.println("File not found.");
        }

        try
        {
            fr = new BufferedReader(new FileReader(html_file)); // try to open the file
        }
        catch (FileNotFoundException e)
        {
            System.err.println("Error opening file!");
        }

        try
        {
            line = fr.readLine();
            while (line != null) {
                if (line.trim().contains("<")) {
                    int open = line.indexOf("<");
                    if (line.charAt(open + 1) != '/' && line.charAt(open + 1) != '!') { // if the next character after < is neither / nor !
                        String newS = line.substring(open);                             // it selects the string contained between < and >
                        int close = newS.indexOf('>');                                  // and inserts it into the stack
                        String tagToPush = line.substring(open + 1, close);
                        newStack.push(tagToPush);
                        System.out.println("Contents inserted into the stack : " + tagToPush);
                    }
                    else if (line.charAt(open + 1) == '/')                              // if the next character after < is /
                    {                                                                   // it selects the string between </ and >
                        String pString = line.substring(open + 2);
                        int pClose = pString.indexOf('>');
                        String tagToPop = line.substring(open + 2, pClose + 2);
                        //System.out.println("Out " +tagToPop);
                        if (tagToPop.equalsIgnoreCase(newStack.peek())) {                       // checks if the aforementioned string is equal with the top one
                            System.out.println("Contents removed from the stack : " +tagToPop); // inside the stack and removes it
                            newStack.pop();
                        } else System.out.println("No matching tag found!");
                    }
                }
                else {
                    System.out.println("Error in finding tags");
                    break;
                }
                line = fr.readLine();
            }

            if (!newStack.isEmpty())                                //if the stack isn't empty, it means a tag hasn't properly closed
            {
                System.out.println("There are unmatched tags!");
            }
        }
        catch (IOException e)
        {
            System.err.println("Error reading file!");
        }
    }
}