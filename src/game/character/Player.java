package game.character;

import game.Game;
import game.controller.MouseAndKeyBoardPlayerController;
import game.enums.Facing;
import game.physics.AABoundingRect;
import game.state.CharacterSelectState;
import game.character.Projectile;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;

import java.util.Date;



public class Player extends Character {
	
	private int health;
	private boolean isDead = false;
	private Date oldDate;
	private Date oldDate2;
	private Sound jump;
	private static int evolutionStage;
	private int evolutionPercentage;
	private boolean canEvolve = false;
	private Projectile projectile;
	private Animation attackAnimationRight;
	private Animation attackAnimationLeft;
	private Animation evolutionAnimation;
	private Sound sparkle = new Sound("data/music/sparkle.wav");
    private Sound punch = new Sound("/data/music/punch.wav");

	
	
	//private int choice = CharacterSelectState.CHARACTER_CHOICE;
	
    public Player(float x, float y, Projectile projectile) throws SlickException{
        super(x,y);
        this.projectile = projectile;
        setSprite(new Image("data/img/characters/player/charmander_2.png"));
        SpriteSheet evolutionSprite = new SpriteSheet(new Image("data/img/sprite2.png"), 48, 48);
        evolutionAnimation = new Animation(evolutionSprite, 40);
	        
	    setMovingAnimation(new Image[]{new Image("data/img/characters/player/charmander_1.png"),new Image("data/img/characters/player/charmander_2.png"),
	                                       new Image("data/img/characters/player/charmander_3.png"),new Image("data/img/characters/player/charmander_2.png")}
	                                       ,125);
	
	    attackAnimationRight = new Animation(new Image[]{new Image("data/img/characters/player/charmander_attack.png")}, 300);
	    attackAnimationLeft = new Animation(new Image[]{new Image("data/img/characters/player/charmander_attack.png").getFlippedCopy(true,  false)}, 300);
	
	    setY(y-12);
	    boundingShape = new AABoundingRect(x, y, 26, 28);
	        
	    accelerationSpeed = 0.001f;
	    maximumSpeed = 0.15f;
	    maximumFallSpeed = 0.3f;
	    decelerationSpeed = 0.001f;
	       
        
        evolutionStage = 0;
        evolutionPercentage = 0;
        oldDate = new Date();
        oldDate2 = new Date();
        this.setHealth(100);
        this.jump = new Sound("data/music/jump_wav.wav");     
      
    }   
    
    
    public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public void setOldDate(Date oldDate) {
		this.oldDate = oldDate;
	}



	public void setOldDate2(Date oldDate2) {
		this.oldDate2 = oldDate2;
	}


	public void setJump(Sound jump) {
		this.jump = jump;
	}



	public void setEvolutionPercentage(int evolutionPercentage) {
		this.evolutionPercentage = evolutionPercentage;
	}



	public void setCanEvolve(boolean canEvolve) {
		this.canEvolve = canEvolve;
	}



	public void setProjectile(Projectile projectile) {
		this.projectile = projectile;
	}


	public void setAttackAnimationRight(Animation attackAnimationRight) {
		this.attackAnimationRight = attackAnimationRight;
	}



	public void setAttackAnimationLeft(Animation attackAnimationLeft) {
		this.attackAnimationLeft = attackAnimationLeft;
	}

	public AABoundingRect getAABoundingRect() {
    	return (AABoundingRect)boundingShape;
    }

	public void hasEvolved() throws SlickException {
		if(Game.CHARACTER_CHOICE == 0) {
	    	if(evolutionStage == 0) {
	    		//projectile.setMovingAnimation(images, frameDuration);
	    		sparkle.play();
	    		evolutionPercentage = 0;
	            accelerationSpeed = 0.002f;
	            setY(y-12);
	            boundingShape = new AABoundingRect(x, y, 32, 32);
	            maximumSpeed = 0.2f;
	            maximumFallSpeed = 0.3f;
	            decelerationSpeed = 0.002f;
	            projectile.setProjectileDamage(-2);
	            setSprite(new Image("data/img/characters/player/charmeleon.png"));
	            setMovingAnimation(new Image[]{new Image("data/img/characters/player/charmeleon_1.png"),new Image("data/img/characters/player/charmeleon_2.png"),
	                    new Image("data/img/characters/player/charmeleon_3.png"),new Image("data/img/characters/player/charmeleon_2.png")}
	                    ,100);
	            attackAnimationRight = new Animation(new Image[]{new Image("data/img/characters/player/charmeleon_attack.png")}, 300);
	            attackAnimationLeft = new Animation(new Image[]{new Image("data/img/characters/player/charmeleon_attack.png").getFlippedCopy(true,  false)}, 300);
	            evolutionStage=1;
	            setCanEvolve();
	    	} else 	if(evolutionStage == 1) {
	    		sparkle.play();
	    		evolutionStage=2;
	    		setY(y-22);
	    		boundingShape = new AABoundingRect(x, y, 52, 53);
	    		//setX(x+8); setY(y-12);
	    		evolutionPercentage = 0;
	            accelerationSpeed = 0.003f;
	            maximumSpeed = 0.35f;
	            maximumFallSpeed = 0.2f;
	            decelerationSpeed = 0.003f;
	            projectile.setProjectileDamage(-3);
	            setSprite(new Image("data/img/characters/player/charizard_2.png"));
	            setMovingAnimation(new Image[]{new Image("data/img/characters/player/charizard_1.png"),new Image("data/img/characters/player/charizard_2.png"),
	                    new Image("data/img/characters/player/charizard_3.png"),new Image("data/img/characters/player/charizard_2.png")}
	                    ,125);
	            attackAnimationRight = new Animation(new Image[]{new Image("data/img/characters/player/charizard_attack.png")}, 300);
	            attackAnimationLeft = new Animation(new Image[]{new Image("data/img/characters/player/charizard_attack.png").getFlippedCopy(true,  false)}, 300);
	            setCanEvolve();
	    	}
		} else if( Game.CHARACTER_CHOICE == 1) {
			if(evolutionStage == 0) {
				sparkle.play();
				evolutionPercentage = 0;
	            accelerationSpeed = 0.002f;
	            setY(y-12);
	            boundingShape = new AABoundingRect(x, y, 30, 32);
	            maximumSpeed = 0.2f;
	            maximumFallSpeed = 0.3f;
	            decelerationSpeed = 0.002f;
	            projectile.setProjectileDamage(-2);
	            setSprite(new Image("data/img/characters/player/wartortle_right_2.png"));
	            setMovingAnimation(new Image[]{new Image("data/img/characters/player/wartortle_right_1.png"),new Image("data/img/characters/player/wartortle_right_2.png"),
	                    new Image("data/img/characters/player/wartortle_right_3.png"),new Image("data/img/characters/player/wartortle_right_2.png")}
	                    ,125);
	            this.movingAnimations.put(Facing.LEFT, new Animation(new Image[]{new Image("data/img/characters/player/wartortle_left_1.png"),new Image("data/img/characters/player/wartortle_left_2.png"),
	                    new Image("data/img/characters/player/wartortle_left_3.png"),new Image("data/img/characters/player/wartortle_left_2.png")}
	                    ,125));
	            attackAnimationRight = new Animation(new Image[]{new Image("data/img/characters/player/wartortle_attack_right.png")}, 300);
	            attackAnimationLeft = new Animation(new Image[]{new Image("data/img/characters/player/wartortle_attack_left.png")}, 300);
	            evolutionStage=1;
	            setCanEvolve();
			} else if (evolutionStage == 1) {
				sparkle.play();
				evolutionStage=2;
	    		setY(y-22);
	    		boundingShape = new AABoundingRect(x, y, 53, 52);
	    		evolutionPercentage = 0;
	            accelerationSpeed = 0.003f;
	            maximumSpeed = 0.35f;
	            maximumFallSpeed = 0.45f;
	            decelerationSpeed = 0.003f;
	            projectile.setProjectileDamage(-2);
	            setSprite(new Image("data/img/characters/player/blastoise_2.png"));
	            setMovingAnimation(new Image[]{new Image("data/img/characters/player/blastoise_1.png"),new Image("data/img/characters/player/blastoise_2.png"),
	                    new Image("data/img/characters/player/blastoise_3.png"),new Image("data/img/characters/player/blastoise_2.png")}
	                    ,125);
	            attackAnimationRight = new Animation(new Image[]{new Image("data/img/characters/player/blastoise_attack.png")}, 300);
	            attackAnimationLeft = new Animation(new Image[]{new Image("data/img/characters/player/blastoise_attack.png").getFlippedCopy(true,  false)}, 300);
	            setCanEvolve();
			}
		} else if(Game.CHARACTER_CHOICE == 2){
			if(evolutionStage == 0) {
				sparkle.play();
				evolutionPercentage = 0;
	            accelerationSpeed = 0.002f;
	            setY(y-12);
	            boundingShape = new AABoundingRect(x, y, 37, 32);
	            maximumSpeed = 0.2f;
	            maximumFallSpeed = 0.3f;
	            decelerationSpeed = 0.002f;
	            projectile.setProjectileDamage(-1);
	            setSprite(new Image("data/img/characters/player/ivysaur_2.png"));
	            setMovingAnimation(new Image[]{new Image("data/img/characters/player/ivysaur_1.png"),new Image("data/img/characters/player/ivysaur_2.png"),
	                    new Image("data/img/characters/player/ivysaur_3.png"),new Image("data/img/characters/player/ivysaur_2.png")}
	                    ,125);

	            attackAnimationRight = new Animation(new Image[]{new Image("data/img/characters/player/ivysaur_attack.png")}, 300);
	            attackAnimationLeft = new Animation(new Image[]{new Image("data/img/characters/player/ivysaur_attack.png").getFlippedCopy(true,  false)}, 300);
	            evolutionStage=1;
	            setCanEvolve();
			} else if (evolutionStage == 1) {
				sparkle.play();
				evolutionStage=2;
	    		setY(y-22);
	    		boundingShape = new AABoundingRect(x, y, 57, 53);
	    		//setX(x+8); setY(y-12);
	    		evolutionPercentage = 0;
	            accelerationSpeed = 0.003f;
	            maximumSpeed = 0.35f;
	            maximumFallSpeed = 0.41f;
	            decelerationSpeed = 0.003f;
	            projectile.setProjectileDamage(-2);
	            setSprite(new Image("data/img/characters/player/venusaur_1.png"));
	            setMovingAnimation(new Image[]{new Image("data/img/characters/player/venusaur_1.png"),new Image("data/img/characters/player/venusaur_2.png"),
	                    new Image("data/img/characters/player/venusaur_3.png"),new Image("data/img/characters/player/venusaur_2.png")}
	                    ,125);
	            attackAnimationRight = new Animation(new Image[]{new Image("data/img/characters/player/venusaur_attack.png")}, 300);
	            attackAnimationLeft = new Animation(new Image[]{new Image("data/img/characters/player/venusaur_attack.png").getFlippedCopy(true,  false)}, 300);
	            setCanEvolve();
			}
		} else {
			if(evolutionStage == 0) {
	    		//projectile.setMovingAnimation(images, frameDuration);
	    		sparkle.play();
	    		evolutionPercentage = 0;
	            accelerationSpeed = 0.002f;
	            setY(y-12);
	            boundingShape = new AABoundingRect(x, y, 25, 32);
	            maximumSpeed = 0.2f;
	            maximumFallSpeed = 0.3f;
	            decelerationSpeed = 0.002f;
	            projectile.setProjectileDamage(-2);
	            setSprite(new Image("data/img/characters/player/pikachu_2.png"));
	            setMovingAnimation(new Image[]{new Image("data/img/characters/player/pikachu_1.png"),new Image("data/img/characters/player/pikachu_2.png"),
	                    new Image("data/img/characters/player/pikachu_3.png")}, 110);
	            attackAnimationRight = new Animation(new Image[]{new Image("data/img/characters/player/pikachu_attack.png")}, 300);
	            attackAnimationLeft = new Animation(new Image[]{new Image("data/img/characters/player/pikachu_attack.png").getFlippedCopy(true,  false)}, 300);
	            evolutionStage=1;
	            setCanEvolve();
	    	} else 	if(evolutionStage == 1) {
	    		sparkle.play();
	    		evolutionStage=2;
	    		setY(y-22);
	    		boundingShape = new AABoundingRect(x, y, 34, 40);
	    		//setX(x+8); setY(y-12);
	    		evolutionPercentage = 0;
	            accelerationSpeed = 0.003f;
	            maximumSpeed = 0.35f;
	            maximumFallSpeed = 0.2f;
	            decelerationSpeed = 0.003f;
	            projectile.setProjectileDamage(-3);
	            setSprite(new Image("data/img/characters/player/raichu_2.png"));
	            setMovingAnimation(new Image[]{new Image("data/img/characters/player/raichu_1.png"),new Image("data/img/characters/player/raichu_2.png"),
	                    new Image("data/img/characters/player/raichu_3.png")}, 115);
	            attackAnimationRight = new Animation(new Image[]{new Image("data/img/characters/player/raichu_attack.png")}, 300);
	            attackAnimationLeft = new Animation(new Image[]{new Image("data/img/characters/player/raichu_attack.png").getFlippedCopy(true,  false)}, 300);
	            setCanEvolve();
	    	}
		}
    }
    
    public void updateBoundingShape(){
    	//if(evolutionStage == 0 || evolutionStage == 1) {
    		boundingShape.updatePosition(x,y);
    	//} else {
    	//	boundingShape.updatePosition(x,y);
    	//}
    }
    
    public int getEvolutionPercentage() {
    	return evolutionPercentage;
    }
    
    public void modEvolutionPercentage(int i) {
    	evolutionPercentage += i;
    	if(evolutionPercentage >= 100) {
    		evolutionPercentage = 100;
    	}
    	setCanEvolve();
    }
    
    private void setCanEvolve() {
    	if((evolutionPercentage == 100) && (evolutionStage == 0 || evolutionStage == 1)) {
    		canEvolve = true;
    	} else {
    		canEvolve = false;
    	}
    }
    
    public int getEvolutionStage() {
    	return evolutionStage;
    }
    
    public boolean getCanEvolve() {
    	return canEvolve;
    }
    
    private void setEvolutionStage(int i) {
    	evolutionStage = i;
    }
    
    private void setHealth(int h) {
    	if(health >= 100) {
    		health = 100;
    	} else {
    		health = h;
    	}
    }
    
	public void modHealth(int h, Date newDate) {
		
    	if(newDate.getTime() - oldDate.getTime() >= 200) {
            punch.play();
    		health += h;
    	}
    	oldDate = new Date();
    	if(health <= 0) {
    		isDead = true;
    	}
    }
	
    public void jump(){
    	/*if(!(onGround) && doubleJump) {
    		y_velocity = -0.65f;
    		doubleJump = false;
    	}*/
        if(onGround){
        	jump.play();
        	//if(evolutionStage == 0 || evolutionStage == 1) {
        	//} else {
        	//	y_velocity = -0.65f;
        	//	doubleJump = true;
        	//}
        	if(Game.CHARACTER_CHOICE == 0 && evolutionStage == 2) {
        		y_velocity = -0.55f;
        	} else if(Game.CHARACTER_CHOICE == 0 && evolutionStage != 2) {
        		y_velocity = -0.41f;
        	} else if(Game.CHARACTER_CHOICE == 1 && evolutionStage == 2) {
        		y_velocity = -0.49f;
        	} else if(Game.CHARACTER_CHOICE == 1 && evolutionStage != 2) {
        		y_velocity = -0.40f;
        	} else if(Game.CHARACTER_CHOICE == 2 && evolutionStage == 2) {
        		y_velocity = -0.46f;
        	} else if(Game.CHARACTER_CHOICE == 2 && evolutionStage !=2) {
        		y_velocity = -0.39f;
        	} else if(Game.CHARACTER_CHOICE == 4 && evolutionStage == 2) {
        		y_velocity = -0.49f;
        	} else {
        		y_velocity = -0.42f;
        	}
        }
    }
    
    
	
	public void enemyCollideDeltaLeft(Date newDate) {
		if(newDate.getTime() - oldDate2.getTime() >=1000) {
			if(this.getFacing() == Facing.RIGHT) {
				this.setFacing(Facing.LEFT);
				this.setX(this.getX()-15);
				this.moveLeft(5);

				oldDate2 = new Date();
			} else {
				this.setX(this.getX()-15);
				this.moveLeft(5);

				oldDate2 = new Date();
			}
		}
	}
	
	public void enemyCollideDeltaRight(Date newDate) {
		if(newDate.getTime() - oldDate2.getTime() >=1000) {
			if(this.getFacing() == Facing.LEFT) {
				this.setFacing(Facing.RIGHT);
				this.setX(this.getX()+15);
				this.moveRight(5);
				
				oldDate2 = new Date();
			} else {
				this.setX(this.getX()+15);
				this.moveRight(5);

				oldDate2 = new Date();
			}
		}
	}
    
    public boolean getIsDead() {
    	return isDead;
    }
    
    public int getHealth() {
    	return health;
    }
    
    @Override
    public void render(float offset_x, float offset_y){
        
        //draw a moving animation if we have one and we moved within the last 150 miliseconds
        if(movingAnimations != null && moving /*&& !(Game.IS_FIRING)*/){
            movingAnimations.get(facing).draw(x-2-offset_x,y-2-offset_y);
            if(Game.IS_EVOLVING == true) {
            	evolutionAnimation.start();
            	evolutionAnimation.draw(x-2-offset_x, y-2-offset_y);
            	if(evolutionAnimation.getFrame() == 47) {
            		Game.IS_EVOLVING = false;
            		evolutionAnimation.restart();
            		evolutionAnimation.stop();
            	}
            }
        } else if((moving == false) && (Game.IS_FIRING == true) && (this.getFacing() == Facing.LEFT)) {
        	
        	attackAnimationLeft.draw(x-2-offset_x, y-2-offset_y);
        } else if((moving == false) && (Game.IS_FIRING == true) && (this.getFacing() == Facing.RIGHT)) {
        	attackAnimationRight.draw(x-2-offset_x, y-2-offset_y);        	
        }
        else {
            sprites.get(facing).draw(x-2-offset_x, y-2-offset_y);  
            if(Game.IS_EVOLVING == true) {
            	evolutionAnimation.start();
            	evolutionAnimation.draw(x-2-offset_x, y-2-offset_y);
            	if(evolutionAnimation.getFrame() == 47) {
            		
            		Game.IS_EVOLVING = false;
            		evolutionAnimation.restart();
            		evolutionAnimation.stop();
            	}
            }

        }
    }

}