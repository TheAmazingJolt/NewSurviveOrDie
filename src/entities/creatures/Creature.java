package entities.creatures;

import java.awt.Rectangle;

import entities.Entity;
import main.Handler;

public abstract class Creature extends Entity{

	public static final int DEFAULT_CREATURE_WIDTH = 64;
	public static final int DEFAULT_CREATURE_HEIGHT = 64;
	
	protected float defaultSpeed = 5;
	protected float speed;
	protected float xSpeed;
	protected float ySpeed;
	protected float xMove;
	protected float yMove;
	
	protected int tileX;
	protected int tileY;
	
	protected boolean tileCollision = false;
	
	protected Rectangle cb;
	protected Rectangle ar;
	
	public Creature(Handler handler, float x, float y, int width, int height, int health, int id, String name) {
		super(handler, x, y, width, height, health, id, name);
		speed = defaultSpeed;
		xMove = 0.0F;
		yMove = 0.0F;
	}
	
	public void move() {
		if(!handler.getWorld().getEntityManager().getPlayer().getInventory().isActive()) {
			if(!checkEntityCollisions(xMove, 0.0F)) {
				moveX();
			}
			if(!checkEntityCollisions(0.0F, yMove)) {
				moveY();
			}
		}
	}
	
	public void moveX() {
		if(xMove > 0.0F) {
			int tx = (int) (x + xMove + (float) bounds.x + (float) bounds.width) / 64;
			if(!collisionWithTile(tx, (int)(y + (float)bounds.y) / 64) && !collisionWithTile(tx, (int)(y + (float)bounds.y + (float)bounds.height) / 64))
                x += xMove;
		}else if(xMove < 0.0F) {
	            int tx = (int)(x + xMove + (float)bounds.x) / 64;
	            if(!collisionWithTile(tx, (int)(y + (float)bounds.y) / 64) && !collisionWithTile(tx, (int)(y + (float)bounds.y + (float)bounds.height) / 64))
	                x += xMove;
	    }
	}
	
	public void moveY()
    {
        if(yMove < 0.0F) {
            int ty = (int)(y + yMove + (float)bounds.y) / 64;
            if(!collisionWithTile((int)(x + (float)bounds.x) / 64, ty) && !collisionWithTile((int)(x + (float)bounds.x + (float)bounds.width) / 64, ty))
                y += yMove;
        }else if(yMove > 0.0F) {
            int ty = (int)(y + yMove + (float)bounds.y + (float)bounds.height) / 64;
            if(!collisionWithTile((int)(x + (float)bounds.x) / 64, ty) && !collisionWithTile((int)(x + (float)bounds.x + (float)bounds.width) / 64, ty))
                y += yMove;
        }
    }
	
	protected boolean collisionWithTile(int x, int y)
    {
		this.speed = defaultSpeed * handler.getWorld().getTile(x, y).speedMultiplier();
    	this.tileCollision = handler.getWorld().getTile(x, y).isSolid();
    	if(handler.getWorld().getTile(x, y).isDoor() && this == handler.getWorld().getEntityManager().getPlayer())
    		handler.getWorld().getTile(x, y).enter();
        return handler.getWorld().getTile(x, y).isSolid();
    }

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getxSpeed() {
		return xSpeed;
	}

	public void setxSpeed(float xSpeed) {
		this.xSpeed = xSpeed;
	}

	public float getySpeed() {
		return ySpeed;
	}

	public void setySpeed(float ySpeed) {
		this.ySpeed = ySpeed;
	}

	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}

	public int getTileX() {
		return tileX;
	}

	public void setTileX(int tileX) {
		this.tileX = tileX;
	}

	public int getTileY() {
		return tileY;
	}

	public void setTileY(int tileY) {
		this.tileY = tileY;
	}

	public float getDefaultSpeed() {
		return defaultSpeed;
	}

	public static int getDefaultCreatureWidth() {
		return DEFAULT_CREATURE_WIDTH;
	}

	public static int getDefaultCreatureHeight() {
		return DEFAULT_CREATURE_HEIGHT;
	}
	
}
