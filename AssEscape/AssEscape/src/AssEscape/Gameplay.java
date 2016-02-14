package AssEscape;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Gameplay extends BasicGameState{
	
	int turn;
	int id = 2;
	int mapnumber;
	
	boolean drawhighlight;
	boolean canmove;
	int highlightx;
	int highlighty;
	int tilex;
	int tiley;
	
	int [] duration = new int[] {300, 300};
	
	Image map1;
	Image map2;
	Image map3;
	Image map4;
	Image map5;
	Image map6;
	Image mapbackground;
	Image deque;
	Image menubutton;
	Image restartbutton;
	Image highlight;
	Image cantmovehighlight;
	Image excuselvl1;
	
	Map level1;
	Map current;
	
	Enemy[] level1enemies;
	PowerUp[] level1powerups;
	
	Player player;
	
	
	public Gameplay(){
		super();
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		
		try{
			map1 = new Image("img/map1.PNG");
			deque = new Image("img/deque.png");
			menubutton = new Image("img/menubutton.png");
			restartbutton = new Image("img/restartbutton.png");
			mapbackground = new Image("img/mapbackground.png");
			highlight = new Image("img/highlight.png");
			cantmovehighlight = new Image("img/cantmovehighlight.png");
			excuselvl1 = new Image("img/excuselvl1.png");
		}
		
		catch(SlickException e){
			e.printStackTrace();
		}
		
		//TODO animation
		turn = 0;
		player = new Player(6);
		player.x = 60;
		player.y = 60;
		player.finalx = 60;
		player.finaly = 60;
		drawhighlight = false;
		
		level1enemies = new Enemy [] {new Enemy(6, 6, 30, 8)};
		level1powerups = new PowerUp [] {new PowerUp(excuselvl1, 8, 8, 30)};
		
		level1 = new Map(map1, 16, level1enemies, level1powerups);
		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		g.drawImage(mapbackground, 50, 50);
		
		for(int i = 0; i < current.enemies.length; i++){
			if(current.enemies[i].turn != 3){
				current.enemies[i].current.draw(current.enemies[i].x, current.enemies[i].y);
			}
		}
		
		for(int i = 0; i < current.powerups.length; i++){
			if(!current.powerups[i].collected){
				current.powerups[i].sprite.draw(current.powerups[i].x, current.powerups[i].y, current.tileheight/48f);
			}
		}
		
		g.drawImage(deque, 575, 50);
		g.drawImage(menubutton, 575, 475);
		g.drawImage(restartbutton, 675, 475);
		
		player.current.draw(player.x, player.y);
		
		if(drawhighlight){
			if(canmove){
				g.drawImage(highlight, highlightx, highlighty);
			}
			else{
				g.drawImage(cantmovehighlight, highlightx, highlighty);
			}
		}	
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int sec) throws SlickException {
		
		//turn 0: player selects a square
		if(turn == 0){
			
			int mousex = gc.getInput().getAbsoluteMouseX();
			int mousey = gc.getInput().getAbsoluteMouseY();
			
			if(mousex > 60 && mousex < 540){
				tilex = (mousex - 60)/current.tileheight;
			}
			
			else{
				drawhighlight = false;
			}
			
			if(mousey > 60 && mousey < 540){
				tiley = (mousey - 60)/current.tileheight;
			}
			
			else{
				drawhighlight = false;
			}
			
			if(mousex > 60 && mousex < 540 && mousey > 60 && mousey < 540){
				drawhighlight = true;
				highlightx = tilex * current.tileheight + 60;
				highlighty = tiley * current.tileheight + 60;
			}
			
			
			if(Math.abs(player.x - highlightx)/30 + Math.abs(player.y - highlighty)/30 <= player.moverange){
				canmove = true;
			}
			else{
				canmove = false;
			}
	
			if(gc.getInput().isMousePressed(0)){
				if(canmove){
					turn = 1;
					drawhighlight = false;
					player.finalx = tilex * current.tileheight + 60;
					player.finaly = tiley * current.tileheight + 60;
				}
			}
		}
		
		//turn 1: player movement
		else if(turn == 1){
			if(player.x != player.finalx){
				if(player.finalx < player.x){
					player.x = player.x - 10;
				}
				else{
					player.x = player.x + 10;
				}
			}
			else if(player.y != player.finaly){
				if(player.finaly < player.y){
					player.y = player.y - 10;
				}
				else{
					player.y = player.y + 10;
				}
			}
			else{
				for(int i = 0; i < current.powerups.length; i++){
					if(current.powerups[i].x == player.x && current.powerups[i].y == player.y){
						current.powerups[i].collected = true;
						turn = 2;
					}
				}
				turn = 2;
			}
		}
		
		//turn 2: monster ai selects a square and moves
		else if(turn == 2){
			
			for(int i = 0; i < current.enemies.length; i++){
				if(current.enemies[i].turn == 0){
					current.enemies[i].turn = 1;
					
					double totalmovedub = Math.random()*(current.enemies[i].moverange - 1);
					int totalmove = (int)totalmovedub + 1;
					
					double vertmovedub = (Math.random()*totalmove - 1);
					int vertmove = (int)vertmovedub + 1;
					int horizmove = totalmove - vertmove;
					
					if(Math.random() < 0.5){
						vertmove = -vertmove;
					}
					
					if(Math.random() < 0.5){
						horizmove = -horizmove;
					}
					
					current.enemies[i].finalx = current.enemies[i].x + horizmove * current.tileheight;
					current.enemies[i].finaly = current.enemies[i].y + vertmove * current.tileheight;
					
					if(current.enemies[i].finalx < 60){
						current.enemies[i].finalx = 60;
					}
					else if(current.enemies[i].finalx > 510){
						current.enemies[i].finalx = 510;
					}
					
					if(current.enemies[i].finaly < 60){
						current.enemies[i].finaly = 60;
					}
					else if(current.enemies[i].finaly > 510){
						current.enemies[i].finaly = 510;
					}
				}
			}
			
			int movedenemies = 0;
			
			for(int i = 0; i < current.enemies.length; i++){
				
				if(current.enemies[i].turn == 1){
					
					if(current.enemies[i].x != current.enemies[i].finalx){
						if(current.enemies[i].finalx < current.enemies[i].x){
							current.enemies[i].x = current.enemies[i].x - 10;
						}
						else{
							current.enemies[i].x = current.enemies[i].x + 10;
						}
					}
					
					else if(current.enemies[i].y != current.enemies[i].finaly){
						if(current.enemies[i].finaly < current.enemies[i].y){
							current.enemies[i].y = current.enemies[i].y - 10;
						}
						else{
							current.enemies[i].y = current.enemies[i].y + 10;
						}
					}
					
					else{
						current.enemies[i].turn = 2;
						movedenemies++;
					}
				}
				
				
			}
			
			
			if(movedenemies == current.enemies.length){
				turn = 0;
				for(int i = 0; i < current.enemies.length; i++){
					current.enemies[i].turn = 0;
				}
			}
		}
		
	}

	@Override
	public int getID() {
		return id;
	}
	
	public void choosemap(int which){
		mapnumber = which;
	}
	
}
