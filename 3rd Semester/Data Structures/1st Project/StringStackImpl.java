import java.io.PrintStream;
import java.util.NoSuchElementException;

public class StringStackImpl
{
    public int size = 0;
    private Node bottom;
    private Node top;

    public boolean isEmpty()
    {
        return bottom == null;
    }

    public void push(String item)
    {
        if (isEmpty())
        {
            Node newNode = new Node(item);
            top = bottom = newNode;
            size++;
        }
        else
        {
            top = new Node(item, top);
        }

    }

    public String pop() throws NoSuchElementException
    {
        String removedItem = (String) top.element;
        if (isEmpty()) throw new NoSuchElementException();
        else if (top == bottom)
        {
            top = bottom = null;
            size--;
        }
        else
        {
            Node t = top.next;
            top = t;
            size--;
        }
        return removedItem;
    }

    public String peek() throws NoSuchElementException
    {
        if (isEmpty()) throw new NoSuchElementException();
        else
            return String.valueOf(top);
    }

    public void printStack(PrintStream stream)
    {
        if (isEmpty()) throw new NoSuchElementException();
        else
        {
            Node current = top;
            while (current != null)
            {
                System.out.printf("%s ",current.element);
                current = current.next;
            }
        }
    }

    public int size()
    {
        if (isEmpty())
        {
            return 0;
        }
        else {
            return size;
        }
    }
}
