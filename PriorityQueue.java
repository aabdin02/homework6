package homework6;


import java.util.LinkedList;
import java.util.List;
/**
 * Priority Queue class 
 * @author Aeone and Aisha
 *
 * @param <E>
 */
public class PriorityQueue<E extends Comparable<E>> {
	// The Data is stored in Linked List.

	private class Node {

		public E elements;
		public Node next;
		public Node prev;

		public Node(E elements) {
			if (elements == null) {
				throw new IllegalArgumentException("elements = " + elements);
			}
			this.elements = elements;
		}
	}

	// beginning and end of the doubly linked list.
	private Node head, tail;
	// number of nodes in the Linked List.
	private int size;

	public PriorityQueue() {

	}

	/**
	 * insert the object in the queue. Use the Comparable method to compareTo()
	 * to implement the ordering.
	 *
	 * @param o
	 */

	public void insert(E o) {

		Node node = new Node(o);

		Node p = head;
		while (p != null && o.compareTo(p.elements) > 0) {
			p = p.next;
		}
		// p is the first node that is >= o
		// insert the node just before p
		// node before p
		Node prev;
		if (p == null) {
			prev = tail;
		} else {
			prev = p.prev;
		}
		// attach prev to the new node
		if (prev != null) {
			prev.next = node;
			node.prev = prev;
		} else {
			head = node;
		}
		// attach the new node to p
		node.next = p;
		if (p != null) {
			p.prev = node;
		} else {
			tail = node;
		}

		size++;
	}

	/**
	 * swaps two nodes
	 */

	/**
	 * removes and returns the object from the front. Throw an appropriate
	 * exception if the PriorityQueue is empty.
	 *
	 * @return
	 */
	public E remove() {
		Node toBeRemoved = head;
		if (head != null) {
			head = head.next;
		}
		if (head == null) {
			tail = null;
			// throw new NullPointerException();
		}
		size--;
		return toBeRemoved.elements;
	}

	/**
	 * returns the object at the front without changing the PriorityQueue. Throw
	 * an appropriate exception if the PriorityQueue is empty.
	 */
	public E front() {
		return head.elements;
	}

	/**
	 * To check whether the queue is empty.
	 */
	public boolean isEmpty() {
		return head == null;
	}

	/**
	 * Returns size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Test methods to be used by the unit tests; used to make sure all of the
	 * links are correct(going forward and backward) by traversing the queue and
	 * constructing a list containing its contents.
	 *
	 * @return
	 */
	public List<E> testForwardTraversal(PriorityQueue<E> list) {
		System.out.println("Forward Traversal");
		List<E> forward = new LinkedList<E>();
		if (head == null) { // if empty list
			return null;
		} else {
			for (Node n = head; n != null; n = n.next) {
				forward.add(n.elements);
			}
			System.out.println(forward);
			return forward;
		}
	}

	public List<E> testReverseTraversal(PriorityQueue<E> list) {
		System.out.println("Reverse Traversal");
		List<E> reverse = new LinkedList<E>();
		if (head == null) { // if empty list
			return null;
		} else {
			for (Node n = tail; n != null; n = n.prev) {
				reverse.add(n.elements);
			}
			System.out.println(reverse);
			return reverse;
		}
	}
}