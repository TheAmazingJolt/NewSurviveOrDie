package items.crafting.brewing;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gfx.Assets;
import items.Item;

public class BrewingRecipe {

	public static ArrayList<BrewingRecipe> recipes = new ArrayList<BrewingRecipe>();
	
	public static BrewingRecipe healthBoostPotion = new BrewingRecipe(Assets.healthBoostPotionItem, Item.healthPotion, Item.ironBar, 2, Item.healthBoostPotion, 1);
	public static BrewingRecipe healthPotion = new BrewingRecipe(Assets.healthPotionItem, Item.waterBottle, Item.healingPowder, 1, Item.healthPotion, 2);
	public static BrewingRecipe healingPowder = new BrewingRecipe(Assets.healingPowderItem, Item.rottenFlesh, Item.crushedIron, 1, Item.healingPowder, 3);
	
	protected BufferedImage texture;
	protected Item catalyst;
	protected Item finalItem;
	protected Item baseItem;
	protected int amtOfFire;
	
	protected int id;
	
	public BrewingRecipe(BufferedImage texture, Item baseItem, Item catalyst, int amtOfFire, Item finalItem, int id) {
		this.texture = texture;
		this.baseItem = baseItem;
		this.catalyst = catalyst;
		this.amtOfFire = amtOfFire;
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

	public Item getCatalyst() {
		return catalyst;
	}

	public void setCatalyst(Item catalyst) {
		this.catalyst = catalyst;
	}

	public Item getFinalItem() {
		return finalItem;
	}

	public void setFinalItem(Item finalItem) {
		this.finalItem = finalItem;
	}

	public int getAmtOfFire() {
		return amtOfFire;
	}

	public void setAmtOfFire(int amtOfFire) {
		this.amtOfFire = amtOfFire;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Item getBaseItem() {
		return baseItem;
	}

	public void setBaseItem(Item baseItem) {
		this.baseItem = baseItem;
	}
	
}
