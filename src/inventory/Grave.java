package inventory;

import java.awt.Graphics;
import java.util.ArrayList;

import entities.creatures.Player;
import gfx.Assets;
import items.Item;
import main.Handler;

public class Grave {

	private int x;
	private int y;
	private int width = 64;
	private int height = 64;
	
	private ArrayList<Item> items = new ArrayList<Item>();
	private ArrayList<Integer> counts = new ArrayList<Integer>();
 	
	private Player player;
	
	private Handler handler;
	
	private boolean collected = false;
	
	public Grave(ArrayList<Item> items, Player player, float x, float y, Handler handler) {
		this.x = (int) x;
		this.y = (int) y;
		for(int i = 0; i < items.size(); i++) {
			this.items.add(items.get(i));
			counts.add(items.get(i).getCount());
			System.out.println(items.get(i).getCount());
		}
		this.player = player;
		this.handler = handler;
	}
	
	public void tick() {
		if(collected)
			return;
		if(player.getX() >= this.x && player.getX() <= this.x + this.width) {
			if(player.getY() >= this.y - player.getHeight() && player.getY() <= this.y + this.height) {
				for(int i = 0; i < items.size(); i++) {
					player.getInventory().addItem1(items.get(i), counts.get(i));
				}
				collected = true;
			}
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(Assets.grave, (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width, height, null);
	}

	public boolean isCollected() {
		return collected;
	}
	
	
	
}
