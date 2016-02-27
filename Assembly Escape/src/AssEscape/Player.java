package AssEscape;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Player {
	
	//coordinates + coordinates a mobile player is moving towards
	
	int x;
	int y;
	int finalx;
	int finaly;
	int moverange;
	
	Image player;
	Image player2;
	
	//animations are all the same at the moment lol. might change them eventually
	
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
	
	public Player(int movelimit){
		
		try {
			player = new Image("img/player.png");
			player2 = new Image("img/player2.png");
		} 
		catch (SlickException e) {
			e.printStackTrace();
		}
		
		up = new Image[] {player, player2};
		down = new Image[] {player, player2};
		left = new Image[] {player, player2};
		right = new Image[] {player, player2};
		
		moveUp = new Animation(up, duration, true);
		moveDown = new Animation(down, duration, true);
		moveLeft = new Animation(left, duration, true);
		moveRight = new Animation(right, duration, true);
		
		current = moveDown;
		
		moverange = movelimit;
		
	}
	
	
}
