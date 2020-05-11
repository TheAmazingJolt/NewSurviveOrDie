package states;

import gfx.Assets;
import gfx.Text;
import java.awt.Color;
import java.awt.Graphics;
import java.text.SimpleDateFormat;
import java.util.Date;

import main.Handler;
import saving.Load;
import ui.Button;

// Referenced classes of package states:
//            State

public class LoadState extends State
{

    public static String saveName;
    
    private static boolean loading = false;
    private static boolean toLoad = false;
    private static boolean cantLoad = false;
    
    private static int worldToLoad = 1;
    private static int saveToLoad;

    private Button yesButton;
    private Button noButton;
    private Button easySelect;
    private Button normalSelect;
    private Button hardSelect;
    
	private int buttonX;
	private int button2X;
	private int button3X;
	private int button4X;
	private int button5X;
	private int buttonY;
	private int buttonWidth;
	private int buttonHeight;
	
	private boolean difficultySelection = false;
	
	public static String subAreaName;
	
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    private Date date = new Date(); 
	
    public LoadState(Handler handler)
    {
        super(handler);
        buttonX = (handler.getWidth() / 8) * 2 + 105;
        button2X = (handler.getWidth() / 8) * 4 + 105;
        button3X = handler.getWidth() / 4;
        button4X = handler.getWidth() / 2;
        button5X = handler.getWidth() / 4 * 3;
        
        buttonY = (handler.getHeight() / 3) * 2;
        buttonWidth = 210;
        buttonHeight = 120;
        
        yesButton = new Button(Assets.smallButton, buttonX, buttonY, buttonWidth, buttonHeight, true, "Yes", handler);
        noButton = new Button(Assets.smallButton, button2X, buttonY, buttonWidth, buttonHeight, true, "No", handler);
        easySelect = new Button(Assets.smallButton, button3X, buttonY, buttonWidth, buttonHeight, true, "Easy", handler);
        normalSelect = new Button(Assets.smallButton, button4X, buttonY, buttonWidth, buttonHeight, true, "Normal", handler);
        hardSelect = new Button(Assets.smallButton, button5X, buttonY, buttonWidth, buttonHeight, true, "Hard", handler);
    }
    
    

    public static int getWorldToLoad() {
		return worldToLoad;
	}



	public static void setWorldToLoad(int world) {
		worldToLoad = world;
	}



	public void tick()
    {
		if(!(loading && toLoad) && !difficultySelection) {
			yesButton.tick();
			noButton.tick();
		}
		if(yesButton != null && yesButton.isClicked()) {
            try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            if(!cantLoad) {
    			handler.getGame().setCreationDate(dateFormat.format(date));
    			handler.getGame().setCreationDateSet(true);
    			State.setState(handler.getGame().saveSelectionState);
            }
		}else if((noButton.isClicked() || cantLoad) && !difficultySelection) {
    		handler.getWorld().setAmountOfSaves(handler.getWorld().getAmountOfSaves() + 1);
			if(handler.getWorld().getAmountOfSaves() < 1)
				handler.getWorld().setLoadedSave(1);
			else if(handler.getWorld().getAmountOfSaves() >= 1)
				handler.getWorld().setLoadedSave(handler.getWorld().getAmountOfSaves());
    		handler.getWorld().setCurrentArea(1);
    		try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    		difficultySelection = true;
		}
		
		if(difficultySelection) {
			easySelect.tick();
			normalSelect.tick();
			hardSelect.tick();
			if(easySelect.isClicked()) {
				handler.getGame().setDifficultyLevel(0.5f);
	    		State.setPreviousState(handler.getGame().loadState);
	    		State.setState(handler.getGame().gameState);
			}else if(normalSelect.isClicked()) {
				handler.getGame().setDifficultyLevel(1);
	    		State.setPreviousState(handler.getGame().loadState);
	    		State.setState(handler.getGame().gameState);
			}else if(hardSelect.isClicked()) {
				handler.getGame().setDifficultyLevel(2);
	    		State.setPreviousState(handler.getGame().loadState);
	    		State.setState(handler.getGame().gameState);
			}
		}
		
        if(toLoad && loading) {
        	saveName = "save" + saveToLoad;
        	loadStuff(saveToLoad);
        }
    }

    public void render(Graphics g)
    {
    	g.drawImage(Assets.startScreen, 0, 0, handler.getWidth(), handler.getHeight(), null);
        if(!loading && !cantLoad && !difficultySelection) {
            Text.drawString(g, "Do you want to load save data?", handler.getWidth() / 2, handler.getHeight() / 7, true, Color.WHITE, Assets.font70);
            yesButton.render(g);
            noButton.render(g);
        }else if(difficultySelection) {
        	Text.drawString(g, "Select Difficulty", handler.getWidth() / 2, handler.getHeight() / 7, true, Color.WHITE, Assets.font70);
        	easySelect.render(g);
        	normalSelect.render(g);
        	hardSelect.render(g);
        }
    }

    public static void setWorldName(String name)
    {
        saveName = name;
    }

    public static boolean isLoading() {
		return loading;
	}

	public static void setLoading(boolean loading) {
		LoadState.loading = loading;
	}

	public static boolean isToLoad() {
		return toLoad;
	}

	public static void setToLoad(boolean toLoad) {
		LoadState.toLoad = toLoad;
	}

	public static int getSaveToLoad() {
		return saveToLoad;
	}

	public static void setSaveToLoad(int saveToLoad) {
		LoadState.saveToLoad = saveToLoad;
	}

	public static boolean isCantLoad() {
		return cantLoad;
	}

	public static void setCantLoad(boolean cantLoad) {
		LoadState.cantLoad = cantLoad;
	}

	public void loadStuff(int num)
    {
    	Load.loadOtherWorldData(saveName, handler);
    	if(!subAreaName.contains(handler.getWorld().getSubAreaName())) {
    		handler.getWorld().setSubAreaName(subAreaName);
        	handler.getWorld().unload();
        	handler.getWorld().loadWorld();
    	}
		handler.getWorld().setEntitiesLoaded(false);
        handler.getWorld().setLoadedSave(num);
        handler.getWorld().setSaveName(saveName);
        Load.loadPlayerData(handler.getWorld().getEntityManager().getPlayer(), saveName);
        Load.loadItemData(handler.getWorld().getEntityManager().getPlayer().getInventory(), saveName);
        Load.loadSaveData(saveName, handler);
		State.setPreviousState(handler.getGame().loadState);
        State.setState(handler.getGame().gameState);
    }
}
