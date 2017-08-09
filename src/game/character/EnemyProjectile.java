package game.character;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import game.enums.Facing;
import game.physics.AABoundingRect;

public class EnemyProjectile extends Character {

	private int projectileDamage;
	public EnemyProjectile(float x, float y) throws SlickException {
		super(x, y);
		setSprite(new Image("data/img/flames_transparent.png"));
        
        setMovingAnimation(new Image[]{new Image("data/img/sb_1.png"),new Image("data/img/sb_2.png"),
                                       new Image("data/img/sb_3.png"),new Image("data/img/sb_4.png")}
                                       ,125);
		
		
        boundingShape = new AABoundingRect(x+3, y, 26, 32);
        
        accelerationSpeed = 1.00f;
        maximumSpeed = 1.0f;
        maximumFallSpeed = 0.3f;
        decelerationSpeed = 0.001f;
        projectileDamage = -10;
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