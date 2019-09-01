import java.io.PrintStream;
import java.util.NoSuchElementException;

public class IntQueueImpl implements IntQueue
{
    public int size = 0;
    private Node head;
    private Node tail;


    public boolean isEmpty()
    {
        return head == null;
    }

    public void put(int item)
    {
        Node newNode = new Node(item);
        if (isEmpty())
        {
            head = tail = newNode;
            size++;
        }
        else
        {
            tail.next = newNode;
            tail = newNode;
            size++;
        }
    }

    public int get() throws NoSuchElementException
    {
        Object removedNode = head.element;
        if (isEmpty()) throw new NoSuchElementException();
        else if (head == tail) {
            head = tail = null;
            size--;
        }
        else {
            head.next = head;
            size--;
        }
        return (int) removedNode;
    }

    public int peek() throws NoSuchElementException
    {
        if (isEmpty()) throw new NoSuchElementException();
        else
            return Integer.parseInt(String.valueOf(head));
    }

    public void printQueue(PrintStream stream)
    {
        if (isEmpty()) System.out.println("Queue is empty");
        else
        {
            Node current = head;
            while (current != null)
            {
                System.out.printf("%s ",current.element);
                current = current.next;
            }
        }
    }

    public int size()
    {
        if (isEmpty()) return 0;
        else
        {
            return size;
        }
    }
}
