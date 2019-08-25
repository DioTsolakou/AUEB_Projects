public abstract class Expense 
{
    private String code;
    private String description;

    public Expense(String code, String description)
    {
        this.code = code;
        this.description = description;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String print()
    {
        return ("Expense Code " + this.code + "\t Expense Description " + this.description);
    }	
}
