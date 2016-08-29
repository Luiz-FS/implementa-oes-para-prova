package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	@Override
	public void insertFirst(T element) {
		
		if (element == null) return;

		DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<>(element, new DoubleLinkedListNode<T>(),
				new DoubleLinkedListNode<T>());

		if (getHead().getData() == null) {
			setHead(newNode);
			setLast(newNode);
			
		} else {

			DoubleLinkedListNode<T> head = (DoubleLinkedListNode<T>) getHead();

			newNode.setNext(head);
			head.setPrevious(newNode);

			setHead(newNode);
		}
	}

	@Override
	public void insert(T element) {
		
		if (element == null) return;

		if (isEmpty()) {

			insertFirst(element);

		} else {

			DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<>(element, new DoubleLinkedListNode<T>(),
					new DoubleLinkedListNode<T>());

			getLast().setNext(newNode);
			newNode.setPrevious(getLast());
			setLast(newNode);
		}
	}

	@Override
	public void removeFirst() {

		if (! isEmpty()) {

			if (getHead().getNext().getData() == null) {
				setHead(new DoubleLinkedListNode<T>());
				this.last = new DoubleLinkedListNode<>();
				
			} else {

				setHead(getHead().getNext());
				DoubleLinkedListNode<T> aux = (DoubleLinkedListNode<T>) getHead();
				aux.setPrevious(new DoubleLinkedListNode<T>());
			}
		}
	}

	@Override
	public void remove(T element) {

		if (! isEmpty()) {

			if (getHead().getData().equals(element)) {
				removeFirst();

			} else {

				DoubleLinkedListNode<T> aux = (DoubleLinkedListNode<T>) getHead().getNext();

				while (aux.getData() != null && (! aux.getData().equals(element))) {
					aux = (DoubleLinkedListNode<T>) aux.getNext();
				}

				if (aux.getData() != null) {

					if (aux.getNext().getData() == null) {
						removeLast();

					} else {

						aux.getPrevious().setNext(aux.getNext());

						DoubleLinkedListNode<T> auxNext = (DoubleLinkedListNode<T>) aux.getNext();
						auxNext.setPrevious(aux.getPrevious());
					}
				}
			}
		}
	}

	@Override
	public void removeLast() {

		if (! isEmpty()) {

			if (getHead().getData().equals(getLast().getData())){
				removeFirst();
				
			} else {
				DoubleLinkedListNode<T> aux = this.last.getPrevious();
				aux.setNext(new DoubleLinkedListNode<T>());
				this.last = aux;
			}
		}
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
