
public class Car extends Enemy{
	
	boolean horizontal;
	
	public Car(int tileplacex, int tileplacey, int tilewidth, int move, boolean getHorizontal) {
		super(tileplacex, tileplacey, tilewidth, move, "img/car.png", 4);
		horizontal = getHorizontal;
	}
	
	public boolean isHorizontal () {
		return horizontal;
	}
	
}
