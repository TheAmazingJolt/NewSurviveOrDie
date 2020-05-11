package states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import main.Handler;
import saving.Load;
import world.Area;
import world.World;

public class GameState extends State{
	
	private World world;
	
	private boolean worldFirstTick = false;
	
	public GameState(Handler handler) {
		super(handler);
		world = new World(handler);
		handler.setWorld(world);
	}

	public void tick() {
		if(!world.isEntitiesLoaded() && worldFirstTick) {
			for(Area a : world.getAreas()) {
				world.getEntityManager().setSubAreaNum(a.getAreaId());
				Load.loadEntityData(handler, LoadState.saveName, a.getAreaName(), world.getEntityManager().getEntities());
			}
			world.getEntityManager().setSubAreaNum(world.getSubAreaNum());
			Load.loadEntityData(handler, LoadState.saveName, world.getSubAreaName(), world.getEntityManager().getEntities());
			world.setEntitiesLoaded(true);
		}
		
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE) 
				&& !handler.getWorld().getEntityManager().getPlayer().getInventory().isActive()
				&& !handler.getWorld().getEntityManager().getPlayer().getBrewing().isActive()) {
			State.setPreviousState(handler.getGame().gameState);
			State.setState(handler.getGame().pauseState);
		}
		world.tick();
		worldFirstTick = true;
	}

	public void render(Graphics g) {
		world.render(g);
	}

}
