package AssEscape;

import org.newdawn.slick.Image;

public class Map {
	Image map;
	int height;
	int tileheight;
	Enemy [] enemies;
	PowerUp [] powerups;
	
	public Map(Image mappy, int nheight, Enemy [] enem, PowerUp [] power){
		map = mappy;
		height = nheight;
		tileheight = 480/height;
		enemies = enem;
		powerups = power;
	}
}
