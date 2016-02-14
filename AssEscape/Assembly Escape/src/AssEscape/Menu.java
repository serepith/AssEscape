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
	int id = 1;
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		
		try{
			button1 = new Image("img/lvl1button.png");
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
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		if(container.getInput().getAbsoluteMouseX() > 100 && container.getInput().getAbsoluteMouseX() < 300 && container.getInput().getAbsoluteMouseY() > 100 && container.getInput().getAbsoluteMouseY() < 200 && container.getInput().isMousePressed(0)){
			((AssemblyEscape) game).changemap(1);
			game.enterState(2, new FadeOutTransition(Color.black), new FadeInTransition());
		}
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return id;
	}

}
