/* Project Title: Pokemon 2D Platformer (Level-based)
 * Created by: Mohammad Zariff Ahsham Ali - ID: 1530184642
 * 			   Mohammed Taufiqur Rahman   - ID: 1521285642
 * 
 * Libraries used: Slick2D based on LWJGL
 * Music, Art and sprites downloaded from the internet.
 * Some Physics-based algorithms were referenced from the internet.	
 */


package game;


import game.state.CharacterSelectState;
import game.state.GameOverState;
import game.state.LevelState;
import game.state.MainMenuState;
import game.state.SettingsState;
import game.state.VictoryState;

import java.util.Date;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame {
    
    //set the window width and then the height according to a aspect ratio
    public static final int     WINDOW_WIDTH  = 1280;
    public static final int     WINDOW_HEIGTH = WINDOW_WIDTH / 16 * 9;
    public static final boolean FULLSCREEN    = false;
    
    public static final float  SCALE         = (float) (1.25*((double)WINDOW_WIDTH/1280));
    public static final String GAME_NAME     = "Pokemon";
    public static boolean BOSS_DEAD = false;
    public static boolean IS_PROJECTILE_COLLIDE = true;
    public static boolean IS_ENEMY_PROJECTILE_COLLIDE  = true;
    public static Date PROJECTILE_DATE = new Date();
    public static boolean IS_FIRING = false;
    public static boolean HAS_FIRED = false;
    public static boolean IS_EVOLVING = false;
    public static long START_TIMER = System.currentTimeMillis();
    public static int CHARACTER_CHOICE = 0;
	public static boolean VSYNC = true;
	public static int FPS = 60;
    
    public Game() {
        super(GAME_NAME);
    }
 
    public void initStatesList(GameContainer gc) throws SlickException {
 
        //create a level state, this state will do the whole logic and rendering for individual levels
    	
    	addState(new LevelState("level_1_new"));
    	addState(new MainMenuState("Main_Menu"));
    	addState(new CharacterSelectState("Character_Select"));
    	addState(new SettingsState("Settings"));
    	addState(new GameOverState("Game Over!"));
    	addState(new VictoryState("You Won!!"));
        this.enterState(1);
        
       
    }
 
    public static void main(String[] args) throws SlickException {
         AppGameContainer app = new AppGameContainer(new Game());

         //set the size of the display to the width and height and full screen or not
         app.setDisplayMode(WINDOW_WIDTH, WINDOW_HEIGTH, FULLSCREEN);
         //this will attempt to create a frame rate of approximately 60 frames per second
         app.setTargetFrameRate(FPS);
         app.setVSync(VSYNC);
         
         app.start();
    }
 
}