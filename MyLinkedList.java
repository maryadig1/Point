package write;



//MyLinkedList.java
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
* Custom LinkedList implementation for storing double values.
* Supports adding elements, getting size, iterating, and calculating sum and sum of squares for efficiency.
*/
public class MyLinkedList implements Iterable<Double> {
 private Node head;
 private Node tail;
 private int size;

 private static class Node {
     double value;
     Node next;

     Node(double value) {
         this.value = value;
     }
 }

 public MyLinkedList() {
     head = null;
     tail = null;
     size = 0;
 }

 /**
  * Adds a double value to the end of the list.
  * @param value the double to add
  */
 public void add(double value) {
     Node newNode = new Node(value);
     if (tail == null) {
         head = newNode;
         tail = newNode;
     } else {
         tail.next = newNode;
         tail = newNode;
     }
     size++;
 }

 /**
  * Returns the number of elements in the list.
  * @return the size
  */
 public int size() {
     return size;
 }

 /**
  * Calculates the sum of all elements in the list.
  * @return the sum
  */
 public double sum() {
     double sum = 0.0;
     for (Node current = head; current != null; current = current.next) {
         sum += current.value;
     }
     return sum;
 }

 /**
  * Calculates the sum of squares of (value - mean) for standard deviation.
  * @param mean the mean value
  * @return the sum of squares
  */
 public double sumOfSquares(double mean) {
     double sumSq = 0.0;
     for (Node current = head; current != null; current = current.next) {
         double diff = current.value - mean;
         sumSq += diff * diff;
     }
     return sumSq;
 }

 @Override
 public Iterator<Double> iterator() {
     return new Iterator<Double>() {
         private Node current = head;

         @Override
         public boolean hasNext() {
             return current != null;
         }

         @Override
         public Double next() {
             if (!hasNext()) {
                 throw new NoSuchElementException();
             }
             double value = current.value;
             current = current.next;
             return value;
         }
     };
 }
}