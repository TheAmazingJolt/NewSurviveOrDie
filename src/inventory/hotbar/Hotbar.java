package inventory.hotbar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import gfx.Assets;
import items.Item;
import main.Handler;

public class Hotbar {

	private Handler handler;
	
	private ArrayList<Item> hotbarItems;
	private ArrayList<Item> toRemove;
	
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

	public ArrayList<Item> getHotbarItems() {
		return hotbarItems;
	}

	public int getSelectedItem() {
		return selectedItem;
	}
	
}
