package entities.creatures.hostile;

import entities.creatures.Creature;
import main.Handler;

public abstract class Hostile extends Creature{
	
	protected Creature following;
	
	protected boolean isFollowing = false;
	protected boolean isAttacking = false;

	public Hostile(Handler handler, float x, float y, int width, int height, int health, int id, String name) {
		super(handler, x, y, width, height, health, id, name);
	}
	
	public void updateHitbox(int arSize) {
		if(xMove > 0) {
			if(yMove > 0) {//down right
	            ar.x = cb.x + cb.width;
	            ar.y = cb.y + cb.height - 45;
			}else if(yMove < 0) {//up right
	            ar.x = cb.x + cb.width;
	            ar.y = cb.y - arSize - 19;
			}else if(yMove == 0) {//right
	        	ar.x = cb.x + cb.width;
	        	ar.y = cb.y + cb.height - 60;
			}
		}else if(xMove < 0) {
			if(yMove > 0) {//down left
	            ar.x = cb.x - cb.width;
	        	ar.y = (cb.y + cb.height / 2) - arSize / 2 - 18;
			}else if(yMove < 0) {//up left
	            ar.x = cb.x - cb.width;
	            ar.y = cb.y - arSize - 19;
			}else if(yMove == 0) {//left
	        	ar.x = cb.x - arSize;
	        	ar.y = cb.y + cb.height - 60;
			}
		}else if(xMove == 0) {
			if(yMove > 0) {//down
	        	ar.x = (cb.x + cb.width / 2) - arSize / 2;
	        	ar.y = cb.y + cb.height - 45;
			}else if(yMove < 0) {//up
                ar.x = (cb.x + cb.width / 2) - arSize / 2;
            	ar.y = cb.y - arSize - 19;
			}else if(yMove == 0) {//still
	        	ar.x = (cb.x + cb.width / 2) - arSize / 2;
	        	ar.y = cb.y + cb.height - 60;
			}
		}
	}
	
	public void follow(Creature c, float distance) {
        if(c.getX() >= this.x - 150 && c.getX() <= this.x + this.width + 150) {
        	if(c.getY() >= this.y - 150 && c.getY() <= this.y + this.height + 150) {
        		if(c.getX() > this.x) {
        			this.xMove = speed;
        		}else if(c.getX() < this.x) {
        			this.xMove = -speed;
        		}else if(c.getX() == this.x) {
        			this.xMove = 0;
        		}
        		
        		if(c.getY() > this.y) {
        			this.yMove = speed;
        		}else if(c.getY() < this.y) {
        			this.yMove = -speed;
        		}else if(c.getY() == this.y) {
        			this.yMove = 0;
        		}
            }else {
            	xMove = 0;
            	yMove = 0;
            }
        }else {
        	xMove = 0;
        	yMove = 0;
        }
	}

}
