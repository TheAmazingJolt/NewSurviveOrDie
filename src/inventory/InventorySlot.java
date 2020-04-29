package inventory;

import java.awt.Color;
import java.awt.Graphics;

import gfx.Assets;
import gfx.Text;
import items.Item;
import main.Handler;

public class InventorySlot {

	private int slotX;
	private int slotY;
	private int startX;
	private int startY;
	private int originalX;
	private int originalY;
	
	private int textX;
	private int textY;
	
	private int slotWidth = 96;
	private int slotHeight = 96;
	
	private int startLocation;
	private int location;
	
	private Item itemStored;
	
	private Handler handler;
	
	private boolean selected;
	private boolean checkingTicks;
	
	private int ticks;
	
	public InventorySlot(int location, int sx, int sy, Handler handler) {
		slotX = sx;
		slotY = sy;
		startX = sx;
		startY = sy;
		originalX = sx;
		originalY = sy;
		this.location = location;
		this.startLocation = location;
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
		
		if(itemStored != null) {
			boolean itemExists = false;
			for(Item i : handler.getWorld().getEntityManager().getPlayer().getInventory().getInventoryItems()) {
				if(i.getId() == itemStored.getId()) {
					itemExists = true;
				}
			}
			if(!itemExists) {
				itemStored = null;
			}
		}
		
		int mouseX = handler.getMouseManager().getMouseX();
		int mouseY = handler.getMouseManager().getMouseY();
		if(handler.getMouseManager().isLeftPressed() && !selected) {
			if(mouseX >= slotX && mouseX <= slotX + slotWidth) {
				if(mouseY >= slotY && mouseY <= slotY + slotHeight) {
					boolean allDeselected = true;
					for(InventorySlot s : handler.getWorld().getEntityManager().getPlayer().getInventory().getSlots()) {
						if(s != this) {
							if(s.isSelected()) {
								allDeselected = false;
							}
						}
					}
					if(allDeselected) {
						selected = true;
						checkingTicks = true;
						return;
					}
					
				}
			}
		}
		
		if(selected) {
			slotX = mouseX - (slotWidth / 2);
			slotY = mouseY - (slotHeight / 2);
			updateTextCoords();
		}
		
		if(handler.getMouseManager().isLeftPressed() && selected) {
			for(InventorySlot s : handler.getWorld().getEntityManager().getPlayer().getInventory().getSlots()) {
				if(s != this) {
					if(mouseX >= s.getSlotX() && mouseX <= s.getSlotX() + slotWidth) {
						if(mouseY >= s.getSlotY() && mouseY <= s.getSlotY() + slotHeight) {
							if(itemStored != null) {
								if(!itemStored.isNewlyCrafted() && (s.getLocation() == 19 || location == 19))
									break;
								Item currentItem = this.itemStored;
								Item newItem = s.itemStored;
								s.setItemStored(currentItem);
								if(newItem == null) {
									this.setItemStored(null);
									this.selected = false;
									s.setSelected(false);
									this.updateTextCoords();
									checkingTicks = true;
									s.setCheckingTicks(true);
								}else {
									this.setItemStored(newItem);
									this.selected = false;
									s.setSelected(false);
									this.updateTextCoords();
									checkingTicks = true;
									s.setCheckingTicks(true);
								}
								if(itemStored != null && itemStored.isNewlyCrafted()) {
									startLocation = this.getLocation();
									originalX = this.startX;
									originalY = this.startY;
									itemStored.setNewlyCrafted(false);
								}
							}
						}
					}
				}else if(s == this) {
					if(mouseX >= s.getSlotX() && mouseX <= s.getSlotX() + slotWidth) {
						if(mouseY >= s.getSlotY() && mouseY <= s.getSlotY() + slotHeight) {
							this.setSlotX(startX);
							this.setSlotY(startY);
							this.selected = false;
							this.updateTextCoords();
							checkingTicks = true;
						}
					}
				}
			}
		}
	}
	
	public void render(Graphics g) {
		if(itemStored != null) {
			g.drawImage(itemStored.getTexture(), slotX, slotY, slotWidth, slotHeight, null);
			Text.drawString(g, Integer.toString(itemStored.getCount()), textX, textY - 4, true, Color.WHITE, Assets.font35);
		}
	}
	
	public void updateTextCoords() {
		textX = slotX + (slotWidth / 2);
		textY = slotY + 90;
	}
	
	public void reset() {
		selected = false;
		slotX = startX;
		slotY = startY;
		updateTextCoords();
	}
	
	public void setToDefault() {
		this.location = startLocation;
		this.startX = originalX;
		this.startY = originalY;
		reset();
	}

	public Item getItemStored() {
		return itemStored;
	}

	public void setItemStored(Item itemStored) {
		this.itemStored = itemStored;
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

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
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

	public boolean isCheckingTicks() {
		return checkingTicks;
	}

	public void setCheckingTicks(boolean checkingTicks) {
		this.checkingTicks = checkingTicks;
	}
	
}
