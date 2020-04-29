package entities;

import java.util.ArrayList;

public class EntityList {

	private ArrayList<Entity> entities;
	
	public EntityList() {
		entities = new ArrayList<Entity>();
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
	
}
