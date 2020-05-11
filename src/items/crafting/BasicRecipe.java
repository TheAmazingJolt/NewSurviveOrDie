package items.crafting;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gfx.Assets;
import items.Item;

public class BasicRecipe {

	public static ArrayList<BasicRecipe> recipes = new ArrayList<BasicRecipe>();
	
	public static BasicRecipe axeItem = new BasicRecipe(Assets.axeItem, Item.wood, Item.stone, Item.axe, 1);
	public static BasicRecipe cutWoodItem = new BasicRecipe(Assets.cutWoodItem, Item.axe, Item.wood, Item.cutWood, 2);
	public static BasicRecipe cutWoodItem2 = new BasicRecipe(Assets.cutWoodItem, Item.ironAxe, Item.wood, Item.cutWood, 3);
	public static BasicRecipe fireItem = new BasicRecipe(Assets.fireItem, Item.cutWood, Item.flint, Item.fire, 4);
	public static BasicRecipe ironBarItem = new BasicRecipe(Assets.ironBarItem, Item.ironOre, Item.fire, Item.ironBar, 5);
	public static BasicRecipe woodenHandleItem = new BasicRecipe(Assets.woodenHandleItem, Item.axe, Item.cutWood, Item.woodenHandle, 6);
	public static BasicRecipe ironAxeItem = new BasicRecipe(Assets.ironAxeItem, Item.woodenHandle, Item.ironBar, Item.ironAxe, 7);
	public static BasicRecipe bottleItem = new BasicRecipe(Assets.bottleItem, Item.crushedIron, Item.fire, Item.bottle, 8);
	public static BasicRecipe hammerHeadItem = new BasicRecipe(Assets.hammerHeadItem, Item.axe, Item.ironBar, Item.hammerHead, 9);
	public static BasicRecipe hammerHeadItem2 = new BasicRecipe(Assets.hammerHeadItem, Item.ironAxe, Item.ironBar, Item.hammerHead, 10);
	public static BasicRecipe hammerItem = new BasicRecipe(Assets.hammerItem, Item.hammerHead, Item.woodenHandle, Item.hammer, 11);
	public static BasicRecipe crushedIronItem = new BasicRecipe(Assets.crushedIronItem, Item.stone, Item.ironBar, Item.crushedIron, 12);
	public static BasicRecipe crushedIronItem2 = new BasicRecipe(Assets.crushedIronItem, Item.hammer, Item.ironBar, Item.crushedIron, 13);
	
	protected BufferedImage texture;
	protected Item item1;
	protected Item item2;
	protected Item finalItem;
	
	protected int id;
	
	public BasicRecipe(BufferedImage texture, Item item1, Item item2, Item finalItem, int id) {
		this.texture = texture;
		this.item1 = item1;
		this.item2 = item2;
		this.finalItem = finalItem;
		this.id = id;
		recipes.add(this);
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}

	public Item getItem1() {
		return item1;
	}

	public void setItem1(Item item1) {
		this.item1 = item1;
	}

	public Item getItem2() {
		return item2;
	}

	public void setItem2(Item item2) {
		this.item2 = item2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Item getFinalItem() {
		return finalItem;
	}

	public void setFinalItem(Item finalItem) {
		this.finalItem = finalItem;
	}
	
}
