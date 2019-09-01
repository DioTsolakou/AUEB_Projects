class Node
{
    Object element;
    Node next;

    Node(Object element)
    {
        this(element, null);
    }

    Node(Object element, Node node)
    {
        this.element = element;
        next = node;
    }

    Object getElement()
    {
        return element;
    }

    Node getNext()
    {
        return next;
    }
}
