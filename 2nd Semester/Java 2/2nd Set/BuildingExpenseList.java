import java.io.*;
import java.util.ArrayList;

public class BuildingExpenseList
{
    ArrayList<BuildingExpense> BuildingExpenses;

    public BuildingExpenseList()
    {
        BuildingExpenses = new ArrayList<BuildingExpense>();
    }

    public void add(BuildingExpense buildingExpense)
    {
        boolean alreadyExists = false;

        for(BuildingExpense existingBuildingExpense : BuildingExpenses)
        {
            if (existingBuildingExpense.getBuilding().getCode().equals(buildingExpense.getBuilding().getCode()) &&
                    existingBuildingExpense.getExpense().getClass().getName().equals(buildingExpense.getExpense().getClass().getName()))
            {
                alreadyExists = true;
                break;
            }
        }
        if(!alreadyExists)
            BuildingExpenses.add(buildingExpense);
        else
            System.out.println("Building expense already exists. Please add a new building expense.");
    }

    public ArrayList<BuildingExpense> getBuildingExpenses()
    {
        return (BuildingExpenses);
    }

    public void print()
    {
        for(BuildingExpense buildingExpense : BuildingExpenses)
        {
            System.out.println(buildingExpense.print());
        }
    }


    public void printExpenseByBuilding(String code)
    {
        for(BuildingExpense buildingExpense : BuildingExpenses)
        {
            if (buildingExpense.getBuilding().getCode().equals(code))
            {
                System.out.println(buildingExpense.print());
            }
        }
    }

    public 	double getTotalCostByBuilding(String code)
    {
        double totalCost = 0;

        for(BuildingExpense buildingExpense : BuildingExpenses)
        {
            if (buildingExpense.getBuilding().getCode().equals(code))
            {
                totalCost += buildingExpense.getCost();
            }
        }
        return (totalCost);
    }

    public 	double getTotalCostByExpenseType(String type)
    {
        double totalCost = 0;

        for(BuildingExpense buildingExpense : BuildingExpenses)
        {
            if (buildingExpense.getExpense().getClass().getName().equalsIgnoreCase(type))
            {
                totalCost += buildingExpense.getCost();
            }
        }
        return (totalCost);
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

        temp = new File("building_expenses_temp.txt");

        try
        {
            writer = new BufferedWriter(new FileWriter(temp));
        }
        catch (IOException e)
        {
            System.out.println("Can't write to file");
        }

        try {
            writer.write("BUILDING_LIST" + "\n" + "{" );
            for (BuildingExpense buildingExpense : BuildingExpenses) {
                try {
                    if (buildingExpense instanceof BuildingExpense) {
                        writer.write("\n" + "\t" + "BUILDING" + "\n" + "\t" + "{" + "\n" + "\t" + "\t" + "BUILDING_CODE "
                                + buildingExpense.getBuilding().getCode()
                                + "\n" + "\t" + "\t" + "BUILDING_DESCR " + buildingExpense.getBuilding().getDescription()
                                + "\n" + "\t" + "\t" + "ADDRESS " + buildingExpense.getBuilding().getAddress()
                                + "\n" + "\t" + "\t" + "SURFACE " + buildingExpense.getBuilding().getArea()
                                + "\n" + "\t" + "\t" + "PRICE " + buildingExpense.getBuilding().getAreaPrice()
                                + "\n" + "\t" + "\t" + "EXPENSES" + "\n" + "\t" + "\t" + "{"
                                + "\n" + "\t" + "\t" + "\t" + "EXPENSE" + "\n" + "\t" + "\t" + "\t" + "{"
                                + "\n" + "\t" + "\t" + "\t" + "\t" + "TYPE " + buildingExpense.getExpense().getClass().getName()
                                + "\n" + "\t" + "\t" + "\t" + "\t" + "EXPENSE_TYPE_CODE " + buildingExpense.getExpense().getCode()
                                + "\n" + "\t" + "\t" + "\t" + "\t" + "CONSUMPTION " + buildingExpense.getEstimatedMonthlyConsumption()
                                + "\n" + "\t" + "\t" + "\t" + "}" + "\n" + "\t" + "\t" + "}" + "\n" + "\t" + "}" + "\n");
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
        /*BuildingExpense buildingExpense = null;*/

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
            System.err.println("Read error! ");
        }
    }
}