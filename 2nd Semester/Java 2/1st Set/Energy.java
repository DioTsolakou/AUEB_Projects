public class Energy extends Usage 
{
	private double monthlyTvCharge;
	
	public Energy(String code, String description, String unit, double pricePerUnit, double monthlyFee, double monthlyTvCharge) 
	{
		super(code, description, unit, pricePerUnit, monthlyFee);
		this.monthlyTvCharge = monthlyTvCharge;
	}
	
	public double getMonthlyTvCharge()
	{
		return(monthlyTvCharge);
	}
	
	public void setMonthlyTvCharge(double monthlyTvCharge)
	{
		this.monthlyTvCharge = monthlyTvCharge;
	}	
}
