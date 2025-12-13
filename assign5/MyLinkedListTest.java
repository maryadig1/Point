package write;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MyLinkedListTest {

 @Test
 public void testAddAndSize() {
     MyLinkedList list = new MyLinkedList();
     list.add(1.0);
     list.add(2.0);
     assertEquals(2, list.size());
 }

 @Test
 public void testSum() {
     MyLinkedList list = new MyLinkedList();
     list.add(1.0);
     list.add(2.0);
     list.add(3.0);
     assertEquals(6.0, list.sum(), 0.001);
 }

 @Test
 public void testSumOfSquares() {
     MyLinkedList list = new MyLinkedList();
     list.add(1.0);
     list.add(2.0);
     list.add(3.0);
     double mean = 2.0;
     assertEquals(2.0, list.sumOfSquares(mean), 0.001); // (1-2)^2 + (2-2)^2 + (3-2)^2 = 1 + 0 + 1 = 2
 }

 @Test
 public void testIterator() {
     MyLinkedList list = new MyLinkedList();
     list.add(1.0);
     list.add(2.0);
     int count = 0;
     for (double d : list) {
         count++;
     }
     assertEquals(2, count);
 }

 @Test
 public void testEmptyList() {
     MyLinkedList list = new MyLinkedList();
     assertEquals(0, list.size());
     assertEquals(0.0, list.sum(), 0.001);
 }
}
