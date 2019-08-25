public class Fixed extends BuildingExpense
{
    float ppM2;

    public Fixed(String expCode, String expDescription, Building build, String expType, float ppM2)
    {
        super(expCode, expDescription, Building build);
        this.ppM2 = ppM2;
    }

    public Fixed(){}

    public float getPpM2()
    {
        return ppM2;
    }

    public void setPpM2(float ppM2)
    {
        this.ppM2 = ppM2;
    }

    public float Cleaning(float ppM2, float sqM)
    {
        return ppM2*sqM;
    }

    public float Rent(float ppM2, float sqM)
    {
        return ppM2*sqM;
    }
}