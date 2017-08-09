package game.character;

import java.util.Date;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import game.enums.Facing;
import game.physics.AABoundingRect;

public class Enemy extends Character {
	
	protected int enemyHealth;
	protected boolean isDead;
	protected Date oldDate;

	public Enemy(float x, float y) throws SlickException {
		super(x, y);
		setSprite(new Image("data/img/flames_transparent.png"));
        
        setMovingAnimation(new Image[]{new Image("data/img/characters/player/mightyena_1.png"),new Image("data/img/characters/player/mightyena_2.png"),
                                       new Image("data/img/characters/player/mightyena_3.png"),new Image("data/img/characters/player/mightyena_2.png")}
                                       ,100);
        boundingShape = new AABoundingRect(x+3, y, 26, 27);
        
        accelerationSpeed = 1f;
        maximumSpeed = 0.3f;
        maximumFallSpeed = 0.75f;
        decelerationSpeed = 1f;
        setFacing(Facing.LEFT);
        setEnemyHealth(7);
        oldDate = new Date();
        isDead = false;
        
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
    		player.modEvolutionPercentage(20);
    		enemyHealth = 0;
    		this.setMoving(false);
    		boundingShape = new AABoundingRect(0, 0, 0, 0);
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
}
