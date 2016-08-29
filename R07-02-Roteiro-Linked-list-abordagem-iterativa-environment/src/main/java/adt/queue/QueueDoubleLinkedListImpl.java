package adt.queue;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class QueueDoubleLinkedListImpl<T> implements Queue<T> {


	protected DoubleLinkedList<T> list;
	protected int size;

	public QueueDoubleLinkedListImpl(int size) {
		
		if (size <= 0) {
			throw new RuntimeException("Capacidade nao pode ser vazia ou negativa");
		}
		
		this.size = size;
		this.list = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {

		if (! isFull()) {
			this.list.insert(element);
			
		} else {
			throw new QueueOverflowException();
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {

		if (! isEmpty()){
			DoubleLinkedListImpl<T> aux = (DoubleLinkedListImpl<T>) this.list;

			T element = aux.getHead().getData();
			this.list.removeFirst();

			return element;

		} else {
			throw new QueueUnderflowException();
		}
	}

	@Override
	public T head() {

		if (! isEmpty()) {
			DoubleLinkedListImpl<T> aux = (DoubleLinkedListImpl<T>) this.list;

			T element = aux.getHead().getData();

			return element;

		} else {
			throw null;
		}
	}

	@Override
	public boolean isEmpty() {
		return this.list.isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.list.size() == size;
	}

}
