package game.controller;

import game.character.Player;
import game.character.Projectile;

import org.newdawn.slick.Input;

public abstract class PlayerController {
    
    protected Player player;
    protected Projectile projectile;
    protected Player player2;
    protected Projectile projectile2;
    
    public PlayerController(Player player, Projectile projectile){
        this.player = player;
        this.projectile = projectile;
    }
    
    public abstract void handleInput(Input i, int delta);

}
