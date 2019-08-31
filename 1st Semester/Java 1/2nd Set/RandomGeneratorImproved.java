import acm.util.*;

public class RandomGeneratorImproved extends RandomGenerator
{	
	public int nextPrime(int n)
	{
		return (nextPrime(0, n - 1));
	}
	
	public int nextPrime(int low, int high)
	{
		boolean flag = false;
		int prime;
		
		for (int i = low; i <= high; i++)
		{
			if (PrimeCheck(i))
			{
				flag = true;
				break;
			}		
		}
		
		if (flag)
		{
			while (true)
			{
				prime = this.nextInt(low, high);	
				if (PrimeCheck(prime)) return prime;		
			}	
		}
		else return -1;
	}
	
	public int nextPof2(int n)
	{
		return(nextPof2(0, n - 1));
	}
	
	public int nextPof2(int low, int high)
	{
		boolean flag = false;
		int pof2;
		for (int i = low; i <= high; i++)
		{
			if (Pof2Check(i))
			{	
				flag = true;
				break;
			}
		}
		
		if (flag)
		{
			while (true)
			{
				pof2 = this.nextInt(low, high);
				if (Pof2Check(pof2)) return pof2;
			}
		}
		else return -1;
	}
	
	public int nextFib(int n)
	{
		return (nextFib(0, n - 1));
	}
	
	public int nextFib(int low, int high)
	{
		boolean flag = false;
		int fib = -1;
		for (int i = low; i <= high; i++)
		{
			if (FibCheck(i))
			{
				flag = true;
				break;
			}
		}
		
		if (flag)
		{
			while (true)
			{
				fib = this.nextInt(low, high);
				if (FibCheck(fib)) break;
			}
		}
		return fib;
	}
	
	public int nextSquared(int n)
	{
		return (nextSquared(0, n - 1));
	}
	
	public int nextSquared(int low, int high)
	{
		boolean flag = false;
		int squ;
		for (int i = low; i <= high; i++)
		{
			if (SqCheck(i))
			{
				flag = true;
				break;
			}
		}
		
		if (flag)
		{
			while (true)
			{
				squ = this.nextInt(low, high);
				if (SqCheck(Squ)) return squ;
			}
		}
		else return -1;
	}
	
	public boolean PrimeCheck(int n)
	{
		if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (int i = 3; i * i <= n; i += 2)
            if (n % i == 0) return false;
        return true;
	}
	
	public boolean Pof2Check(int n)
	{
		if (n <= 0) return false;
		while (n > 1)
		{
			if (n % 2 != 0) return false;
			n = n/2;
		}
		return true;
	}
	
	public boolean FibCheck(int n)
	{
		boolean flag = false;
		int fib1, fib2, currentFib;
		fib1 = 1;
		fib2 = 0;
		if ((n == 0) || (n == 1)) flag = true;
		if(n >= 2) 
		{
			while (true) 
			{
				currentFib = fib1 + fib2;
				if (n == currentFib) flag = true;
				fib2 = fib1;
				fib1 = currentFib;
				if (currentFib >= n) break;
			}
		}
		return flag;
	}

	public boolean SqCheck(int n)
	{
		int sq = 1;
		int num = 1;
		while (sq <= n)
		{
			if (sq == n) return true;
			num++;
			sq = num * num;
		}
		return false;
	}
}	
