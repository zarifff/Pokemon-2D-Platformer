package game.level;

import java.util.ArrayList;

import game.Game;
import game.character.Character;
import game.character.Enemy;
import game.character.EnemyProjectile;
import game.character.Mewtwo;
import game.character.Player;
import game.character.Projectile;
import game.controller.MouseAndKeyBoardPlayerController;
import game.level.tile.AirTile;
import game.level.tile.SolidTile;
import game.level.tile.Tile;
import game.enums.Facing;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import java.util.Date;

public class Level {
    
    private TiledMap map;
    
    //a list of all characters present somewhere on this map
    private ArrayList<Character> characters;
    private Player player;

    private Enemy enemy = new Enemy(335, 325);
    private Enemy enemy2 = new Enemy(580, 325);
    private Enemy enemy3 = new Enemy(1240, 300);
    private Enemy enemy4 = new Enemy(1895, 615);
    private Enemy enemy5 = new Enemy(2100, 610);
    private Enemy enemy6 = new Enemy(2820, 405);
    private Enemy enemy7 = new Enemy(3005, 405);
    private Enemy enemy8 = new Enemy(3890, 640);
    private Enemy enemy9 = new Enemy(4065, 640);
    private Enemy enemy10 = new Enemy(4477, 730);

    private Mewtwo mewtwo = new Mewtwo(6000, 650 );
    private EnemyProjectile enemyProjectile = new EnemyProjectile(33, 33);

    private ArrayList<LevelObject> levelObjects;
    
    private Tile[][] tiles;
    
    private Image background;
    
    public Level(String level, Player player, Projectile projectile) throws SlickException{
        map = new TiledMap("data/levels/" + level + ".tmx","data/img");
        characters = new ArrayList<Character>();
        
        levelObjects = new ArrayList<LevelObject>();
        
        //add all the stuff to our map
        this.player = player;
        addCharacter(player);
        addCharacter(enemy);
        addCharacter(enemy2);
        addCharacter(enemy3);
        addCharacter(enemy4);
        addCharacter(enemy5);
        addCharacter(enemy6);
        addCharacter(enemy7);
        addCharacter(enemy8);
        addCharacter(enemy9);
        addCharacter(enemy10);

        addCharacter(projectile);
        addCharacter(mewtwo);
        addCharacter(enemyProjectile);
        
        loadTileMap();
        
        background = new Image("data/img/backgrounds/" + map.getMapProperty("background", "night_background.png"));
        
    }
    
    private void loadTileMap(){
        //create an array to hold all the tiles in the map
        tiles = new Tile[map.getWidth()][map.getHeight()];
        
        int layerIndex = map.getLayerIndex("CollisionLayer");
        
        if(layerIndex == -1){
            System.err.println("Map does not have the layer \"CollisionLayer\"");
            System.exit(0);
        }
        
        //loop through the whole map
        for(int x = 0; x < map.getWidth(); x++){
            for(int y = 0; y < map.getHeight(); y++){
                
                //get the tile
                int tileID = map.getTileId(x, y, layerIndex);
                
                Tile tile = null;
                
                //and check what kind of tile it is (
                switch(map.getTileProperty(tileID, "tileType", "solid")){
                    case "air":
                        tile = new AirTile(x,y);
                        break;
                    default:
                        tile = new SolidTile(x,y);
                        break;
                }
                tiles[x][y] = tile;
            }
        }
    }
    
    public void addCharacter(Character c){
        characters.add(c);
    }
    
    public void removeCharacter(Character c) {
    	characters.remove(c);
    }
    
    public ArrayList<Character> getCharacters(){
        return characters;
    }
    
    public void addLevelObject(LevelObject obj){
        levelObjects.add(obj);
    }
    
    public ArrayList<LevelObject> getLevelObjects(){
        return levelObjects;
    }
    
    public Tile[][] getTiles(){
        return tiles;
    }
    
    public void removeObject(LevelObject obj){
        levelObjects.remove(obj);
    }
    
    public void removeObjects(ArrayList<LevelObject> objects) {
        levelObjects.removeAll(objects);
    }
    
    public Player getPlayer() {
    	return player;
    }
    
    public int getXOffset(){ /*OFFSET for moving camera naturally */
        int offset_x = 0;
        
        int half_width = (int) (Game.WINDOW_WIDTH/Game.SCALE/2);
        
        int maxX = (int) (map.getWidth()*32)-half_width;
        
        if(player.getX() < half_width){
            offset_x = 0;
        }else if(player.getX() > maxX){
            offset_x = maxX-half_width;
        }else{
            offset_x = (int) (player.getX()-half_width);
        }
        
        return offset_x;
    }
    
    //similar to the getXOffset() method
    public int getYOffset(){
        int offset_y = 0;
        
        int half_heigth = (int) (Game.WINDOW_HEIGTH/Game.SCALE/2);
        
        int maxY = (int) (map.getHeight()*32)-half_heigth;
        
        if(player.getY() < half_heigth){
            offset_y = 0;
        }else if(player.getY() > maxY){
            offset_y = maxY-half_heigth;
        }else{
            offset_y = (int) (player.getY()-half_heigth);
        }
        
        return offset_y;
    }
    
    public void render() {
        int offset_x = getXOffset();
        int offset_y = getYOffset();
        
        //render the background
        renderBackground();

        //then the map on top of that
        map.render(-(offset_x%32), -(offset_y%32), offset_x/32, offset_y/32, 33, 19);
        
        for(LevelObject obj : levelObjects){
            obj.render(offset_x, offset_y);
        }
        
        //and then render the characters on top of the map
        for(Character c : characters){
            c.render(offset_x,offset_y);
        }
        
    }
    
    private void renderBackground(){ // Parallax Scrolling Algorithm
        
        float backgroundXScrollValue = (background.getWidth()-Game.WINDOW_WIDTH/Game.SCALE);
        float backgroundYScrollValue = (background.getHeight()-Game.WINDOW_HEIGTH/Game.SCALE);
        
        float mapXScrollValue = ((float)map.getWidth()*32-Game.WINDOW_WIDTH/Game.SCALE);
        float mapYScrollValue = ((float)map.getHeight()*32-Game.WINDOW_HEIGTH/Game.SCALE);
        
        float scrollXFactor = backgroundXScrollValue/mapXScrollValue * -1;
        float scrollYFactor = backgroundYScrollValue/mapYScrollValue * -1;
        
        background.draw(this.getXOffset()*scrollXFactor,this.getYOffset()*scrollYFactor);
    }
    
    public Enemy getEnemy() {
    	return enemy;
    }
    
    public Mewtwo getMewtwo() {
    	return mewtwo;
    }
    
    public EnemyProjectile getEnemyProjectile() {
    	return enemyProjectile;
    }
}