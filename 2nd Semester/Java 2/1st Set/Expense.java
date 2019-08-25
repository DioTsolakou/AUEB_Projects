public class Expense
{
    String expCode;
    String expDescription;

    public Expense(String expCode, String expDescription)
    {
        this.expCode = expCode;
        this.expDescription = expDescription;
    }

    public Expense(){}

    public String getCode()
    {
        return expCode;
    }

    public void setCode(String code)
    {
        this.expCode = code;
    }

    public String getExpDescription()
    {
        return expDescription;
    }

    public void setExpDescription(String expDescription)
    {
        this.expDescription = expDescription;
    }

    public String printExp()
    {
        return "Code " +expCode+ "\t Description " + expDescription;
    }
}