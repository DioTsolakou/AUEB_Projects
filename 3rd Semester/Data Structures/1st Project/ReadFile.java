import java.io.*;
import java.util.*;

public class ReadFile
{
    IntQueueImpl newQueue = new IntQueueImpl(); //instantiates a new Queue

    public void readFile(String path)
    {
        int sum = 0; // initialize the total amount of money spent or earned through buying or selling shares
        File txt = null;
        BufferedReader reader = null;
        String line;

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

        try // try to read the lines of the file
        {
            line = reader.readLine();
            while (line != null)
            {
                StringTokenizer st = new StringTokenizer(line, " ");
                String token = st.nextToken();
                if (token.equalsIgnoreCase("buy"))
                {
                    int amountBought = Integer.parseInt(st.nextToken());
                    if (st.nextToken().equalsIgnoreCase("price"))
                    {
                        int priceBought = Integer.parseInt(st.nextToken());

                        sum -= amountBought*priceBought; // decreases the total amount of money according to what was bought and at what price
                        System.out.println("You bought " +amountBought+ " shares at a price of " +priceBought);
                        System.out.println();
                        System.out.println("Your current sum is : " +sum);

                        for (int i = 0; i < amountBought; i++) // fills the queue with the amount of shares bought (e.g. 50 places in the queue) and fills each with the price that it was bought
                        {
                            newQueue.put(priceBought);
                        }
                    }
                }
                else if (token.equalsIgnoreCase("sell"))
                {
                    int amountSold = Integer.parseInt(st.nextToken());
                    if (amountSold > newQueue.size())
                    {
                        System.err.println("You can't sell more than what you have!");
                        break;
                    }
                    if (st.nextToken().equalsIgnoreCase("price"))
                    {
                        int priceSold = Integer.parseInt(st.nextToken());

                        sum += amountSold*priceSold; // increases the total amount of money according to what was sold and at what price
                        System.out.println("You sold " +amountSold+ " shares at a price of " +priceSold);
                        System.out.println();
                        System.out.println("Your current sum is : " +sum);

                        for (int j = 0; j < amountSold; j++) // removes the amount of shares sold (e.g 20 places/items are removed from the queue)
                        {
                            newQueue.get();
                        }
                    }
                }
                line = reader.readLine();
            }
        }
        catch (IOException e)
        {
            System.err.println("Error reading file!");
        }

        try
        {
            reader.close();
        }
        catch (IOException e)
        {
            System.err.println("Error closing file.");
        }

        System.out.println();
        System.out.println("Your total net benefit/loss is : " +sum);
    }
}