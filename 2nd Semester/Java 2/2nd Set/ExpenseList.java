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
		
		/*
        for(Expense existingExpense : Expenses)
        {
            if (existingExpense.getClass().getName().equals(expense.getClass().getName()))
            {
                alreadyExists = true;
                break;
            }
        }
        if(!alreadyExists)
		*/	
            Expenses.add(expense);
        /*else
            System.out.println("Expense type already exists. Please add a new expense type.");
		*/
    }

    public Expense get(String code)
    {
        for(Expense expense : Expenses)
        {
            if (expense.getCode().equals(code))
            {
                return (expense);
            }
        }
        return null;
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

    public void writeFile(String path)
    {

        File f = null;
        File temp = null;
        BufferedWriter writer = null;

        try
        {
            f = new File(path);
        }
        catch (NullPointerException e)
        {
            System.out.println("Can't create file");
        }

        temp = new File("expenses_temp.txt");

        try
        {
            writer = new BufferedWriter(new FileWriter(temp));
        }
        catch (IOException e)
        {
            System.out.println("Can't write to file");
        }

        try {
            writer.write("EXPENSE_TYPE_LIST" + "\n" + "{");

            for (Expense expense : Expenses) {
                try {
                    if (expense instanceof Energy) {
                        writer.write("\n" + "\t" + "EXPENSE_TYPE" + "\n" + "\t" + "{" + "\n" + "\t" + "\t" + "EXPENSE_TYPE_CODE "
                                + expense.getCode()
                                + "\n" + "\t" + "\t" + "EXPENSE_TYPE_DESCR " + expense.getDescription()
                                + "\n" + "\t" + "\t" + "TYPE " + expense.getClass().getName()
                                + "\n" + "\t" + "\t" + "MEASUREMENT_UNIT " + ((Energy) expense).getUnit()
                                + "\n" + "\t" + "\t" + "PRICE " + ((Energy) expense).getPricePerUnit()
                                + "\n" + "\t" + "\t" + "MONTHLY_FIX_RATE " + ((Energy) expense).getMonthlyFixRate()
                                + "\n" + "\t" + "\t" + "MONTHLY_TV_CHARGE " + ((Energy) expense).getMonthlyTvCharge()
                                + "\n" + "\t" + "}" + "\n");
                    }
                    if (expense instanceof Telephone) {
                        writer.write("\n" + "\t" + "EXPENSE_TYPE" + "\n" + "\t" + "{" + "\n" + "\t" + "\t" + "EXPENSE_TYPE_CODE "
                                + expense.getCode()
                                + "\n" + "\t" + "\t" + "EXPENSE_TYPE_DESCR " + expense.getDescription()
                                + "\n" + "\t" + "\t" + "TYPE " + expense.getClass().getName()
                                + "\n" + "\t" + "\t" + "MEASUREMENT_UNIT " + ((Telephone) expense).getUnit()
                                + "\n" + "\t" + "\t" + "PRICE " + ((Telephone) expense).getPricePerUnit()
                                + "\n" + "\t" + "\t" + "MONTHLY_FIX_RATE " + ((Telephone) expense).getMonthlyFixRate()
                                + "\n" + "\t" + "\t" + "MONTHLY_FEE " + ((Telephone) expense).getMonthlyFee()
                                + "\n" + "\t" + "}" + "\n");
                    }
                    if (expense instanceof Water) {
                        writer.write("\n" + "\t" + "EXPENSE_TYPE" + "\n" + "\t" + "{" + "\n" + "\t" + "\t" + "EXPENSE_TYPE_CODE "
                                + expense.getCode()
                                + "\n" + "\t" + "\t" + "EXPENSE_TYPE_DESCR " + expense.getDescription()
                                + "\n" + "\t" + "\t" + "TYPE " + expense.getClass().getName()
                                + "\n" + "\t" + "\t" + "MEASUREMENT_UNIT " + ((Water) expense).getUnit()
                                + "\n" + "\t" + "\t" + "PRICE " + ((Water) expense).getPricePerUnit()
                                + "\n" + "\t" + "\t" + "MONTHLY_FIX_RATE " + ((Water) expense).getMonthlyFixRate()
                                + "\n" + "\t" + "\t" + "PRICE_OVER_HUNDRED " + ((Water) expense).getPriceOverHundred()
                                + "\n" + "\t" + "}" + "\n");
                    }
                    if (expense instanceof Cleaning) {
                        writer.write("\n" + "\t" + "EXPENSE_TYPE" + "\n" + "\t" + "{" + "\n" + "\t" + "\t" + "EXPENSE_TYPE_CODE "
                                + expense.getCode()
                                + "\n" + "\t" + "\t" + "EXPENSE_TYPE_DESCR " + expense.getDescription()
                                + "\n" + "\t" + "\t" + "TYPE " + expense.getClass().getName()
                                + "\n" + "\t" + "\t" + "MEASUREMENT_UNIT " + " "
                                + "\n" + "\t" + "\t" + "PRICE " + ((Cleaning) expense).getPricePerSquareMeter()
                                + "\n" + "\t" + "}" + "\n");
                    }
                    if (expense instanceof Rent) {
                        writer.write("\n" + "\t" + "EXPENSE_TYPE" + "\n" + "\t" + "{" + "\n" + "\t" + "\t" + "EXPENSE_TYPE_CODE "
                                + expense.getCode()
                                + "\n" + "\t" + "\t" + "EXPENSE_TYPE_DESCR " + expense.getDescription()
                                + "\n" + "\t" + "\t" + "TYPE " + expense.getClass().getName()
                                + "\n" + "\t" + "\t" + "MEASUREMENT_UNIT " + " "
                                + "\n" + "\t" + "\t" + "PRICE " + ((Rent) expense).getPricePerSquareMeter()
                                + "\n" + "\t" + "}" + "\n");
                    }
                } catch (IOException e) {
                    System.err.println("Error writing file");
                }
            }
            writer.write("}");
        }
        catch (IOException e)
        {
            System.err.println("Error writing file!");
        }

        try
        {
            f.delete();
            temp.renameTo(f);
        }
        finally
        {
            System.out.println("File Updated!");
        }

        try
        {
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
        Expense expense = null;

        try
        {
            f = new File(data);
        }
        catch (NullPointerException e)
        {
            System.out.println ("Can't open file");
        }

        try
        {
            reader = new BufferedReader(new FileReader(f));
        }
        catch (IOException e)
        {
            System.out.println("Can't read from file");
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

    public void readExpensesFromFile(String data)
    {
        String code = null, description = null, unit = null, type = null;
        double price = -1, montlhyFix = -1, monthlyFee = 1, monthlyTv = -1, priceOverHundred = -1;

        File f = null;
        BufferedReader reader = null;
        Expense expense  = null;
        String line;

        try
        {
            f = new File(data);
        }
        catch (NullPointerException e)
        {
            System.err.println("File not found.");
        }

        try
        {
            reader = new BufferedReader(new FileReader(f));
        }
        catch (FileNotFoundException e)
        {
            System.err.println("Error opening file!");
        }
		
		
		try
        {
            line = reader.readLine();
            while(line != null)
            {
                if(line.trim().equalsIgnoreCase("EXPENSE_TYPE"))
                {					
					//System.out.println("EXPENSE_TYPE");
                    while(line != null)
                    {
                        if(line.trim().toLowerCase().startsWith("EXPENSE_TYPE_CODE ".toLowerCase()))
                            code = (line.trim().substring(18).trim());
						
                        if(line.trim().toLowerCase().startsWith("EXPENSE_TYPE_DESCR ".toLowerCase()))
							description = (line.trim().substring(19).trim());
						
                        if (line.trim().toLowerCase().startsWith("TYPE ".toLowerCase())) {
							if (line.trim().substring(5).trim().equalsIgnoreCase("Energy"))
                            {
                                        /*expense = new Energy();*/
                                        type = "Energy";
                            } 
							else if (line.trim().substring(5).trim().equalsIgnoreCase("Telephone"))
                            {
                                        /*expense = new Telephone();*/
                                        type = "Telephone";
                            } 
							else if (line.trim().substring(5).trim().equalsIgnoreCase("Water"))
                            {
                                        /*expense = new Water();*/
                                        type = "Water";
                            }
							else if (line.trim().substring(5).trim().equalsIgnoreCase("Cleaning"))
                            {
                                        /*expense = new Cleaning();*/
                                        type = "Cleaning";
                            } 
							else if (line.trim().substring(5).trim().equalsIgnoreCase("Rent"))
                            {
                                        /*expense = new Rent();*/
                                        type = "Rent";
                            }
                        }
						if (line.trim().toLowerCase().startsWith("MEASUREMENT_UNIT ".toLowerCase()))
                            unit = line.trim().substring(17).trim();
						
                        if (line.trim().toLowerCase().startsWith("PRICE ".toLowerCase()))
                            price = Double.parseDouble(line.trim().substring(6).trim());
						
						if (line.trim().toLowerCase().startsWith("MONTHLY_FIX_RATE ".toLowerCase()))
                            montlhyFix = Double.parseDouble(line.trim().substring(17).trim());
						
						if (line.trim().toLowerCase().startsWith("MONTHLY_FEE ".toLowerCase()))
                            monthlyFee = Double.parseDouble(line.trim().substring(12).trim());
						
						if (line.trim().toLowerCase().startsWith("MONTHLY_TV_CHARGE ".toLowerCase()))
                            monthlyTv = Double.parseDouble(line.trim().substring(18).trim());
						
						if (line.trim().toLowerCase().startsWith("PRICE_OVER_HUNDRED ".toLowerCase()))
                            priceOverHundred = Double.parseDouble(line.trim().substring(19).trim());
						
                        line = reader.readLine();
						if(line.trim().equalsIgnoreCase("}"))
						{
							if (type.equalsIgnoreCase("Energy"))
                            {
                                expense = new Energy(code, description, unit, price, montlhyFix, monthlyTv);
								//add(expense);
                            }
                            else if (type.equalsIgnoreCase("Telephone"))
                            {
                                expense = new Telephone(code, description, unit, price, montlhyFix, monthlyFee);
								//add(expense);
                            }
                             else if (type.equalsIgnoreCase("Water"))
                            {
                                expense = new Water(code, description, unit, price, montlhyFix, priceOverHundred);
								//add(expense);
                            }
                            else if (type.equalsIgnoreCase("Cleaning"))
                            {
                                expense = new Cleaning(code, description, price);
								//add(expense);
                            }
                            else if (type.equalsIgnoreCase("Rent"))
                            {
                                expense = new Rent(code, description, price);
								//add(expense);
                            }
                            else
                            {
								System.out.println("Such expense type does not exist!");
                            }
							add(expense);
							break;
						}
                    }
					System.out.println(expense.print());
                }				
                line = reader.readLine();
            }
        }
        catch(Exception ex)
        {

        }
        finally
        {

        }
		
		
		
		

        try {
            line = reader.readLine();
            while (line != null) {
                if (line.trim().equalsIgnoreCase("EXPENSE_TYPE_LIST")) {
                    line = reader.readLine();
                    if (line.trim().equals("{")) {
                        line = reader.readLine();
                        if (line.trim().equalsIgnoreCase("EXPENSE_TYPE")) {
                            line = reader.readLine();
                            if (line.trim().equals("{")) {
                                if (line.trim().toLowerCase().startsWith("EXPENSE_TYPE_CODE ".toLowerCase())) {
                                    code = line.trim().substring(18).trim();
                                    line = reader.readLine();
                                }
                                if (line.trim().toLowerCase().startsWith("EXPENSE_TYPE_DESCR ".toLowerCase())) {
                                    description = line.trim().substring(19);
                                    line = reader.readLine();
                                }
                                if (line.trim().toLowerCase().startsWith("TYPE ".toLowerCase())) {
                                    if (line.trim().substring(5).trim().equalsIgnoreCase("Energy"))
                                    {
                                        /*expense = new Energy();*/
                                        type = "Energy";
                                    } else if (line.trim().substring(5).trim().equalsIgnoreCase("Telephone"))
                                    {
                                        /*expense = new Telephone();*/
                                        type = "Telephone";
                                    } else if (line.trim().substring(5).trim().equalsIgnoreCase("Water"))
                                    {
                                        /*expense = new Water();*/
                                        type = "Water";
                                    } else if (line.trim().substring(5).trim().equalsIgnoreCase("Cleaning"))
                                    {
                                        /*expense = new Cleaning();*/
                                        type = "Cleaning";
                                    } else if (line.trim().substring(5).trim().equalsIgnoreCase("Rent"))
                                    {
                                        /*expense = new Rent();*/
                                        type = "Rent";
                                    }
                                    line = reader.readLine();
                                }
                                if (line.trim().toLowerCase().startsWith("MEASUREMENT_UNIT ".toLowerCase())) {
                                    unit = line.trim().substring(17).trim();
                                    line = reader.readLine();
                                }
                                if (line.trim().toLowerCase().startsWith("PRICE ".toLowerCase())) {
                                    price = Double.parseDouble(line.trim().substring(6).trim());
                                    line = reader.readLine();
                                }
                                if (line.trim().toLowerCase().startsWith("MONTHLY_FIX_RATE ".toLowerCase()))
                                {
                                    montlhyFix = Double.parseDouble(line.trim().substring(17).trim());
                                    line = reader.readLine();
                                }
                                if (line.trim().toLowerCase().startsWith("MONTHLY_FEE ".toLowerCase()))
                                {
                                    monthlyFee = Double.parseDouble(line.trim().substring(12).trim());
                                    line = reader.readLine();
                                }
                                if (line.trim().toLowerCase().startsWith("MONTHLY_TV_CHARGE ".toLowerCase()))
                                {
                                    monthlyTv = Double.parseDouble(line.trim().substring(18).trim());
                                    line = reader.readLine();
                                }
                                if (line.trim().toLowerCase().startsWith("PRICE_OVER_HUNDRED ".toLowerCase()))
                                {
                                    priceOverHundred = Double.parseDouble(line.trim().substring(19).trim());
                                    line = reader.readLine();
                                }
                                if (line.trim().equals("}")) {
                                    line = reader.readLine();
                                    if (line.trim().equals("}"))
                                    {
                                        if (type.equalsIgnoreCase("Energy"))
                                        {
                                            expense = new Energy(code, description, unit, price, montlhyFix, monthlyTv);
                                        }
                                        else if (type.equalsIgnoreCase("Telephone"))
                                        {
                                            expense = new Telephone(code, description, unit, price, montlhyFix, monthlyFee);
                                        }
                                        else if (type.equalsIgnoreCase("Water"))
                                        {
                                            expense = new Water(code, description, unit, price, montlhyFix, priceOverHundred);
                                        }
                                        else if (type.equalsIgnoreCase("Cleaning"))
                                        {
                                            expense = new Cleaning(code, description, price);
                                        }
                                        else if (type.equalsIgnoreCase("Rent"))
                                        {
                                            expense = new Rent(code, description, price);
                                        }
                                        else
                                        {
                                            System.out.println("Such expense type does not exist!");
                                        }
                                        add(expense);
                                    }
                                }
                            }
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
            System.err.println("Error closing file!");
        }
    }
}