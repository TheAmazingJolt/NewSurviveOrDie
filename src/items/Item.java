package items;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gfx.Assets;
import main.Handler;

public class Item {

	public static ArrayList<Item> items = new ArrayList<Item>();
	
	public static Item wood = new Item(Assets.woodItem, 1);
	public static Item stone = new Item(Assets.stoneItem, 2);
	public static Item axe = new Item(Assets.axeItem, true, 2, 3);
	public static Item cutWood = new Item(Assets.cutWoodItem, 4);
	public static Item flint = new Item(Assets.flintItem, 5);
	public static Item ironOre = new Item(Assets.ironOreItem, 6);
	public static Item fire = new Item(Assets.fireItem, 7);
	public static Item ironBar = new Item(Assets.ironBarItem, 8);
	public static Item woodenHandle = new Item(Assets.woodenHandleItem, 9);
	public static Item ironAxe = new Item(Assets.ironAxeItem, true, 3, 10);
	public static Item rottenFlesh = new Item(Assets.rottenFleshItem, 11);
	
	protected Handler handler;
	protected BufferedImage texture;
	
	protected final int id;
	
	protected Rectangle bounds;
	
	protected int x;
	protected int y;
	protected int count;
	protected int attackDamage = 1;
	
	protected boolean pickedUp = false;
	
	protected boolean isTool = false;
	protected boolean newlyCrafted = false;
	
	public Item(BufferedImage texture, int id) {
		pickedUp = false;
		this.texture = texture;
		this.id = id;
		this.count = 1;
		this.isTool = false;
		bounds = new Rectangle(x, y, 32, 32);
		items.add(this);
	}
	
	public Item(BufferedImage texture, boolean isTool, int attackDamage, int id) {
		pickedUp = false;
		this.texture = texture;
		this.id = id;
		this.count = 1;
		this.isTool = isTool;
		this.attackDamage = attackDamage;
		bounds = new Rectangle(x, y, 32, 32);
		items.add(this);
	}
	
	public void tick()
    {
        if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0.0F, 0.0F).intersects(bounds))
        {
            pickedUp = true;
            handler.getWorld().getEntityManager().getPlayer().getInventory().addItem1(this, 1);
        }
    }
	
	public void render(Graphics g)
    {
        if(handler == null)
        {
            return;
        } else
        {
            render(g, (int)((float)x - handler.getGameCamera().getxOffset()), (int)((float)y - handler.getGameCamera().getyOffset()));
            return;
        }
    }
	
	public void render(Graphics g, int x, int y)
    {
        g.drawImage(texture, x, y, 32, 32, null);
    }
	
	public Item createNew(int count)
    {
        Item i = new Item(texture, id);
        i.setPickedUp(true);
        i.setCount(count);
        return i;
    }
	
	public Item createNew(int x, int y)
    {
        Item i = new Item(texture, id);
        i.setPosition(x, y);
        return i;
    }
	
	public void setPosition(int x, int y)
    {
        this.x = x;
        this.y = y;
        bounds.x = x;
        bounds.y = y;
    }

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public boolean isPickedUp() {
		return pickedUp;
	}

	public void setPickedUp(boolean pickedUp) {
		this.pickedUp = pickedUp;
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getId() {
		return id;
	}

	public boolean isTool() {
		return isTool;
	}

	public void setTool(boolean isTool) {
		this.isTool = isTool;
	}

	public boolean isNewlyCrafted() {
		return newlyCrafted;
	}

	public void setNewlyCrafted(boolean newlyCrafted) {
		this.newlyCrafted = newlyCrafted;
	}

	public int getAttackDamage() {
		return attackDamage;
	}

	public void setAttackDamage(int attackDamage) {
		this.attackDamage = attackDamage;
	}
	
}
