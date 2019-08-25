/*SARINOPOULOU KONSTANTINA, SECOND SEMESTER, 3170142
 * COLLAKU DHIOGJENI, SECOND SEMESTER, 3170164*/

import java.io.*;
import java.util.*;

public class mainApp {

    static BuildingList buildingList = new BuildingList();
    static BuildingExpenseList buildingExpenseList = new BuildingExpenseList();
    static ExpenseList expenseList = new ExpenseList();


    public static void main(String[] args) {
        /*init();*/

        Scanner in = new Scanner(System.in); //input scanner initialization

        boolean done = false;
        boolean expDone = false;
        String answer, code, description, address, expType, expCode, expDescription, unit, path;
        double areaPrice, sqM, ppM2, ppUnit, monthlyFix, price, priceOver100, monthlyFees, ert;

        //expenseList.printFile("expense.txt");
        expenseList.readExpensesFromFile("expense.txt");
        //expenseList.printFile("expense.txt");


        readBuildingExpensesFromFile("building_expense.txt");
        //buildingExpenseList.printFile("building_expense.txt");
        while (!done) {
            System.out.println("\n1. Insert new building");
            System.out.println("2. Insert new expense");
            System.out.println("3. Show all buildings");
            System.out.println("4. Show the expenses for a building");
            System.out.println("5. Calculate the cost for a building");
            System.out.println("6. Calculate the cost of an expense");
            System.out.println("7. Save to text file");
            System.out.println("0. Exit");
            System.out.print("> ");
            answer = in.nextLine();

            if (answer.equals("1")) {
                System.out.println("New Building? ");
                System.out.print("Code? ");
                code = in.nextLine();
                System.out.print("Description? ");
                description = in.nextLine();
                System.out.print("Address? ");
                address = in.nextLine();
                System.out.print("Area Price? ");
                areaPrice = in.nextDouble();
                System.out.print("Square Meters? ");
                sqM = in.nextDouble();
                Building bud1 = new Building(code, description, address, areaPrice, sqM);
                buildingList.add(bud1);
            } else if (answer.equals("2")) {
                while (!expDone) {
                    System.out.println("New Expense? ");
                    System.out.println();
                    buildingList.print();
                    System.out.println();
                    System.out.println("Please type the code of the building you would like to select : ");
                    code = in.nextLine();
                    Building buildFind = buildingList.get(code);
                    System.out.println(buildFind.getCode());
                    System.out.println("Please type Fixed or Usage");
                    expType = in.nextLine();
                    if (expType.equalsIgnoreCase("Fixed")) {
                        System.out.println("Expense Code? ");
                        expCode = in.nextLine();
                        System.out.println("Expense Description? Please type Cleaning or Rent");
                        expDescription = in.nextLine();
                        System.out.println("Building Code? ");
                        code = in.nextLine();
                        Building existsBuilding = buildingList.get(code);
                        System.out.println("Price per square meter? ");
                        ppM2 = in.nextDouble();

                        if (expDescription.equalsIgnoreCase("Cleaning")) {
                            Cleaning new_cleaning = new Cleaning(expCode, expDescription, ppM2);
                            BuildingExpense new_build_exp = new BuildingExpense(existsBuilding, new_cleaning);
                            buildingExpenseList.add(new_build_exp);
                            expDone = true;
                        } else if (expDescription.equalsIgnoreCase("Rent")) {
                            Rent new_rent = new Rent(expCode, expDescription, ppM2);
                            BuildingExpense new_build_exp = new BuildingExpense(existsBuilding, new_rent);
                            buildingExpenseList.add(new_build_exp);
                            expDone = true;
                        } else {
                            System.out.println("Wrong fixed expense type. Please try again. ");
                        }

                    } else if (expType.equalsIgnoreCase("Usage")) {
                        System.out.println("Code? ");
                        expCode = in.nextLine();
                        System.out.println("Expense Type? ");
                        System.out.println("Please type Water or Telephone or Energy ");
                        expDescription = in.nextLine();
                        System.out.println("Building Code? ");
                        code = in.nextLine();
                        Building existsBuilding = buildingList.get(code);
                        if (expDescription.equalsIgnoreCase("Water")) {
                            System.out.println("Unit of consumption measurement? ");
                            unit = in.nextLine();
                            System.out.println("Price per unit? ");
                            ppUnit = in.nextDouble();
                            System.out.println("Price if usage is over 100 cubic meters? ");
                            priceOver100 = in.nextDouble();
                            System.out.println("Monthly fee of water usage? ");
                            monthlyFix = in.nextDouble();
                            Water new_water = new Water(expCode, expDescription, unit, ppUnit, monthlyFix, priceOver100);
                            BuildingExpense new_build_exp = new BuildingExpense(existsBuilding, new_water);
                            buildingExpenseList.add(new_build_exp);
                            expDone = true;
                        } else if (expDescription.equalsIgnoreCase("Telephone")) {
                            System.out.println("Unit of consumption measurement? ");
                            unit = in.nextLine();
                            System.out.println("Price per unit? ");
                            ppUnit = in.nextDouble();
                            System.out.println("Monthly fee of telephone usage? ");
                            monthlyFix = in.nextDouble();
                            System.out.println("Monthly telephony charges? ");
                            monthlyFees = in.nextDouble();
                            Telephone new_telephone = new Telephone(expCode, expDescription, unit, ppUnit, monthlyFix, monthlyFees);
                            BuildingExpense new_build_exp = new BuildingExpense(existsBuilding, new_telephone);
                            buildingExpenseList.add(new_build_exp);
                            expDone = true;
                        } else if (expDescription.equalsIgnoreCase("Energy")) {
                            System.out.println("Unit of consumption measurement? ");
                            unit = in.nextLine();
                            System.out.println("Price per unit? ");
                            ppUnit = in.nextDouble();
                            System.out.println("Monthly fee of energy usage? ");
                            monthlyFix = in.nextDouble();
                            System.out.println("Monthly ERT charges? ");
                            ert = in.nextDouble();
                            Energy new_energy = new Energy(expCode, expDescription, unit, ppUnit, monthlyFix, ert);
                            BuildingExpense new_build_exp = new BuildingExpense(existsBuilding, new_energy);
                            buildingExpenseList.add(new_build_exp);
                            expDone = true;
                        }
                        buildingExpenseList.writeFile("building_expense.txt");
                        buildingExpenseList.printFile("building_expense.txt");
                        done = true;
                    } else System.out.println("There is no expense type that matches the given name");
                    buildingExpenseList.print();
                }
            } else if (answer.equals("3")) {
                buildingList.print();
            } else if (answer.equals("4")) {
                System.out.println("Choose a building by typing its code");
                buildingList.print();
                code = in.nextLine();
                buildingExpenseList.printExpenseByBuilding(code);
            } else if (answer.equals("5")) {
                buildingList.print();
                System.out.println();
                System.out.println("Choose a building by typing its code ");
                code = in.nextLine();
                System.out.println();
                System.out.println(buildingExpenseList.getTotalCostByBuilding(code));
            } else if (answer.equals("6")) {
                expenseList.print();
                System.out.println();
                System.out.println("Choose type of expense ");
                expType = in.nextLine();
                System.out.println();
                System.out.println(buildingExpenseList.getTotalCostByExpenseType(expType));
            } else if (answer.equals("7")) {
                write();
                buildingExpenseList.printFile("building_expense.txt");
                expenseList.writeFile("expense.txt");
                expenseList.printFile("expense.txt");
            } else if (answer.equals("0")) done = true;
        }
    }

    public static void readBuildingExpensesFromFile(String data) {
        double consumption = 0.0;

        File f = null;
        BufferedReader reader = null;
        Building building = null;
        BuildingExpense buildingExpense = null;
        Expense expense = null;
        String line = null;

        try {
            f = new File(data);
        } catch (NullPointerException e) {
            System.err.println("File not found.");
        }

        try {
            reader = new BufferedReader(new FileReader(f));
        } catch (FileNotFoundException e) {
            System.err.println("Error opening file!");
        }

        System.out.println("readBuildingExpensesFromFile");


        try {
            line = reader.readLine();
            while (line != null) {
                if (line.trim().equalsIgnoreCase("BUILDING")) {
                    building = new Building();
                    //System.out.println("BUILDING");
                    while (line != null) {
                        if (line.trim().toLowerCase().startsWith("BUILDING_CODE ".toLowerCase()))
                            building.setCode(line.trim().substring(14).trim());

                        if (line.trim().toLowerCase().startsWith("BUILDING_DESCR ".toLowerCase()))
                            building.setDescription(line.trim().substring(15).trim());

                        if (line.trim().toLowerCase().startsWith("ADDRESS ".toLowerCase()))
                            building.setAddress(line.trim().substring(8).trim());

                        if (line.trim().toLowerCase().startsWith("SURFACE ".toLowerCase()))
                            building.setArea(Double.parseDouble(line.trim().substring(8).trim()));

                        if (line.trim().toLowerCase().startsWith("PRICE ".toLowerCase()))
                            building.setAreaPrice(Double.parseDouble(line.trim().substring(6).trim()));

                        if (line.trim().equalsIgnoreCase("EXPENSES")) {
                            //System.out.println("EXPENSES");
                            while (line != null) {
                                if (line.trim().equalsIgnoreCase("EXPENSE")) {
                                    //System.out.println("EXPENSE");
                                    while (line != null) {
										/*
										if (line.trim().toLowerCase().startsWith("TYPE ".toLowerCase())
										{
											if (line.trim().substring(5).trim().equalsIgnoreCase("Energy"))
                                            {
                                                //expense = new Energy();
                                            }
                                            else if (line.trim().substring(5).trim().equalsIgnoreCase("Telephone"))
                                            {
                                                //expense = new Telephone();
                                            }
                                            else if (line.trim().substring(5).trim().equalsIgnoreCase("Water"))
                                            {
                                                //expense = new Water();
                                            }
                                            else if (line.trim().substring(5).trim().equalsIgnoreCase("Cleaning"))
                                            {
                                                //expense = new Cleaning();
                                            }
                                            else if (line.trim().substring(5).trim().equalsIgnoreCase("Rent"))
                                            {
                                                expense = new Rent();
                                            }
										}*/

                                        if (line.trim().toLowerCase().startsWith("EXPENSE_TYPE_CODE ".toLowerCase())) {
                                            expense = expenseList.get(line.trim().substring(18).trim());
                                        }

                                        if (line.trim().toLowerCase().startsWith("CONSUMPTION ".toLowerCase())) {
                                            consumption = Double.parseDouble(line.trim().substring(12).trim());
                                        }

                                        line = reader.readLine();
                                        if (line.trim().equalsIgnoreCase("}")) {
                                            buildingExpense = new BuildingExpense(building, expense, consumption);
                                            buildingExpenseList.add(buildingExpense);
                                            break;
                                        }
                                    }
                                }
                                line = reader.readLine();
                                if (line.trim().equalsIgnoreCase("}"))
                                    break;
                            }
                        }
                        line = reader.readLine();
                        if (line.trim().equalsIgnoreCase("}"))
                            buildingList.add(building);
                        break;
                    }
                    System.out.println(building.print());
                }
                line = reader.readLine();
            }
            System.out.println(buildingExpenseList.toString());
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {

        }
    }

    public static String write()
    {
        BuildingExpense buildingExpense = null;
        String temp = "";

        try {
            for (Building building : buildingList.getBuildings())
            {
                temp = "\n" + "\t" + "BUILDING" + "\n" + "\t" + "{" + "\n" + "\t" + "\t" + "BUILDING_CODE "
                        + building.getCode()
                        + "\n" + "\t" + "\t" + "BUILDING_DESCR " + building.getDescription()
                        + "\n" + "\t" + "\t" + "ADDRESS " + building.getAddress()
                        + "\n" + "\t" + "\t" + "SURFACE " + building.getArea()
                        + "\n" + "\t" + "\t" + "PRICE " + building.getAreaPrice();
                for (Building building : buildingList.getBuildings())
                {
                    temp += "\n" + "\t" + "\t" + "EXPENSES" + "\n" + "\t" + "\t" + "{"
                            + "\n" + "\t" + "\t" + "\t" + "EXPENSE" + "\n" + "\t" + "\t" + "\t" + "{"
                            + "\n" + "\t" + "\t" + "\t" + "\t" + "TYPE " + buildingExpense.getExpense().getClass().getName()
                            + "\n" + "\t" + "\t" + "\t" + "\t" + "EXPENSE_TYPE_CODE " + buildingExpense.getExpense().getCode()
                            + "\n" + "\t" + "\t" + "\t" + "\t" + "CONSUMPTION " + buildingExpense.getEstimatedMonthlyConsumption()
                            + "\n" + "\t" + "\t" + "\t" + "}" + "\n" + "\t" + "\t" + "}" + "\n" + "\t" + "}" + "\n";
                }
            }
            return temp;
        }
        catch (IOException e)
        {
            System.err.println("Error writing file");
        }
    }
	
	public static void instantiateBuilding(String string)
	{
		Building building;
		
		
	}	
}

 /*   private static void init()
    {
        expenseList.add(new Rent("1.1", "Rent", 10));
        expenseList.add(new Telephone("2.2", "Telephone", "Minutes", 0.5, 35, 10));
        expenseList.add(new Cleaning("2.3", "Cleaning", 7.5));
        expenseList.add(new Energy("2.4", "Energy", "kWh", 0.12, 25, 12.5));
        expenseList.add(new Water("1.4", "Water", "m3", 0.1, 35, 0.5));


        Building b1 = new Building("1", "Offices", "5th Avenue", 130, 340);
        Building b2 = new Building("2", "Shop", "St. John", 100, 200);
        Building b3 = new Building("3", "Fire Station", "Weston Road", 250, 550);
        Building b4 = new Building("4", "Police Station", "Two Bridges Road", 700, 1000);
        Building b5 = new Building("5", "Restaurant", "St Nicholas Avenue", 400, 600);

        buildingList.add(b1);
        buildingList.add(b2);
        buildingList.add(b3);
        buildingList.add(b4);
        buildingList.add(b5);

        //first building expenses
        Rent r1 = new Rent("1.1", "Rent", 10);
        Cleaning c1 = new Cleaning("1.3", "Cleaning", 5);
        Energy en1 = new Energy("1.2", "Energy", "kWh", 0.09, 20, 15);
        Water wat1 = new Water("1.4", "Water", "m3", 0.1, 35, 0.5);

        BuildingExpense fix1 = new BuildingExpense(b1, r1);
        BuildingExpense use1 = new BuildingExpense(b1, en1, 100.0);
        BuildingExpense fix1_1 = new BuildingExpense(b1, c1);
        BuildingExpense use1_1 = new BuildingExpense(b1, wat1, 105.0);

        buildingExpenseList.add(fix1);
        buildingExpenseList.add(use1);
        buildingExpenseList.add(fix1_1);
        buildingExpenseList.add(use1_1);

        //second building expenses
        Rent r2 = new Rent("2.1", "Rent", 12);
        Telephone tele2 = new Telephone("2.2", "Telephone", "Minutes", 0.5, 35, 10);
        Cleaning c2 = new Cleaning("2.3", "Cleaning", 7.5);
        Energy en2 = new Energy("2.4", "Energy", "kWh", 0.12, 25, 12.5);

        BuildingExpense fix2 = new BuildingExpense(b2, r2);
        BuildingExpense use2 = new BuildingExpense(b2, tele2, 50);
        BuildingExpense fix2_1 = new BuildingExpense(b2, c2);
        BuildingExpense use2_1 = new BuildingExpense(b2, en2, 35);

        buildingExpenseList.add(fix2);
        buildingExpenseList.add(use2);
        buildingExpenseList.add(fix2_1);
        buildingExpenseList.add(use2_1);

        //third building expenses
        Rent r3 = new Rent("3.1", "Rent", 13.5);
        Telephone tele3 = new Telephone("3.2", "Telephone", "Minutes", 0.1, 20, 12);
        Cleaning c3 = new Cleaning("3.3", "Cleaning", 13);
        Water wat3 = new Water("3.4", "Water", "m3", 0.05, 32.5, 0.25);

        BuildingExpense fix3 = new BuildingExpense(b3, r3);
        BuildingExpense use3 = new BuildingExpense(b3, tele3, 50);
        BuildingExpense fix3_1 = new BuildingExpense(b3, c3);
        BuildingExpense use3_1 = new BuildingExpense(b3, wat3, 350);

        buildingExpenseList.add(fix3);
        buildingExpenseList.add(use3);
        buildingExpenseList.add(fix3_1);
        buildingExpenseList.add(use3_1);

        //fourth building expenses
        Rent r4 = new Rent("4.1", "Rent", 10);
        Telephone tele4 = new Telephone("4.2", "Telephone", "Minutes", 0.2, 15, 12);
        Cleaning c4 = new Cleaning("4.3", "Cleaning", 12);
        Energy en4 = new Energy("4.4", "Energy", "kWh", 0.11, 30, 10);

        BuildingExpense fix4 = new BuildingExpense(b4, r4);
        BuildingExpense use4 = new BuildingExpense(b4, tele4, 500);
        BuildingExpense fix4_1 = new BuildingExpense(b4, c4);
        BuildingExpense use4_1 = new BuildingExpense(b4, en4, 35);

        buildingExpenseList.add(fix4);
        buildingExpenseList.add(use4);
        buildingExpenseList.add(fix4_1);
        buildingExpenseList.add(use4_1);

        //fifth building expenses
        Rent r5 = new Rent("5.1", "Rent", 50);
        Telephone tele5 = new Telephone("5.2", "Telephone", "Minutes", 0.6, 30, 20);
        Cleaning c5 = new Cleaning("5.3", "Cleaning", 15);
        Water wat5 = new Water("5.4", "Water", "m3", 0.3, 32, 0.65);

        BuildingExpense fix5 = new BuildingExpense(b5, r5);
        BuildingExpense use5 = new BuildingExpense(b5, tele5, 505);
        BuildingExpense fix5_1 = new BuildingExpense(b5, c5);
        BuildingExpense use5_1 = new BuildingExpense(b5, wat5, 355);

        buildingExpenseList.add(fix5);
        buildingExpenseList.add(use5);
        buildingExpenseList.add(fix5_1);
        buildingExpenseList.add(use5_1);*/