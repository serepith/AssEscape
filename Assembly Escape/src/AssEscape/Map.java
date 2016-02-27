package AssEscape;

import org.newdawn.slick.Image;

public class Map {
	
	//its a map
	//all map data is contained within the map class so it's all accessed through current (in Gameplay)
	
	Image map;
	int height;
	int tileheight;
	Enemy [] enemies;
	PowerUp [] powerups;
	ImpassableZone [] chesthighwalls;
	
	public Map(Image mappy, int nheight, Enemy [] enem, PowerUp [] power, ImpassableZone [] walls){
		map = mappy;
		height = nheight;
		tileheight = 480/height;
		enemies = enem;
		powerups = power;
		chesthighwalls = walls;
	}
}
