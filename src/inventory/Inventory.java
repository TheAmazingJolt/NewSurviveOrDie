package inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import gfx.Assets;
import gfx.Text;
import inventory.hotbar.Hotbar;
import items.Item;
import items.crafting.CraftingSlot;
import main.Handler;

public class Inventory {
	
	private Handler handler;
	private Hotbar hotbar;
	
	private boolean active = false;
	
	private static ArrayList<Item> inventoryItems;
	private static ArrayList<Item> inventoryItemsReset;
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
	
	private InventorySlot slot1;
	private InventorySlot slot2;
	private InventorySlot slot3;
	private InventorySlot slot4;
	private InventorySlot slot5;
	private InventorySlot slot6;
	private InventorySlot slot7;
	private InventorySlot slot8;
	private InventorySlot slot9;
	private InventorySlot slot10;
	private InventorySlot slot11;
	private InventorySlot slot12;
	private InventorySlot slot13;
	private InventorySlot slot14;
	private InventorySlot slot15;
	private InventorySlot slot16;
	
	private InventorySlot craftSlot1;
	private InventorySlot craftSlot2;
	private InventorySlot craftSlot3Main;

	private CraftingSlot craftSlot3;
	
	public Inventory(Handler handler, Hotbar hotbar) {
		this.handler = handler;
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
		
		slot1 = new InventorySlot(1, invX + 51, invY + 50, handler);
		slot2 = new InventorySlot(2, slot1.getSlotX() + 105, slot1.getSlotY(), handler);
		slot3 = new InventorySlot(3, slot2.getSlotX() + 105, slot1.getSlotY(), handler);
		slot4 = new InventorySlot(4, slot3.getSlotX() + 105, slot1.getSlotY(), handler);

		slot5 = new InventorySlot(5, slot1.getSlotX(), slot1.getSlotY() + 105, handler);
		slot6 = new InventorySlot(6, slot2.getSlotX(), slot5.getSlotY(), handler);
		slot7 = new InventorySlot(7, slot3.getSlotX(), slot5.getSlotY(), handler);
		slot8 = new InventorySlot(8, slot4.getSlotX(), slot5.getSlotY(), handler);

		slot9 = new InventorySlot(9, slot1.getSlotX(), slot5.getSlotY() + 105, handler);
		slot10 = new InventorySlot(10, slot2.getSlotX(), slot9.getSlotY(), handler);
		slot11 = new InventorySlot(11, slot3.getSlotX(), slot9.getSlotY(), handler);
		slot12 = new InventorySlot(12, slot4.getSlotX(), slot9.getSlotY(), handler);

		slot13 = new InventorySlot(13, slot1.getSlotX(), slot9.getSlotY() + 105, handler);
		slot14 = new InventorySlot(14, slot2.getSlotX(), slot13.getSlotY(), handler);
		slot15 = new InventorySlot(15, slot3.getSlotX(), slot13.getSlotY(), handler);
		slot16 = new InventorySlot(16, slot4.getSlotX(), slot13.getSlotY(), handler);
		
		craftSlot1 = new InventorySlot(17, slot4.getSlotX() + 132, slot1.getSlotY(), handler);
		craftSlot2 = new InventorySlot(18, craftSlot1.getSlotX() + 154, slot1.getSlotY(), handler);
		craftSlot3Main = new InventorySlot(19, invX + 576, invY + 194, handler);
		
		craftSlot3 = new CraftingSlot(invX + 576, invY + 194, handler);
		
		slots.add(slot1);
		slots.add(slot2);
		slots.add(slot3);
		slots.add(slot4);
		slots.add(slot5);
		slots.add(slot6);
		slots.add(slot7);
		slots.add(slot8);
		slots.add(slot9);
		slots.add(slot10);
		slots.add(slot11);
		slots.add(slot12);
		slots.add(slot13);
		slots.add(slot14);
		slots.add(slot15);
		slots.add(slot16);
		slots.add(craftSlot1);
		slots.add(craftSlot2);
		slots.add(craftSlot3Main);
	}
	
	public void tick() {
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E) && !handler.getWorld().getEntityManager().getPlayer().getBrewing().isActive()) {
			if(active) {
				for(InventorySlot s : slots) {
					s.setItemStored(null);
					s.setToDefault();
				}
				inventoryItemsReset.addAll(inventoryItems);
				inventoryItems.clear();
				for(Item i : inventoryItemsReset) {
					addItem1(i, i.getCount());
				}
				inventoryItemsReset.clear();
			}
			active = !active;
		}else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE) && active) {
			for(InventorySlot s : slots) {
				s.setItemStored(null);
				s.setToDefault();
			}
			inventoryItemsReset.addAll(inventoryItems);
			inventoryItems.clear();
			for(Item i : inventoryItemsReset) {
				addItem1(i, i.getCount());
			}
			inventoryItemsReset.clear();
			active = false;
		}else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_RIGHT) && active) {
			this.active = false;
			handler.getWorld().getEntityManager().getPlayer().getBrewing().setActive(true);
		}
		
		if(!active)
			return;
		
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
		g.drawImage(Assets.inventory, invX, invY, invWidth, invHeight, null);
		Text.drawString(g, "Inventory", invX + invWidth / 2, invY + invHeight / 30 - 2, true, Color.WHITE, Assets.font50);
		
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
		craftSlot3.tick();
	}
	
	public void renderSlots(Graphics g) {
		craftSlot3.render(g);
		for(InventorySlot s : slots) {
			if(!s.isSelected())
				s.render(g);
		}
		for(InventorySlot s : slots) {
			if(s.isSelected())
				s.render(g);
		}
	}
	
	public void addItem1(Item i, int amt) {
		for(Item item : inventoryItems) {
			if(item.getId() == i.getId()) {
				item.setCount(item.getCount() + amt);
				return;
			}
		}
		i.setCount(amt);
		inventoryItems.add(i);
		for(int ii = 0; ii < inventoryItems.size(); ii++) {
			for(InventorySlot s : slots) {
				if(s.getLocation() == ii + 1) {
					s.setItemStored(inventoryItems.get(ii));
				}
			}
		}
	}
	
	public void addItem2(Item i, int amt) {
		for(Item item : inventoryItems) {
			if(item.getId() == i.getId()) {
				item.setCount(item.getCount() + amt);
				return;
			}
		}
		i.setCount(amt);
		inventoryItems.add(i);
	}
	
	public void removeItem(Item item, int amt)
    {
        for(Item i : inventoryItems) {
        	if(item.getId() == i.getId()) {
        		i.setCount(i.getCount() - amt);
        		if(i.getCount() <= 0) {
        			inventoryItems.remove(i);
        		}
        		break;
        	}else {
        		continue;
        	}
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

	public ArrayList<Item> getInventoryItems() {
		return inventoryItems;
	}

	public static void setInventoryItems(ArrayList<Item> inventoryItems) {
		Inventory.inventoryItems = inventoryItems;
	}

	public CraftingSlot getCraftSlot3() {
		return craftSlot3;
	}

	public void setCraftSlot3(CraftingSlot craftSlot3) {
		this.craftSlot3 = craftSlot3;
	}
	
}
