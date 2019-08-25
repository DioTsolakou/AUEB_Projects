public class Water extends Usage
{
    private double priceOverHundred;

    public Water(String code, String description, String unit, double pricePerUnit, double monthlyFixRate, double priceOverHundred)
    {
        super(code, description, unit, pricePerUnit, monthlyFixRate);
        this.priceOverHundred = priceOverHundred;
    }

    public Water(){}

    public double getPriceOverHundred()
    {
        return (priceOverHundred);
    }

    public void setPriceOverHundred(double priceOverHundred)
    {
        this.priceOverHundred = priceOverHundred;
    }
}