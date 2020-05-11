package inventory.hotbar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import entities.creatures.Player;
import gfx.Assets;
import items.Item;
import main.Handler;
import tiles.Tile;

public class Hotbar {

	private Handler handler;
	
	private ArrayList<Item> hotbarItems;
	private ArrayList<Item> toRemove;
	private ArrayList<Item> toAdd;
	
	private int hotbarX;
	private int hotbarY;
	private int hotbarWidth;
	private int hotbarHeight;
	
	private int slot1X;
    private int slot2X;
    private int slot3X;
    private int slot4X;
    private int slot5X;
	private int slotY;
	private int slotSize;
	
	private int dividerWidth;
	
	private int selectedItem = 0;
	
	public Hotbar(Handler handler) {
		hotbarX = 332;
		hotbarY = 645;
		hotbarWidth = 299;
		hotbarHeight = 56;
		
		slot1X = hotbarX + 7;
		slotY = hotbarY + 5;
		slotSize = 45;
		dividerWidth = 15;
		
		slot2X = slot1X + slotSize + dividerWidth;	
		slot3X = slot2X + slotSize + dividerWidth;	
		slot4X = slot3X + slotSize + dividerWidth;	
		slot5X = slot4X + slotSize + dividerWidth;
		
		this.handler = handler;
		hotbarItems = new ArrayList<Item>();
		toRemove = new ArrayList<Item>();
		toAdd = new ArrayList<Item>();
	}
	
	public void tick() {
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_1)) {
			selectedItem = 0;
		}else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_2)) {
				selectedItem = 1;
		}else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_3)) {
			selectedItem = 2;
		}else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_4)) {
			selectedItem = 3;
		}else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_5)) {
			selectedItem = 4;
		}
		
		if(hotbarItems.size() <= 1) {
			selectedItem = 0;
		}else if(hotbarItems.size() == 2 && selectedItem > 1) {
			selectedItem = 1;
		}else if(hotbarItems.size() == 3 && selectedItem > 2) {
			selectedItem = 2;
		}else if(hotbarItems.size() == 4 && selectedItem > 3) {
			selectedItem = 3;
		}else if(hotbarItems.size() == 5 && selectedItem > 4) {
			selectedItem = 4;
		}
		
		hotbarItems.addAll(toAdd);
		toAdd.clear();
		
		boolean inventoryHasItem1 = false;
		boolean inventoryHasItem2 = false;
		boolean inventoryHasItem3 = false;
		boolean inventoryHasItem4 = false;
		boolean inventoryHasItem5 = false;
		for(int i = 0; i < hotbarItems.size(); i++) {
			for(Item ii : handler.getWorld().getEntityManager().getPlayer().getInventory().getInventoryItems()) {
				if(hotbarItems.get(i).getId() == ii.getId()) {
					if(i == 0) {
						inventoryHasItem1 = true;
						break;
					}else if(i == 1) {
						inventoryHasItem2 = true;
						break;
					}else if(i == 2) {
						inventoryHasItem3 = true;
						break;
					}else if(i == 3) {
						inventoryHasItem4 = true;
						break;
					}else if(i == 4) {
						inventoryHasItem5 = true;
						break;
					}
				}
			}
			if(i == 0 && !inventoryHasItem1) {
				toRemove.add(hotbarItems.get(i));
			}else if(i == 1 && !inventoryHasItem2) {
				toRemove.add(hotbarItems.get(i));
			}else if(i == 2 && !inventoryHasItem3) {
				toRemove.add(hotbarItems.get(i));
			}else if(i == 3 && !inventoryHasItem4) {
				toRemove.add(hotbarItems.get(i));
			}else if(i == 4 && !inventoryHasItem5) {
				toRemove.add(hotbarItems.get(i));
			}
		}
		hotbarItems.removeAll(toRemove);
		toRemove.clear();
		
		if(hotbarItems.size() >= 1 && !(selectedItem >= hotbarItems.size())
				&& !handler.getWorld().getEntityManager().getPlayer().getInventory().isActive()
				&& !handler.getWorld().getEntityManager().getPlayer().getBrewing().isActive()) {
			if(handler.getMouseManager().isRightPressed()) {
				use();
			}
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(Assets.hotbar, hotbarX, hotbarY, hotbarWidth, hotbarHeight, null);
		for(int i = 0; i < hotbarItems.size(); i++) {
			if(i == 0) {
				g.drawImage(hotbarItems.get(i).getTexture(), slot1X, slotY, slotSize, slotSize, null);
			}else if(i == 1) {
				g.drawImage(hotbarItems.get(i).getTexture(), slot2X, slotY, slotSize, slotSize, null);
			}else if(i == 2) {
				g.drawImage(hotbarItems.get(i).getTexture(), slot3X, slotY, slotSize, slotSize, null);
			}else if(i == 3) {
				g.drawImage(hotbarItems.get(i).getTexture(), slot4X, slotY, slotSize, slotSize, null);
			}else if(i == 4) {
				g.drawImage(hotbarItems.get(i).getTexture(), slot5X, slotY, slotSize, slotSize, null);
			}
		}
		if(selectedItem == 0) {
			g.setColor(Color.YELLOW);
			g.drawRect(slot1X, slotY, slotSize, slotSize);
			g.setColor(null);
		}else if(selectedItem == 1) {
			g.setColor(Color.YELLOW);
			g.drawRect(slot2X, slotY, slotSize, slotSize);
			g.setColor(null);
		}else if(selectedItem == 2) {
			g.setColor(Color.YELLOW);
			g.drawRect(slot3X, slotY, slotSize, slotSize);
			g.setColor(null);
		}else if(selectedItem == 3) {
			g.setColor(Color.YELLOW);
			g.drawRect(slot4X, slotY, slotSize, slotSize);
			g.setColor(null);
		}else if(selectedItem == 4) {
			g.setColor(Color.YELLOW);
			g.drawRect(slot5X, slotY, slotSize, slotSize);
			g.setColor(null);
		}
	}

	public void use() {
		Player player = handler.getWorld().getEntityManager().getPlayer();
		if(hotbarItems.get(selectedItem).getId() == Item.bottle.getId()) {
			float pX = player.getX() + player.getWidth() / 2;
			float pY = player.getY() + player.getHeight() / 2;
			int tileX = (int) Math.floor(pX / 64);
			int tileY = (int) Math.floor(pY / 64);
			if(handler.getWorld().getTile(tileX, tileY).id == Tile.waterTile.id) {
				player.getInventory().removeItem(Item.bottle, 1);
				player.getInventory().addItem1(Item.waterBottle, 1);
				toAdd.add(Item.waterBottle);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return;
		}else if(hotbarItems.get(selectedItem).getId() == Item.healthPotion.getId()) {
			player.getInventory().removeItem(Item.healthPotion, 1);
			player.getInventory().addItem1(Item.bottle, 1);
			toAdd.add(Item.bottle);
			player.setHealth(player.getHealth() + 5);
			if(player.getHealth() > player.getMaxHealth()) {
				player.setHealth(player.getMaxHealth());
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return;
		}else if(hotbarItems.get(selectedItem).getId() == Item.healthBoostPotion.getId()) {
			player.getInventory().removeItem(Item.healthBoostPotion, 1);
			player.getInventory().addItem1(Item.bottle, 1);
			toAdd.add(Item.bottle);
			player.setMaxHealth(player.getMaxHealth() + 5);
			player.setHealth(player.getMaxHealth());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return;
		}else if(hotbarItems.get(selectedItem).getId() == Item.bossKey.getId()) {
			float mX = handler.getMouseManager().getMouseX() + handler.getGameCamera().getxOffset();
			float mY = handler.getMouseManager().getMouseY() + handler.getGameCamera().getyOffset();
			int tileX = (int) Math.floor(mX / 64);
			int tileY = (int) Math.floor(mY / 64);
			if(handler.getWorld().getTile(tileX, tileY).id == Tile.bossEntrance.id) {
				player.getInventory().removeItem(Item.bossKey, 1);
				handler.getWorld().getTile(tileX, tileY).setUnlocked(true);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return;
		}
		
	}
	
	public ArrayList<Item> getHotbarItems() {
		return hotbarItems;
	}

	public int getSelectedItem() {
		return selectedItem;
	}
	
}
