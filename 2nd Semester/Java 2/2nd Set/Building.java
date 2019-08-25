public class Building
{
    private String code;
    private String description;
    private String address;
    private double areaPrice;
    private double area;

    public Building(String code, String description, String address, double areaPrice, double area)
    {
        this.code = code;
        this.description = description;
        this.address = address;
        this.areaPrice = areaPrice;
        this.area = area;
    }

    public Building(){}

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

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public double getAreaPrice()
    {
        return areaPrice;
    }

    public void setAreaPrice(double areaPrice)
    {
        this.areaPrice = areaPrice;
    }

    public double getArea()
    {
        return area;
    }

    public void setArea(double area)
    {
        this.area= area;
    }

    public String print()
    {
        return "Code " +code+ "\t Description " +description+ "\t Address " +address+ "\t Area Price " +areaPrice+ "\t Square Meters " + area;
    }
}