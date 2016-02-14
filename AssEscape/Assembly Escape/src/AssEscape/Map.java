package AssEscape;

import org.newdawn.slick.Image;

public class Map {
	Image map;
	int height;
	int tileheight;
	Enemy [] enemies;
	
	public Map(Image mappy, int nheight, Enemy [] enem){
		map = mappy;
		height = nheight;
		tileheight = 480/height;
		enemies = enem;
	}
}
