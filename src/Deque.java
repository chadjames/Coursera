import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by chad on 2/1/2016.
 */

public class Deque<Item> implements Iterable<Item> {
    Node first;
    Node last;
    int count;

    public Deque() {
        count = 0;
    }                         // construct an empty deque

    public boolean isEmpty() {
        return true;
    }               // is the deque empty?

    public int size() {
        return count;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }

        if (first == null) {
            first = new Node<Item>();
            first.data = item;
        } else {
            Node oldFirst = first;
            first = new Node<Item>();
            first.data = item;
            first.next = oldFirst;
            oldFirst.previous = first;
        }

        if (last == null) {
            last = first;
        }

        count++;
    }


    public void addLast(Item item) {
        if (first == null) {
            addFirst(item);
        } else {
            Node oldLast = last;
            last = new Node();
            last.data = item;
            last.previous = oldLast;
            oldLast.next = last;
            count++;

        }

    }

    public Item removeFirst() {
        if(size() == 0){
            throw new NoSuchElementException();
        }
        Node oldFirst = first;
        first = oldFirst.next;
        oldFirst.previous = null;
        count--;

        return (Item) oldFirst.data;

    }

    public Item removeLast() {
        if(size() == 0){
            throw new NoSuchElementException();
        }
        Node oldLast = last;
        last = oldLast.previous;
        if(size() > 1){last.next = null;}
        count--;


        return (Item) oldLast.data;
    }

    public Iterator<Item> iterator() {
        Node current = first;
        
        
        return new Iterator<Item>() {
            Node<Item> current = first;
            @Override
            public boolean hasNext() {
                return current.next != null;
            }

            @Override
            public Item next() {
                Item result = current.data;
                current = current.next;
                return result;
            }
        };
    }       // return an iterator over items in order from front to end

    public static void main(String[] args) {
    }   // unit testing

    class Node<Item> {
        protected Item data;
        protected Node next;
        protected Node previous;


    }
}
