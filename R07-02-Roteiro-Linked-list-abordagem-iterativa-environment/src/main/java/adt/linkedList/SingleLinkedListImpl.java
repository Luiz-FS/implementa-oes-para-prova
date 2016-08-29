package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;



	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {

		return getHead().getData() == null;
	}

	@Override
	public int size() {

		int size = 0;

		SingleLinkedListNode<T> aux = getHead();

		while (aux.getData() != null) {

			size += 1;
			aux = aux.getNext();
		}

		return size;
	}

	@Override
	public T search(T element) {
		
		if (element == null) return null;

		SingleLinkedListNode<T> aux = getHead();

		while (aux.getData() != null && (! aux.getData().equals(element))) {

			aux = aux.getNext();
		}

		if (aux.getData() == null) {
			return null;
		} else {
			return aux.getData();
		}
	}

	@Override
	public void insert(T element) {
		
		if (element == null) return;

		SingleLinkedListNode<T> newNode = new SingleLinkedListNode<>(element, new SingleLinkedListNode<T>());

		if (isEmpty()) {

			setHead(newNode);

		} else {

			SingleLinkedListNode<T> aux = getHead();

			while (aux.getNext().getData() != null) {
				aux = aux.getNext();
			}

			aux.setNext(newNode);
		}
	}

	@Override
	public void remove(T element) {

		if (isEmpty() || element == null) {
			return;

		} else if (getHead().getData().equals(element)) {

			setHead(getHead().getNext());
		} else {

			SingleLinkedListNode<T> aux = getHead();
			SingleLinkedListNode<T> prev = null;

			while (aux.getData() != null && (! aux.getData().equals(element))) {
				prev = aux;
				aux = aux.getNext();
			}

			if (aux.getData() != null) {

				if (aux.getNext().getData() == null) {
					prev.setNext(new SingleLinkedListNode<T>());

				} else {

					prev.setNext(aux.getNext());
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray(){

		if (isEmpty()) {

			T[] array = (T[]) new Object[] {};
			return array;
		}

		T[] array  = (T[]) new Object[size()];

		SingleLinkedListNode<T> aux = getHead();

		int i = 0;

		while (aux.getData() != null) {
			array[i] = aux.getData();
			aux = aux.getNext();
			i++;
		}

		return array;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}


}
