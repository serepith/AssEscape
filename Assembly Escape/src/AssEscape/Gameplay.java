import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Gameplay extends BasicGameState{
	
	//turn refers to the "phase" that gameplay is in. turn 0 is player action. turn 1 is player movement (no interaction)
	//turn 2 is monster movement
	
	//id refers to the id of the state (game state)
	
	//mapnumber refers to...the number of the map currently in use, what else?
	int turn;
	int id = 2;
	int mapnumber;
	
	//booleans! probably not the best way to do this but the one that was easiest for me to code
	//booleans refer to...exactly what they're named after
	//overrestartbutton, overmenubutton, inmap refer to what region the mouse is in
	//drawhighlight is like inmap except it is also false when it's not the player's turn to move
	//inmoverange refers to the player's move range; it also defines the highlihgt color
	boolean overrestartbutton;
	boolean overmenubutton;
	boolean drawhighlight;
	boolean inmoverange;
	boolean notwall;
	boolean inmap;
	
	//again, pretty self-explanatory; highlightx and highlighty are the coordinates of the highlight
	//they're calculated from tilex and tiley, which refer to the top left corner of the tile that the mouse is on
	int highlightx;
	int highlighty;
	int tilex;
	int tiley;
	
	//declaring images and maps
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
	Image itsawallhighlight;
	Image excuselvl1;
	
	
	Map level1;
	Map level2;
	Map level3;
	Map level4;
	Map level5;
	Map level6;
	Map current;
	
	Sound alert;
	
	Enemy[] level1enemies;
	PowerUp[] level1powerups;
	ImpassableZone[] level1walls;
	
	Enemy[] level2enemies;
	PowerUp[] level2powerups;
	ImpassableZone[] level2walls;
	
	Enemy[] level3enemies;
	PowerUp[] level3powerups;
	ImpassableZone[] level3walls;
	
	Enemy[] level4enemies;
	PowerUp[] level4powerups;
	ImpassableZone[] level4walls;
	
	Enemy[] level5enemies;
	PowerUp[] level5powerups;
	ImpassableZone[] level5walls;
	
	Enemy[] level6enemies;
	PowerUp[] level6powerups;
	ImpassableZone[] level6walls;
	
	Player player;
	
	Deque<PowerUp> powerups;
	
	//TODO: pathfinding algorithm :P use for player and monsters
	
	public Gameplay(){
		super();
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		
		
		try{
			map1 = new Image("img/map1.PNG");
			map2 = new Image("img/map2.png");
			map3 = new Image("img/map3.png");
			map4 = new Image("img/map4.png");
			map5 = new Image("img/map5.png");
			map6 = new Image("img/map6.png");
			deque = new Image("img/deque.png");
			menubutton = new Image("img/menubutton.png");
			restartbutton = new Image("img/restartbutton.png");
			mapbackground = new Image("img/mapbackground.png");
			highlight = new Image("img/highlight.png");
			cantmovehighlight = new Image("img/cantmovehighlight.png");
			excuselvl1 = new Image("img/excuse.png");
			itsawallhighlight = new Image("img/itsawallhighlight.png");
			alert = new Sound("img/alert.ogg");
		}
		
		catch(SlickException e){
			e.printStackTrace();
		}
		
		turn = 0;
		
		//refers to highlight over squares upon mouseover
		drawhighlight = false;
		
		level1enemies = new Enemy [] {new DeanCos(10, 10, 30, 3), new Enemy(5, 7, 30, 6, "img/McLaughlin.png", 2)};
		level2enemies = new Enemy [] {new DeanCos(10, 5, 30, 6), new Enemy(6, 7, 30, 6, "img/Burke.png", 2), new Enemy(7, 10, 30, 6, "img/McLaughlin.png", 2)};
		//level2enemies = new Enemy [] {new DeanCos(10, 5, 30, 6)};
		level3enemies = new Enemy [] {new DeanCos(11, 5, 30, 2), new Enemy(3, 6, 30, 1, "img/Spanier.png", 2), new Enemy(11, 11, 30, 1, "img/McLaughlin.png", 2)};
		level4enemies = new Enemy [] {new DeanCos(11, 7, 30, 4), new Enemy(4, 6, 30, 1, "img/DocJ.png", 2), new Enemy(7, 11, 30, 1, "img/Spanier.png", 2)};
		level5enemies = new Enemy [] {new DeanCos(9, 6, 30, 4), new Enemy(5, 7, 30, 3, "img/Waterman.png", 3), new Enemy(1,9, 30, 3, "img/MrGary.png", 2)};
		level6enemies = new Enemy [] {new DeanCos(9, 6, 30, 6), new Enemy(9, 14, 30, 1, "img/Waterman.png", 2), new Enemy(15, 0, 30, 3, "img/MrGary.png", 2), new Enemy(12, 10, 30, 3, "img/Burke.png", 2), new Enemy(7, 8, 30, 3, "img/Mclaughlin.png", 2), new Enemy(0, 5, 30, 3, "img/DocJ.png", 2)};
		
		level1powerups = new PowerUp [] {new PowerUp(excuselvl1, 5, 13, 30)};
		level2powerups = new PowerUp [] {new PowerUp(excuselvl1, 6, 12, 30)};
		level3powerups = new PowerUp [] {new PowerUp(excuselvl1, 2, 5, 30), new PowerUp(excuselvl1, 4, 4, 30)};
		level4powerups = new PowerUp [] {new PowerUp(excuselvl1, 7, 5, 30), new PowerUp(excuselvl1, 6, 16, 30)};
		level5powerups = new PowerUp [] {new PowerUp(excuselvl1, 9, 9, 30), new PowerUp(excuselvl1, 11, 14, 30), new PowerUp(excuselvl1, 4, 11, 30)};
		level6powerups = new PowerUp [] {new PowerUp(excuselvl1, 0, 11, 30), new PowerUp(excuselvl1, 3, 8, 30), new PowerUp(excuselvl1, 5, 3, 30), new PowerUp(excuselvl1, 10, 2, 30), new PowerUp(excuselvl1, 13, 0, 30), new PowerUp(excuselvl1, 15, 4, 30), new PowerUp(excuselvl1, 12, 7, 30), new PowerUp(excuselvl1, 4, 14, 30)};
		
		
		level1walls = new ImpassableZone [] {new ImpassableZone(450, 90, 30, 90), new ImpassableZone(210, 240, 150, 90)};
		level2walls = new ImpassableZone [] {new ImpassableZone(90, 60, 150, 60), new ImpassableZone(360, 60, 180, 60), new ImpassableZone(60, 150, 90, 330), new ImpassableZone(300, 240, 60, 120), new ImpassableZone(420, 150, 120, 60), new ImpassableZone(480, 210, 60, 120), new ImpassableZone(300, 450, 90, 90), new ImpassableZone(480, 450, 60, 90), new ImpassableZone(150, 450, 120, 90), new ImpassableZone(450, 330, 90, 90)};
		level3walls = new ImpassableZone [] {new ImpassableZone(210, 210, 120, 30), new ImpassableZone(390, 240, 60, 60), new ImpassableZone(240, 330, 30, 30), new ImpassableZone(210, 360, 60, 60), new ImpassableZone(240, 420, 30, 30)};
		level4walls = new ImpassableZone [] {new ImpassableZone(300, 60, 300, 60), new ImpassableZone(60, 180, 60, 120), new ImpassableZone(300, 180, 60, 210), new ImpassableZone(450, 180, 90, 180), new ImpassableZone(150, 270, 60, 270), new ImpassableZone(60, 420, 60, 120), new ImpassableZone(270, 480, 150, 60)};
		level5walls = new ImpassableZone [] {new ImpassableZone(150, 150, 60, 30), new ImpassableZone(390, 60, 90, 30), new ImpassableZone(420, 90, 60, 30), new ImpassableZone(150, 240, 30, 60), new ImpassableZone(180, 210, 30, 120), new ImpassableZone(150, 330, 60, 240), new ImpassableZone(270, 510, 60, 30), new ImpassableZone(510, 480, 30, 60)};
		level6walls = new ImpassableZone [] {new ImpassableZone(240, 150, 120, 90), new ImpassableZone(480, 210, 60, 120), new ImpassableZone(90, 270, 60, 60), new ImpassableZone(90, 360, 60, 60), new ImpassableZone(450, 360, 90, 120)};
		
		
		level1 = new Map(map1, 16, level1enemies, level1powerups, level1walls);
		level2 = new Map(map2, 16, level2enemies, level2powerups, level2walls);
		level3 = new Map(map3, 16, level3enemies, level3powerups, level3walls);
		level4 = new Map(map4, 16, level4enemies, level4powerups, level4walls);
		level5 = new Map(map5, 16, level5enemies, level5powerups, level5walls);
		level6 = new Map(map6, 16, level6enemies, level6powerups, level6walls);
		
		powerups = new Deque<PowerUp>();
		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		g.drawImage(mapbackground, 50, 50);
		
		g.drawImage(current.map, 60, 60);
		
		//if the enemy has spotted the player, they are drawn in red; else greyscale
		for(int i = 0; i < current.enemies.length; i++){
			if(current.enemies[i].spottedplayer){
				current.enemies[i].current.draw(current.enemies[i].x, current.enemies[i].y, 1/6f, Color.red);
			}
			else{
				current.enemies[i].current.draw(current.enemies[i].x, current.enemies[i].y, 1/6f);
			}
		}
		
		
		g.drawImage(deque, 575, 50);
		g.drawImage(menubutton, 575, 475);
		g.drawImage(restartbutton, 675, 475);
		
		for(int i = 0; i < current.powerups.length; i++){
			if(!current.powerups[i].collected){
				current.powerups[i].sprite.draw(current.powerups[i].x, current.powerups[i].y, current.tileheight/48f);
			}
		}
		
		//drawing collected powerups; got some funky numbers b/c of layout
		Link<PowerUp> iterator = powerups.frontValue();
		
		int powerupsdrawn = 0;
		
		int drawx = 641;
	
		if(powerups.length() > 5){
			drawx = 605;
		}
		
		int drawy = 80;
		
		while(iterator != null){
			if(powerupsdrawn == 5){
				drawx = 690;
				drawy = 80;
			}
			
			iterator.element().sprite.draw(drawx, drawy, 30/42f);
			iterator = iterator.next();
			powerupsdrawn++;
			
			drawy = drawy + 72;
			
		}
		
		player.current.draw(player.x, player.y);
		
		//different highlights for can move, can't move bc out of range, can't move bc wall
		
		if(drawhighlight){
			if(inmoverange && notwall){
				g.drawImage(highlight, highlightx, highlighty);
			}
			else if(!inmoverange){
				g.drawImage(cantmovehighlight, highlightx, highlighty);
			}
			else if(!notwall){
				g.drawImage(itsawallhighlight, highlightx, highlighty);
			}
		}
		
		//draw enemies' fields of vision
		for(int i = 0; i < current.enemies.length; i++){
			if(current.enemies[i].drawvision){
				for(int j = 1; j < current.enemies[i].vision + 1; j++){
					for(int k = -j; k < j + 1; k++){
						g.drawImage(highlight, current.enemies[i].x - k*30, current.enemies[i].y + j*30);
					}
				}
			}
		}
	}

	//gotta get collision detection working for update; at the moment teachers will walk on top of each other
	
	@Override
	public void update(GameContainer gc, StateBasedGame game, int sec) throws SlickException {
		
		//turn 0: player selects a square
		if(turn == 0){
			
			//check mouse location
			
			int mousex = gc.getInput().getAbsoluteMouseX();
			int mousey = gc.getInput().getAbsoluteMouseY();
			
			//on map?
			
			if(mousex > 60 && mousex < 540 && mousey > 60 && mousey < 540){
				
				tilex = (mousex - 60)/current.tileheight;
				tiley = (mousey - 60)/current.tileheight;
				
				drawhighlight = true;
				inmap = true;
				overmenubutton = false;
				overrestartbutton = false;
				highlightx = tilex * current.tileheight + 60;
				highlighty = tiley * current.tileheight + 60;
				
				for(int i = 0; i < current.enemies.length; i++){
					if(mousex > current.enemies[i].x && mousex < current.enemies[i].x + 30 && mousey > current.enemies[i].y && mousey < current.enemies[i].y + 30){
						current.enemies[i].drawvision = true;
					}
					else{
						current.enemies[i].drawvision = false;
					}
				}
				
			}
			
			//menu button?
			
			else if(mousex > 575 && mousex < 650 && mousey > 475 && mousey < 550){
				drawhighlight = false;
				inmap = false;
				overmenubutton = true;
				overrestartbutton = false;
			}
			
			//restart button?
			
			else if(mousex > 675 && mousex < 750 && mousey > 475 && mousey < 550){
				drawhighlight = false;
				inmap = false;
				overmenubutton = false;
				overrestartbutton = true;
			}
			
			//nowhere interactive?
			
			else{
				drawhighlight = false;
				inmap = false;
				overmenubutton = false;
				overrestartbutton = false;
			}
			
			//in move range?
			
			if(Math.abs(player.x - highlightx)/30 + Math.abs(player.y - highlighty)/30 <= player.moverange){
				inmoverange = true;
				
			}
			
			else{
				inmoverange = false;
			}
			
			//on a wall?
			
			boolean foundwall = false;
			for(int i = 0; i < current.chesthighwalls.length && !foundwall; i++){
				if(mousex > current.chesthighwalls[i].x && mousex < current.chesthighwalls[i].x + current.chesthighwalls[i].width && mousey > current.chesthighwalls[i].y && mousey < current.chesthighwalls[i].y + current.chesthighwalls[i].height){
					 notwall = false;
					 foundwall = true;
					 
					 if(current.chesthighwalls[i].getClass().equals(FinishLine.class)){
						 if(mousex > ((FinishLine) current.chesthighwalls[i]).entrancex && mousex < ((FinishLine) current.chesthighwalls[i]).entrancex + ((FinishLine) current.chesthighwalls[i]).entrancew && mousey > ((FinishLine) current.chesthighwalls[i]).entrancey && mousey < ((FinishLine) current.chesthighwalls[i]).entrancey + ((FinishLine) current.chesthighwalls[i]).entranceh){
							 notwall = true;
							 foundwall = false;
						 }
					 }
				}
				else{
					notwall = true;
				}
			}
			
			//response to mouse click
			
			if(gc.getInput().isMousePressed(0)){
				if(inmoverange && notwall && inmap){
					turn = 1;
					drawhighlight = false;
					player.finalx = tilex * current.tileheight + 60;
					player.finaly = tiley * current.tileheight + 60;
				}
				
				else if(overmenubutton){
					turn = 0;
					player.x = 60;
					player.y = 60;
					for(int i = 0; i < current.enemies.length; i++){
						current.enemies[i].resetCoord(30);
					}
					game.enterState(1, new FadeOutTransition(Color.black), new FadeInTransition());
				}
				
				else if(overrestartbutton){
					turn = 0;
					turn = 0;
					player.x = 60;
					player.y = 60;
					for(int i = 0; i < current.enemies.length; i++){
						current.enemies[i].resetCoord(30);
					}
					game.enterState(2, new FadeOutTransition(Color.black), new FadeInTransition());
				}
			}
		}
		
		//turn 1: player movement
		else if(turn == 1){
			
			//as player moves, check if enemy has spotted them
			
			for(int i = 0; i < current.enemies.length; i++){
				for(int j = 1; j <= current.enemies[i].vision; j++){
					if(!current.enemies[i].spottedplayer && player.x >= current.enemies[i].x - 30*j && player.x <= current.enemies[i].x + 30*(j + 1) && player.y >= current.enemies[i].y + 30 && player.y <= current.enemies[i].y + 30*(j + 1)){
						current.enemies[i].spottedplayer = true;
						alert.play();
						current.enemies[i].turn = 3;
					}
				}
			}
			
			//move code
			
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
		
			
			//check if powerups collected
			
			else{
				for(int i = 0; i < current.powerups.length; i++){
					if(current.powerups[i].x == player.x && current.powerups[i].y == player.y && !current.powerups[i].collected){
						current.powerups[i].collected = true;
						powerups.enqueue(current.powerups[i]);
						turn = 2;
					}
				}
				turn = 2;
			}
			
			//check if reached finish
			
			for(int i = 0; i < current.chesthighwalls.length; i++){
				if(current.chesthighwalls[i].getClass().equals(FinishLine.class)){
					if(player.x > ((FinishLine) current.chesthighwalls[i]).entrancex && player.x < ((FinishLine) current.chesthighwalls[i]).entrancex + ((FinishLine) current.chesthighwalls[i]).entrancew && player.y > ((FinishLine) current.chesthighwalls[i]).entrancey && player.y < ((FinishLine) current.chesthighwalls[i]).entrancey + ((FinishLine) current.chesthighwalls[i]).entranceh){
						 System.out.println("ya did it");
					 }
				}
			}
			
		}
		
		//turn 2: monster ai selects a square and moves
		else if(turn == 2){
			
			//there's a bunch of for loops here because I want to check each enemy in turn order, not enemy order
			
			for(int i = 0; i < current.enemies.length; i++){
				if(current.enemies[i].turn == 0){
					current.enemies[i].turn = 1;
					boolean foundspot = false;
					
					//TODO: sometimes will glitch on wall. FIX
					//loop one: if an appropriate spot has not been found calculate until a spot that isn't
					//in a wall or off the map is chosen
					int vertmove;
					int horizmove;
					
					while(!foundspot){
									
						//check if car
						if (current.enemies[i] instanceof Car) {
						
							Car car = (Car) current.enemies[i];
							
							if (car.isHorizontal()) {
								horizmove = current.enemies[i].moverange;
								vertmove = 0;
							}
							else {
								horizmove = 0;
								vertmove = current.enemies[i].moverange;
							}
						}
						else{
							double totalmovedub = Math.random()*(current.enemies[i].moverange - 1);
							int totalmove = (int)totalmovedub + 1;
							
							double vertmovedub = (Math.random()*totalmove - 1);
							 vertmove = (int)vertmovedub + 1;
							 horizmove = totalmove - vertmove;
							
							if(Math.random() < 0.5){
								vertmove = -vertmove;
							}
							
							if(Math.random() < 0.5){
								horizmove = -horizmove;
							}

						}
						current.enemies[i].finalx = current.enemies[i].x + horizmove * current.tileheight;
						current.enemies[i].finaly = current.enemies[i].y + vertmove * current.tileheight;
						
						if(current.enemies[i].finalx >= 60 && current.enemies[i].finalx < 540 && current.enemies[i].finaly >= 60 && current.enemies[i].finaly < 540){
							inmap = true;
						}
						else{
							inmap = false;
						}
						
						boolean foundwall = false;
						
						for(int j = 0; j < current.chesthighwalls.length && !foundwall; j++){
							
							if(current.enemies[i].finalx >= current.chesthighwalls[j].x && current.enemies[i].finalx <= current.chesthighwalls[j].x + current.chesthighwalls[j].width && current.enemies[i].finaly >= current.chesthighwalls[j].y && current.enemies[i].finaly <= current.chesthighwalls[j].y + current.chesthighwalls[j].height){
								 notwall = false;
								 foundwall = true;
							}
							else{
								notwall = true;
							}
							
						}
						
						if(inmap && notwall){
							foundspot = true;
						}
						else{
							foundspot = false;
						}
						
					}
					
					
				}
			}
			
			//movedenemies is a variable that is added to on both turn two and four
			//it's a total of enemies that have reached their final coordinates
			
			int movedenemies = 0;
			
			for(int i = 0; i < current.enemies.length; i++){
				
				//loop two: enemy movement
				
				if(current.enemies[i].turn == 1){
					
					boolean xplus = false;
					boolean yplus = false;
					
					int nextx = current.enemies[i].x - 10;
					int nexty = current.enemies[i].y - 10;
					
					if(current.enemies[i].finalx > current.enemies[i].x){
						nextx = current.enemies[i].x + 10;
						xplus = true;
					}
		
					if(current.enemies[i].finaly > current.enemies[i].y){
						nexty = current.enemies[i].y + 10;
						yplus = true;
					}
					
					boolean foundwallx = false;
					boolean foundwally = false;
					
					//oh--chest high walls is an fps/rpg joke
					//instead of invisible walls developers will put like, chest high barriers
					//fences or whatever
					//that are theoretically easily jumped
					//chest high walls are what you use in shooters for cover
					
					for(int j = 0; j < current.chesthighwalls.length && (!foundwallx || !foundwally); j++){
						
						if(nextx >= current.chesthighwalls[j].x && nextx <= current.chesthighwalls[j].x + current.chesthighwalls[j].width && current.enemies[i].y >= current.chesthighwalls[j].y && current.enemies[i].y <= current.chesthighwalls[j].y + current.chesthighwalls[j].height){
							 foundwallx = true;
						}
						
						if(nexty >= current.chesthighwalls[j].y && nexty <= current.chesthighwalls[j].y + current.chesthighwalls[j].height && current.enemies[i].x >= current.chesthighwalls[j].x && current.enemies[i].x <= current.chesthighwalls[j].x + current.chesthighwalls[j].width){
							 foundwally = true;
						}
						
					}
					
					if(!foundwallx && !foundwally){
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
						}
					}
					
					else if(foundwallx && foundwally){
						current.enemies[i].x = current.enemies[i].finalx;
						current.enemies[i].y = current.enemies[i].finaly;
					}
					
					else if(foundwallx){

						if(yplus){
							 current.enemies[i].y = current.enemies[i].y + 10;
						 }
						 else{
							 current.enemies[i].y = current.enemies[i].y - 10;
						 }
					}
					
					else{
						
						if(xplus){
							 current.enemies[i].x = current.enemies[i].x + 10;
						 }
						 else{
							 current.enemies[i].x = current.enemies[i].x - 10;
						 }
					}
					
				}
				
				
				if(current.enemies[i].x == current.enemies[i].finalx && current.enemies[i].y == current.enemies[i].finaly){
					movedenemies++;
				}
				
			}
			
			for(int i = 0; i < current.enemies.length; i++){
				
				//checking again if the player is within line of sight
				//so yeah that's done both when the player moves and when the enemy moves
				
				if(current.enemies[i].turn == 2){
					for(int j = 1; j <= current.enemies[i].vision; j++){
						if(!current.enemies[i].spottedplayer && player.x >= current.enemies[i].x - 30*j && player.x <= current.enemies[i].x + 30*(j + 1) && player.y >= current.enemies[i].y + 30 && player.y <= current.enemies[i].y + 30*(j + 1)){
							current.enemies[i].spottedplayer = true;
							alert.play();
							current.enemies[i].turn = 3;
						}

					}
				}
				
			}
			
			for(int i = 0; i < current.enemies.length; i++){
				
				//if the player has been spotted set enemy's final coordinates to next to the player
				//this, uh, glitches
				//and I'm not sure why
				//for some reason the enemy will move 1/3 of a tile (10 px)
				//and end the turn
				//and then move to the goal spot on the next turn?
				//so...yeah
				
				if(current.enemies[i].turn == 3){
					
					if(current.enemies[i].x < player.x){
						current.enemies[i].finalx = player.x - 30;
					}
					
					else{
						current.enemies[i].finalx = player.x + 30;
					}
					
					current.enemies[i].finaly = player.y;

					current.enemies[i].turn = 4;
					
				}
				
				
			}
			
			
			for(int i = 0; i < current.enemies.length; i++){
				
				//exact same move code as two but slightly different circumstances
				
				if(current.enemies[i].turn == 4){
					boolean xplus = false;
					boolean yplus = false;
					
					int nextx = current.enemies[i].x - 10;
					int nexty = current.enemies[i].y - 10;
					
					if(current.enemies[i].finalx > current.enemies[i].x){
						nextx = current.enemies[i].x + 10;
						xplus = true;
					}
		
					if(current.enemies[i].finaly > current.enemies[i].y){
						nexty = current.enemies[i].y + 10;
						yplus = true;
					}
					
					boolean foundwallx = false;
					boolean foundwally = false;
					
					for(int j = 0; j < current.chesthighwalls.length && (!foundwallx || !foundwally); j++){
						
						if(nextx >= current.chesthighwalls[j].x && nextx <= current.chesthighwalls[j].x + current.chesthighwalls[j].width && current.enemies[i].y >= current.chesthighwalls[j].y && current.enemies[i].y <= current.chesthighwalls[j].y + current.chesthighwalls[j].height){
							 foundwallx = true;
						}
						
						if(nexty >= current.chesthighwalls[j].y && nexty <= current.chesthighwalls[j].y + current.chesthighwalls[j].height && current.enemies[i].x >= current.chesthighwalls[j].x && current.enemies[i].x <= current.chesthighwalls[j].x + current.chesthighwalls[j].width){
							 foundwally = true;
						}
						
					}
					
					if(!foundwallx && !foundwally){
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
							current.enemies[i].turn = 5;
						}
					}
					
					else if(foundwallx && foundwally){
						current.enemies[i].x = current.enemies[i].finalx;
						current.enemies[i].y = current.enemies[i].finaly;
					}
					
					else if(foundwallx){

						if(yplus){
							 current.enemies[i].y = current.enemies[i].y + 10;
						 }
						 else{
							 current.enemies[i].y = current.enemies[i].y - 10;
						 }
					}
					
					else{
						
						if(xplus){
							 current.enemies[i].x = current.enemies[i].x + 10;
						 }
						 else{
							 current.enemies[i].x = current.enemies[i].x - 10;
						 }
					
				}
				
				
					if(current.enemies[i].x == current.enemies[i].finalx && current.enemies[i].y == current.enemies[i].finaly){
						movedenemies++;
					}
				}
				
				
				
				
			}
			
			//once all enemies are done moving, go back to player's turn and reset all enemy turns to start
			
			if(movedenemies == current.enemies.length){
				turn = 0;
				for(int j = 0; j < current.enemies.length; j++){
					if(current.enemies[j].spottedplayer){
						current.enemies[j].turn = 3;
					}
					else{
						current.enemies[j].turn = 0;
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
		
		if(mapnumber == 1){
			player = new Player(6);
			player.x = 60;
			player.y = 60;
			player.finalx = 60;
			player.finaly = 60;
		}
		else if(mapnumber == 2){
			player = new Player(6);
			player.x = 150;
			player.y = 420;
			player.finalx = 150;
			player.finaly = 420;
		}
		else if(mapnumber == 3){
			player = new Player(6);
			player.x = 60;
			player.y = 60;
			player.finalx = 60;
			player.finaly = 60;
		}
		else if(mapnumber == 4){
			player = new Player(6);
			player.x = 60;
			player.y = 60;
			player.finalx = 60;
			player.finaly = 60;
		}
		else if(mapnumber == 5){
			player = new Player(6);
			player.x = 60;
			player.y = 60;
			player.finalx = 60;
			player.finaly = 60;
		}
		else{
			player = new Player(6);
			player.x = 60;
			player.y = 60;
			player.finalx = 60;
			player.finaly = 60;
		}
	}
	
}
