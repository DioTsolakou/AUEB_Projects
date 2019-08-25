public class BuildingExpense 
{
	private Building building;
	private Expense expense;
	private double estimatedMonthlyConsumption;

	public BuildingExpense(Building building, Expense expense, double estimatedMonthlyConsumption) 
	{
		this.building = building;
		this.expense = expense;
		this.estimatedMonthlyConsumption = estimatedMonthlyConsumption;
	}
	
    public BuildingExpense(Building building, Expense expense)
    {
        this.building = building;
        this.expense = expense;
    }	
	
	public Building getBuilding()
	{
		return (this.building);
	}
	
	public Expense getExpense()
	{
		return (this.expense);
	}
		
	public double getCost()
	{
		double cost = 0;
		
		if(this.expense instanceof Rent)
			cost = getRentCost();
		else if(this.expense instanceof Cleaning)
			cost = getCleaningCost();
		else if(this.expense instanceof Energy)
			cost = getEnergyCost();
		else if(this.expense instanceof Water)
			cost = getWaterCost();
		else if(this.expense instanceof Telephone)
			cost = getTelephoneCost();
		
		return(cost);
	}
	
	private double getRentCost()
	{
		double cost;
		cost = ((Rent)this.expense).getPricePerSquareMeter() * this.building.getArea();
		return(cost);
	}
	
	private double getCleaningCost()
	{
		double cost;
		cost = ((Cleaning)this.expense).getPricePerSquareMeter() * this.building.getArea();
		return(cost);
	}
	
	private double getEnergyCost()
	{
		double cost;
		Energy energy = (Energy)this.expense;
		
		cost = energy.getMonthlyFixRate() + energy.getMonthlyTvCharge() + (this.building.getArea() * this.building.getAreaPrice()) + (energy.getPricePerUnit() * this.estimatedMonthlyConsumption);
		return (cost);
	}
	
	private double getWaterCost()
	{
		double cost;
		Water water = (Water)this.expense;
		
		if(this.estimatedMonthlyConsumption > 100)
			cost = water.getMonthlyFixRate() + (water.getPriceOverHundred() * this.estimatedMonthlyConsumption);
		else
			cost = water.getMonthlyFixRate() + (water.getPricePerUnit() * this.estimatedMonthlyConsumption);
		
		return (cost);
	}
	
	private double getTelephoneCost()
	{
		double cost;
		Telephone telephone = (Telephone)this.expense;
		
		cost = telephone.getMonthlyFixRate() + telephone.getMonthlyFee() + (telephone.getPricePerUnit() * this.estimatedMonthlyConsumption);
		
		return (cost);
	}

	public String print()
    {
        return "Building Code " +this.getBuilding().getCode()+ "\t Expense Type " +this.getExpense().getDescription()+ "\t Estimated Monthly Consumption " + estimatedMonthlyConsumption;
    }
}