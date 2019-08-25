import acm.program.*;

public class ticket extends Program{
    private static final double price = 1.4;
    private static final int guardian = 0;

    public void run(){
        double change;
        double sum = 0;
        while (sum < price){
            double input = readDouble("Please give the required money.");
            if(input == guardian)break;
            if(input != 0.1 && input != 0.2 && input !=0.5 && input !=1 && input != 2 && input != 5){
                println("You gave invalid amount of money, please try again giving 0.1,0.2,0.5,1,2 or 5 euros.");
            }
            else{
                sum += input;

            }
        }
        if(sum == price){
            println("Here is your ticket.");
        }
        else if(sum < price){
            println("You didn't put enough money to buy the ticket.");
            change = sum;
            change_method(change);

        }
        else {
            change = sum - price;
            println("Here is your ticket. ");
            while (change > 0){
                change = change_method(change);
            }

        }
    }
    private double change_method(double change){
        while(change/2 >= 1){
            println("You have change: 2 euros.");
            change = change - 2;
        }
        while(change/1 >= 1){
            println("You have change: 1 euros.");
            change = change - 1;
        }
        while(change/0.5 >= 1){
            println("You have change: 0.5 euros.");
            change = change - 0.5;
        }
        while(change/0.2 >= 1){
            println("You have change: 0.2 euros.");
            change = change - 0.2;
        }
        while(change >= 0.1){
            println("You have change: 0.1 euros.");
            change = change - 0.1;
        }
        return change;
    }
}