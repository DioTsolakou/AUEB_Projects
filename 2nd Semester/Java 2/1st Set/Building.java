public class Building
{
    String code;
    String description;
    String address;
    float areaPrice;
    float sqM;

    public Building (String code, String description, String address, float areaPrice, float sqM)
    {
        this.code = code;
        this.description = description;
        this.address = address;
        this.areaPrice = areaPrice;
        this.sqM = sqM;
    }

    public Building() {}

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

    public float getAreaPrice()
    {
        return areaPrice;
    }

    public void setAreaPrice(float areaPrice)
    {
        this.areaPrice = areaPrice;
    }

    public float getSqM()
    {
        return sqM;
    }

    public void setSqM(float sqM)
    {
        this.sqM = sqM;
    }

    public String printBuilding()
    {
        return "Code " +code+ "\t Description " +description+ "\t Address " +address+ "\t Area Price ";
    }

/*    public float calcEnergy(float stateFees, float price, float kWh)
    {
        return monthlyFix + ert + stateFees + price*kWh;
    }*/
}