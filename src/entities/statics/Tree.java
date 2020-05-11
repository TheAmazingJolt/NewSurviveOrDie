package entities.statics;

import java.awt.Color;
import java.awt.Graphics;

import gfx.Assets;
import items.Item;
import main.Handler;

public class Tree extends StaticEntity{

	private static int maxHealth = 10;
	
	public Tree(Handler handler, float x, float y, int id) {
		super(handler, x, y, 64, 128, maxHealth, id, "Tree");
		this.id = id;
		this.health = maxHealth;
		bounds.x = 10;
		bounds.y = (int) (height / 1.5);
		bounds.width = width - 20;
		bounds.height = (int) (height - (height / 1.5));
	}

	public void tick() {
		if(!active) {
            bounds.width = 0;
            bounds.height = 0;
            return;
        }
	}

	public void render(Graphics g) {
		g.drawImage(Assets.tree, (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width, height, null);
		g.setColor(Color.red);
		g.fillRect((int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset() - 5), width, 3);
		g.setColor(Color.green);
		g.fillRect((int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset() - 5), (int)(width * ((float) health/maxHealth)), 3);
		if(handler.getGame().isDebug()) {
    		g.setColor(Color.RED);
    	    g.drawRect((int) (this.getCollisionBounds(0.0f, 0.0f).x - handler.getGameCamera().getxOffset()), (int) (this.getCollisionBounds(0.0f, 0.0f).y - handler.getGameCamera().getyOffset()), this.getCollisionBounds(0.0f, 0.0f).width, this.getCollisionBounds(0.0f, 0.0f).height);
    	    g.setColor(null);
    	}
	}
	
	public void die() {
		handler.getWorld().getItemManager().addItem(Item.wood.createNew((int) x + 16, (int) y + 64) );
	}

}
