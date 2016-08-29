package adt.bst;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import adt.bt.BTNode;

public class StudentBSTTest {

	private BSTImpl<Integer> tree;
	private BSTImpl<Integer> tree2;
	private BTNode<Integer> NIL = new BTNode<Integer>();

	private void fillTree() {
		
		Integer[] array = { 6, 23, -34, 5, 9, 2, 0, 76, 12, 67, 232, -40 };
		for (int i : array) {
			tree.insert(i);
		}
		
		Integer[] array2 = { 17, 13, 35, 7, 15, 20, 40, 5, 8, 14, 16, 18, 25, 37, 45 };
		for (int i : array2) {
			tree2.insert(i);
		}
	}

	@Before
	public void setUp() {
		tree = new BSTImpl<>();
		tree2 = new BSTImpl<>();
	}

	@Test
	public void testInit() {
		assertTrue(tree.isEmpty());
		assertEquals(0, tree.size());
		assertEquals(-1, tree.height());

		assertEquals(NIL, tree.getRoot());

		assertArrayEquals(new Integer[] {}, tree.order());
		assertArrayEquals(new Integer[] {}, tree.preOrder());
		assertArrayEquals(new Integer[] {}, tree.postOrder());

		assertEquals(NIL, tree.search(12));
		assertEquals(NIL, tree.search(-23));
		assertEquals(NIL, tree.search(0));

		assertEquals(null, tree.minimum());
		assertEquals(null, tree.maximum());

		assertEquals(null, tree.sucessor(12));
		assertEquals(null, tree.sucessor(-23));
		assertEquals(null, tree.sucessor(0));

		assertEquals(null, tree.predecessor(12));
		assertEquals(null, tree.predecessor(-23));
		assertEquals(null, tree.predecessor(0));
		
		tree2.insert(17);
		
		assertNull(tree2.sucessor(17));
		assertNull(tree2.predecessor(17));
		
		assertEquals(new Integer(17), tree2.maximum().getData());
		assertEquals(new Integer(17), tree2.minimum().getData());
		
		assertEquals(new Integer(17), tree2.search(17).getData());
		assertEquals(NIL, tree2.search(100));
		
		assertEquals(0, tree2.height());
		
		Integer[] array = {17};
		
		assertArrayEquals(array, tree2.preOrder());
		assertArrayEquals(array, tree2.order());
		assertArrayEquals(array, tree2.postOrder());
		
		assertEquals(1, tree2.size());
		
		tree2.remove(5);
		assertEquals(1, tree2.size());
		
		tree2.remove(17);
		assertEquals(0, tree2.size());
	}

	@Test
	public void testMinMax() {
		tree.insert(6);
		assertEquals(new Integer(6), tree.minimum().getData());
		assertEquals(new Integer(6), tree.maximum().getData());

		tree.insert(23);
		assertEquals(new Integer(6), tree.minimum().getData());
		assertEquals(new Integer(23), tree.maximum().getData());

		tree.insert(-34);
		assertEquals(new Integer(-34), tree.minimum().getData());
		assertEquals(new Integer(23), tree.maximum().getData());

		tree.insert(5);
		assertEquals(new Integer(-34), tree.minimum().getData());
		assertEquals(new Integer(23), tree.maximum().getData());

		tree.insert(9);
		assertEquals(new Integer(-34), tree.minimum().getData());
		assertEquals(new Integer(23), tree.maximum().getData());
		
		fillTree();
		
		assertEquals(new Integer(5), tree2.minimum().getData());
		assertEquals(new Integer(45), tree2.maximum().getData());
	}

	@Test
	public void testSucessorPredecessor() {

		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

		assertEquals(null, tree.predecessor(-40));
		assertEquals(new Integer(-34), tree.sucessor(-40).getData());

		assertEquals(new Integer(-40), tree.predecessor(-34).getData());
		assertEquals(new Integer(0), tree.sucessor(-34).getData());

		assertEquals(new Integer(-34), tree.predecessor(0).getData());
		assertEquals(new Integer(2), tree.sucessor(0).getData());

		assertEquals(new Integer(0), tree.predecessor(2).getData());
		assertEquals(new Integer(5), tree.sucessor(2).getData());
		
		tree.remove(5);
		assertEquals(11, tree.size());
		assertEquals(new Integer(-34), tree.predecessor(0).getData());
		
		tree.remove(9);
		assertEquals(10, tree.size());
		assertEquals(new Integer(23), tree.sucessor(12).getData());
		
		tree.remove(76);
		assertEquals(9, tree.size());
		assertEquals(new Integer(232), tree.sucessor(67).getData());
		assertEquals(new Integer(23), tree.predecessor(67).getData());
		
		assertNull(tree2.predecessor(5));
		assertNull(tree2.sucessor(45));
		
		assertEquals(new Integer(17), tree2.sucessor(16).getData());
		assertEquals(new Integer(17), tree2.predecessor(18).getData());
		assertEquals(new Integer(15), tree2.sucessor(14).getData());
	}

	@Test
	public void testSize() {
		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

		int size = 12;
		assertEquals(size, tree.size());

		while (!tree.isEmpty()) {
			tree.remove(tree.getRoot().getData());
			assertEquals(--size, tree.size());
		}
	}

	@Test
	public void testHeight() {
		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

		Integer[] preOrder = new Integer[] { 6, -34, -40, 5, 2, 0, 23, 9, 12,
				76, 67, 232 };
		assertArrayEquals(preOrder, tree.preOrder());
		assertEquals(4, tree.height());

		tree.remove(0);
		assertEquals(3, tree.height());

		tree.remove(2);
		assertEquals(3, tree.height());
		
		assertEquals(3, tree2.height());
		assertEquals(15, tree2.size());
		
		tree2.remove(17);
		assertEquals(14, tree2.size());
		assertEquals(3, tree2.height());
		
		tree2.remove(15);
		assertEquals(13, tree2.size());
		assertEquals(3, tree2.height());
	}

	@Test
	public void testRemove() {
		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

		Integer[] order = { -40, -34, 0, 2, 5, 6, 9, 12, 23, 67, 76, 232 };
		assertArrayEquals(order, tree.order());

		tree.remove(6);
		order = new Integer[] { -40, -34, 0, 2, 5, 9, 12, 23, 67, 76, 232 };
		assertArrayEquals(order, tree.order());

		tree.remove(9);
		order = new Integer[] { -40, -34, 0, 2, 5, 12, 23, 67, 76, 232 };
		assertArrayEquals(order, tree.order());

		assertEquals(NIL, tree.search(6));
		assertEquals(NIL, tree.search(9));
		
		tree.remove(100);
		assertEquals(10, tree.size());
		
		assertEquals(15, tree2.size());
		tree2.remove(13);
		assertEquals(14, tree2.size());
		tree2.remove(35);
		assertEquals(13, tree2.size());
		
		assertEquals(NIL, tree.search(13));
		assertEquals(NIL, tree.search(35));
		
		Integer[] order2 = { 5, 7, 8, 14, 15, 16, 17, 18, 20, 25, 37, 40, 45 };
		assertArrayEquals(order2, tree2.order());
	}

	@Test
	public void testSearch() {

		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

		assertEquals(new Integer(-40), tree.search(-40).getData());
		assertEquals(new Integer(-34), tree.search(-34).getData());
		assertEquals(NIL, tree.search(2534));
		
		
		assertEquals(new Integer(15), tree2.search(15).getData());
		assertEquals(new Integer(20), tree2.search(20).getData());
		assertEquals(NIL, tree2.search(2534));
	}
	
	@Test
	public void testPercorreBST() {
		
		fillTree();
		
		Integer[] preOrdem = { 17, 13, 7, 5, 8, 15, 14, 16, 35, 20, 18, 25, 40, 37, 45 };
		assertArrayEquals(preOrdem, tree2.preOrder());
		
		Integer[] ordem = { 5, 7, 8, 13, 14, 15, 16, 17, 18, 20, 25, 35, 37, 40, 45 };
		assertArrayEquals(ordem, tree2.order());
		
		Integer[] postOrdem = { 5, 8, 7, 14, 16, 15, 13, 18, 25, 20, 37, 45, 40, 35, 17 };
		assertArrayEquals(postOrdem, tree2.postOrder());
	}
}
