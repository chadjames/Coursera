import java.util.Iterator;

/**
 * Created by chad on 2/1/2016.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    public RandomizedQueue() {}                // construct an empty randomized queue
    public boolean isEmpty() {return false;}                // is the queue empty?
    public int size()  {return 0;}                      // return the number of items on the queue
    public void enqueue(Item item) {}          // add the item
    public Item dequeue(){return (Item) new Object();}                    // remove and return a random item
    public Item sample() {return (Item) new Object();}                    // return (but do not remove) a random item
    public Iterator<Item> iterator() {return new Iterator<Item>() {
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Item next() {
            return null;
        }
    };
}             // return an independent iterator over items in random order
    public static void main(String[] args) {}  // unit testing
}
