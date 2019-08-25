public class Rent extends Fixed
{
    public Rent(String code, String description, double pricePerSquareMeter)
    {
        super(code, description, pricePerSquareMeter);
    }

    public Rent(){}

    public String toString()
    {
        String temp ="";

        temp = "EXPENSE\n" +
               "{\n" +
               "    TYPE Rent\n" +
               "    EXPENSE_TYPE_CODE " + this.getCode() + "\n" +
               "   CONSUMPTION 0.0\n" +
               "}";

        return (temp);
    }
}
