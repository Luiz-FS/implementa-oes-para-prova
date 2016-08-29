package adt.queue;

import adt.stack.Stack;
import adt.stack.StackImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class QueueUsingStack<T> implements Queue<T> {

	private Stack<T> stack1;
	private Stack<T> stack2;

	public QueueUsingStack(int size) {

		if (size < 0) {
			throw new RuntimeException("Tamanho da fila nao pode ser negativo");
		}

		stack1 = new StackImpl<T>(size);
		stack2 = new StackImpl<T>(size);
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {

		if (! isFull()) {

			switchStack(stack2, stack1);

			try {
				this.stack1.push(element);
			} catch (StackOverflowException e) {
				throw new QueueOverflowException();
			}

		} else {
			throw new QueueOverflowException();
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {

		if(! isEmpty()) {

			switchStack(this.stack1, this.stack2);

			try {

				T element = this.stack2.pop();
				return element;

			} catch (StackUnderflowException e) {
				throw new QueueUnderflowException();
			}

		} else {
			throw new QueueUnderflowException();
		}
	}

	private void switchStack(Stack<T> stack1, Stack<T> stack2) {

		while (! stack1.isEmpty()) {
				
			try {
				stack2.push(stack1.pop());
			} catch (StackOverflowException | StackUnderflowException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public T head() {

		if (! isEmpty()) {

				switchStack(this.stack1, this.stack2);
			
			T element = this.stack2.top();
			return element;

		} else {
			return null;
		}
	}

	@Override
	public boolean isEmpty() {

		if (this.stack1.isEmpty() && this.stack2.isEmpty())
			return true;
		else
			return false;
	}

	@Override
	public boolean isFull() {

		if (this.stack1.isFull() || this.stack2.isFull()) 
			return true;
		else
			return false;
	}

}
