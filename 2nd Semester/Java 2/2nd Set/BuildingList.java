import java.util.ArrayList;

public class BuildingList
{
    ArrayList<Building> Buildings;

    public BuildingList()
    {
        this.Buildings = new ArrayList<Building>();
    }

    public void add(Building building)
    {
        boolean alreadyExists = false;

        for(Building existingBuilding : Buildings)
        {
            if (existingBuilding.getCode().equals(building.getCode()))
            {
                alreadyExists = true;
                break;
            }
        }
        if(!alreadyExists)
            Buildings.add(building);
        else
            System.out.println("Building already exists. Please add a new building");
    }

    public Building get(String code)
    {
        for(Building building : Buildings)
        {
            if (building.getCode().equals(code))
            {
                return (building);
            }
        }
        return null;
    }

    public ArrayList<Building> getBuildings()
    {
        return (Buildings);
    }

    public void print()
    {
        for(Building building : Buildings)
        {
            System.out.println(building.print());
        }
    }
}
