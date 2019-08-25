import acm.program.*;

public class Ticket extends Program 
{
	private static final double price = 1.4;
	private static final int SENTINEL = 0;
	
	public void run() 
	{
		double total = 0.0;
		double change = 0.0;
		while (true) 
		{
			double money = readDouble("Give the money needed for the ticket ");
			if (money == SENTINEL) 
				break;
			
			if (money == 0.1 || money == 0.2 || money == 0.5 || money == 1 || money == 2 || money == 5) 
			{
				total += money;
			}
			else 
			{
				println("False amount given.");
			}
		}
		 
		if (total == price) 
		{
			println("Here is your ticket.");
		}
		else if (total == 0) 
		{
			println("Not enough money to buy your ticket.");
		}
		else if (total < price) 
		{
			println("Not enough money to buy your ticket.");
			change = total;
			while (change/1 >= 1) 
			{
				println("You have change: 1 euro");
				change = change - 1;
			}
			while (change/0.5 >= 1) 
			{
				println("You have change: 0.5 euros");
				change = change - 0.5;
			}
			while (change/0.2 >= 1) 
			{
				println("You have change: 0.2 euros");
				change = change - 0.2;
			}
			while (change/0.09 >= 1) 
			{
				println("You have change: 0.1 euros");
				change = change - 0.1;
			}
		}
		else 
		{
			change = total - price;	
			println("Here is your ticket. ");
			while (change >= 0.1) 
			{
				while(change/5 >= 1) 
				{
					println("You have change: 5 euros.");					
					change = change - 5;
				}					
				while(change/2 >= 1) 
				{
					println("You have change: 2 euros.");					
					change = change - 2;
				}
				while(change/1 >= 1) 
				{
					println("You have change: 1 euros.");					
					change = change - 1;
				}
				while(change/0.5 >= 1) 
				{
					println("You have change: 0.5 euros.");					
					change = change - 0.5;
				}
				while(change/0.2 >= 1) 
				{
					println("You have change: 0.2 euros.");					
					change = change - 0.2;
				}
				while(change >= 0.09) 
				{
					println("You have change: 0.1 euros.");
					change = change - 0.1;
				}					
			}	

		}
	}
}