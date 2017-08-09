package game.character;

import java.util.Date;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import game.Game;
import game.enums.Facing;
import game.physics.AABoundingRect;

public class Mewtwo extends Enemy {
	
	protected int enemyHealth;
	protected boolean isDead;
	protected Date oldDate;

	public Mewtwo(float x, float y) throws SlickException {
		super(x, y);
		setSprite(new Image("data/img/flames_transparent.png"));
        
        setMovingAnimation(new Image[]{new Image("data/img/mewtwo_1.png"),new Image("data/img/mewtwo_2.png"),
                                       new Image("data/img/mewtwo_3.png")}
                                       ,175);
        boundingShape = new AABoundingRect(x+3, y, 36, 40);
        
        accelerationSpeed = 1f;
        maximumSpeed = 0.60f;
        maximumFallSpeed = 0.3f;
        decelerationSpeed = 1f;
        setFacing(Facing.LEFT);
        setEnemyHealth(32);
        oldDate = new Date();
        isDead = false;
        
	}
	
    public void jump(){
        if(onGround)
            y_velocity = -0.60f;
    }
	
	protected void setEnemyHealth(int h) {
		enemyHealth = h;
    }
	
	public void modHealth(int damage, Date newDate, Player player) {
		if(newDate.getTime() - oldDate.getTime() >= 200) {
    		enemyHealth += damage;
    	}
    	oldDate = new Date();
    	if(enemyHealth <= 0 && isDead != true) {
    		isDead = true;
    		player.modEvolutionPercentage(0);
    		enemyHealth = 0;
    		this.setMoving(false);
    		boundingShape = new AABoundingRect(0, 0, 0, 0);
    		Game.BOSS_DEAD = true;
    	}
	}
	
	public void updateBoundingShape(){
        boundingShape.updatePosition(x+3,y);
    }
	
    public boolean getIsDead() {
    	return isDead;
    }
    
    public int getEnemyHealth() {
    	return enemyHealth;
    }

    public AABoundingRect getAABoundingRect() {
    	return (AABoundingRect)boundingShape;
    }
}