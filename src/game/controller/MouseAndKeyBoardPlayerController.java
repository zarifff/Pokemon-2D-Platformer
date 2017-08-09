package game.controller;

import game.Game;
import game.character.*;
import game.enums.Facing;


import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;



public class MouseAndKeyBoardPlayerController extends PlayerController {

	private Sound attack;

    public MouseAndKeyBoardPlayerController(Player player, Projectile projectile) {
        super(player, projectile);
        try {
			attack  = new Sound("data/music/attack.wav");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void handleInput(Input i, int delta) {
        //handle any input from the keyboard
        handleKeyboardInput(i,delta);
    }
    
    private void handleKeyboardInput(Input i, int delta) {

        //we can both use the WASD or arrow keys to move around
        if((i.isKeyDown(Input.KEY_A) || i.isKeyDown(Input.KEY_LEFT)) && (Game.IS_FIRING == false)){
            player.moveLeft(delta);
        }else if((i.isKeyDown(Input.KEY_D) || i.isKeyDown(Input.KEY_RIGHT)) && (Game.IS_FIRING == false)){
            player.moveRight(delta);
        }else{
            //we dont move if we don't press left or right, this will have the effect that our player decelerates
            player.setMoving(false);
        }
        
        if(i.isKeyPressed(Input.KEY_SPACE)){
            player.jump();
        }
        if(i.isKeyPressed(Input.KEY_X)) {
        	if((Game.IS_PROJECTILE_COLLIDE == true || (Math.abs(projectile.getX() - player.getX()) > 800)) && (Game.IS_FIRING == false)) {
        		Game.HAS_FIRED = true;
        		Game.START_TIMER = System.currentTimeMillis();

        		attack.play();
        		projectile.setFacing(player.getFacing());
        		if(player.getEvolutionStage() == 2 && Game.CHARACTER_CHOICE != 4) {
        			projectile.setY(player.getY() + 16);
        		} else {
        			projectile.setY(player.getY());
        		}
        		if(player.getFacing() == Facing.LEFT) {
        			projectile.setX(player.getX() - 20);
        			       			
        		} else {
        			projectile.setX(player.getX() + 20);
        			
        		}
        		Game.IS_PROJECTILE_COLLIDE = false;
        	} 

        	
        }
        if(i.isKeyPressed(Input.KEY_Z)) {
        	if(player.getCanEvolve()) {
        		try {
        			Game.IS_EVOLVING = true;
					player.hasEvolved();
				} catch (SlickException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        }

    }
}
