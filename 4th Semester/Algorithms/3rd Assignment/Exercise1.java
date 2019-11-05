import java.util.*;

public class Exercise1
{
    public int distance[];
    public int vertices;
    public Map<Integer, String> fixed;
    PriorityQueue <Node> pq;
    List<List<Node>> neighbors;

    public Exercise1(int vertices)
    {
        this.vertices = vertices;
        distance = new int[vertices];
        fixed = new HashMap<>();
        pq = new PriorityQueue<>(vertices, new Node());
    }

    public void dijkstra(String start, String end)
    {
        for (int i = 0; i < vertices; i++)
        {
            distance[i] = Integer.MAX_VALUE;
        }


    }

    public void findShortestCycle(String a, String b)
    {
        dijkstra(a, b);
        dijkstra(b, a);
    }
}
