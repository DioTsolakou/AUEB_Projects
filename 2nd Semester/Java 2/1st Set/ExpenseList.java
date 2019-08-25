import java.util.ArrayList;

public class ExpenseList
{
    private ArrayList<Expense> exp1 = new ArrayList<Expense>();

    public void addExpense(Expense exp)
    {
        exp1.add(exp);
    }

    public void showExps()
    {
        for (Expense exp : exp1)
        {
            System.out.println(exp.printExp());
        }
    }
}