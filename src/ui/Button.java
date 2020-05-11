package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import gfx.Assets;
import gfx.Text;
import main.Handler;

public class Button {

	private int x;
	private int y;
	private int textX;
	private int textY;
	private int width;
	private int height;
	private int mouseX;
	private int mouseY;

	private boolean centered;
	private boolean hovering;
	private boolean clicked;
	private boolean active = true;
	
	private String text;
	private Color textColor = Color.WHITE;
	
	private BufferedImage texture;
	private Handler handler;
	
	public Button(BufferedImage texture, int x, int y, int width, int height, boolean centered, String text, Handler handler) {
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.centered = centered;
		this.handler = handler;
		this.text = text;
		if(centered) {
			this.x = x - width / 2;
		}
		setTextX();
	}
	
	public void tick() {
		clicked = false;
		mouseX = handler.getMouseManager().getMouseX();
		mouseY = handler.getMouseManager().getMouseY();
		if(mouseX >= x && mouseX <= x + width) {
			if(mouseY >= y && mouseY <= y + height) {
				hovering = true;
			}else {
				hovering = false;
			}
		}else {
			hovering = false;
		}
		if(hovering) {
			textColor = Color.YELLOW;
		}else {
			textColor = Color.WHITE;
		}
		if(handler.getMouseManager().isLeftPressed()) {
			if(hovering) {
				this.clicked = true;
			}
		}
	}
	
	public void render(Graphics g) {
		if(!active) {
			return;
		}
		g.drawImage(texture, x, y, width, height, null);
		Text.drawString(g, text, textX, textY, true, textColor, Assets.font50);
	}
	
	public boolean isClicked() {
		return clicked;
	}

	public void setX(int x) {
		this.x = x;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setTextX() {
		this.textX = this.x + this.width / 2;
		this.textY = this.y + this.height / 2;
	}

	public boolean isCentered() {
		return centered;
	}

	public void setCentered(boolean centered) {
		this.centered = centered;
	}
	
}
