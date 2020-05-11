package items.crafting.brewing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import gfx.Assets;
import gfx.Text;
import inventory.Inventory;
import inventory.InventorySlot;
import inventory.hotbar.Hotbar;
import items.Item;
import main.Handler;

public class BrewingInventory {

	private Handler handler;
	private Inventory inventory;
	private Hotbar hotbar;
	
	private boolean active = false;
	
	private ArrayList<Item> inventoryItems;
	private ArrayList<Item> inventoryItemsReset;
	private ArrayList<InventorySlot> slots;
	
	private int invX;
	private int invY;
	private int invWidth;
	private int invHeight;
	
	private int finalCraftSlotX;
	private int finalCraftSlotY;
	private int finalCraftSlotSize;
	
	private int hotbarX;
	private int hotbarY;
	private int hotbarWidth;
	private int hotbarHeight;
	private int hotbarSlot1X;
	private int hotbarSlot2X;
	private int hotbarSlot3X;
	private int hotbarSlot4X;
	private int hotbarSlot5X;
	private int hotbarSlotY;
	private int hotbarDivider;
	private int hotbarSlotSize;

	private Item itemToCraft;
	
	private InventorySlot brewSlot1;
	private InventorySlot brewSlot2;
	private InventorySlot brewSlot3;
	private InventorySlot brewSlot4Main;
	
	private BrewingSlot brewSlot4;
	
	public BrewingInventory(Handler handler, Inventory inventory, Hotbar hotbar) {
		this.handler = handler;
		this.inventory = inventory;
		this.hotbar = hotbar;
		inventoryItems = new ArrayList<Item>();
		inventoryItemsReset = new ArrayList<Item>();
		slots = new ArrayList<InventorySlot>();
		
		invX = 96;
		invY = 48;
		invWidth = 768;
		invHeight = 576;
		
		finalCraftSlotX = invX + 576;
		finalCraftSlotY = invY + 194;
		finalCraftSlotSize = 96;
		
		hotbarX = invX + 520;
		hotbarY = invY + 327;
		hotbarWidth = 210;
		hotbarHeight = 46;
		hotbarSlotSize = 35;
		hotbarDivider = 6;
		hotbarSlot1X = hotbarX + 6;
		hotbarSlot2X = hotbarSlot1X + hotbarSlotSize + hotbarDivider;
		hotbarSlot3X = hotbarSlot2X + hotbarSlotSize + hotbarDivider;
		hotbarSlot4X = hotbarSlot3X + hotbarSlotSize + hotbarDivider;
		hotbarSlot5X = hotbarSlot4X + hotbarSlotSize + hotbarDivider;
		hotbarSlotY = hotbarY + 6;
		
		brewSlot1 = new InventorySlot(17, 594, 98, handler);
		brewSlot2 = new InventorySlot(18, 748, 98, handler);
		brewSlot3 = new InventorySlot(19, 594, invY + 200, handler);
		brewSlot4Main = new InventorySlot(20, 748, invY + 200, handler);
		
		brewSlot4 = new BrewingSlot(748, invY + 200, handler);
		
		for(InventorySlot s : inventory.getSlots()) {
			if(s.getLocation() < 17) {
				slots.add(s);
			}
		}
		slots.add(brewSlot1);
		slots.add(brewSlot2);
		slots.add(brewSlot3);
		slots.add(brewSlot4Main);
	}
	
	public void tick() {
		if((handler.getKeyManager().keyJustPressed(KeyEvent.VK_E) || handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) && active) {
			for(InventorySlot s : slots) {
				s.setItemStored(null);
				s.setToDefault();
			}
			inventoryItemsReset.addAll(inventoryItems);
			inventory.getInventoryItems().clear();
			for(Item i : inventoryItemsReset) {
				inventory.addItem1(i, i.getCount());
			}
			inventoryItemsReset.clear();
			active = false;
		}else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_LEFT) && active) {
			this.active = false;
			handler.getWorld().getEntityManager().getPlayer().getInventory().setActive(true);
		}
		
		if(!active)
			return;
		
		inventoryItems = inventory.getInventoryItems();
		
		int mouseX = handler.getMouseManager().getMouseX();
		int mouseY = handler.getMouseManager().getMouseY();
		if(handler.getMouseManager().isLeftPressed()) {
			if(mouseX >= hotbarX && mouseX <= hotbarX + hotbarWidth) {
				if(mouseY >= hotbarY && mouseY <= hotbarY + hotbarHeight) {
					InventorySlot selectedSlot = null;
					boolean hotbarContainsItem = false;
					for(InventorySlot s : slots) {
						if(s.isSelected()) {
							selectedSlot = s;
						}
					}
					if(selectedSlot != null) {
						for(Item i : hotbar.getHotbarItems()) {
							if(i.getId() == selectedSlot.getItemStored().getId()) {
								hotbarContainsItem = true;
							}
						}
						if(!hotbarContainsItem) {
							hotbar.getHotbarItems().add(selectedSlot.getItemStored());
						}
					}
				}
			}
		}
		
		tickSlots();
	}
	
	public void render(Graphics g) {
		if(!active)
			return;
		g.drawImage(Assets.brewing, invX, invY, invWidth, invHeight, null);
		Text.drawString(g, "Brewing", invX + invWidth / 2, invY + invHeight / 30 - 2, true, Color.WHITE, Assets.font50);
		Text.drawString(g, "Catalyst", 642, 146, true, Color.WHITE, Assets.font28);
		Text.drawString(g, "Base", 796, 139, true, Color.WHITE, Assets.font28);
		Text.drawString(g, "Potion", 796, 155, true, Color.WHITE, Assets.font28);
		Text.drawString(g, "Final", 796, 287, true, Color.WHITE, Assets.font28);
		Text.drawString(g, "Potion", 796, 305, true, Color.WHITE, Assets.font28);
		
		for(int i = 0; i < hotbar.getHotbarItems().size(); i++) {
			if(i == 0) {
				g.drawImage(hotbar.getHotbarItems().get(i).getTexture(), hotbarSlot1X, hotbarSlotY, hotbarSlotSize, hotbarSlotSize, null);
			}else if(i == 1) {
				g.drawImage(hotbar.getHotbarItems().get(i).getTexture(), hotbarSlot2X, hotbarSlotY, hotbarSlotSize, hotbarSlotSize, null);
			}else if(i == 2) {
				g.drawImage(hotbar.getHotbarItems().get(i).getTexture(), hotbarSlot3X, hotbarSlotY, hotbarSlotSize, hotbarSlotSize, null);
			}else if(i == 3) {
				g.drawImage(hotbar.getHotbarItems().get(i).getTexture(), hotbarSlot4X, hotbarSlotY, hotbarSlotSize, hotbarSlotSize, null);
			}else if(i == 4) {
				g.drawImage(hotbar.getHotbarItems().get(i).getTexture(), hotbarSlot5X, hotbarSlotY, hotbarSlotSize, hotbarSlotSize, null);
			}
		}
		
		if(itemToCraft != null)
			g.drawImage(itemToCraft.getTexture(), finalCraftSlotX, finalCraftSlotY, finalCraftSlotSize, finalCraftSlotSize, null);
		renderSlots(g);
		
	}
	
	public void tickSlots() {
		for(InventorySlot s : slots) {
			s.tick();
		}
		brewSlot4.tick();
	}
	
	public void renderSlots(Graphics g) {
		brewSlot4.render(g);
		for(InventorySlot s : slots) {
			if(!s.isSelected())
				s.render(g);
		}
		for(InventorySlot s : slots) {
			if(s.isSelected())
				s.render(g);
		}
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public ArrayList<InventorySlot> getSlots() {
		return slots;
	}

	public void setSlots(ArrayList<InventorySlot> slots) {
		this.slots = slots;
	}
	
}
