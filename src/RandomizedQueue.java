import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by cjamec1 on 2/4/2016.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] data;
    private int count;
    private static int MIN = 5;

    public RandomizedQueue() {
        data = (Item[]) new Object[0];
        count = 0;
    }

    public boolean isEmpty() {
        return data.length == 0;
    }

    public int size() {
        return count;
    }

    public void enqueue(Item item) {
        if (isEmpty()) {
            initialize();
        }
        data[count] = item;
        count++;
        if (count > (data.length / 2)) {
            growArray();
        }

    }

    public Item dequeue() {
        if (count == 0) {
            throw new UnsupportedOperationException();
        }
        int randomIndex = randomIndex();
        Item item = data[randomIndex];
        if (count > 1 && randomIndex != count - 1) {
            data[randomIndex] = data[count - 1];
            data[count - 1] = null;
        } else {
            data[randomIndex] = null;
        }
        count--;
        if (data.length > MIN && count < (data.length / 4)) {
            shrinkArray();
        }
        return item;
    }

    public Item sample() {
        return data[StdRandom.uniform(0, count)];
    }

    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            Item[] shuffled = (Item[]) shuffle(copyArray(data));
            int returned = 0;

            @Override
            public boolean hasNext() {
                return returned < count;
            }

            @Override
            public Item next() {
                if (returned == count) {
                    throw new NoSuchElementException();
                }
                Item result = shuffled[returned];
                returned++;
                return result;

            }
        };
    }

    private void initialize() {
        data = (Item[]) new Object[MIN];
    }

    private int randomIndex() {
        return StdRandom.uniform(0, (count <= 1) ? 1 : count - 1);
    }

    private void growArray() {
        Item[] resized = (Item[]) new Object[data.length * 2];
        int j = 0;
        while (j < data.length) {
            resized[j] = data[j];
            j++;
        }
        data = resized;
    }
    private void shrinkArray() {
        Item[] resized = (Item[]) new Object[data.length/4];
        int j = 0;
        while (j < count) {
            resized[j] = data[j];
            j++;
        }
        data = resized;
    }


    private Object[] shuffle(Object[] source) {
        for (int i = 1; i < count; i++) {
            int random = StdRandom.uniform(0, i);
            swap(source, i, random);
        }
        return source;
    }

    private void swap(Object[] source, int i, int r) {
        Object first = source[i];
        Object last = source[r];
        source[r] = first;
        source[i] = last;
    }

    private Object[] copyArray(Object[] source) {
        Object[] clone = new Object[source.length];
        for (int i = 0; i < source.length; i++) {
            clone[i] = source[i];
        }
        return clone;
    }
}

