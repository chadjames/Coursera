import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by chad on 2/1/2016.
 */

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int count;

    public Deque() {
        count = 0;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

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
            Node<Item> oldFirst = first;
            first = new Node<>();
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
            Node<Item> oldLast = last;
            last = new Node<>();
            last.data = item;
            last.previous = oldLast;
            oldLast.next = last;
            count++;
        }
    }

    public Item removeFirst() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        Node<Item> oldFirst = first;
        if (count == 1) {
            first = last;
        } else {
            first = oldFirst.next;
            oldFirst.previous = null;
        }
        count--;

        return oldFirst.data;
    }

    public Item removeLast() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        Node<Item> oldLast = last;
        last = oldLast.previous;
        if (size() > 1) {
            last.next = null;
        }
        if (size() == 1) {
            first = null;
        }
        count--;

        return oldLast.data;
    }

    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            Node<Item> current = first;
            @Override
            public boolean hasNext() {
                return current != null && (current == last || current.next != null);
            }

            @Override
            public Item next() {
                if (current == null) {
                    throw new NoSuchElementException();
                }
                Item result = current.data;
                current = current.next;
                return result;
            }
        };
    }

    private class Node<Item> {
        protected Item data;
        protected Node<Item> next;
        protected Node<Item> previous;
    }
}
