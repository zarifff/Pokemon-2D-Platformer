package game.state;


import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class VictoryState extends BasicGameState{
	
	private Music palletTown;
	private Image end;
	private String menu;
	
	public VictoryState(String menu) {
		   this.menu = menu;// TODO Auto-generated constructor stub
	}

	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		palletTown = new Music("data/music/pallet_town.wav", true);
		end = new Image("data/img/backgrounds/endscreen_1.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		end.draw();
		g.setColor(Color.white);
		g.drawString("You Have defeated Mewtwo and rescued Ash!! \n\nThanks For Playing!!", 450, 260);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if(!palletTown.playing()) {
			palletTown.play();
		}
		if(gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			System.exit(0);
		}
	}

	@Override
	public int getID() {
		return 5;
	}

}
