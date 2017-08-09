package game.state;

import game.Game;

import game.character.Character;
import game.character.Player;
import game.controller.MouseAndKeyBoardPlayerController;
import game.controller.PlayerController;
import game.level.Level;
import game.level.tile.Tile;
import game.physics.AABoundingRect;
import game.physics.Physics;

import java.awt.Font;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
//import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

public class MainMenuState extends BasicGameState{
	private Image controlScreen;
	protected String menu;
    public static Music introMusic;
	Animation introA;
	int [] duration = {150,150,150,150,200,150,150,150,150};
	Image logo;
	private int currentChoice = 0;
	private String[] options = {
		"Start",
		"Controls",
		"Quit"
	};
	
	private boolean displayControls = false;
	
	private Font font2 = new Font("Courier", Font.BOLD, 48);
	private TrueTypeFont font = new TrueTypeFont(font2, true);
	private Sound changeMenu;
	
	public MainMenuState(String menu) {
		   this.menu = menu;// TODO Auto-generated constructor stub
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		changeMenu = new Sound("data/music/change_menu.wav");
		controlScreen = new Image("data/img/backgrounds/controls.png");
		introMusic = new Music("data/music/Pkmn_Korrina.ogg", true);
		Image [] intro = {new Image("data/img/TitleSplash1.png"),new Image("data/img/TitleSplash2.png"),new Image("data/img/TitleSplash3.png"),new Image("data/img/TitleSplash4.png"),new Image("data/img/TitleSplash5.png"),new Image("data/img/TitleSplash6.png"),new Image("data/img/TitleSplash7.png"),new Image("data/img/TitleSplash8.png"),new Image("data/img/TitleSplash9.png")};
		logo = new Image("res/Pokemon.png");
		introA = new Animation(intro,duration,false);
		introMusic.loop();
	}

	@Override 
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

		logo.draw(80,20);
		introA.draw();

		if(displayControls == true) {
			controlScreen.draw();
		} else {
			logo.draw(80, 20);
			introA.draw();
			g.setFont(font);
			for(int i = 0; i < options.length; i++) {
				if(i == currentChoice) {
					g.setColor(Color.red);
				}
				else {
					g.setColor(Color.white);
				}
				g.drawString(options[i], 550, 300 + i * 50);
			}
		}
	}
	


	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        //update/refreshes screen images/update method is for things to change.
	    Input input = gc.getInput(); 
	    
	    
	    if(input.isKeyPressed(Input.KEY_UP) && displayControls == false) {
	    	changeMenu.play();
	    	currentChoice--;
	    	if(currentChoice == -1) {
	    		currentChoice = 2;
	    	}
	    }
	    if(input.isKeyPressed(Input.KEY_DOWN) && displayControls == false) {
	    	changeMenu.play();
	    	currentChoice++;
	    	if(currentChoice == 3) {
	    		currentChoice = 0;
	    	}
	    }
	    if(input.isKeyPressed(Input.KEY_ENTER)){
	    	if(currentChoice == 0){
	    		changeMenu.play();
	    		introMusic.fade(1000, 0.3f, false);
	    		//introMusic.setVolume(2);
	    		sbg.enterState(2);
	    		sbg.closeRequested();
	    	} else if(currentChoice == 1) {
	    		changeMenu.play();
	    		displayControls = true;
	    		
	    	} else {
	    		if(displayControls == false) {
	    			System.exit(0);
	    		}
	    	}
	    }

	    if(input.isKeyPressed(Input.KEY_ESCAPE)) {
	    	changeMenu.play();
	    	displayControls = false;
	    }
	    introA.update(delta);
		input.clearKeyPressedRecord();
	}


	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 1;
	}
	



}


