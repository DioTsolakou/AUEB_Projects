public class ListNode
{
    String data;
    ListNode next;
    ListNode(String word)
    {
        this(word, null);
    }

    ListNode(String word, ListNode node)
    {
        data = word;
        next = node;
    }
}