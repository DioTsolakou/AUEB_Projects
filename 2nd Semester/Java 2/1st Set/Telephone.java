public class Telephone extends Usage
{
    float monthlyFee;

    public Telephone(String expCode, String expDescription, float ppUnit, String unit, float monthlyFix, float monthlyFees)
    {
        super(expCode, expDescription, ppUnit, unit, monthlyFix);
        this.monthlyFee = monthlyFees;
    }

    public Telephone(){}

    public float getMonthlyFee()
    {
        return monthlyFee;
    }

    public void setMonthlyFee(float monthlyFee)
    {
        this.monthlyFee = monthlyFee;
    }

    public float calcTel(float price, float time)
    {
        return monthlyFix + monthlyFee + price*time;
    }
}