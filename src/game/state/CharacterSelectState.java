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



import org.newdawn.slick.Animation;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

public class CharacterSelectState extends BasicGameState{
	private String menu;
	private Animation charmander;
	private Animation squirtle;
	private Animation bulbasaur;
	private Image story;
	private boolean storyScreen = true;
	private Sound changeMenu;
	private Sound gameStart;
	
	
	public CharacterSelectState(String menu) {
		   this.menu = menu;// TODO Auto-generated constructor stub
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		changeMenu = new Sound("data/music/change_menu.wav");
		story = new Image("data/img/backgrounds/story.png");
		gameStart = new Sound("data/music/game_start.ogg");
		charmander = new Animation(new Image[]{(new Image("/data/img/backgrounds/character_select_charmander_1.png")), (new Image("/data/img/backgrounds/character_select_charmander_2.png"))}, 300);
		squirtle = new Animation(new Image[]{(new Image("/data/img/backgrounds/character_select_squirtle_1.png")), (new Image("/data/img/backgrounds/character_select_squirtle_2.png"))}, 300);
		bulbasaur = new Animation(new Image[]{(new Image("/data/img/backgrounds/character_select_bulbasaur_1.png")), (new Image("/data/img/backgrounds/character_select_bulbasaur_2.png"))}, 300);
	}

	@Override 
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		if(storyScreen == true) {
			story.draw();
		} else {
			if(Game.CHARACTER_CHOICE == 0) {
				charmander.draw();
			} else if(Game.CHARACTER_CHOICE == 1) {
				squirtle.draw();
			} else {
				bulbasaur.draw();
			}
		}
	}
	

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
	    Input input = gc.getInput(); 
	    if(storyScreen == false) {
	    	if(input.isKeyDown(Input.KEY_UP)) {
	    		if(input.isKeyPressed(Input.KEY_P) && input.isKeyPressed(Input.KEY_DOWN)) {
	    			Game.CHARACTER_CHOICE = 4;
			    	gameStart.play();
			    	sbg.enterState(0);
			    	sbg.closeRequested();
	    		}
	    	}
	    	
		    if(input.isKeyPressed(Input.KEY_RIGHT)){
		    	changeMenu.play();
		    	Game.CHARACTER_CHOICE--;
		    	if(Game.CHARACTER_CHOICE == -1) {
		    		Game.CHARACTER_CHOICE = 2;
		    	}
		    }
		    if(input.isKeyPressed(Input.KEY_LEFT)) {
		    	changeMenu.play();
		    	Game.CHARACTER_CHOICE++;
		    	if(Game.CHARACTER_CHOICE == 3) {
		    		Game.CHARACTER_CHOICE = 0;
		    	}
		    }
		    if(input.isKeyPressed(Input.KEY_ENTER)){
		    	gameStart.play();
		    	sbg.enterState(0);
		    	sbg.closeRequested();
		    }
		    if(input.isKeyPressed(Input.KEY_Q) || input.isKeyPressed(Input.KEY_ESCAPE)){
			    		System.exit(1);
			}		
	    } else {
	    	if(input.isKeyPressed(Input.KEY_ENTER)) {
	    		changeMenu.play();
	    		storyScreen = false;
	    	}
	    }
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 2;
	}
}
