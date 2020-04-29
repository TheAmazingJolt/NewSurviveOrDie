package entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;

import entities.creatures.Player;

public class EntityManager {

	private Player player;
	
	private EntityList entities;
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
	
	public EntityManager() {
	}

	public void setup(Player player) {
		entities = new EntityList();
		toAdd = new EntityList();
		toRemove = new EntityList();
		this.player = player;
		toAdd.getEntities().add(player);
	}
	
	public void tick() {
		if(subAreaNum == 1) {
			for(Entity e : entities.getEntities()) {
				e.tick();
			}
			entities.getEntities().sort(renderSorter);
	        entities.getEntities().addAll(toAdd.getEntities());
	        toAdd.getEntities().clear();
	        entities.getEntities().removeAll(toRemove.getEntities());
	        toRemove.getEntities().clear();
		}
	}
	
	public void render(Graphics g) {
		if(subAreaNum == 1) {
			for(Entity e : entities.getEntities()) {
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
		}
    }
    
    public void remove(Entity e)
    {
    	if(subAreaNum == 1) {
	        toAdd.getEntities().remove(e);
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
