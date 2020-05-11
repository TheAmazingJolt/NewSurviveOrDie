package states;

import gfx.Assets;
import gfx.Text;
import java.awt.Color;
import java.awt.Graphics;
import main.Handler;
import saving.Save;
import ui.Button;
import world.Area;

public class SaveState extends State
{

    private String saveName;
    
    private Button button1;
    private Button button2;
    
	private int buttonX;
	private int button2X;
	private int buttonY;
	private int buttonWidth;
	private int buttonHeight;
	
    public SaveState(Handler handler)
    {
        super(handler);
        buttonX = (handler.getWidth() / 8) * 2 + 105;
        button2X = (handler.getWidth() / 8) * 4 + 105;
        buttonY = (handler.getHeight() / 3) * 2;
        buttonWidth = 210;
        buttonHeight = 120;
        button1 = new Button(Assets.smallButton, buttonX, buttonY, buttonWidth, buttonHeight, true, "Yes", handler);
        button2 = new Button(Assets.smallButton, button2X, buttonY, buttonWidth, buttonHeight, true, "No", handler);
    }

    public void tick()
    {
    	button1.tick();
    	button2.tick();
    	if(button1.isClicked()) {
    		saveName = "save" + handler.getWorld().getLoadedSave();
    		Save.saveOtherWorldData(handler, saveName);
    		Save.saveSaveData(handler, saveName);
            Save.saveItemData(handler, saveName);
            for(Area a : handler.getWorld().getAreas()) {
            	handler.getWorld().getEntityManager().setSubAreaNum(a.getAreaId());
                Save.saveEntityData(handler, saveName, a.getAreaName());
            }
            Save.savePlayerData(handler.getWorld().getEntityManager().getPlayer(), saveName);
            Save.saveOtherData(handler.getWorld().getAmountOfSaves());
            System.exit(0);
    	}else if(button2.isClicked()) {
            System.exit(0);
    	}
    }

    public void render(Graphics g)
    {
    	g.drawImage(Assets.startScreen, 0, 0, handler.getWidth(), handler.getHeight(), null);
        Text.drawString(g, "Would you like to save data?", handler.getWidth() / 2, handler.getHeight() / 7, true, Color.WHITE, Assets.font70);
        button1.render(g);
        button2.render(g);
    }

}
