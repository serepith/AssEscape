package AssEscape;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Enemy {
	boolean spottedplayer = false;
	boolean drawvision = false;
	
	//tilex and tiley are like, coordinates on the tile grid (so from 0-15)
	//finalx and finaly are the coordinates a mobile monster is moving towards
	//vision is at the moment only one shape--a triangle--
	//which is calculated from one int
	//height = vision, base width = 2*vision + 1
	
	int x;
	int y;
	int tilex;
	int tiley;
	int finalx;
	int finaly;
	int moverange;
	int vision;
	
	//0 = has not moved yet, 1 = moving, 2 = looking for player, 3 = spotted player, 4 = move after spotting player, 5 = nothing
	int turn;
	
	Image current;
	
	
	
	public Enemy(int tileplacex, int tileplacey, int tilewidth, int move, String img, int vis){
		try {
			current = new Image(img);
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
		
		moverange = move;
		
		turn = 0;
		
		vision = vis;
		
	}
	
	public void resetCoord(int tilewidth){
		x = 60 + tilex * tilewidth;
		y = 60 + tiley * tilewidth;
	}
}
