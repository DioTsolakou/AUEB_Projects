public class Water extends Usage
{
    float price1;
    float price2;

    public Water(String expCode, String expDescription, float ppUnit, String unit, float monthlyFix, float price1, float price2)
    {
		super(expCode, expDescription, ppUnit, unit, monthlyFix);
        this.price1 = price1;
        this.price2 = price2;
    }

    public float getPrice1()
    {
        return price1;
    }

    public void setPrice1(float price)
    {
        this.price1 = price;
    }

    public float getPrice2()
    {
        return price2;
    }

    public void setPrice2(float price2)
    {
        this.price2 = price2;
    }

    public float calcWater(float cubic)
    {
        if (cubic > 100)
        {
            return monthlyFix + price2*cubic;
        }
        else if (cubic <= 100)
        {
            return monthlyFix + price1*cubic;
        }
        else
        {
            System.out.println("Invalid amount of cubic meters");
            return -1;
        }
    }
}