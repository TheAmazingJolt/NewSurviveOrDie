package tiles;

import gfx.Assets;
import saving.Load;
import states.LoadState;

public class BossRoomEntranceTile extends Tile{

	public BossRoomEntranceTile(int id) {
		super(Assets.entranceTileLocked[0], id);
	}

	public void enter() {
		if(!unlocked)
			return;
		handler.getWorld().unload();
		if(handler.getWorld().getSubAreaNum() == 1) {
			handler.getWorld().setSubAreaNum(2);
			handler.getWorld().getEntityManager().setSubAreaNum(2);
			handler.getWorld().setSubAreaName("bossRoom1");
			handler.getWorld().loadWorld();
			handler.getWorld().tick();
			this.texture = Assets.entranceTile[1];
			Load.loadEntityData(handler, LoadState.saveName, handler.getWorld().getSubAreaName(), handler.getWorld().getEntityManager().getEntities());
		}else {
			handler.getWorld().setSubAreaNum(1);
			handler.getWorld().getEntityManager().setSubAreaNum(1);
			handler.getWorld().setSubAreaName("area1");
			handler.getWorld().loadTiles(960, 1848);
			handler.getWorld().tick();
			this.texture = Assets.entranceTile[0];
			Load.loadEntityData(handler, LoadState.saveName, handler.getWorld().getSubAreaName(), handler.getWorld().getEntityManager().getEntities());
		}
	}
	
	public boolean isDoor() {
		return true;
	}
	
	public boolean isSolid() {
		if(!unlocked)
			return true;
		return false;
	}
	
	public void updateTextures() {
		if(unlocked) {
			if(handler.getWorld().getSubAreaNum() == 1) {
				this.texture = Assets.entranceTile[0];
			}else {
				this.texture = Assets.entranceTile[1];
			}
		}else if(!unlocked) {
			if(handler.getWorld().getSubAreaNum() == 1) {
				this.texture = Assets.entranceTileLocked[0];
			}else {
				this.texture = Assets.entranceTileLocked[1];
			}
		}
	}

}
