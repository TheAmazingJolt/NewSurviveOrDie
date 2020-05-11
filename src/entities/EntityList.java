package entities;

import java.util.ArrayList;

public class EntityList {

	private ArrayList<Entity> entities;
	private ArrayList<Entity> entities2;
	
	public EntityList() {
		entities = new ArrayList<Entity>();
		entities2 = new ArrayList<Entity>();
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
	
	public ArrayList<Entity> getEntities2() {
		return entities2;
	}

	public void setEntities2(ArrayList<Entity> entities) {
		this.entities2 = entities;
	}
	
}
