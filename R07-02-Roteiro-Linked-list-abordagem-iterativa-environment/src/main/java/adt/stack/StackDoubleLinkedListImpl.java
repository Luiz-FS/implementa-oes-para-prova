package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class StackDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> list;
	protected int size;

	public StackDoubleLinkedListImpl(int size) {
		
		if (size <= 0) {
			throw new RuntimeException("Capacidade nao pode ser vazia ou negativa");
		}
		
		this.size = size;
		this.list = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		
		if (! isFull()) {
			
			this.list.insert(element);
			
		} else {
			throw new StackOverflowException();
		}

	}

	@Override
	public T pop() throws StackUnderflowException {
		
		if (! isEmpty()){
			DoubleLinkedListImpl<T> aux = (DoubleLinkedListImpl<T>) this.list;

			T element = aux.getLast().getData();
			this.list.removeLast();

			return element;

		} else {
			throw new StackUnderflowException();
		}
	}

	@Override
	public T top() {
		
		if (! isEmpty()) {
			DoubleLinkedListImpl<T> aux = (DoubleLinkedListImpl<T>) this.list;

			T element = aux.getLast().getData();

			return element;

		} else {
			return null;
		}
	}

	@Override
	public boolean isEmpty() {
		
		return this.list.isEmpty();
	}

	@Override
	public boolean isFull() {
		
		return this.list.size() == this.size;
	}

}
