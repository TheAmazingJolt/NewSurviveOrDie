package entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;

import entities.creatures.Player;
import main.Handler;

public class EntityManager {

	private Player player;
	
	public EntityList entities;
	private EntityList toAdd;
	private EntityList toRemove;
	
	private int subAreaNum = 1;
	
	private Comparator<Entity> renderSorter = new Comparator<Entity>() {
		public int compare(Entity a, Entity b) {
			if(a.getY() + a.getHeight() < b.getY() + b.getHeight()) {
				return -1; //a is rendered after b
			}
			return 1; //a is rendered before b
		}
	};
	
	public EntityManager(Handler handler) {

	}

	public void setup(Player player) {
		entities = new EntityList();
		toAdd = new EntityList();
		toRemove = new EntityList();
		this.player = player;
		toAdd.getEntities().add(player);
		toAdd.getEntities2().add(player);
	}
	
	public void tick() {
		if(subAreaNum == 1) {
			for(Entity e : entities.getEntities()) {
				e.tick();
			}
			entities.getEntities().sort(renderSorter);
	        entities.getEntities().removeAll(toRemove.getEntities());
	        toRemove.getEntities().clear();
	        entities.getEntities().addAll(toAdd.getEntities());
	        toAdd.getEntities().clear();
		}else if(subAreaNum == 2) {
			for(Entity e : entities.getEntities2()) {
				e.tick();
			}
			entities.getEntities2().sort(renderSorter);
	        entities.getEntities2().removeAll(toRemove.getEntities2());
	        toRemove.getEntities2().clear();
	        entities.getEntities2().addAll(toAdd.getEntities2());
	        toAdd.getEntities2().clear();
		}
	}
	
	public void render(Graphics g) {
		if(subAreaNum == 1) {
			for(Entity e : entities.getEntities()) {
				if(e.isActive())
					e.render(g);
			}
			player.postRender(g);
		}else if(subAreaNum == 2) {
			for(Entity e : entities.getEntities2()) {
				if(e.isActive())
					e.render(g);
			}
			player.postRender(g);
		}
	}
	
	public void add(Entity e)
    {
		if(subAreaNum == 1) {
	        toAdd.getEntities().add(e);
		}else if(subAreaNum == 2) {
	        toAdd.getEntities2().add(e);
		}
    }
    
    public void remove(Entity e)
    {
    	if(subAreaNum == 1) {
    		toRemove.getEntities().remove(e);
		}else if(subAreaNum == 1) {
			toRemove.getEntities2().remove(e);
		}
    }
    
    public void clear() {
    	if(subAreaNum == 1) {
    		toRemove.getEntities().addAll(entities.getEntities());
    		toAdd.getEntities().add(player);
    	}else if(subAreaNum == 2) {
    		toRemove.getEntities2().addAll(entities.getEntities2());
    		toAdd.getEntities2().add(player);
    	}
    }
    
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Entity> getEntities() {
		if(subAreaNum == 1) {
			return entities.getEntities();
		}else if(subAreaNum == 2) {
			return entities.getEntities2();
		}
		return null;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities.setEntities(entities);
	}

	public int getSubAreaNum() {
		return subAreaNum;
	}

	public void setSubAreaNum(int subAreaNum) {
		this.subAreaNum = subAreaNum;
	}
	
}
