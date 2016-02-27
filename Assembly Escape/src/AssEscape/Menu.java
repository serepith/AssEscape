package AssEscape;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Menu extends BasicGameState{
	
	Image button1;
	Image button2;
	Image button3;
	Image button4;
	Image button5;
	Image button6;
	int id = 1;
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		
		try{
			button1 = new Image("img/lvl1button.png");
			button2 = new Image("img/lvl2button.png");
			button3 = new Image("img/lvl3button.png");
			button4 = new Image("img/lvl4button.png");
			button5 = new Image("img/lvl5button.png");
			button6 = new Image("img/lvl6button.png");
		}
		
		catch(SlickException e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

		g.setFont(new TrueTypeFont(new Font("Courier New", Font.PLAIN, 50), true));
		g.drawString("MAIN MENU", 250, 10);
		g.drawImage(button1, 100, 100);
		g.drawImage(button2, 500, 100);
		g.drawImage(button3, 100, 260);
		g.drawImage(button4, 500, 260);
		g.drawImage(button5, 100, 420);
		g.drawImage(button6, 500, 420);
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
		//if ya click the mouse
		//1. call changemap which calls choosemap in gameplay...which is stupid
		//2. change state
		
		if(container.getInput().getAbsoluteMouseX() > 100 && container.getInput().getAbsoluteMouseX() < 300 && container.getInput().getAbsoluteMouseY() > 100 && container.getInput().getAbsoluteMouseY() < 200 && container.getInput().isMousePressed(0)){
			((AssemblyEscape) game).changemap(1);
			game.enterState(2, new FadeOutTransition(Color.black), new FadeInTransition());
		}
		
		else if(container.getInput().getAbsoluteMouseX() > 500 && container.getInput().getAbsoluteMouseX() < 700 && container.getInput().getAbsoluteMouseY() > 100 && container.getInput().getAbsoluteMouseY() < 200 && container.getInput().isMousePressed(0)){
			((AssemblyEscape) game).changemap(2);
			game.enterState(2, new FadeOutTransition(Color.black), new FadeInTransition());
		}
		
		else if(container.getInput().getAbsoluteMouseX() > 100 && container.getInput().getAbsoluteMouseX() < 300 && container.getInput().getAbsoluteMouseY() > 260 && container.getInput().getAbsoluteMouseY() < 360 && container.getInput().isMousePressed(0)){
			((AssemblyEscape) game).changemap(3);
			game.enterState(2, new FadeOutTransition(Color.black), new FadeInTransition());
		}
		
		else if(container.getInput().getAbsoluteMouseX() > 500 && container.getInput().getAbsoluteMouseX() < 600 && container.getInput().getAbsoluteMouseY() > 260 && container.getInput().getAbsoluteMouseY() < 360 && container.getInput().isMousePressed(0)){
			((AssemblyEscape) game).changemap(4);
			game.enterState(2, new FadeOutTransition(Color.black), new FadeInTransition());
		}
		
		else if(container.getInput().getAbsoluteMouseX() > 100 && container.getInput().getAbsoluteMouseX() < 300 && container.getInput().getAbsoluteMouseY() > 420 && container.getInput().getAbsoluteMouseY() < 520 && container.getInput().isMousePressed(0)){
			((AssemblyEscape) game).changemap(5);
			game.enterState(2, new FadeOutTransition(Color.black), new FadeInTransition());
		}
		
		else if(container.getInput().getAbsoluteMouseX() > 500 && container.getInput().getAbsoluteMouseX() < 700 && container.getInput().getAbsoluteMouseY() > 420 && container.getInput().getAbsoluteMouseY() < 520 && container.getInput().isMousePressed(0)){
			((AssemblyEscape) game).changemap(6);
			game.enterState(2, new FadeOutTransition(Color.black), new FadeInTransition());
		}
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return id;
	}

}
