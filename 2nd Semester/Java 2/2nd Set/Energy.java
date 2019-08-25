public class Energy extends Usage
{
    private double monthlyTvCharge;

    public Energy(String code, String description, String unit, double pricePerUnit, double monthlyFixRate, double monthlyTvCharge)
    {
        super(code, description, unit, pricePerUnit, monthlyFixRate);
        this.monthlyTvCharge = monthlyTvCharge;
    }

    public Energy(){}

    public double getMonthlyTvCharge()
    {
        return(monthlyTvCharge);
    }

    public void setMonthlyTvCharge(double monthlyTvCharge)
    {
        this.monthlyTvCharge = monthlyTvCharge;
    }

    public String toString()
    {
        String temp ="";

        temp = "EXPENSE\n" +
                "{\n" +
                "    TYPE Energy\n" +
                "    EXPENSE_TYPE_CODE " + this.getCode() + "\n" +
                "   CONSUMPTION 0.0\n" +
                "}";

        return (temp);
    }
}