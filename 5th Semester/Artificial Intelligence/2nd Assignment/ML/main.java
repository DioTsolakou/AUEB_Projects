package ML;

import java.util.Scanner;

public class main
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        String hamPath;
        String spamPath;
        String testHamPath;
        String testSpamPath;

        //train ham emails ID3
        System.out.println("Please give the directory path to the training ham emails for the ID3 algorithm : ");
        hamPath = in.nextLine();

        Dictionary dictionaryHam = new Dictionary();
        dictionaryHam.readEmails(hamPath);


        //train spam emails ID3
        System.out.println("Please give the directory path to the training spam emails for the ID3 algorithm : ");
        spamPath = in.nextLine();

        Dictionary dictionarySpam = new Dictionary();
        dictionarySpam.readEmails(spamPath);


        //train ham emails AdaBoost
        //System.out.println("Please give the directory path to the testing ham emails for the AdaBoost algorithm : ");
        //testHamPath = in.nextLine();

        //train spam emails AdaBoost
        //System.out.println("Please give the directory path to the testing spam emails for the AdaBoost algorithm : ");
        //testSpamPath = in.nextLine();
    }
}
