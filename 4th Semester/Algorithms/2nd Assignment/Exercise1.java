public class Exercise1
{
    private Integer[] leaves;
    private Integer[] solution;

    public Integer shortestRoute(Integer[] array)
    {
        leaves = new Integer[array.length];
        solution = new Integer[array.length];

        if (array.length == 0 || array[0] == 0)
        {
            return -1;
        }

        leaves[0] = 0;

        for (int i = 1; i < leaves.length; i++)
        {
            leaves[i] = Integer.MAX_VALUE - 1;
        }

        for (int i = 0; i < array.length; i++)
        {
            for (int j = 0; j < i; j++)
            {
                if (i <= j + array[j] && leaves[j] + 1 < leaves[i])
                {
                    solution[i] = j;
                    leaves[i] = leaves[j] + 1;
                }
            }
        }

        Integer[] temp_array = new Integer[solution.length];
        for (int i = solution.length - 1; i > 0; i = solution[i])
        {
            temp_array[i] = i + 1;
        }
        System.out.print("[ 1 ");
        for (int i = 0; i < temp_array.length; i++)
        {
            if (temp_array[i] != null)
            {
                System.out.print(temp_array[i] + " ");
            }
        }
        System.out.print("] , ");

        return leaves[array.length - 1];
    }
}
