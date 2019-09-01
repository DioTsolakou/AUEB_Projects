public class main
{
    public static void main(String[] args)
    {
        ST newTree = new ST();

        newTree.addStopWord("Hello");
        newTree.addStopWord("there");
        newTree.load("D:\\Users\\Diogenis\\Uni\\Data Structures\\Third_Exercise\\src\\txt.txt");
        newTree.toString();
    }
}
