public class Exercise2
{
    private Integer[][] caloriesAndFat;

    public Integer caloriesAndFatDiet(Integer calories[], Integer fat[], Integer wantedCalories)
    {
        caloriesAndFat = new Integer[2][wantedCalories + 1];

        for (int i = 0; i < 2; i++)
        {
            caloriesAndFat[i][0] = 0;
        }

        for (int j = 0; j <= wantedCalories; j++)
        {
            caloriesAndFat[0][j] = Integer.MAX_VALUE;
        }

        for (int i = 1; i < 2; i++)
        {
            for (int j = 0; j <= wantedCalories; j++)
            {
                if (calories[i - 1] > j)
                {
                    caloriesAndFat[i][j] = caloriesAndFat[i - 1][j];
                }
                else
                {
                    caloriesAndFat[i][j] = Math.min(caloriesAndFat[i - 1][j], caloriesAndFat[i][j - calories[i - 1]] + fat[i - 1]);
                }
            }
        }

        System.out.println(Integer.MAX_VALUE - caloriesAndFat[0][1]);
        return Integer.MAX_VALUE + caloriesAndFat[1][wantedCalories];
    }
}
