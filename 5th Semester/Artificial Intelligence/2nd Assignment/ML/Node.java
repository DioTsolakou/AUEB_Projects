package ML;

public class Node
{
    Object data;
    Node nextNode;

    Node(Object object)
    {
        this(object, null);
    }

    Node(Object object, Node node)
    {
        data = object;
        nextNode = node;
    }

    Object getObject()
    {
        return data;
    }

    Node getNext()
    {
        return nextNode;
    }
}