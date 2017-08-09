package game.state;

import game.Game;
import game.character.Player;
import game.controller.MouseAndKeyBoardPlayerController;
import game.controller.PlayerController;
import game.level.Level;
import game.level.object.Objective;
import game.physics.AABoundingRect;
import game.physics.Physics;
import game.character.*;
import game.state.MainMenuState;

import java.awt.Font;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class LevelState extends BasicGameState {

    private Level  level;
    private String startinglevel;
    private Player player;
    private PlayerController playerController;
    private Physics physics;
    //private Enemy enemy;
    private Projectile projectile;
    static Music bgm;
    Image health_100,health_90,health_80,health_70,health_60,health_50,health_40,health_30,health_20,health_10,health_0;
    private boolean doItOnce = true;
    private float alpha = 0;
    public static float ALPHA = 0;
    public static float R = 0.0f;
    public static float B = 0.0f;
    public static float G = 0.0f;
    private int currentChoice = 0;
    private Font font2 = new Font("Courier", Font.BOLD, 20);
	private TrueTypeFont font = new TrueTypeFont(font2, true);
	private String[] options = {
			"Resume",
			"Settings",
			"Quit Game"
		};
	public static float VOLUME = 0.7f;
	private Sound change_menu;
	private Sound gameOver;
    private Image hud;
	
    public LevelState(String startingLevel){
        this.startinglevel = startingLevel;
    }
    
    public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
        
    	hud = new Image("data/img/hud_2.png");
    	change_menu = new Sound("data/music/change_menu.wav");
    	gameOver = new Sound("data/music/game_over.wav"); 
       //at the start of the game we don't have a player yet
        projectile = new Projectile(0, 0 );
    	player = new Player(32, 220, projectile);
        playerController = new MouseAndKeyBoardPlayerController(player, projectile);
        //once we initialize our level, we want to load the right level
        level = new Level(startinglevel, player, projectile);
        
        level.addLevelObject(new Objective(4665,700));


        physics = new Physics();
        bgm = new Music("/data/Music/Level_Theme.ogg", true);
        
        health_100 = new Image("/data/img/HP/hp100.png");
        health_90 = new Image("/data/img/HP/health90.png");
        health_80 = new Image("/data/img/HP/health80.png");
        health_70 = new Image("/data/img/HP/health70.png");
        health_60 = new Image("/data/img/HP/health60.png");
        health_50 = new Image("/data/img/HP/health50.png");
        health_40 = new Image("/data/img/HP/health40.png");
        health_30 = new Image("/data/img/HP/health30.png");
        health_20 = new Image("/data/img/HP/health20.png");
        health_10 = new Image("/data/img/HP/health10.png");
        health_0 = new Image("/data/img/HP/health0.png");
        
        
    }

    public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
        //every update we have to handle the input from the player
    	if(player.getY() >= 1350 || player.getIsDead()) { //////////////////If the player falls too far down, he 'dies' and enters the game over screen.
    		bgm.setVolume(0.15f);
    		gameOver.play();
    		sbg.enterState(4);
    		sbg.closeRequested();
    	}
    	if(Game.BOSS_DEAD == true) {
    		bgm.stop();
    		sbg.enterState(5);
    		sbg.closeRequested();
    	}


        playerController.handleInput(container.getInput(), delta);
        if(doItOnce) { //Initialize the player according to character choice once only.
        	if(Game.CHARACTER_CHOICE == 0) {
    	        player.setSprite(new Image("data/img/characters/player/charmander_2.png"));
    	        
    	        player.setMovingAnimation(new Image[]{new Image("data/img/characters/player/charmander_1.png"),new Image("data/img/characters/player/charmander_2.png"),
    	                                       new Image("data/img/characters/player/charmander_3.png"),new Image("data/img/characters/player/charmander_2.png")}
    	                                       ,125);
    	
    	        player.setAttackAnimationRight(new Animation(new Image[]{new Image("data/img/characters/player/charmander_attack.png")}, 300));
    	        player.setAttackAnimationLeft(new Animation(new Image[]{new Image("data/img/characters/player/charmander_attack.png").getFlippedCopy(true,  false)}, 300));
    	
    	        player.setY(player.getY()-12);
    	        player.setBoundingShape(new AABoundingRect(player.getX(), player.getY(), 26, 28));
    	        player.setAccelerationSpeed(0.001f);
    	        player.setMaximumSpeed(0.15f);
    	        player.setMaximumFallSpeed(0.3f);
    	        player.setDecelerationSpeed(0.001f);
        	} else if(Game.CHARACTER_CHOICE == 1) {
    	        player.setSprite(new Image("data/img/characters/player/squirtle_2.png"));
    	        projectile.setMovingAnimation(new Image[]{new Image("data/img/water_1.png"), new Image("data/img/water_2.png"), new Image("data/img/water_3.png")}, 120);
    	        player.setMovingAnimation(new Image[]{new Image("data/img/characters/player/squirtle_1.png"),new Image("data/img/characters/player/squirtle_2.png"),
    	                                       new Image("data/img/characters/player/squirtle_3.png"),new Image("data/img/characters/player/squirtle_2.png")}
    	                                       ,125);
    	
    	        player.setAttackAnimationRight(new Animation(new Image[]{new Image("data/img/characters/player/squirtle_attack.png")}, 300));
    	        player.setAttackAnimationLeft(new Animation(new Image[]{new Image("data/img/characters/player/squirtle_attack.png").getFlippedCopy(true,  false)}, 300));
    	
    	        player.setY(player.getY()-12);
    	        player.setBoundingShape(new AABoundingRect(player.getX(), player.getY(), 32, 28));

    	        player.setAccelerationSpeed(0.001f);
    	        player.setMaximumSpeed(0.15f);
    	        player.setMaximumFallSpeed(0.3f);
    	        player.setDecelerationSpeed(0.001f);
        	} else if(Game.CHARACTER_CHOICE == 2){
    	        player.setSprite(new Image("data/img/characters/player/bulbasaur_3.png"));
    	        projectile.setMovingAnimation(new Image[]{new Image("data/img/leaf_1.png"), new Image("data/img/leaf_2.png"), new Image("data/img/leaf_3.png")}, 100);
    	        player.setMovingAnimation(new Image[]{new Image("data/img/characters/player/bulbasaur_1.png"),new Image("data/img/characters/player/bulbasaur_2.png"),
    	                                       new Image("data/img/characters/player/bulbasaur_3.png"),new Image("data/img/characters/player/bulbasaur_1.png")}
    	                                       ,125);
    	
    	        player.setAttackAnimationRight(new Animation(new Image[]{new Image("data/img/characters/player/bulbasaur_attack.png")}, 300));
    	        player.setAttackAnimationLeft(new Animation(new Image[]{new Image("data/img/characters/player/bulbasaur_attack.png").getFlippedCopy(true,  false)}, 300));
    	
    	        player.setY(player.getY()-12);
    	        player.setBoundingShape(new AABoundingRect(player.getX(), player.getY(), 28, 24));

    	        player.setAccelerationSpeed(0.001f);
    	        player.setMaximumSpeed(0.15f);
    	        player.setMaximumFallSpeed(0.3f);
    	        player.setDecelerationSpeed(0.001f);
        	} else {
        		player.setSprite(new Image("data/img/characters/player/pichu_idle.png"));
    	        projectile.setMovingAnimation(new Image[]{new Image("data/img/electric_1.png"), new Image("data/img/electric_2.png")}, 100);
    	        player.setMovingAnimation(new Image[]{new Image("data/img/characters/player/pichu_1.png"),new Image("data/img/characters/player/pichu_2.png")} ,100);
    	
    	        player.setAttackAnimationRight(new Animation(new Image[]{new Image("data/img/characters/player/pichu_attack.png")}, 300));
    	        player.setAttackAnimationLeft(new Animation(new Image[]{new Image("data/img/characters/player/pichu_attack.png").getFlippedCopy(true,  false)}, 300));
    	
    	        player.setY(player.getY()-12);
    	        player.setBoundingShape(new AABoundingRect(player.getX(), player.getY(), 21, 33));
    	        player.setAccelerationSpeed(0.001f);
    	        player.setMaximumSpeed(0.15f);
    	        player.setMaximumFallSpeed(0.3f);
    	        player.setDecelerationSpeed(0.001f);
        	}
        	doItOnce = false;
        }

        if(Game.HAS_FIRED == true) {
        	if(System.currentTimeMillis() - Game.START_TIMER <= 300) {
        		Game.IS_FIRING = true;
        		player.setMoving(false);
        	} else {
        		Game.IS_FIRING = false;
        		Game.HAS_FIRED = false;
        		player.setMoving(true);
        	}
        }
        physics.handlePhysics(level, delta, level.getEnemyProjectile());
        if(!bgm.playing() && (MainMenuState.introMusic.playing() == false)) {
        	bgm.loop();
        	bgm.setVolume(VOLUME);
        } else {
        	MainMenuState.introMusic.fade(delta, alpha, doItOnce);
        	if(!bgm.playing()) {
        		bgm.loop();
        		bgm.setVolume(VOLUME);
        	}
        }
        
        if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
        	container.setPaused(!container.isPaused());
        }
            	if(container.getInput().isKeyPressed(Input.KEY_UP) && container.isPaused()) {
            		change_menu.play();
            		currentChoice--;
            		if(currentChoice == -1) {
            			currentChoice = 2;
            		}
            	}
            	if(container.getInput().isKeyPressed(Input.KEY_DOWN) && container.isPaused()) {
            		change_menu.play();
            		currentChoice++;
            		if(currentChoice == 3) {
            			currentChoice = 0;
            		}
            	}
            	if(container.getInput().isKeyPressed(Input.KEY_ENTER)) {	
            		if(currentChoice == 0){
            			container.setPaused(!container.isPaused());
	    		
            		} else if(currentChoice == 1) {
            			sbg.enterState(3);
	    		
            		} else {
            			System.exit(0);
            		}
	    
            	}  
            
         if(container.getInput().isKeyDown(Input.KEY_LSHIFT) && container.getInput().isKeyDown(Input.KEY_RSHIFT)) {
        	 projectile.setProjectileDamage(-20); ///////////////////// CHEAT CODE :P
         }
    }
    
    public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
        g.scale(Game.SCALE, Game.SCALE);
        g.setColor(Color.white);
        g.setFont(font);
        
        //render the level
        level.render();
        
    	if(player.getX() >= 5500 && player.getY() >= 200) {
        	g.drawString("Mewtwo's Health: " + level.getMewtwo().getEnemyHealth() + "/32", 700, 20);
    	}
    	
        if (container.isPaused()) //pause menu stuff
        {
        	  Rectangle rect = new Rectangle (0, 0, Game.WINDOW_WIDTH, Game.WINDOW_HEIGTH);
        	    g.setColor(new Color (0.2f, 0.2f, 0.2f, alpha));
        	    g.fill(rect);
        	 
        	    for(int i = 0; i < options.length; i++) {
        	    	
        			if(i == currentChoice) {
        				g.setColor(Color.red);
        				
        			}
        			else {
        				g.setColor(Color.white);
        			}
        			g.drawString(options[i], 450, 200 + i * 50);
        			container.getInput().clearKeyPressedRecord();

        	    }

        	    if (alpha < 0.5f)
        	        alpha += 0.01f;
        	}
        	else
        	{
        	    if (alpha > 0)
        	        alpha -= 0.01f;
        }
       
        switch(player.getHealth() / 10){ //Health bar stuff
	        case 10:
	      	  	health_100.draw(0, 0);
	      	  	break;
	        case  9:  
	        	health_90.draw(0, 0);
	      	break;
	        case  8:  
	      	  	health_80.draw(0, 0);
	        	break;
	        case  7:  
	        	health_70.draw(0, 0);
	        	break;
	        case  6:  
	      	  	health_60.draw(0, 0);
	        	break;
	        case  5:  
	      	  	health_50.draw(0, 0);
	        	break;
	        case  4:  
	      	  	health_40.draw(0, 0);
	        	break;
	        case  3:  
	      	  	health_30.draw(0, 0);
	        	break;
	        case  2:  
	      	  	health_20.draw(0, 0);
	        	break;
	        case  1:  
	      	  	health_10.draw(0, 0);
	          	break;	
	        default:  
	      	  	health_0.draw(0, 0);
	          	break;	  	
        }

        hud.draw(0, 68);
        if(player.getHealth() <= 50 && player.getHealth() > 25 ) {
        	g.setColor(Color.yellow);
        	g.drawString(player.getHealth() + "/100", 72, 27);
        } else if(player.getHealth() <= 25 && player.getHealth() > 10) {
        	g.setColor(Color.orange);
        	g.drawString(player.getHealth() + "/100", 72, 27);
        } else if(player.getHealth() >= 0 && player.getHealth() <= 10) {
        	g.setColor(Color.red);
        	g.drawString(player.getHealth() + "/100", 72, 27);
        } else {
        	g.setColor(Color.white);
        	g.drawString(player.getHealth() + "/100", 72, 27);
        }
        g.setColor(Color.white);
        //g.drawString("X: " + level.getEnemy().getX() + "  xV: " + level.getEnemy().getXVelocity(), 20, 80); ////////////////////////////////////////DEBUGGING
        //g.drawString("Y: " + level.getEnemy().getY() + "  yV: " + level.getEnemy().getYVelocity(), 20, 100);
        //g.drawString("Player X: " + player.getX(), 20, 60);
        //g.drawString("Player Y: " + player.getY(), 20, 80);
        if(player.getEvolutionPercentage() == 100) {
        	g.setColor(Color.red);
        	g.drawString("Evo: " + player.getEvolutionPercentage(), 32, 72);
        } else {
        	g.drawString("Evo: " + player.getEvolutionPercentage(), 32, 72);
        }
        g.setColor(Color.white);
        //g.drawString("Can Evolve: " + player.getCanEvolve(), 20, 100);
        //g.drawString("Player Shape X: " + player.getAABoundingRect().height, 20, 140);  //MORE DEBUGGINGGGGGG
        //g.drawString("Player Shape Y: " + player.getAABoundingRect().width, 20, 160);
        //g.drawString("Player Shape Pos X: " + player.getAABoundingRect().getX(), 20, 180);
        //g.drawString("Player Shape Pos Y: " + player.getAABoundingRect().getY(), 20, 200);
        //g.drawString("Character Choice: " + Game.CHARACTER_CHOICE, 20, 220);
        
        Rectangle screen = new Rectangle (0, 0, Game.WINDOW_WIDTH, Game.WINDOW_HEIGTH);
	    g.setColor(new Color (R, B, G, ALPHA));
	    g.fill(screen);
	    container.setVSync(Game.VSYNC);
	    container.setTargetFrameRate(Game.FPS);
	

        //g.drawString("Projectile X: " + projectile.getX(), 20, 100);
        //g.drawString("Mewtwo Shape Height: " + level.getMewtwo().getAABoundingRect().height, 20, 100);
        //g.drawString("Mewtwo Shape Width: " + level.getMewtwo().getAABoundingRect().width, 20, 120); //AHH DEBUGGINGG
        //g.drawString("Mewtwo Shape X: " + level.getMewtwo().getAABoundingRect().getX(), 20, 100);
        //g.drawString("Mewtwo Shape Y: " + level.getMewtwo().getAABoundingRect().getY(), 20, 120);

    }
    

    public int getID() {
        //this is the id for changing states
        return 0;
    }

}