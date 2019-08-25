public class BuildingExpense extends Expense
{
    Building build;
    String expType;
    float calcConsumption;

    public BuildingExpense(String expCode, String expDescription, Building build, String expType)
    {
        super(expCode, expDescription);
        this.expType = expType;
    }

    public BuildingExpense(){}

    public String getExpType()
    {
        return expType;
    }

    public void setExpType(String expType)
    {
        this.expType = expType;
    }

    public float getCalcConsumption()
    {
        return calcConsumption;
    }

    public void setCalcConsumption(float calcConsumption)
    {
        this.calcConsumption = calcConsumption;
    }

    public String printBExp()
    {
        return "Building" +Building+ "\t Expense Type" +expType+ "\t Calculated Consumption" +calcConsumption;
    }
}