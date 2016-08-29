package adt.bst;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		
		if (! isEmpty()) {
			
			BSTNode<T> esq = (BSTNode<T>) this.root.getLeft();
			BSTNode<T> dir = (BSTNode<T>) this.root.getRight();
			
			return Math.max(maiorCaminho(esq), maiorCaminho(dir));
			
		} else {
			
			return -1;
		}
	}
	
	private int maiorCaminho(BSTNode<T> node) {
		
		if (! node.isEmpty()) {
			
			BSTNode<T> esq = (BSTNode<T>) node.getLeft();
			BSTNode<T> dir = (BSTNode<T>) node.getRight();
			
			return 1 + Math.max(maiorCaminho(esq), maiorCaminho(dir));
			
		} else {
			
			return 0;
		}
	}

	@Override
	public BSTNode<T> search(T element) {
		
		if (element != null) {
			
			if (isEmpty()) {
				
				return new BSTNode<>();
			
			} else {
				
				return search(this.root, element);
			}
			
		} else {
			
			return new BSTNode<>();
		}
	}
	
	private BSTNode<T> search(BSTNode<T> node, T element) {
		
		BSTNode<T> prox = new BSTNode<>();
		
		if (! node.isEmpty()) {
			
			if (node.getData().equals(element)) {
				
				return node;
				
			} else if (element.compareTo(node.getData()) > 0) {
				
				prox = (BSTNode<T>) node.getRight();
				return search(prox, element);
				
			} else {
				
				prox = (BSTNode<T>) node.getLeft();
				return search(prox, element);
			}
			
		} else {
			
			return prox;
		}
	}

	@Override
	public void insert(T element) {

		if (element != null) {
			
			BSTNode<T> newNode = new BSTNode<>();
			newNode.setData(element);
			newNode.setLeft(new BSTNode<T>());
			newNode.setRight(new BSTNode<T>());

			if (isEmpty()) {

				this.root = newNode;

			} else {

				insert(element, this.root, newNode);
			}
		}
	}

	private void insert(T element, BSTNode<T> node, BSTNode<T> newNode) {

		if (element.compareTo(node.getData()) > 0) {

			if (node.getRight().isEmpty()) {

				node.setRight(newNode);
				newNode.setParent(node);

			} else {

				BSTNode<T> right = (BSTNode<T>) node.getRight();

				insert(element, right, newNode);
			}

		} else if (element.compareTo(node.getData()) < 0) {

			if (node.getLeft().isEmpty()) {

				node.setLeft(newNode);
				newNode.setParent(node);
				
			} else {
				
				BSTNode<T> left = (BSTNode<T>) node.getLeft();
				
				insert(element, left, newNode);
			}
		}
	}

	@Override
	public BSTNode<T> maximum() {
		
		if (isEmpty())
			return null;
		else 
			return maximum(this.root);
	}
	
	private BSTNode<T> maximum(BSTNode<T> node) {
		
		if (node.getRight().isEmpty()) {
			
			return node;
			
		} else {
			
			BSTNode<T> right = (BSTNode<T>) node.getRight();
			
			return maximum(right);
		}
	}

	@Override
	public BSTNode<T> minimum() {
		
		if (isEmpty())
			return null;
		else
			return minimum(this.root);
	}
	
	private BSTNode<T> minimum(BSTNode<T> node) {
		
		if (node.getLeft().isEmpty()) {
			
			return node;
			
		} else {
			
			BSTNode<T> left = (BSTNode<T>) node.getLeft();
			
			return minimum(left);
		}
			
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		
		BSTNode<T> node = search(element);
		
		if (! node.isEmpty()) {
			
			if (! node.getRight().isEmpty()) {
				
				BSTNode<T> right = (BSTNode<T>) node.getRight();
				
				return minimum(right);
				
			} else {
				
				return percorreInversoSucessor(node, element);
			}
			
		} else {
			
			return null;
		}
		
	}
	
	private BSTNode<T> percorreInversoSucessor (BSTNode<T> node, T element) {
		
		if (node.getParent() != null) {
			
			if (element.compareTo(node.getParent().getData()) < 0) {
				
				BSTNode<T> parent = (BSTNode<T>) node.getParent();
				
				return parent;
				
			} else {
				
				BSTNode<T> parent = (BSTNode<T>) node.getParent();
				
				return percorreInversoSucessor(parent, element);
			}
			
		} else {
			
			return null;
		}
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		
		BSTNode<T> node = search(element);
		
		if (! node.isEmpty()) {
			
			if (! node.getLeft().isEmpty()) {
				
				BSTNode<T> left = (BSTNode<T>) node.getLeft();
				
				return maximum(left);
				
			} else {
				
				return percorreInversoPredecessor(node, element);
			}
			
		} else {
			
			return null;
		}
		
	}
	
	private BSTNode<T> percorreInversoPredecessor(BSTNode<T> node, T element) {
		
		if (node.getParent() != null) {
			
			if (element.compareTo(node.getParent().getData()) > 0) {
				
				BSTNode<T> parent = (BSTNode<T>) node.getParent();
				
				return parent;
				
			} else {
				
				BSTNode<T> parent = (BSTNode<T>) node.getParent();
				
				return percorreInversoPredecessor(parent, element);
			}
			
		} else {
			
			return null;
		}
	}

	@Override
	public void remove(T element) {
		
		BSTNode<T> node = search(element);
		
		if (! node.isEmpty()) {
			
			if (node.getParent() == null) {
				removeRaiz();
				
			} else {
				
				removeNode(node);
			}
		}
	}
	
	private void removeRaiz() {
		
		if (isLeaf(this.root)) {
			
			this.root = new BSTNode<>();
			
		} else if (! this.root.getLeft().isEmpty() && this.root.getRight().isEmpty()) {
			
			BSTNode<T> left = (BSTNode<T>) this.root.getLeft();
			
			this.root = left;
			left.setParent(null);
			
		} else if (this.root.getLeft().isEmpty() && ! this.root.getRight().isEmpty()) {
			
			BSTNode<T> right = (BSTNode<T>) this.root.getRight();
			
			this.root = right;
			right.setParent(null);
			
		} else if (! this.root.getLeft().isEmpty() && ! this.root.getRight().isEmpty()) {
			
			BSTNode<T> nodeSubtituto = sucessor(this.root.getData());
			
			this.root.setData(nodeSubtituto.getData());
			
			removeNode(nodeSubtituto);
		}
	}
	
	private void removeNode(BSTNode<T> node) {
		
		if (isLeaf(node)) {
			
			realocaNode(node, new BSTNode<T>());
			
		} else if (! node.getLeft().isEmpty() && node.getRight().isEmpty()) {
			
			BSTNode<T> left = (BSTNode<T>) node.getLeft();
			
			realocaNode(node, left);
			
			left.setParent(node.getParent());
			
		} else if (node.getLeft().isEmpty() && ! node.getRight().isEmpty()) {
			
			BSTNode<T> right = (BSTNode<T>) node.getRight();
			
			realocaNode(node, right);
			
			right.setParent(node.getParent());
			
		} else if (! node.getLeft().isEmpty() && ! node.getRight().isEmpty()) {
			
			BSTNode<T> nodeSubstituto = sucessor(node.getData());
			
			node.setData(nodeSubstituto.getData());
			
			removeNode(nodeSubstituto);
		}
	}
	
	private void realocaNode(BSTNode<T> node, BSTNode<T> transition) {
		
		if (node.getData().equals(node.getParent().getRight().getData())) {
			
			node.getParent().setRight(transition);
			
		} else {
			
			node.getParent().setLeft(transition);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] preOrder() {
		
		T[] array;
		
		if (isEmpty()) {
			
			array = (T[]) new Comparable[] {};
			
		} else {
			
			array = (T[]) new Comparable[size()];
			
			preOrder(array, this.root, 0);
			
		}
		
		return array;
	}
	
	private int preOrder(T[] array ,BSTNode<T> node, int index) {
		
		if (! node.isEmpty()) {
			
			array[index] = node.getData();
			index++;
			
			BSTNode<T> prox = (BSTNode<T>) node.getLeft();
			
			index = preOrder(array, prox, index);
			
			prox = (BSTNode<T>) node.getRight();
			
			index = preOrder(array, prox, index);
			
		}
		
		return index;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] order() {

		T[] array;
		
		if (isEmpty()) {
			
			array = (T[]) new Comparable[] {};
			
		} else {
			
			array = (T[]) new Comparable[size()];
			
			order(array, this.root, 0);
			
		}
		
		return array;
	}
	
	private int order(T[] array ,BSTNode<T> node, int index) {
		
		if (! node.isEmpty()) {
			
			BSTNode<T> prox = (BSTNode<T>) node.getLeft();
			
			index = order(array, prox, index);
			
			array[index] = node.getData();
			index++;
			
			prox = (BSTNode<T>) node.getRight();
			
			index = order(array, prox, index);
			
		}
		
		return index;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] postOrder() {

		T[] array;
		
		if (isEmpty()) {
			
			array = (T[]) new Comparable[] {};
			
		} else {
			
			array = (T[]) new Comparable[size()];
			
			postOrder(array, this.root, 0);
			
		}
		
		return array;
	}
	
	private int postOrder(T[] array ,BSTNode<T> node, int index) {
		
		if (! node.isEmpty()) {
			
			BSTNode<T> prox = (BSTNode<T>) node.getLeft();
			
			index = postOrder(array, prox, index);
			
			prox = (BSTNode<T>) node.getRight();
			
			index = postOrder(array, prox, index);
			
			array[index] = node.getData();
			index++;
			
		}
		
		return index;
	}
	
	private boolean isLeaf(BSTNode<T> node) {
		return node.getLeft().isEmpty() && node.getRight().isEmpty();
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
			+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
