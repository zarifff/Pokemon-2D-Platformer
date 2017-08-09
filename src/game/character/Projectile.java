package game.character;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import game.Game;
import game.enums.Facing;
import game.physics.AABoundingRect;
import game.state.CharacterSelectState;

public class Projectile extends Character {

	private int projectileDamage;
	
	public Projectile(float x, float y) throws SlickException {
		super(x, y);
		setSprite(new Image("data/img/flames_transparent.png"));
        
        setMovingAnimation(new Image[]{new Image("data/img/flame_1.png"),new Image("data/img/flame_2.png"),
                                       new Image("data/img/flame_3.png"),new Image("data/img/flame_4.png")}
                                       ,125);
        boundingShape = new AABoundingRect(x+3, y, 26, 24);
        
        accelerationSpeed = 1.00f;
        maximumSpeed = 0.6f;
        maximumFallSpeed = 0.3f;
        decelerationSpeed = 0.001f;
        projectileDamage = -1;
	}
	
	
	public void setProjectileDamage(int i) {
		projectileDamage = i;
	}
	
	public void updateBoundingShape(){
        boundingShape.updatePosition(x+3,y);
    }
	
	public void decelerate(int delta) {
		
	}
	
	public void applyGravity(float gravity) {
		
	}
	
	public int getProjectileDamage() {
		return projectileDamage;
	}

}
