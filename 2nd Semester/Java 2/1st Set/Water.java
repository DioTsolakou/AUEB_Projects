public class Water extends Usage
{
	private double priceOverHundred;
	
	public Water(String code, String description, String unit, double pricePerUnit, double monthlyFee, double priceOverHundred) 
	{
		super(code, description, unit, pricePerUnit, monthlyFee);
		this.priceOverHundred = priceOverHundred;
	}
	
	public double getPriceOverHundred()
	{
		return (priceOverHundred);
	}
	
	public void setPriceOverHundred(double priceOverHundred)
	{
		this.priceOverHundred = priceOverHundred;
	}
}