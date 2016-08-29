package adt.queue;

public class QueueImpl<T> implements Queue<T> {

	private T[] array;
	private int tail;
		
	
	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		
		if (size < 0) {
			throw new RuntimeException("Tamanho da fila nao pode ser negativo");
		}
		
		array = (T[])new Object[size];
		tail = -1;
	}

	@Override
	public T head() {
		
		if (! isEmpty()) {
			return this.array[0];
			
		} else {
			return null;
		}
	}

	@Override
	public boolean isEmpty() {
		
		if (this.tail == -1)
			return true;
		else
			return false;
	}

	@Override
	public boolean isFull() {
		
		if (this.tail == this.array.length - 1)
			return true;
		else
			return false;
	}
	
	private void shiftLeft(){
		
		T aux;
		
		for (int i = 0; i < this.tail; i++) {
			
			aux = this.array[i];
			this.array[i] = this.array[i+1];
			this.array[i+1] = aux;
		}
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		
		if (! isFull()) {
			
			this.tail++;
			this.array[this.tail] = element;
			
		} else {
			throw new QueueOverflowException();
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		
		if (! isEmpty()) {
			
			T element = this.array[0];
			shiftLeft();
			this.tail--;
			
			return element;
		} else {
			throw new QueueUnderflowException();
		}
	}


}
