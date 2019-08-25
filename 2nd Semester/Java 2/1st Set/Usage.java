public abstract class Usage extends Expense
{
	private double pricePerUnit;
	private String unit;
	private double monthlyFixRate;
	
	public Usage(String code, String description, String unit, double pricePerUnit, double monthlyFixRate) 
	{
		super(code, description);
		this.pricePerUnit = pricePerUnit;
		this.unit = unit;
		this.monthlyFixRate = monthlyFixRate;
	}
	
	public double getPricePerUnit()
	{
		return (pricePerUnit);
	}
	
	public void setPricePerUnit(double pricePerUnit)
	{
		this.pricePerUnit = pricePerUnit;
	}	
	
	public String getUnit()
	{
		return (unit);
	}
	
	public void setUnit(String unit)
	{
		this.unit = unit;
	}	
	
	public double getMonthlyFixRate()
	{
		return (monthlyFixRate);
	}
	
	public void setMonthlyFixRate(double monthlyFixRate)
	{
		this.monthlyFixRate = monthlyFixRate;
	}	
}
