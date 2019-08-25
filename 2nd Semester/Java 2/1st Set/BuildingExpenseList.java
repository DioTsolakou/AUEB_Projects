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
}