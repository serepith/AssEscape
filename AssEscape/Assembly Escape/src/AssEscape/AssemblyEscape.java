package AssEscape;

import java.util.logging.Level;
import java.util.logging.Logger;


import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class AssemblyEscape extends StateBasedGame{
	
	public static final int splash = 0;
	public static final int main = 1;
	public static final int game = 2;
	
	static SplashScreen splashscreen = new SplashScreen();
	Gameplay gameplay = new Gameplay();
	Menu menu = new Menu();
	
	public void changemap(int mapnumber){
		if(mapnumber == 1){
			gameplay.current = gameplay.level1;
		}
	}

	public AssemblyEscape(String title) {
		super(title);
		
	}

	public static void main(String[] args) {
		try
		{
			AssemblyEscape assesc = new AssemblyEscape("Assembly Escape");
			AppGameContainer appgc;
			appgc = new AppGameContainer(assesc);
			appgc.setIcon("img/icon.PNG");
			appgc.setShowFPS(false);
			appgc.setDisplayMode(800, 600, false);
			appgc.setMinimumLogicUpdateInterval(20);
			appgc.start();
			
		}
		catch (SlickException ex)
		{
			Logger.getLogger(AssemblyEscape.class.getName()).log(Level.SEVERE, null, ex);
		}


	}


	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		
		this.addState(splashscreen);
		this.addState(menu);
		this.addState(gameplay);
		
	}
	

}