import acm.program.*;

public class Morse extends Program
{
	public void run()
	{							
		String[] morse = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---",
							"-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-",
							"..-", "...-", ".--", "-..-", "-.--", "--.."};
		String answer = readLine("Please type your text ");
		println(translate(answer, morse));
	}

	public String translate(String answer, String[] array)
	{
		answer = answer.toUpperCase();
		char ch;
		int ascii;
		String result = "";
		for (int i = 0; i < answer.length(); i++)
		{
			ch = answer.charAt(i);
			ascii = (int) ch;
			if (ascii == 32) result += "\n";
			else if ( ascii >= 65 && ascii <= 90)
			{
				result += array[ascii - 65] + " ";
			}
		}
		return result;
	}
}
