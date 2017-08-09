package game.state;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import game.Game;

public class SettingsState extends BasicGameState{
	
	protected String menu;
	protected Image demo;
	protected Image settings;
	private Font font2 = new Font("Courier", Font.BOLD, 30);
	private TrueTypeFont font = new TrueTypeFont(font2, true);
	private int currentChoice = 0;
	private int currentselection = 0;
	int M = 0;
	int B = 0;
	int V = 0;
	int F =0;
	private String[] options = {
			"Brightness",
			"Volume",
		    "Vsync",
		    "Framerate",
			"Reset to Default"
		};
	
		
	
	public SettingsState(String menu) {
		   this.menu = menu;// TODO Auto-generated constructor stub
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		// TODO Auto-generated method stub
		demo = new Image("res/00000.png");
		settings = new Image("res/settings.png");
		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		demo.draw(880,50);
		settings.draw(100,50);
		g.setFont(font);
		g.drawString("Settings", 190,70);
		for(int i = 0; i < options.length; i++) {
			if(i == currentChoice) {
				g.setColor(Color.red);
			}
			else {
				g.setColor(Color.white);
			}
			g.drawString(options[i], 300, 200 + i * 50);
		}
		switch(currentselection){
		case 0:
			g.drawString(" ", 420,100);
			if(B == 1) g.drawString("Brightness [Medium]", 420,100);
			else if(B == 2) g.drawString("Brightness [Low]", 420,100);
			else g.drawString("Brightness [High]", 420,100);
			break;
		case 1:
			if(M == 0){
				g.drawString("Volume 100%", 420,100);
			}else if(M == 2){
				g.drawString("Volume 0%", 420,100);
			}else{
				g.drawString("Volume 50%", 420,100);
			}
			break;
			
		case 2:
			if(V==1)g.drawString("VSync [on]", 420,100);
			else g.drawString("VSync [off]", 420,100);
			break;
		case 3:
			if(F == 1)g.drawString("Framerate [unlimited]", 420,100);
			else if(F == 2)g.drawString("Framerate [30]", 420,100);
			else g.drawString("Framerate [60]", 420,100);
			break;	
		case 4:
			g.drawString("Reset? ", 420,100);
			break;
		default:
			g.drawString("", 420,100);
		}
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int arg2) throws SlickException {
		// TODO Auto-generated method stub
	
		 if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)){
			 sbg.enterState(0);
		 }	    
		    
		    if(container.getInput().isKeyPressed(Input.KEY_UP)) {
		    	currentChoice--;
		    	currentselection--;
		    	if(currentChoice == -1) {
		    		currentChoice = 4;
		    		currentselection = 4;
		    	}
		    }
		    if(container.getInput().isKeyPressed(Input.KEY_DOWN)) {
		    	
		    	currentChoice++;
		    	currentselection++;
		    	if(currentChoice >4) {
		    		currentChoice = 0;
		    		currentselection = 0;
		    	}
		    }
		    if(container.getInput().isKeyPressed(Input.KEY_ENTER)){
		    	if(currentChoice == 0){
		    		if(LevelState.ALPHA == 0){
		    		    B = 1;	
		    		LevelState.ALPHA = 0.08f;
		    		}else if (LevelState.ALPHA == 0.08f){
		    			LevelState.ALPHA = 0.3f;
		    			B = 2;
		    		}else{
		    			LevelState.ALPHA = 0.0f;
		    			B = 3;
		    		}
	
		    	}  else if(currentChoice == 1) {
		    		if(LevelState.bgm.getVolume() == 1 ){
		    			LevelState.bgm.setVolume(0.5f);
		    			M = 1;	
		    		}else if(M == 1){
		    			LevelState.bgm.setVolume(0f);
		    			M = 2;
		    		}else{
		    			LevelState.bgm.setVolume(1f);
		    			M = 0;
		    		}	
		    		
		    	} else if(currentChoice == 2) {
		    		if(Game.VSYNC == false){
		    			Game.VSYNC = true;
		    			V=1;
		    		}else{
		    			Game.VSYNC = false;
		    			V=0;
		    		}
		    		
		    	} else if(currentChoice == 3) {
		    		if(Game.FPS == 60){
		    			Game.FPS = 30;
		    			F = 2;
		    		}else if(Game.FPS == 30){
		    			Game.FPS = 0;
		    			F = 1;
		    		}else{
		    			Game.FPS = 60;
		    			F = 0;
		    		}
		    		
		    	} else if(currentChoice == 4){
		    		LevelState.ALPHA = 0.0f;
		    		B = 3;
	    			Game.FPS = 60;
	    			F = 0;
	    			Game.VSYNC = false;
	    			V=0;
		    	}
		    }
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 3;
	}

}
