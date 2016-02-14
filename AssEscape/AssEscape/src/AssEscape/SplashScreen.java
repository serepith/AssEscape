package AssEscape;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class SplashScreen extends BasicGameState {
	
	Image splash;
	int id = 0;

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		try{
			splash = new Image("img/splash.PNG");
		}
		
		catch(SlickException e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		g.drawImage(splash, 0, 0);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int sec) throws SlickException {
		
		if(gc.getInput().isMouseButtonDown(0)){
			game.enterState(1, new FadeOutTransition(Color.black), new FadeInTransition());
		}
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return id;
	}

}
