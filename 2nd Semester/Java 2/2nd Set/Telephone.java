public class Telephone extends Usage
{
    double monthlyFee;

    public Telephone(String code, String description, String unit, double pricePerUnit, double monthlyFixRate, double monthlyFee)
    {
        super(code, description, unit, pricePerUnit, monthlyFixRate);
        this.monthlyFee = monthlyFee;
    }

    public Telephone(){}

    public double getMonthlyFee()
    {
        return(this.monthlyFee);
    }

    public void setMonthlyFee(double monthlyFee)
    {
        this.monthlyFee = monthlyFee;
    }
}