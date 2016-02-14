package AssEscape;

import org.newdawn.slick.Image;

public class PowerUp {
	Image sprite;
	boolean collected;
	
	int x;
	int y;
	int tilex;
	int tiley;
	
	int dequeindex;
	
	public PowerUp(Image s, int xx, int yy, int tilewidth){
		sprite = s;
		tilex = xx;
		tiley = yy;
		x = tilex * tilewidth + 60;
		y = tiley * tilewidth + 60;
		collected = false;
	}
}
