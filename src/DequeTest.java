

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

import static org.junit.Assert.*;

public class DequeTest {
    Deque<String> deque;

    @Test
    public void testIsEmpty() throws Exception {
        deque = new Deque<>();
        assertTrue(deque.isEmpty());
    }

    @Test
    public void testSize() throws Exception {
        deque = new Deque<>();
        assertTrue(deque.size() == 0);

    }

    @Test
    public void testNullPointerException(){
        deque = new Deque<>();
        try{
            deque.addFirst(null);
            fail();
        }catch (NullPointerException e){

        }

    }

    @Test
    public void testAddFirst() throws Exception {
        deque = new Deque<>();
        deque.addFirst("String");
        assertTrue(deque.size() == 1);
        assertTrue(Objects.equals(deque.removeFirst(), "String"));
    }

    @Test
    public void testAddFirstAddsSecondItem() throws Exception {
        deque = new Deque<>();
        deque.addFirst("String1");
        deque.addFirst("String2");
        assertTrue(deque.size() == 2);
        assertEquals("String2",deque.removeFirst());
    }
    @Test
    public void testAddFirstAddsLastItem() throws Exception {
        deque = new Deque<>();
        deque.addFirst("String1");
        assertTrue(deque.size() == 1);
        assertEquals("String1",deque.removeLast());
    }

    @Test
    public void testAddLast() throws Exception {
        deque = new Deque<>();
        deque.addLast("String2");
        assertEquals(1,deque.size());
        deque.removeLast();
        deque.addLast("String3");
        assertEquals("String3",deque.removeLast());
    }

    @Test
    public void testAddLastAfterFirst() throws Exception {
        deque = new Deque<>();
        deque.addFirst("String1");
        deque.addLast("String2");
        assertTrue(deque.size() == 2);
        assertEquals("String2",deque.removeLast());

    }

    @Test
    public void testRemoveFirstDecrementsSize() throws Exception {
        deque = new Deque<>();
        deque.addFirst("String1");
        deque.addFirst("String2");
        deque.removeFirst();

        assertTrue(deque.size() == 1);

    }
    @Test
    public void testRemoveFirstThrowsExceptionIfEmpty() throws Exception {
        deque = new Deque<>();
        try{
            deque.removeFirst();
            fail();
        }catch (NoSuchElementException e){

        }

    }

    @Test
    public void testRemoveFirstCreatesNewFirst() throws Exception {
        deque = new Deque<>();
        deque.addFirst("String1");
        deque.addFirst("String2");
        assertEquals(deque.removeFirst(), "String2");
        assertEquals(deque.removeFirst(), "String1");

    }
    @Test
    public void testRemoveLastDecrementsSize() throws Exception {
        deque = new Deque<>();
        deque.addFirst("String1");
        deque.addFirst("String2");
        deque.removeLast();

        assertTrue(deque.size() == 1);

    }
    @Test
    public void testRemoveLastThrowsExceptionIfEmpty() throws Exception {
        deque = new Deque<>();
        try{
            deque.removeLast();
            fail();
        }catch (NoSuchElementException e){

        }

    }

    @Test
    public void testRemoveLast() throws Exception {
        deque = new Deque<>();
        deque.addFirst("String1");
        deque.addFirst("String2");
        assertEquals(deque.removeLast(), "String1");
        assertTrue(deque.size() == 1);


    }

    @Test
    public void testIterator() throws Exception {
        deque = new Deque<>();
        deque.addFirst("String1");
        deque.addFirst("String2");
        deque.addFirst("String3");
        Iterator<String> it = deque.iterator();
        assertTrue(it.hasNext());
        assertEquals(it.next(), "String3");
        assertEquals(it.next(), "String2");
        assertEquals(it.next(), "String1");
    }

    @Test
    public void testAddLastFailResult() throws Exception {
        deque = new Deque<>();
        deque.isEmpty();
        deque.addLast("String2");
        deque.isEmpty();
        deque.removeLast();
        deque.addLast("String2");

        assertEquals(1,deque.size());
        assertEquals("String2",deque.removeLast());
    }

    @Test
    public void testAddFirstFailResult() throws Exception {
        deque = new Deque<>();
        deque.isEmpty();
        deque.addLast("1");
        deque.removeFirst();
        deque.addLast("3");
        deque.isEmpty();
        deque.addLast("5");
        deque.isEmpty();
        deque.removeFirst();
        deque.removeFirst();

        assertEquals(0,deque.size());
    }

    @Test
    public void testMultipleAddFirst(){
        Deque<Integer> deque = new Deque<Integer>();
        for(int i = 1; i <= 1000; i++){
            deque.addFirst(i);

        }
        int count = 0;
        Iterator<Integer> it = deque.iterator();
        while(it.hasNext()){
            it.next();
            count++;
        }
        assertTrue(count + " does not = 1000",  count == 1000);
    }

    @Test
    public void testRandomCallsToAdd(){
        Deque<Integer> deque = new Deque<Integer>();

        deque.addLast(0);
        deque.removeFirst();
        deque.addLast(2);
        assertTrue(2 == deque.removeFirst());

    }
    @Test
    public void testRandomCallsToAdd1() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addLast(0);
        deque.removeFirst();
        deque.addLast(2);
        deque.addLast(3);
        int result = deque.removeFirst();
        assertEquals( 2,result );
    }

    @Test
    public void testRandomCallsToAdd2() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addLast(0);
        deque.removeFirst();
        assertTrue(deque.isEmpty());
        assertTrue(deque.isEmpty());
        assertTrue(deque.isEmpty());
        deque.addLast(5);
        int result = deque.removeFirst();
        assertEquals( 5,result );

    }

    @Test
    public void testRandomCallsToAdd3() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addLast(0);
        deque.addFirst(1);
        deque.removeFirst();
        assertFalse(deque.isEmpty());
        assertTrue(deque.size() == 1);
        assertEquals(0,(int)deque.removeFirst());
        assertTrue(deque.isEmpty());
        assertTrue(deque.isEmpty());
        deque.addLast(5);
        int result = deque.removeFirst();
        assertEquals( 5,result );

    }

}