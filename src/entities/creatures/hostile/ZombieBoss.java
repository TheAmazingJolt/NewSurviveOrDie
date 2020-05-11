package entities.creatures.hostile;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import entities.creatures.Player;
import gfx.Animation;
import gfx.Assets;
import main.Handler;
import utils.Timer;

public class ZombieBoss extends Hostile{
	
	private static int maxHealth = 20;
	
	private long attackCooldown;
	
	private Timer timer;
	
	private Animation animDown;
    private Animation animUp;
    private Animation animLeft;
    private Animation animRight;
    
    private Player player;

	public ZombieBoss(Handler handler, float x, float y, int id, Player player) {
		super(handler, x, y, 128, 128, maxHealth, id, "ZombieBoss");
		this.id = id;
		startX = x;
		startY = y;
		bounds.x = 44;
		bounds.y = 15;
		bounds.width = 40;
		bounds.height = 68;
		attackCooldown = 300L;
        animDown = new Animation(500, Assets.zombieDown);
        animUp = new Animation(500, Assets.zombieUp);
        animLeft = new Animation(500, Assets.zombieLeft);
        animRight = new Animation(500, Assets.zombieRight);
        this.isFollowing = false;
        this.isAttacking = false;
        this.following = player;
        this.player = player;
        this.handler = handler;
        ar = new Rectangle();
        this.speed = 2f;
        this.defaultSpeed = 2f;
	}

	public void tick() {
		if(!active) {
            bounds.width = 0;
            bounds.height = 0;
			return;
		}
		animDown.tick();
		animUp.tick();
		animLeft.tick();
		animRight.tick();
		move();
		checkAttacks();
	}

	public void render(Graphics g) {
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

	public void die() {
		player.setKilledBosses(player.getKilledBosses() + 1);
	}
	
	private void checkAttacks()
    {
        cb = getCollisionBounds(0.0F, 0.0F);
        int arSize = 40;
        ar.width = arSize;
        ar.height = arSize * 3;
        if(!this.isAttacking && this.following.isActive()) {
        	this.follow(following, 150);
        }
        updateHitbox(arSize);
    	if(timer == null)
    		timer = new Timer(attackCooldown, 1);
    	timer.tick();
    	if(!timer.isCompleted()) {
    		return;
    	}else if(timer.isCompleted()) {
    		timer = null;
    	}
        if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0.0F, 0.0F).intersects(ar)) {
        	System.out.println(1 * handler.getGame().getDifficultyLevel());
            handler.getWorld().getEntityManager().getPlayer().hurt(2 * handler.getGame().getDifficultyLevel());
            this.isAttacking = true;
        }else {
        	this.isAttacking = false;
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

}
