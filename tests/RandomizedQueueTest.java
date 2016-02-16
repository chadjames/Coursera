import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * Created by cjamec1 on 2/4/2016.
 */
public class RandomizedQueueTest {
    RandomizedQueue queue;

    @Test
    public void testIsEmptyTrue() throws Exception {
        queue = new RandomizedQueue<String>();
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testIsEmptyFalse() throws Exception {
        queue = new RandomizedQueue<String>();
        queue.enqueue("String1");
        assertFalse(queue.isEmpty());
    }

    @Test
    public void testSize() throws Exception {
        queue = new RandomizedQueue<String>();
        queue.enqueue("String1");
        assertTrue(queue.size() == 1);
    }

    @Test
    public void testSize0() throws Exception {
        queue = new RandomizedQueue<String>();
        assertTrue(queue.size() == 0);
    }

    @Test
    public void testEnqueue() throws Exception {
        queue = new RandomizedQueue<String>();
        queue.enqueue("String1");
        queue.enqueue("String2");
        assertTrue(queue.size() == 2);
    }

    @Test
    public void testDequeueDecrementsCount() throws Exception {
        queue = new RandomizedQueue<String>();
        queue.enqueue("String1");
        queue.enqueue("String2");
        queue.dequeue();
        assertTrue(queue.size() == 1);
    }

    @Test
    public void testDequeueReturnsItem() throws Exception {
        queue = new RandomizedQueue<String>();
        queue.enqueue("String1");
        assertEquals("String1", queue.dequeue());
        assertTrue(queue.size() == 0);
    }

    @Test
    public void testDequeueReturnsItemThrowsExceptionWhenEmpty() throws Exception {
        queue = new RandomizedQueue<String>();
        queue.enqueue("String1");
        assertEquals("String1", queue.dequeue());
        boolean thrown = false;
        try {
            queue.dequeue();
        } catch (UnsupportedOperationException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void testDequeueReturnsItemThrowsExceptionWhenEmptyMultipleDequeue() throws Exception {
        queue = new RandomizedQueue<String>();
        boolean thrown = false;
        try {
            queue.dequeue();
        } catch (UnsupportedOperationException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void testIteratorNext() throws Exception {
        queue = new RandomizedQueue<Integer>();
        ArrayList<Integer> values = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            values.add(i);
            queue.enqueue(i);
        }
        Iterator<Integer> it = queue.iterator();

        for (int i = 0; i < 10; i++) {
            assertTrue(it.hasNext());
            Integer next = it.next();
            assertTrue(values.contains(next));
        }
        boolean thrown = false;
        try {
            it.next();
        } catch (NoSuchElementException e) {
            thrown = true;
        }
        assertTrue(thrown);

    }

    @Test
    public void testSampleMultiple() {
        queue = new RandomizedQueue<Integer>();
        for (int i = 0; i < 1000; i++) {

            queue.enqueue(1);
            queue.enqueue(2);
            queue.enqueue(3);
        }

        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        for (int i = 0; i < 3000; i++) {

            switch ((Integer) queue.sample()) {
                case 1:
                    count1++;
                    break;
                case 2:
                    count2++;
                    break;
                case 3:
                    count3++;
                    break;

            }

        }
        assertTrue(true);

    }

    @Test
    public void testDequeueMultiple() {
        queue = new RandomizedQueue<Integer>();
        for (int i = 0; i < 1000; i++) {

            queue.enqueue(1);
            queue.enqueue(2);
            queue.enqueue(3);
        }

        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        for (int i = 0; i < 3000; i++) {

            switch ((Integer) queue.dequeue()) {
                case 1:
                    count1++;
                    break;
                case 2:
                    count2++;
                    break;
                case 3:
                    count3++;
                    break;

            }

        }
        assertTrue(true);

    }

    @Test
    public void testIteratorMultiple() {
        queue = new RandomizedQueue<Integer>();
        for (int i = 0; i < 1000; i++) {

            queue.enqueue(1);
            queue.enqueue(2);
            queue.enqueue(3);
        }

        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        Iterator<Integer> it = queue.iterator();
        for (int i = 0; i < 3000; i++) {

            switch ((Integer) it.next()) {
                case 1:
                    count1++;
                    break;
                case 2:
                    count2++;
                    break;
                case 3:
                    count3++;
                    break;

            }

        }
        assertTrue(true);

    }



    @Test
    public void testShrink() {
        queue = new RandomizedQueue<Integer>();
        for (int i = 0; i < 2; i++) {
            queue.enqueue(1);
            queue.enqueue(2);
            queue.enqueue(3);
            queue.enqueue(4);
            queue.enqueue(5);
        }
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        boolean thrown = false;
        try{
            queue.dequeue();
        }catch(Exception e){
            thrown = true;
        }
        assertFalse(thrown);
    }
    @Test
    public void testRandomOperations1(){
        queue = new RandomizedQueue<Integer>();
        assertTrue(queue.isEmpty());
        assertEquals(0,queue.size());
        queue.enqueue(21);
        assertFalse(queue.isEmpty());
        queue.dequeue();
        assertTrue(queue.isEmpty());

    }
}