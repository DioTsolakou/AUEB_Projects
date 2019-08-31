import acm.program.*;

public class DNA extends Program
{
	public int findFirstMatchingPosition(String shortDNA, String longDNA)
	{
		String chain = "";
		for (int i = 0; i < shortDNA.length(); i++)
		{
			char ch = shortDNA.charAt(i);
			if (ch == 'A') chain += 'T';
			else if (ch == 'T') chain += 'A';
			else if (ch == 'C') chain += 'G';
			else chain += 'C';
		}
		
		int n = longDNA.indexOf(chain);
		return n;
	}
	
	public void run()
	{
		boolean firstInputIsValid = false;
		boolean secondInputIsValid = false;
		char am;
		String shDNA;
		String loDNA;
		println();
		while (true)
		{
			shDNA = readLine("Type the shorter DNA chain ");
			loDNA = readLine("Type the longer DNA chain ");
			shDNA = shDNA.toUpperCase();
			loDNA = loDNA.toUpperCase();
			
			if (shDNA.length() != 0 && loDNA.length() != 0)
			{	
				if (shDNA.length() > loDNA.length())
				{
					println("The first DNA chain should be shorter than the second");
				}
				else
				{
					firstInputIsValid = false;
					secondInputIsValid = false;
					for (int k = 0; k < shDNA.length(); k++)
					{
						am = shDNA.charAt(k);
						if (am == 'A' || am == 'T' || am == 'C' || am == 'G')
						{
							firstInputIsValid = true;
							break;
						}	
						else
						{
							println("The chains should contain only the next amino acids : A, T, C, G");
							break;
						}
				
					}

					for (int p = 0; p < loDNA.length(); p++)
					{
						am = loDNA.charAt(p);
						if (am == 'A' || am == 'T' || am == 'C' || am == 'G')
						{
							secondInputIsValid = true;
							break;
						}	
						else
						{
							println("The chains should contain only the next amino acids : A, T, C, G");
							break;
						}
					}			
					
					if(firstInputIsValid && secondInputIsValid)
						break;
				}	
			}
		}
		
		println(findFirstMatchingPosition(shDNA, loDNA));
	}
}
