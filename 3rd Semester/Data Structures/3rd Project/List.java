public class List
{
    private ListNode firstNode;
    private ListNode lastNode;
    private String name;

    public List()
    {
        this("list");
    }


    public List(String listName)
    {
        name = listName;
        firstNode = lastNode = null;
    }

    public void insert(String insertItem)
    {
        ListNode node = new ListNode(insertItem);
        if (isEmpty())
            firstNode = lastNode = node;
        else {
            node.next = firstNode;
            firstNode = node;
        }
    }

    public String remove(String name)
    {
        if (isEmpty()) throw new EmptyListException();

        ListNode current = firstNode;

        while (current.next != lastNode)
        {
            if (name.equalsIgnoreCase(current.data))
            {
                current = current.next;
                return current.data;
            }
        }
        return null;
    }

    public boolean isStopWord(String word)
    {
        ListNode current = firstNode;
        while (current.next != lastNode)
        {
            if (current.data.equalsIgnoreCase(word))
            {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean isEmpty()
    {
        return firstNode == null;
    }

    public void print()
    {
        if (isEmpty())
        {
            System.out.printf("Empty %s\n", name);
            return;
        }

        System.out.printf("The %s is: ", name);
        ListNode current = firstNode;

        while (current != null)
        {
            System.out.printf("%s ", current.data);
            current = current.next;
        }

        System.out.println("\n");
    }
}