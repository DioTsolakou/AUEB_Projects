import acm.program.*;

public class RandomGeneratorProgram extends Program
{
	public void run()
	{
		int low = 0;
		int high = 0;
		int args = 0;
		int n = 0;
		println();
		println("What random number would you like to produce? : ");
		println();
		println("Type pr for prime");
		println();
		println("Type po for powers of 2");
		println();
		println("Type fib for fibonacci");
		println();
		println("Type sq for square");
		println();
		while (true)
		{
			String answer = readLine("Type your choice : ");
			args = readInt("How many arguments would you like to insert? ");
			if (args == 1) n = readInt("Give the number ");
			else if (args == 2)
			{
				low = readInt("Give the lower limit ");
				high = readInt("Give the upper limit ");
			}
			else println("Invalid number of arguments");
			
			if (answer.equals("pr"))
			{
				RandomGeneratorImproved rgi = new RandomGeneratorImproved();
				if (args == 1) println(rgi.nextPrime(n));
				else println(rgi.nextPrime(low, high));
			}
			if (answer.equals("po"))
			{
				RandomGeneratorImproved rgi = new RandomGeneratorImproved();
				if (args == 1) println(rgi.nextPof2(n));
				else println(rgi.nextPof2(low, high));
			}
			if (answer.equals("fib"))
			{
				RandomGeneratorImproved rgi = new RandomGeneratorImproved();
				if (args == 1) println(rgi.nextFib(n));
				else println(rgi.nextFib(low, high));
			}
			if (answer.equals("sq"))
			{
				RandomGeneratorImproved rgi = new RandomGeneratorImproved();
				if (args == 1) println(rgi.nextSquared(n));
				else println(rgi.nextSquared(low, high));
			}
			break;
		}
	}
}

