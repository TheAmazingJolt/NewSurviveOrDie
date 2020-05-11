package entities.statics;

import java.awt.Color;
import java.awt.Graphics;

import gfx.Assets;
import items.Item;
import main.Handler;

public class IronOre extends StaticEntity{

	private static int maxHealth = 20;
	
	public IronOre(Handler handler, float x, float y, int id) {
		super(handler, x, y, 64, 64, maxHealth, id, "IronOre");
		this.id = id;
		this.health = maxHealth;
		bounds.x = 3;
		bounds.y = (int) (height / 2);
		bounds.width = width - 6;
		bounds.height = (int) (height - (height / 2));
	}

	public void tick() {
		if(!active) {
            bounds.width = 0;
            bounds.height = 0;
            return;
        }
	}

	public void render(Graphics g) {
		g.drawImage(Assets.ironOre, (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width, height, null);
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
		handler.getWorld().getItemManager().addItem(Item.ironOre.createNew((int) x + 16, (int) y + 16) );
	}

}
