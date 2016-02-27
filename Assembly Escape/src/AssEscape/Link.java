package AssEscape;

//stoled

public class Link<E> {
	private E element; // Value for this node
	
	private Link<E> next; // Pointer to next node in list
	// Constructors
	
	private Link<E> prev;
	
	Link(E it, Link<E> nextval, Link<E> preval){
		element = it;
		next = nextval;
		prev = preval;
		}
	
	Link(E it, Link<E> nextval){
		element = it;
		next = nextval;
	}
	
	Link(Link<E> nextval) { next = nextval; }
	
	Link<E> next() { return next; } // Return next field
	
	Link<E> prev() { return prev; }
	
	Link<E> setPrev(Link<E> prevval){
		return prev = prevval;
	}
	
	Link<E> setNext(Link<E> nextval) // Set next field
	
	{ return next = nextval; } // Return element field
	
	E element() { return element; } // Set element field
	
	E setElement(E it) { return element = it; }
	
	public String toString(){
		return element.toString();
	}
	
	}
