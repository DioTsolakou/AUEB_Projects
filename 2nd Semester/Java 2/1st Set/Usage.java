public class Usage extends BuildingExpense
{
    float ppUnit;
    String unit;
    float monthlyFix;

    public Usage(String expCode, String expDescription, Building build, float ppUnit, String unit, float monthlyFix)
    {
        super(expCode, expDescription, Building build);
        this.ppUnit = ppUnit;
        this.unit = unit;
        this.monthlyFix = monthlyFix;
    }

    public Usage(){}

    public float getPrice()
    {
        return ppUnit;
    }

    public void setPrice(float price)
    {
        this.ppUnit = price;
    }

    public String getUnit()
    {
        return unit;
    }

    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    public float getMonthlyFix()
    {
        return monthlyFix;
    }

    public void setMonthlyFix(float monthlyFix)
    {
        this.monthlyFix = monthlyFix;
    }
}