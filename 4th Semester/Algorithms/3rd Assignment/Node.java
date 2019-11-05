import java.util.Comparator;
import java.util.List;

public class Node implements Comparator<Node>
{
    public String node;
    public List<String> neighbors;

    public Node(){}

    public Node(String node, List<String> neighbors)
    {
        this.node = node;
        this.neighbors = neighbors;
    }

    public int compare(Node a, Node b)
    {
        if (a.neighbors.size() > b.neighbors.size())
        {
            return 1;
        }
        else if (a.neighbors.size() < b.neighbors.size())
        {
            return -1;
        }
        else return 0;
    }
}
