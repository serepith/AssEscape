package AssEscape;


class Deque<E>{
	
	private Link<E> front; // Pointer to front queue node
	private Link<E> rear; // Pointer to rear queuenode
	private int size; // Number of elements in queue
	int drawx = 629;
	int drawy = 60;
	
	
	public Deque() {
		init();
		}
	
	public Deque(int size){
		init();
		}
	
	/** Initialize queue */
	private void init() {
		front = rear = new Link<E>(null);
		size = 0;
	}
	
	/** Reinitialize queue */
	public void clear() {
		init();
		}
	
	/** Put element on rear */
	public void enqueue(E it) {
		rear.setNext(new Link<E>(it, null, rear));
		rear = rear.next();
		size++;
		if(size > 5){
			drawx = 590;
		}
	}
	
	/** Remove and return element from front */
	public E dequeuefront() {
		assert size != 0 : "Queue is empty";
		E it = front.next().element(); // Store dequeued value
		front.setNext(front.next().next()); // Advance front
		if (front.next() == null) rear = front; // Last Object
		size--;
		return it; // Return Object
	}
	
	public E dequeuerear(){
		assert size != 0 : "Queue is empty";
		E it = rear.element();
		rear.prev().setNext(null);
		rear = rear.prev();
		if(front.next() == null) rear = front;
		size--;
		return it;
	}
	
	/** @return Front element */
	public Link<E> frontValue() {
		assert size != 0 : "Queue is empty";
		if(size != 0){
			return front.next();
		}
		else{
			return null;
		}
	}
	
	/** @return Queue size */
	public int length() { return size; }
	
	
	public void display(){
		Link<E> point = front.next();
		while(point != null){
			System.out.println(point.toString());
			point = point.next();
		
	}
}

}