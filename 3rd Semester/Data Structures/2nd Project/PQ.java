import java.io.*;
import java.util.*;

public class PQ<T>
{
    Song newSong = new Song();

    private T[] heap;
    private int size;
    protected Comparator<T> cmp;

    public PQ(int capacity, Comparator<T> cmp) {
        if (capacity < 1) throw new IllegalArgumentException();

        this.heap = (T []) new Object[capacity+1];
        this.size = 0;
        this.cmp = cmp;
    }

    public void insert(T object) {
        // Ensure object is not null
        if (object == null) throw new IllegalArgumentException();
        // Check available space
        if (size == heap.length - 1) throw new IllegalStateException();
        //Check if the heap has filled up to 75% of its capacity
        if (size == (0.75)*heap.length) resize();
        // Place object at the next available position
        heap[++size] = object;
        // Let the newly added object swim
        swim(size);
    }

    public T getMax() {
        // Ensure not empty
        if (isEmpty()) throw new EmptyStackException();
        // Keep a reference to the root object
        T object = heap[1];
        // Replace root object with the one at rightmost leaf
        if (size > 1) heap[1] = heap[size];
        // Dispose the rightmost leaf
        heap[size--] = null;
        // Sink the new root element
        sink(1);
        // Return the object removed
        return object;
    }

    public T max()
    {
        if (isEmpty()) throw new EmptyStackException();
        T object = heap[1];
        return object;
    }

    //O(n) removal
    public T remove(int id)
    {
        if (isEmpty()) throw new EmptyStackException();
        int parent;
        T object = null;
        for (int i = 0; i < size; i++)
        {
            if (id == newSong.getId())
            {
                object = heap[i];
                heap[i] = heap[size];
                size--;
                parent = i/2;

                if (cmp.compare(heap[i], heap[1]) == 0 || cmp.compare(heap[parent], heap[i]) < 0)
                {
                    sink(i);
                }
                else swim(i);
                break;
            }
        }
        return object;
    }

    public void resize()
    {
        T[] doubleHeap = (T []) new Object[heap.length*2];
        for (int i = 0; i < size; i++)
        {
            doubleHeap[i] = heap[i];
        }
        heap = doubleHeap;
    }

    private void swim(int i) {
        while (i > 1) {  //if i root (i==1) return
            int p = i/2;  //find parent
            int result = cmp.compare(heap[i], heap[p]);  //compare parent with child
            if (result <= 0) return;    //if child <= parent return
            swap(i, p);                 //else swap and i=p
            i = p;
        }
    }

    private void sink(int i){
        int left = 2*i, right = left+1, max = left;
        // If 2*i >= size, node i is a leaf
        while (left <= size) {
            // Determine the largest children of node i
            if (right <= size) {
                max = cmp.compare(heap[left], heap[right]) < 0 ? right : left;
            }
            // If the heap condition holds, stop. Else swap and go on.
            if (cmp.compare(heap[i], heap[max]) >= 0) return;
            swap(i, max);
            i = max; left = 2*i; right = left+1; max = left;
        }
    }

    private void swap(int i, int j) {
        T tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    public void printTopHeapElements(int k) {
        for (int i=1; i <= k; i++){
            System.out.println(heap[i]+ " ");
        }
        System.out.println();
    }
    boolean isEmpty(){
        return size == 0;
    }

    public int size()
    {
        return size;
    }

    public void readTextHeap(String path, int k)
    {
        File txt = null;
        BufferedReader reader01 = null;
        String line01;
        int id;
        String title;
        int likes;
        int i = 0;
        int space =  0;
        int position = 0;

        try
        {
            txt = new File(path); // try to find the file
        }
        catch (NullPointerException e)
        {
            System.err.println("File not found.");
        }

        try {
            reader01 = new BufferedReader(new FileReader(txt));
        }
        catch (FileNotFoundException e)
        {
            System.err.println("Error opening file!");
        }

        try
        {
            T object = null;

            while ((line01 = reader01.readLine()) != null)
            {
                space = line01.indexOf(' ');  //takes the index position of the first space
                id = Integer.parseInt(line01.substring(0, space).trim());     //takes the first element of the line until the first space and trims any spaces

                position = space;   //makes the index of the first space the starting position for the next element
                i = line01.length();

                for (space = position; space < i; space++)
                {
                    if (Character.isDigit(line01.charAt(space))) break; //reads until it finds the next digit to surpass any spaces in the song's title
                }

                title = line01.substring(position, space - 1).trim(); //takes the string between the first space(which is now position) until
                //the space before the first digit after the title

                position = space;
                likes = Integer.parseInt(line01.substring(position).trim()); //takes the element between the space after the title until
                //the end of the line

                Song newSong = new Song(id, title, likes, (SongComparator) cmp);
                object = (T) newSong;
                insert(object);

                System.out.println();
                printTopHeapElements(k);
            }
        }
        catch (IOException e)
        {
            System.err.println("Error reading file!");
        }
        finally {
            System.out.println();
        }
    }

    public void readTextHeap(String path)
    {
        File txt = null;
        BufferedReader reader01 = null;
        String line01;
        int id;
        String title;
        int likes;
        int i = 0;
        int space =  0;
        int position = 0;

        try
        {
            txt = new File(path); // try to find the file
        }
        catch (NullPointerException e)
        {
            System.err.println("File not found.");
        }

        try {
            reader01 = new BufferedReader(new FileReader(txt));
        }
        catch (FileNotFoundException e)
        {
            System.err.println("Error opening file!");
        }

        try
        {
            T object = null;

            while ((line01 = reader01.readLine()) != null)
            {
                space = line01.indexOf(' ');  //takes the index position of the first space
                id = Integer.parseInt(line01.substring(0, space).trim());     //takes the first element of the line until the first space and trims any spaces

                position = space;   //makes the index of the first space the starting position for the next element
                i = line01.length();

                for (space = position; space < i; space++)
                {
                    if (Character.isDigit(line01.charAt(space))) break; //reads until it finds the next digit to surpass any spaces in the song's title
                }

                title = line01.substring(position, space - 1).trim(); //takes the string between the first space(which is now position) until
                //the space before the first digit after the title

                position = space;
                likes = Integer.parseInt(line01.substring(position).trim()); //takes the element between the space after the title until
                //the end of the line

                Song newSong = new Song(id, title, likes, (SongComparator) cmp);
                object = (T) newSong;
                insert(object);

                System.out.println(id + " " +title+ " " +likes+ " ");
            }
        }
        catch (IOException e)
        {
            System.err.println("Error reading file!");
        }
        finally {
            System.out.println();
        }
    }
}