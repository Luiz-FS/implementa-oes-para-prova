package adt.stack;

public class StackImpl<T> implements Stack<T> {

	private T[] array;
	private int top;

	@SuppressWarnings("unchecked")
	public StackImpl(int size) {
		
		if (size < 0) {
			throw new RuntimeException("Tamanho da pilha nao pode ser negativo");
		}
		
		array = (T[]) new Object[size];
		top = -1;
	}

	@Override
	public T top() {
		
		if (! isEmpty())
			return this.array[top];
		else
			return null;
	}

	@Override
	public boolean isEmpty() {
		
		if (this.top == -1)
			return true;
		else
			return false;
	}

	@Override
	public boolean isFull() {
		
		if (this.top == this.array.length - 1)
			return true;
		else
			return false;
	}

	@Override
	public void push(T element) throws StackOverflowException {
		
		if (! isFull()) {
			
			this.top++;
			this.array[top] = element;
			
		} else {
			
			throw new StackOverflowException();
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		
		if (! isEmpty()) {
			
			T element = this.array[top];
			this.top--;
			
			return element;
			
		} else {
			
			throw new StackUnderflowException();
		}
	}

}
