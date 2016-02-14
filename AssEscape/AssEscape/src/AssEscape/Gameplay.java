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
	
	Map level1;
	Map current;
	
	Enemy[] level1enemies;
	
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
		}
		
		catch(SlickException e){
			e.printStackTrace();
		}
		
		//TODO animation
		turn = 0;
		player = new Player();
		player.x = 60;
		player.y = 60;
		player.finalx = 60;
		player.finaly = 60;
		drawhighlight = false;
		
		level1enemies = new Enemy [] {new Enemy(6, 6, 30)};
		
		level1 = new Map(map1, 16, level1enemies);
		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		g.drawImage(mapbackground, 50, 50);
		
		for(int i = 0; i < current.enemies.length; i++){
			if(current.enemies[i].alive){
				current.enemies[i].current.draw(current.enemies[i].x, current.enemies[i].y);
			}
		}
		
		g.drawImage(deque, 575, 50);
		g.drawImage(menubutton, 575, 475);
		g.drawImage(restartbutton, 675, 475);
		
		player.current.draw(player.x, player.y);
		
		if(drawhighlight){
			g.drawImage(highlight, highlightx, highlighty);
		}
		
		
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int sec) throws SlickException {
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
	
			if(gc.getInput().isMousePressed(0)){
				turn = 1;
				drawhighlight = false;
				System.out.println(tilex);
				player.finalx = tilex * current.tileheight + 60;
				player.finaly = tiley * current.tileheight + 60;
			}
		}
		
		else if(turn == 1){
			if(player.x != player.finalx){
				player.x = player.x + 10;
			}
			else if(player.y != player.finaly){
				player.y = player.y + 10;
			}
			else{
				turn = 2;
			}
		}
		
		else if(turn == 2){
			
			//TODO: fix this shit
			for(int i = 0; i < current.enemies.length; i++){
				if(current.enemies[i].alive){
					System.out.println("calcd");
					int totalmove = (int)Math.random()*current.enemies[i].moverange;
					int vertmove = (int)Math.random()*totalmove;
					int horizmove = totalmove - vertmove;
					
					current.enemies[i].finalx = current.enemies[i].x + horizmove * current.tileheight;
					current.enemies[i].finaly = current.enemies[i].y + horizmove * current.tileheight;
				}
			}
			
			for(int i = 0; i < current.enemies.length; i++){
				if(current.enemies[i].alive && current.enemies[i].x != current.enemies[i].finalx && current.enemies[i].y != current.enemies[i].finaly){
					if(current.enemies[i].x != current.enemies[i].finalx){
						current.enemies[i].x = current.enemies[i].x + 10;
					}
					else if(current.enemies[i].y != current.enemies[i].finaly){
						current.enemies[i].y = current.enemies[i].y + 10;
					}
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
