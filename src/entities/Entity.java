package entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import main.Handler;

public abstract class Entity {

	protected Handler handler;
	
	protected float x;
	protected float y;
	protected float startX;
	protected float startY;
	
	protected int width;
	protected int height;
	protected int id;
	protected int health;
	protected int startHealth;
	protected int maxHealth;
	
	protected boolean active;
	
	protected boolean ammo;
	
	protected Rectangle bounds;
	
	protected String name = "";
	
	public Entity(Handler handler, float x, float y, int width, int height, int health, int id, String name) {
		active = true;
		this.handler = handler;
		this.name = name;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.startX = x;
		this.startY = y;
		this.health = health;
		this.startHealth = health;
		this.maxHealth = health;
		this.id = id;
		bounds = new Rectangle((int) x, (int) y, width, height);
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public abstract void die();
	
	public void hurt(int amt) {
		this.health -= amt;
		if(this.health < 0) {
			this.active = false;
			this.die();
		}
	}
	
	public boolean checkEntityCollisions(float xOffset, float yOffset) {
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(!e.equals(this) && e.getCollisionBounds(0.0F, 0.0F).intersects(getCollisionBounds(xOffset + 10, yOffset + 10)))
				return true;
		}
		return false;
	}
	
	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		return new Rectangle((int) (this.x + (this.bounds.x + xOffset)), (int) (this.y + (this.bounds.y + yOffset)), this.bounds.width, this.bounds.height);
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getStartX() {
		return startX;
	}

	public boolean isAmmo() {
		return ammo;
	}

	public void setAmmo(boolean ammo) {
		this.ammo = ammo;
	}

	public void setStartX(float startX) {
		this.startX = startX;
	}

	public float getStartY() {
		return startY;
	}

	public void setStartY(float startY) {
		this.startY = startY;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getStartHealth() {
		return startHealth;
	}

	public void setStartHealth(int startHealth) {
		this.startHealth = startHealth;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
