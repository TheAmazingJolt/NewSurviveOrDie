package items.crafting;

import java.awt.Color;
import java.awt.Graphics;

import gfx.Assets;
import gfx.Text;
import inventory.Inventory;
import inventory.InventorySlot;
import items.Item;
import main.Handler;

public class CraftingSlot {

	private int slotX;
	private int slotY;
	private int startX;
	private int startY;
	
	private int textX;
	private int textY;
	
	private int slotWidth = 96;
	private int slotHeight = 96;
	
	private Item itemToCraft = null;
	
	private Handler handler;
	
	private boolean crafted;
	private boolean checkingTicks;
	private boolean itemAlreadyStored;
	
	private int ticks;
	
	public CraftingSlot(int sx, int sy, Handler handler) {
		slotX = sx;
		slotY = sy;
		startX = sx;
		startY = sy;
		this.handler = handler;
		updateTextCoords();
	}
	
	public void tick() {
		if(checkingTicks && ticks < 6) {
			ticks++;
			return;
		}else {
			checkingTicks = false;
			ticks = 0;
		}
		
		Inventory inventory = handler.getWorld().getEntityManager().getPlayer().getInventory();
		
		Item craftItem1 = null;
		Item craftItem2 = null;
		for(InventorySlot s : inventory.getSlots()) {
			if(s.getLocation() == 17 && !s.isSelected()) {
				craftItem1 = s.getItemStored();
			}else if(s.getLocation() == 18 && !s.isSelected()) {
				craftItem2 = s.getItemStored();
			}
		}
		
		if(craftItem1 != null && craftItem2 != null) {
			for(BasicRecipe r : BasicRecipe.recipes) {
				if(r.getItem1().getId() == craftItem1.getId()) {
					if(r.getItem2().getId() == craftItem2.getId()) {
						itemToCraft = r.getFinalItem();
					}
				}else if(r.getItem2().getId() == craftItem1.getId()) {
					if(r.getItem1().getId() == craftItem2.getId()) {
						itemToCraft = r.getFinalItem();
					}
				}
			}
		}
		
		int mouseX = handler.getMouseManager().getMouseX();
		int mouseY = handler.getMouseManager().getMouseY();
		
		if(itemToCraft != null) {
			if(craftItem1 == null || craftItem2 == null) {
				itemToCraft = null;
				return;
			}
			
			if(handler.getMouseManager().isLeftPressed() && !crafted) {
				if(mouseX >= slotX && mouseX <= slotX + slotWidth) {
					if(mouseY >= slotY && mouseY <= slotY + slotHeight) {
						if(!craftItem1.isTool())
							inventory.removeItem(craftItem1, 1);
						if(!craftItem2.isTool())
							inventory.removeItem(craftItem2, 1);
						inventory.addItem2(itemToCraft, 1);
						crafted = true;
					}
				}
			}
			
			itemAlreadyStored = false;
			for(Item i : inventory.getInventoryItems()) {
				if(i.getId() == itemToCraft.getId() && i.getCount() > 1) {
					itemAlreadyStored = true;
				}
			}
	
			for(InventorySlot ss : inventory.getSlots()) {
				if(ss.getLocation() == 19 && crafted && !itemAlreadyStored) {
					ss.setItemStored(itemToCraft);
					ss.getItemStored().setNewlyCrafted(true);
					ss.setSelected(true);
					ss.setCheckingTicks(true);
					reset();
					try {
						Thread.sleep(150);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}else if(ss.getLocation() == 19 && crafted && itemAlreadyStored) {
					if(ss.getItemStored() == itemToCraft)
						ss.setSelected(true);
					reset();
					try {
						Thread.sleep(150);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
	}
	
	public void render(Graphics g) {
		if(itemToCraft != null) {
			g.drawImage(itemToCraft.getTexture(), slotX, slotY, slotWidth, slotHeight, null);
			if(crafted)
				Text.drawString(g, Integer.toString(itemToCraft.getCount()), textX, textY - 4, true, Color.WHITE, Assets.font35);
		}
	}
	
	public void updateTextCoords() {
		textX = slotX + (slotWidth / 2);
		textY = slotY + 90;
	}
	
	public void reset() {
		itemToCraft = null;
		crafted = false;
		slotX = startX;
		slotY = startY;
		updateTextCoords();
	}

	public int getSlotX() {
		return slotX;
	}

	public void setSlotX(int slotX) {
		this.slotX = slotX;
	}

	public int getSlotY() {
		return slotY;
	}

	public void setSlotY(int slotY) {
		this.slotY = slotY;
	}

	public int getTextX() {
		return textX;
	}

	public void setTextX(int textX) {
		this.textX = textX;
	}

	public int getTextY() {
		return textY;
	}

	public void setTextY(int textY) {
		this.textY = textY;
	}

	public int getStartX() {
		return startX;
	}

	public void setStartX(int startX) {
		this.startX = startX;
	}

	public int getStartY() {
		return startY;
	}

	public void setStartY(int startY) {
		this.startY = startY;
	}

	public boolean isCrafted() {
		return crafted;
	}
	
}
