import java.util.*;

public class mainApp {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        BuildingList myBList = new BuildingList();
        ExpenseList myExpList = new ExpenseList();
        BuildingExpenseList myExpTList = new BuildingExpenseList();
		
		       //building initialization
        Building b1 = new Building("1", "Offices", "5th Avenue", 130, 340);
        Building b2 = new Building("2", "Shop", "St. John", 100, 200);
        Building b3 = new Building("3", "Fire Station", "Weston Road", 250, 550);
        Building b4 = new Building("4", "Police Station", "Two Bridges Road", 700, 1000);
        Building b5 = new Building("5", "Restaurant", "St Nicholas Avenue", 400, 600);

        myBList.addBuilding(b1);
        myBList.addBuilding(b2);
        myBList.addBuilding(b3);
        myBList.addBuilding(b4);
        myBList.addBuilding(b5);

        //first building expense type
        Fixed fix1 = new Fixed("f1", "Fixed 1", b1, "Cleaning", 100);
        Usage use1 = new Usage("u1", "Usage 1", b1, 100, "kWh", 100);
        Fixed fix1_1 = new Fixed("f1.1", "Fixed 1.1", b1, "Rent", 50);
        Usage use1_1 = new Usage("u1.1", "Usage 1.1", b1, 200, "m3", 15);

        myExpTList.addBuilExpense(fix1);
        myExpTList.addBuilExpense(use1);
        myExpTList.addBuilExpense(fix1_1);
        myExpTList.addBuilExpense(use1_1);

        //second building expense type
        Fixed fix2 = new Fixed("f2", "Fixed 2", b2, "Rent", 250);
        Usage use2 = new Usage("u2", "Usage 2", b2, 50, "Minutes", 30);
        Fixed fix2_1 = new Fixed("f2.1", "Fixed 2.1", b2, "Cleaning", 10);
        Usage use2_1 = new Usage("u2.1", "Usage 2.1", b2, 35, "kWh", 25);

        myExpTList.addBuilExpense(fix2);
        myExpTList.addBuilExpense(use2);
        myExpTList.addBuilExpense(fix2_1);
        myExpTList.addBuilExpense(use2_1);

        //third building expense type
        Fixed fix3 = new Fixed("f3", "Fixed 3", b3, "Rent", 20);
        Usage use3 = new Usage("u3", "Usage 3", b3, 50, "Minutes", 20);
        Fixed fix3_1 = new Fixed("f3.1", "Fixed 3.1", b3, "Cleaning", 10);
        Usage use3_1 = new Usage("u3.1", "Usage 3.1", b3, 35, "m3", 25);

        myExpTList.addBuilExpense(fix3);
        myExpTList.addBuilExpense(use3);
        myExpTList.addBuilExpense(fix3_1);
        myExpTList.addBuilExpense(use3_1);

        //fourth building expense type
        Fixed fix4 = new Fixed("f4", "Fixed 4", b4, "Rent", 120);
        Usage use4 = new Usage("u4", "Usage 4", b4, 50, "Minutes", 20);
        Fixed fix4_1 = new Fixed("f4.1", "Fixed 4.1", b4, "Cleaning", 10);
        Usage use4_1 = new Usage("u4.1", "Usage 4.1", b4, 35, "kWh", 25);

        myExpTList.addBuilExpense(fix4);
        myExpTList.addBuilExpense(use4);
        myExpTList.addBuilExpense(fix4_1);
        myExpTList.addBuilExpense(use4_1);

        //fifth building expense type
        Fixed fix5 = new Fixed("f5", "Fixed 5", b5, "Rent", 120);
        Usage use5 = new Usage("u5", "Usage 5", b5, 50, "Minutes", 20);
        Fixed fix5_1 = new Fixed("f5.1", "Fixed 5.1", b5, "Cleaning", 10);
        Usage use5_1 = new Usage("u5.1", "Usage 5.1", b5, 35, "m3", 25);

        myExpTList.addBuilExpense(fix5);
        myExpTList.addBuilExpense(use5);
        myExpTList.addBuilExpense(fix5_1);
        myExpTList.addBuilExpense(use5_1);

        //first building expenses
        BuildingExpense ex1_1 = new BuildingExpense("1.1", "Expense 1.1", b1, "Fixed");
        BuildingExpense ex1_2 = new BuildingExpense("1.2", "Expense 1.2", b1, "Usage");
        BuildingExpense ex1_3 = new BuildingExpense("1.3", "Expense 1.3", b1, "Fixed");
        BuildingExpense ex1_4 = new BuildingExpense("1.4", "Expense 1.4", b1, "Usage");

        myExpList.addExpense(ex1_1);
        myExpList.addExpense(ex1_2);
        myExpList.addExpense(ex1_3);
        myExpList.addExpense(ex1_4);

        //second building expenses
        BuildingExpense ex2_1 = new BuildingExpense("2.1", "Expense 2.1", b2, "Fixed");
        BuildingExpense ex2_2 = new BuildingExpense("2.2", "Expense 2.2", b2, "Usage");
        BuildingExpense ex2_3 = new BuildingExpense("2.3", "Expense 2.3", b2, "Fixed");
        BuildingExpense ex2_4 = new BuildingExpense("2.4", "Expense 2.4", b2, "Usage");

        myExpList.addExpense(ex2_1);
        myExpList.addExpense(ex2_2);
        myExpList.addExpense(ex2_3);
        myExpList.addExpense(ex2_4);

        //third building expenses
        BuildingExpense ex3_1 = new BuildingExpense("3.1", "Expense 3.1", b3, "Fixed");
        BuildingExpense ex3_2 = new BuildingExpense("3.2", "Expense 3.2", b3, "Usage");
        BuildingExpense ex3_3 = new BuildingExpense("3.3", "Expense 3.3", b3, "Fixed");
        BuildingExpense ex3_4 = new BuildingExpense("3.4", "Expense 3.4", b3, "Usage");

        myExpList.addExpense(ex3_1);
        myExpList.addExpense(ex3_2);
        myExpList.addExpense(ex3_3);
        myExpList.addExpense(ex3_4);

        //fourth building expenses
        BuildingExpense ex4_1 = new BuildingExpense("4.1", "Expense 4.1", b4, "Fixed");
        BuildingExpense ex4_2 = new BuildingExpense("4.2", "Expense 4.2", b4, "Usage");
        BuildingExpense ex4_3 = new BuildingExpense("4.3", "Expense 4.3", b4, "Fixed");
        BuildingExpense ex4_4 = new BuildingExpense("4.4", "Expense 4.4", b4, "Usage");

        myExpList.addExpense(ex4_1);
        myExpList.addExpense(ex4_2);
        myExpList.addExpense(ex4_3);
        myExpList.addExpense(ex4_4);

        //fifth building expenses
        BuildingExpense ex5_1 = new BuildingExpense("5.1", "Expense 5.1", b5, "Fixed");
        BuildingExpense ex5_2 = new BuildingExpense("5.2", "Expense 5.2", b5, "Usage");
        BuildingExpense ex5_3 = new BuildingExpense("5.3", "Expense 5.3", b5, "Fixed");
        BuildingExpense ex5_4 = new BuildingExpense("5.4", "Expense 5.4", b5, "Usage");

        myExpList.addExpense(ex5_1);
        myExpList.addExpense(ex5_2);
        myExpList.addExpense(ex5_3);
        myExpList.addExpense(ex5_4);
		
        boolean done = false;
        String answer, code, description, address, expType, expCode, expDescription, useType;
        float areaPrice, sqM, ppM2;

        while (!done)
        {
            System.out.println("\n1. Insert new building");
            System.out.println("2. Insert new expense");
            System.out.println("3. Show all buildings");
            System.out.println("4. Show the expenses for a building");
            System.out.println("5. Calculate the cost for a building");
            System.out.println("6. Calculate the cost of an expense");
            System.out.println("0. Exit");
            System.out.print("> ");
            answer = in.nextLine();

            if (answer.equals("1"))
            {
                    System.out.println("New Building? ");
                    System.out.print("Code? ");
                    code = in.nextLine();
                    System.out.print("Description? ");
                    description = in.nextLine();
                    System.out.print("Address? ");
                    address = in.nextLine();
                    System.out.print("Area Price? ");
                    areaPrice = in.nextFloat();
                    System.out.print("Square Meters? ");
                    sqM = in.nextFloat();
                    Building bud1 = new Building(code, description, address, areaPrice, sqM);
                    myBList.addBuilding(bud1);
            }

            else if (answer.equals("2"))
            {
                    System.out.println("New Expense? ");
                    myBList.showBuilds();
                    System.out.println("Please type the code of the building you would like to select : ");
                    code = in.nextLine();
                    myBList.lookUp(code);
                    System.out.println("Please type Fixed or Usage");
                    expType = in.nextLine();
                    if (expType.equalsIgnoreCase("Fixed"))
                    {
                        System.out.println("Expense Code? ");
                        expCode = in.nextLine();
                        System.out.println("Description? ");
                        expDescription = in.nextLine();
                        System.out.println("Price per square meter? ");
                        ppM2 = in.nextFloat();
                        //Fixed fix1 = new Fixed(expCode, expDescription, newbuilding, );
                        //myExpList.addExpense(fix1);
                    }
                    else if (expType.equalsIgnoreCase("Usage"))
                    {
                        System.out.println("Expense Type? ");
                        System.out.println("Please type Water or Telephone or Energy ");
                        useType = in.nextLine();
                        System.out.println("Code? ");
                        expCode = in.nextLine();
                        System.out.println("Description? ");
                        expDescription = in.nextLine();
                        if (useType.equalsIgnoreCase("water"))
                        {

                        }

                    }
                    else System.out.println("There is no expense type that matches the given name");
                    myExpList.showExps();
            }

           else if (answer.equals("3"))
            {
                myBList.showBuilds();
            }

            else if (answer.equals("4"))
            {
                System.out.println("Choose a building by typing its code");
                myBList.showBuilds();
                code = in.nextLine();
                myBList.lookUp(code);
                myExpList.showExps();
            }

            else if (answer.equals("5"))
            {
                myExpTList.showExpBuild();
				//calculation method
            }

            else if (answer.equals("6"))
            {
                myExpTList.showExpBuild();
                System.out.println("Choose type of expense");
				//needs to be filled
            }
            else if (answer.equals("0")) done = true;
        }
    }
}