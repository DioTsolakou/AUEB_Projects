import java.util.ArrayList;

public class BuildingExpenseList
{
    private ArrayList<BuildingExpense> expT1= new ArrayList<BuildingExpense>();

    public void addBuilExpense(BuildingExpense expT)
    {
        expT1.add(expT);
    }

    public void showExpBuild()
    {
        for (BuildingExpense expT : expT1)
        {
            System.out.println(expT.printBExp());
        }
    }
}