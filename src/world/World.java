package world;

import java.awt.Graphics;

import entities.Entity;
import entities.EntityManager;
import entities.creatures.Player;
import entities.creatures.hostile.Zombie;
import items.ItemManager;
import main.Handler;
import tiles.Tile;
import utils.Utils;

public class World {

	private Handler handler;
	
	private int width;
	private int height;
	
	private int entityAmount;
	
	private int spawnX = 0;
	private int spawnY = 0;
	
	private int currentArea = 1;
	private int subAreaNum = 1;
	
	private int[][] tiles;
	
	private boolean loaded = false; //to see if entitymanager and player have been created to avoid causing a nullpointer
	
	private EntityManager entityManager;
	private ItemManager itemManager;
	
	public World(Handler handler) {
		this.handler = handler;
		Tile.setHandler(handler);
		entityManager = new EntityManager();
        itemManager = new ItemManager(handler);
		entityManager.setup(new Player(handler, spawnX, spawnY, -1));
		loadWorld();
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
		loaded = true;
	}
	
	public void tick() {
		entityManager.tick();
        itemManager.tick();
	}
	
	public void render(Graphics g) {
		int xStart = (int) Math.max(0.0F, handler.getGameCamera().getxOffset() / 64F);
		int xEnd = (int)Math.min(width, (handler.getGameCamera().getxOffset() + (float)handler.getWidth()) / 64F + 1.0F);
        int yStart = (int)Math.max(0.0F, handler.getGameCamera().getyOffset() / 64F);
        int yEnd = (int)Math.min(height, (handler.getGameCamera().getyOffset() + (float)handler.getHeight()) / 64F + 1.0F);
        for(int y = yStart; y < yEnd; y++)
        {
            for(int x = xStart; x < xEnd; x++) {
            	getTile(x, y).render(g, (int)((float)(x * 64) - handler.getGameCamera().getxOffset()), (int)((float)(y * 64) - handler.getGameCamera().getyOffset()));
            }
        }
        itemManager.render(g);
        entityManager.render(g);
	}
	
	public void loadWorld() { //basic world loader
		String path = "res/areas/area" + currentArea + "/area" + currentArea + ".txt"; //path of world
		String file = Utils.loadFileAsString(path); //file for world
		String tokens[] = file.split("\\s+"); //tokens for world
		
		String ePath = "res/areas/area" + currentArea + "/entities/area" + currentArea + "Entities.txt"; //path of entities
		String eFile = Utils.loadFileAsString(ePath); //file for entities
		String eTokens[] = eFile.split("\\s+"); //tokens for entities
		
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		this.spawnX = Utils.parseInt(tokens[2]);
		this.spawnY = Utils.parseInt(tokens[3]);
		tiles = new int[width][height];
		String token[];
		
		entityAmount = Utils.parseInt(eTokens[0]);
		String eToken[];
		String eToken2[];
		String entityName;
		int entityNum = 0;
		
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				token = tokens[x + y * width + 4].split("\\ ");
				tiles[x][y] = Utils.parseInt(token[0]);
				if(tiles[x][y] == 0) {
					Tile.getTiles().add(Tile.dirtTile);
				}else if(tiles[x][y] == 1) {
					Tile.getTiles().add(Tile.grassTile);
				}else if(tiles[x][y] == 2) {
					Tile.getTiles().add(Tile.wallTile);
				}
			}
		}
		
		for(int i = 1; i <= entityAmount; i++) {
			Entity e;
			eToken = eTokens[i].split("\\:");
			entityName = eToken[0];
			eToken2 = eToken[1].split("\\,");
			boolean exists = false;
			if(entityName.contains("Zombie")) {
				e = new Zombie(handler, Integer.parseInt(eToken2[0]), Integer.parseInt(eToken2[1]), entityNum, entityManager.getPlayer());
				for(Entity ee : entityManager.getEntities()) {
					if(ee.getId() == e.getId()) {
						exists = true;
					}
				}
				if(!exists)
					entityManager.add(e);
			}
		}
		
	}
	
	public void loadWorld(String p) { //used to load a specific world
		String path = "res/areas/area" + currentArea + p + ".txt";
		String file = Utils.loadFileAsString(path); //file for world
		String tokens[] = file.split("\\s+"); //tokens for world
		
		String ePath = "res/areas/area" + currentArea + "/entities" + p + "Entities.txt"; //path of entities
		String eFile = Utils.loadFileAsString(ePath); //file for entities
		String eTokens[] = eFile.split("\\s+"); //tokens for entities
		
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		this.spawnX = Utils.parseInt(tokens[2]);
		this.spawnY = Utils.parseInt(tokens[3]);
		if(loaded) {
			entityManager.getPlayer().setX(spawnX);
			entityManager.getPlayer().setY(spawnY);
		}
		tiles = new int[width][height];
		String token[];
		
		entityAmount = Utils.parseInt(eTokens[0]);
		String eToken[];
		String eToken2[];
		String entityName;
		int entityNum = 0;
		
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				token = tokens[x + y * width + 4].split("\\ ");
				tiles[x][y] = Utils.parseInt(token[0]);
				if(tiles[x][y] == 0) {
					Tile.getTiles().add(Tile.dirtTile);
				}else if(tiles[x][y] == 1) {
					Tile.getTiles().add(Tile.grassTile);
				}else if(tiles[x][y] == 2) {
					Tile.getTiles().add(Tile.wallTile);
				}
			}
		}
		
		for(int i = 1; i <= entityAmount; i++) {
			Entity e;
			eToken = eTokens[i].split("\\:");
			entityName = eToken[0];
			eToken2 = eToken[1].split("\\,");
			boolean exists = false;
			if(entityName.contains("Zombie")) {
				e = new Zombie(handler, Integer.parseInt(eToken2[0]), Integer.parseInt(eToken2[1]), entityNum, entityManager.getPlayer());
				for(Entity ee : entityManager.getEntities()) {
					if(ee.getId() == e.getId()) {
						exists = true;
					}
				}
				if(!exists)
					entityManager.add(e);
			}
		}
	}
	
	public void loadWorld(String p, int xx, int yy) { //used for having a different spawnx/y
		String path = "res/areas/area" + currentArea + p + ".txt";
		String file = Utils.loadFileAsString(path); //file for world
		String tokens[] = file.split("\\s+"); //tokens for world
		
		String ePath = "res/areas/area" + currentArea + "/entities" + p + "Entities.txt"; //path of entities
		String eFile = Utils.loadFileAsString(ePath); //file for entities
		String eTokens[] = eFile.split("\\s+"); //tokens for entities
		
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		this.spawnX = xx;
		this.spawnY = yy;
		if(loaded) {
			entityManager.getPlayer().setX(spawnX);
			entityManager.getPlayer().setY(spawnY);
		}
		tiles = new int[width][height];
		String token[];
		
		entityAmount = Utils.parseInt(eTokens[0]);
		String eToken[];
		String eToken2[];
		String entityName;
		int entityNum = 0;
		
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				token = tokens[x + y * width + 4].split("\\ ");
				tiles[x][y] = Utils.parseInt(token[0]);
				if(tiles[x][y] == 0) {
					Tile.getTiles().add(Tile.dirtTile);
				}else if(tiles[x][y] == 1) {
					Tile.getTiles().add(Tile.grassTile);
				}else if(tiles[x][y] == 2) {
					Tile.getTiles().add(Tile.wallTile);
				}
			}
		}
		
		for(int i = 1; i <= entityAmount; i++) {
			Entity e;
			eToken = eTokens[i].split("\\:");
			entityName = eToken[0];
			eToken2 = eToken[1].split("\\,");
			boolean exists = false;
			if(entityName.contains("Zombie")) {
				e = new Zombie(handler, Integer.parseInt(eToken2[0]), Integer.parseInt(eToken2[1]), entityNum, entityManager.getPlayer());
				for(Entity ee : entityManager.getEntities()) {
					if(ee.getId() == e.getId()) {
						exists = true;
					}
				}
				if(!exists)
					entityManager.add(e);
			}
		}
	}
	
	public Tile getTile(int x, int y)
    {
        if(x < 0 || y < 0 || x >= width || y >= height)
            return Tile.grassTile;
        if(y == 0) {
        	Tile t = Tile.getTiles().get(x);
        	if(t == null)
                return Tile.dirtTile;
            else
                return t;
        }else {
        	Tile t = Tile.getTiles().get((width * y) + x);
        	if(t == null)
                return Tile.dirtTile;
            else
                return t;
        }
    }
	
	public void unload() {
		Tile.getTiles().clear();
		tiles = null;
		width = 0;
		height = 0;
		spawnX = 0;
		spawnY = 0;
		handler.getWorld().getEntityManager().getPlayer().setX(0);
		handler.getWorld().getEntityManager().getPlayer().setY(0);
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public ItemManager getItemManager() {
		return itemManager;
	}
	
}
