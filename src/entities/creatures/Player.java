package entities.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import entities.Entity;
import gfx.Animation;
import gfx.Assets;
import inventory.Inventory;
import inventory.hotbar.Hotbar;
import main.Handler;
import utils.Timer;

public class Player extends Creature{

	private static int maxHealth = 10;

    private long attackCooldown;
    
    protected int attackStrength = 1;
    
    private Timer timer;
    
    private Rectangle ar;

	private Animation animDown;
    private Animation animUp;
    private Animation animLeft;
    private Animation animRight;
    
    private Inventory inventory;
    private Hotbar hotbar;
    
	public Player(Handler handler, float x, float y, int id) {
		super(handler, x, y, 64, 64, maxHealth, id, "Player");
		startX = x;
		startY = y;
		health = 10;
		bounds.x = 22;
		bounds.y = 30;
		bounds.width = 20;
		bounds.height = 34;
		attackCooldown = 200L;
        animDown = new Animation(500, Assets.playerDown);
        animUp = new Animation(500, Assets.playerUp);
        animLeft = new Animation(500, Assets.playerLeft);
        animRight = new Animation(500, Assets.playerRight);
        ar = new Rectangle();

        hotbar = new Hotbar(handler);
        inventory = new Inventory(handler, hotbar);
	}

	public void tick() {
		if(!active)
			return;
		if(health <= 0)
			die();
        animDown.tick();
        animUp.tick();
        animLeft.tick();
        animRight.tick();
		getInput();
		move();
		checkAttacks();
		inventory.tick();
		hotbar.tick();
		handler.getGameCamera().centerOnEntity(this);
	}

	public void render(Graphics g) {
		if(!active)
			return;
		g.drawImage(getCurrentAnimationFrame(), (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width, height, null);
		g.setColor(Color.red);
		g.fillRect((int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset() - 5), width, 3);
		g.setColor(Color.green);
		g.fillRect((int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset() - 5), (int)(width * ((float) health/maxHealth)), 3);
		if(handler.getGame().isDebug()) {
        	g.setColor(Color.RED);
        	g.drawRect((int) (ar.x - handler.getGameCamera().getxOffset()), (int) (ar.y - handler.getGameCamera().getyOffset()), ar.width, ar.height);
        	g.setColor(null);
        }
	}
	
	public void postRender(Graphics g) {
		hotbar.render(g);
		inventory.render(g);
	}

	public void die() {
		
	}

	private void getInput()
    {
		if(inventory.isActive())
			return;
    	xMove = 0.0F;
        yMove = 0.0F;
        if(handler.getKeyManager().up)
        	yMove = -speed;
        if(handler.getKeyManager().down)
        	yMove = speed;
        if(handler.getKeyManager().left)
        	xMove = -speed;
        if(handler.getKeyManager().right)
        	xMove = speed;
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_F1))
        	handler.getGame().setDebug(!handler.getGame().isDebug());
    }
	
	private void checkAttacks()
    {
    	if(health <= 0)
            die();
    	if(hotbar.getHotbarItems().size() > 0)
    		this.attackStrength = hotbar.getHotbarItems().get(hotbar.getSelectedItem()).getAttackDamage();
        Rectangle cb = getCollisionBounds(0.0F, 0.0F);
        int arSize = 20;
        ar.width = arSize;
        ar.height = arSize * 3;
        if(yMove < 0 && xMove == 0) {//up
        	ar.x = (cb.x + cb.width / 2) - arSize / 2;
        	ar.y = cb.y - arSize;
        }else if(yMove > 0 && xMove == 0|| yMove == 0 && xMove == 0) {//down and still
        	ar.x = (cb.x + cb.width / 2) - arSize / 2;
        	ar.y = cb.y + cb.height - 63;
        }else if(xMove < 0.0F && yMove == 0) {//left
        	ar.x = cb.x - arSize;
        	ar.y = (cb.y + cb.height / 2) - arSize / 2 - 18;
        }else if(xMove > 0.0F && yMove == 0) {//right
        	ar.x = cb.x + cb.width;
        	ar.y = (cb.y + cb.height / 2) - arSize / 2 - 18;
        }
        
        if(xMove > 0.0f && yMove < 0.0f) {//up right
            ar.x = cb.x + cb.width;
            ar.y = cb.y - arSize;
        }else if(xMove > 0.0f && yMove > 0.0f) {//down right
            ar.x = cb.x + cb.width;
            ar.y = cb.y + cb.height - 45;
        }else if(xMove < 0.0f && yMove < 0.0f) {//up left
            ar.x = cb.x - cb.width;
            ar.y = cb.y - arSize;
        }else if(xMove < 0.0f && yMove > 0.0f) {//down left
            ar.x = cb.x - cb.width;
            ar.y = cb.y + cb.height - 45;
        }
        
        if(timer == null)
        	timer = new Timer(attackCooldown, 1);
        timer.tick();
        if(!timer.isCompleted()) {
        	return;
        }else if(timer.isCompleted()) {
        	timer = null;
        }
        
        if(handler.getMouseManager().isLeftPressed()) {
	        for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
	        	if(e != this && e.getCollisionBounds(0, 0).intersects(ar)) {
	           		e.hurt(this.attackStrength);
	           	}
	        }
        }
            
    }
	
	private BufferedImage getCurrentAnimationFrame()
    {
        if(xMove < 0.0F)
            return animLeft.getCurrentFrame();
        if(xMove > 0.0F)
            return animRight.getCurrentFrame();
        if(yMove < 0.0F)
            return animUp.getCurrentFrame();
        else
            return animDown.getCurrentFrame();
    }
	
	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealt) {
		maxHealth = maxHealt;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public Hotbar getHotbar() {
		return hotbar;
	}

	public void setHotbar(Hotbar hotbar) {
		this.hotbar = hotbar;
	}

}
