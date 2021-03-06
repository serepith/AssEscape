package AssEscape;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Enemy {
	boolean spottedplayer = false;
	
	int x;
	int y;
	int tilex;
	int tiley;
	int finalx;
	int finaly;
	int moverange;
	
	//0 = has not moved yet, 1 = moving, 2 = has moved this turn, 3 = dead
	int turn;
	
	Image enemy;
	Image enemy2;
	
	Image [] up;
	Image [] down;
	Image [] left;
	Image [] right;
	
	int [] duration = new int [] {300, 300};
	
	Animation moveUp;
	Animation moveDown;
	Animation moveLeft;
	Animation moveRight;
	
	Animation current;
	
	public Enemy(int tileplacex, int tileplacey, int tilewidth, int move){
		try {
			enemy = new Image("img/enemy1.png");
			enemy2 = enemy;
		}
		catch (SlickException e) {
			e.printStackTrace();
		}
		
		tilex = tileplacex;
		tiley = tileplacey;
		
		x = 60 + tilex * tilewidth;
		y = 60 + tiley * tilewidth;
		
		finalx = x;
		finaly = y;
		
		up = new Image [] {enemy, enemy2};
		down = new Image [] {enemy, enemy2};
		left = new Image [] {enemy, enemy2};
		right = new Image [] {enemy, enemy2};
		
		moveUp = new Animation(up, duration, true);
		moveDown = new Animation(down, duration, true);
		moveLeft = new Animation(left, duration, true);
		moveRight = new Animation(right, duration, true);
		
		current = moveDown;
		
		moverange = move;
		
		turn = 0;
		
	}
}
