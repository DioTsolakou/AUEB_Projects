public abstract class Fixed extends Expense
{
	private double pricePerSquareMeter;
	
	public Fixed(String code, String description, double pricePerSquareMeter)
	{
		super(code, description);
		this.pricePerSquareMeter = pricePerSquareMeter;
	}
	
	public double getPricePerSquareMeter()
	{
		return(this.pricePerSquareMeter);
	}
	
	public void setPricePerSquareMeter(double pricePerSquareMeter)
	{
		this.pricePerSquareMeter = pricePerSquareMeter;
	}	
}
