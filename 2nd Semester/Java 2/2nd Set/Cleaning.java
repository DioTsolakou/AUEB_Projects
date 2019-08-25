public class Cleaning extends Fixed
{
    public Cleaning(String code, String description, double pricePerSquareMeter)
    {
        super(code, description, pricePerSquareMeter);
    }

    public Cleaning(){}

    public String toString()
    {
        String temp ="";

        temp = "EXPENSE\n" +
                "{\n" +
                "   TYPE Cleaning\n" +
                "   EXPENSE_TYPE_CODE " + this.getCode() + "\n" +
                "   CONSUMPTION 0.0\n" +
                "}";

        return (temp);
    }
}
