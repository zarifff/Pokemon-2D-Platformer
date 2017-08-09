package game.level.object;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import game.level.LevelObject;

public class Objective extends LevelObject { /*Did not use this class. Originally
											   planned to make Health pickups and coins for points */
    
    protected Animation animation;

    public Objective(float x, float y) throws SlickException {
        super(x, y);
        
        //add the right animation for this objective
        animation = new Animation(new Image[]{new Image("data/img/flames_transparent.png")}, 100);
        
        animation.setPingPong(true);
        
    }

    public void render(float offset_x, float offset_y) {
        animation.draw(x-2-offset_x,y-2-offset_y);
    }

}
