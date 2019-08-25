public class Energy extends Usage
{
    float ert;

    public Energy(String expCode, String expDescription, float ppUnit, String unit, float monthlyFix, float ert)
    {
        super(expCode, expDescription, ppUnit, unit, monthlyFix);
        this.ert = ert;
    }

    public Energy(){}

    public float getErt()
    {
        return ert;
    }

    public void setErt(float ert)
    {
        this.ert = ert;
    }
}