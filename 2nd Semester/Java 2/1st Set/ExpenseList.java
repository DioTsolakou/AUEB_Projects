import java.io.*;
import java.util.ArrayList;

public class ExpenseList 
{
	ArrayList<Expense> Expenses;
	
	public ExpenseList() 
	{
		this.Expenses = new ArrayList<Expense>();
	}
	
	public void add(Expense expense)
	{
		boolean alreadyExists = false;
		
		for(Expense existingExpense : Expenses)
		{
            if (existingExpense.getClass().getName().equals(expense.getClass().getName()))
            {
            	alreadyExists = true;
            	break;
            }
        }
		if(!alreadyExists)
			Expenses.add(expense);
		else
			System.out.println("Expense type already exists. Please add a new expense type.");
	}
	
    public ArrayList<Expense> getExpenses() 
    {
    	return (Expenses);
    }
    
    public void print()
    {
		for(Expense expense : Expenses)
		{
			System.out.println(expense.getClass().getName());
        }    	
    }

	public void createFile(String path)
	{

		File f = null;
		BufferedWriter writer = null;

		try
		{
			f = new File(path);
		}
		catch (NullPointerException e)
		{
			System.out.println("Can't create file");
		}

		try
		{
			writer = new BufferedWriter(new FileWriter(f));
		}
		catch (IOException e)
		{
			System.out.println("Can't write to file");
		}

		for (Expense expense : Expenses)
		{
			try
			{
				if (expense instanceof Expense)
				{
					writer.write("EXPENSE_TYPE_LIST" + "\n" + "{" + "\n" + "\t" + "EXPENSE_TYPE" + "\n" + "\t" + "{" + "\n" + "\t" + "\t" + "EXPENSE_TYPE_CODE "
							+ expense.getCode()
							+ "\n" + "\t" + "\t" + "EXPENSE_TYPE_DESCR " + expense.getDescription()
							+ "\n" + "\t" + "\t" + "TYPE " + expense.getClass().getName()
							+ "\n" + "\t" + "\t" + "MEASUREMENT_UNIT " + expense.getUnit()
							+ "\n" + "\t" + "Price " + product.getPrice()
							+ "\n" + "}" + "\n" + "\n");
				}
				if (product instanceof DVD)
				{
					numInstancesDVD++;
					writer.write("Product" + "\n" + "{" + "\n" + "\t" + "Item DVD" + "\n" + "\t" + "Title "
							+ product.getTitle()
							+ "\n" + "\t" + "Director " + ((DVD)product).getDirector()
							+ "\n" + "\t" + "Star " + ((DVD)product).getStar()
							+ "\n" + "\t" + "Playing Time " + product.getplayingTime()
							+ "\n" + "\t" + "Price " + product.getPrice()
							+ "\n" + "}" + "\n" + "\n");
				}
			}
			catch (IOException e)
			{
				System.err.println("Error writing file");
			}
		}

		try
		{
			writer.write("Number of CD's  "+numInstancesCD+'\n' );
			writer.write("Number of DVD's "+numInstancesDVD+'\n');
			writer.close();

		}
		catch (IOException e)
		{
			System.err.println("Error closing file.");
		}
	}

	public void printFile(String data)
	{
		File f = null;
		BufferedReader reader = null;
		String line;
		Item product = null;

		try
		{
			f = new File(data);
		}
		catch (NullPointerException e)
		{
			System.out.println ("Can't open file/object");
		}

		try
		{
			reader = new BufferedReader(new FileReader(f));
		}
		catch (IOException e)
		{
			System.out.println("Can't read from file/object");
		}

		try
		{
			line = reader.readLine();
			while (line != null)
			{
				System.out.println(line);
				line  = reader.readLine();
			}
			reader.close();
		}
		catch (IOException e)
		{
			System.err.println("Read error!");
		}
	}
}
