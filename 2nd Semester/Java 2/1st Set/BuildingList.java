import java.util.ArrayList;

public class BuildingList
{
    ArrayList<Building> Buildings = new ArrayList<Building>();

    public void addBuilding(Building b)
    {
        Buildings.add(b);
    }

    public void lookUp(String theCode)
    {
            for (Building b : Buildings)
            {
                if (b.getCode().equalsIgnoreCase(theCode))
                {
                    System.out.println(b.printBuilding());
                }
            }
    }

    public void lookUpD(String desc)
    {
        for (Building b : Buildings)
        {
            if (b.getDescription().equalsIgnoreCase(desc))
            {
                System.out.println(b.printBuilding());
            }
            else System.out.println("A building with the given description does not exist");
        }
    }

    public void showBuilds()
    {
        for (Building b : Buildings)
        {
            System.out.println(b.printBuilding());
        }
    }
}