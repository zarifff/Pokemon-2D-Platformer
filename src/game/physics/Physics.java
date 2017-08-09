package game.physics;

import java.util.ArrayList;

import game.Game;
import game.level.Level;
import game.level.LevelObject;
import game.level.tile.Tile;
import game.character.Character;
import game.character.Enemy;
import game.character.EnemyProjectile;
import game.character.Mewtwo;
import game.character.Player;
import game.character.Projectile;
import game.enums.Facing;
import game.level.object.Objective;
import java.util.Date;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class Physics {

    private final float gravity = 0.0015f;
    private EnemyProjectile enemyProjectile;
    
    public void handlePhysics(Level level, int delta, EnemyProjectile enemyProjectile) throws SlickException{
    	this.enemyProjectile = enemyProjectile;
        handleCharacters(level,delta, this.enemyProjectile);
        handleLevelObjects(level,delta);
    }
    
    private void handleCharacters(Level level, int delta, EnemyProjectile enemyProjectile) throws SlickException{
       
        for(Character c : level.getCharacters()){
            
            //and now decelerate the character if he is not moving anymore
            if(!c.isMoving()){
                c.decelerate(delta);
            }
            
            handleGameObject(c,level,delta);
            
            //special cases for the player
            if(c instanceof Player){
                
                ArrayList<LevelObject> removeQueue = new ArrayList<LevelObject>();
                
                //we have to check if he collides with anything special
                for(LevelObject obj : level.getLevelObjects()){
                    
                    for(Character e : level.getCharacters()) { 					// Mewtwo AI
                    	if(e instanceof Mewtwo) {
                      			if(c.getX() - e.getX() >= 125) {
                      				if(!((Mewtwo) e).getIsDead()) {
                      					e.moveRight(delta);
                      				} 
                      			} 
                      			else if(c.getX() - e.getX() <= -125) {
                      				if(!((Mewtwo) e).getIsDead()) {
                      					e.moveLeft(delta);
                      				}
                      			} 
                			if(Math.abs(c.getY() - e.getY()) >= 20){
                				if(!((Mewtwo) e).getIsDead()) {
                					if(Math.random() >= 0.10) {
                						e.jump();
                					}
                				}
                			}
                			if(obj instanceof Projectile) {
                				if(!((Mewtwo)e).getIsDead()) {
                					if(Math.abs(((Projectile)obj).getX() - e.getX()) >=174) {
                						if(Math.random() >= 0.20) {
                							e.jump();
                						}
                					}
                				}
                			}
                        	if(Game.IS_ENEMY_PROJECTILE_COLLIDE == true && (new Date().getTime() - Game.PROJECTILE_DATE.getTime() >= 1500) && !(((Mewtwo) e).getIsDead())) {
                        		enemyProjectile.setFacing(e.getFacing());
                        		enemyProjectile.setY(e.getY());
                        		if(e.getFacing() == Facing.LEFT) {
                        			enemyProjectile.setX(e.getX() - 20);
                        			enemyProjectile.moveLeft(delta);
                        		} else {
                        			enemyProjectile.setX(e.getX() + 20);
                        			enemyProjectile.moveRight(delta);
                        		}
                        		Game.IS_PROJECTILE_COLLIDE = false;
                        		Game.PROJECTILE_DATE = new Date();
                        	}
                    	}
         
                    	if(e instanceof Enemy) {
                    		if(e.getBoundingShape().checkCollision(c.getBoundingShape())) {
                    			if(Game.CHARACTER_CHOICE == 0) {
                    				((Player) c).modHealth(-5, new Date());
                    			} else if(Game.CHARACTER_CHOICE == 1) {
                    				((Player) c).modHealth(-4, new Date());
                    			} else {
                    				((Player) c).modHealth(-5, new Date());
                    			}
                    			if(e.getFacing() == Facing.LEFT) {
                    				((Player) c).enemyCollideDeltaLeft(new Date());
                    				if(checkCollision(c,level.getTiles())){
                                    	c.setX(c.getX()+24);
                    				}
                    			} else {
                    				((Player) c).enemyCollideDeltaRight(new Date());
                    				if(checkCollision(c,level.getTiles())){
                                    	c.setX(c.getX()-24);
                    				}
                    			}
                    		}
                        	for(Character proj : level.getCharacters()) { /* Mewtwo Dodge Mechanics */
                        		if(proj instanceof Projectile) {
                        			if(e instanceof Mewtwo) {
                        				if(!((Mewtwo)e).getIsDead()) {
                        					if(Math.abs(((Projectile)proj).getX() - e.getX()) <= 150) {
                        						if(Math.random() >= 0.49) {
                        							e.jump();
                        						}
                        					}
                        				}
                        			}
                        			if(proj.getBoundingShape().checkCollision(e.getBoundingShape())) {
                        				proj.setX(0);
                        				proj.setY(0);
                        				proj.setMoving(false);
                        				((Enemy)e).modHealth(((Projectile) proj).getProjectileDamage(), new Date(), (Player)c);
                        				if(c.getFacing() == Facing.LEFT) {
                        					e.setX(e.getX() - 30);
                        					while(checkCollision(e, level.getTiles())) {
                        						e.setX(e.getX()+2);
                        					}
                        				} else {
                        					e.setX(e.getX() + 30);
                        					while(checkCollision(e, level.getTiles())) {
                        						e.setX(e.getX()-2);
                        					}
                        				}
                        				
                        				Game.IS_PROJECTILE_COLLIDE = true;
                        			}
                        		}
                        	}
                        	for(Character proj : level.getCharacters()) { /* Mewtwo Dodge Mechanics */
                        		if(proj instanceof EnemyProjectile) {

                        			if(proj.getBoundingShape().checkCollision(c.getBoundingShape())) {
                        				proj.setMoving(false);
                        				proj.setX(33);
                        				proj.setY(33);
                        				
                        				((Player)c).modHealth(((EnemyProjectile) proj).getProjectileDamage(), new Date());
                        				if(e instanceof Mewtwo) {
                        					if(e.getFacing() == Facing.LEFT) {
                        						c.setX(c.getX() - 15);
                        						while(checkCollision(c, level.getTiles())) {
                        							c.setX(c.getX()+1);
                        						}
                        					}
                        					else {
                        						c.setX(c.getX() + 15);
                        						while(checkCollision(c, level.getTiles())) {
                        							c.setX(c.getX()-1);
                        						}
                        					}
                        				}
                        				Game.IS_ENEMY_PROJECTILE_COLLIDE = true;
                        			}
                        		}
                        	}
                    	}
                    	
                    }
                    
                }
                
                level.removeObjects(removeQueue);
            }

            
        }
    }
    
    private void handleLevelObjects(Level level, int delta){
        for(LevelObject obj : level.getLevelObjects()){
            handleGameObject(obj,level,delta);
        }
    }
    
    private void handleGameObject(LevelObject obj, Level level, int delta){ ////// HUGEEE hard to understand perspective movement calculations
    	
    	if(obj instanceof Enemy /*&& !(obj instanceof Mewtwo)*/) {
    		if(((Enemy) obj).getFacing() == Facing.LEFT && !((Enemy) obj).getIsDead()) {
    			((Enemy) obj).moveLeft(delta);
    		} else if(((Enemy) obj).getFacing() == Facing.RIGHT && !((Enemy) obj).getIsDead()){
    			((Enemy) obj).moveRight(delta);
    		}
    	}
    	if(obj instanceof Projectile) {
    		if(((Projectile) obj).getFacing() == Facing.LEFT) {
    			((Projectile) obj).moveLeft(delta);
    		} else {
    			((Projectile) obj).moveRight(delta);
    		}
    	}
    	
        obj.setOnGround(isOnGroud(obj,level.getTiles()));
        
        if(!obj.isOnGround() || obj.getYVelocity() < 0)
            obj.applyGravity(gravity*delta);
        else
            obj.setYVelocity(0);
        
        float x_movement = obj.getXVelocity()*delta;
        float y_movement   = obj.getYVelocity()*delta;
        
        float step_y = 0;
        float step_x = 0;
        
        if(x_movement != 0){
            step_y = Math.abs(y_movement)/Math.abs(x_movement);
            if(y_movement < 0)
                step_y = -step_y;
            
            if(x_movement > 0)
                step_x = 1;
            else
                step_x = -1;
            
            if((step_y > 1 || step_y < -1) && step_y != 0){
                step_x = Math.abs(step_x)/Math.abs(step_y);
                if(x_movement < 0)
                    step_x = -step_x;
                if(y_movement < 0)
                    step_y = -1;
                else
                    step_y = 1;
            }
        }else if(y_movement != 0){
            if(y_movement > 0)
                step_y = 1;
            else
                step_y = -1;
        }
        
        while(x_movement != 0 || y_movement != 0){
            
            if(x_movement != 0){
                if((x_movement > 0 && x_movement < step_x) || (x_movement > step_x  && x_movement < 0)){
                    step_x = x_movement;
                    x_movement = 0;
                }else
                    x_movement -= step_x;
                
                obj.setX(obj.getX()+step_x);
                
                if(checkCollision(obj,level.getTiles())){
                    
                    obj.setX(obj.getX()-step_x);
                    obj.setXVelocity(0);
                    x_movement = 0;
                    if(obj instanceof Enemy) {
                    	if(((Enemy) obj).getFacing() == Facing.LEFT) {
                    		((Enemy) obj).setFacing(Facing.RIGHT);
                    		
                    	} else {
                    		((Enemy) obj).setFacing(Facing.LEFT);
                    		
                    	}
                    }
                    if(obj instanceof Projectile) {
                    	obj.setX(0);
                    	obj.setY(0);
                    	((Projectile) obj).setMoving(false);
                    	Game.IS_PROJECTILE_COLLIDE = true;
                    }
                    if(obj instanceof EnemyProjectile) {
                    	obj.setX(33);
                    	obj.setY(33);
                    	((EnemyProjectile) obj).setMoving(false);
                    	Game.IS_ENEMY_PROJECTILE_COLLIDE = true;
                    }
                }
                
                
            }
            //same thing for the vertical, or y mmovement
            if(y_movement != 0){
                if((y_movement > 0 && y_movement < step_y) || (y_movement > step_y  && y_movement < 0)){
                    step_y = y_movement;
                    y_movement = 0;
                }else
                    y_movement -= step_y;
                
                obj.setY(obj.getY()+step_y);
                
                if(checkCollision(obj,level.getTiles())){
                    obj.setY(obj.getY()-step_y);
                    obj.setYVelocity(0);
                    y_movement = 0;
                    break;
                }
            }
        }
    }
    
    
    private boolean checkCollision(LevelObject obj, Tile[][] mapTiles){
        //get only the tiles that matter
        ArrayList<Tile> tiles = obj.getBoundingShape().getTilesOccupying(mapTiles);
        for(Tile t : tiles){
            //if this tile has a bounding shape
            if(t.getBoundingShape() != null){
                if(t.getBoundingShape().checkCollision(obj.getBoundingShape())){
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean isOnGroud(LevelObject obj, Tile[][] mapTiles){
        //we get the tiles that are directly underneath the characters
        ArrayList<Tile> tiles = obj.getBoundingShape().getGroundTiles(mapTiles);
        
        obj.getBoundingShape().movePosition(0, 1);
        
        for(Tile t : tiles){
            //not every tile has a bounding shape
            if(t.getBoundingShape() != null){
                //if the ground and the lowered object collide, then we are on the ground
                if(t.getBoundingShape().checkCollision(obj.getBoundingShape())){
                    obj.getBoundingShape().movePosition(0, -1);
                    return true;
                }
            }
        }
        
        obj.getBoundingShape().movePosition(0, -1);
        
        return false;
    }

}
