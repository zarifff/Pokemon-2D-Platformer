package game.state;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameOverState extends BasicGameState{
	private String menu;
	private Image screen;	
	
	public GameOverState(String menu) {
		   this.menu = menu;// TODO Auto-generated constructor stub
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		screen = new Image("data/img/backgrounds/game_over.png");
		
	}

	@Override 
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		screen.draw();
	}
	

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
	    Input input = gc.getInput(); 

	    if(input.isKeyDown(Input.KEY_ENTER) || input.isKeyDown(Input.KEY_ESCAPE)) {
	    	System.exit(0);
	    }
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 4;
	}
}