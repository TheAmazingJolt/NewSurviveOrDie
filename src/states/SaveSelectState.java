package states;

import java.awt.Color;
import java.awt.Graphics;

import gfx.Assets;
import gfx.Text;
import main.Handler;
import saving.Load;

public class SaveSelectState extends State{

	private Handler handler;
	
	private int x;
	private int y;
	private int lineWidth;
	private int lineHeight;
	private int centerOfCenterLineX; //these are based on the very center of the line
	private int centerOfCenterLineY;
	private int lineX;
	private int line1Y;
	private int line2Y;
	private int line3Y;
	private int line4Y;
	private int line5Y;
	private int line6Y;
	private int line7Y;
	private int line8Y;
	private int line9Y;
	
	private int infoBoxX;
	private int infoBoxY;
	private int infoBoxLineX;
	private int infoBoxLine1Y;
	private int infoBoxLine2Y;
	private int infoBoxLine3Y;
	
	private int buttonX;
	private int buttonY;
	private int button2Y;
	private int buttonWidth;
	private int buttonHeight;
	
	private int selectedSave = 1;
	private int amountOfSaves;
	
	private boolean saveAmtSet = false;
	private boolean saveIsSelected = true;
	private boolean dataLoaded = false;
	
	private Color colorForPlayButton = Color.WHITE;
	private Color colorForCancelButton = Color.WHITE;
	
	public SaveSelectState(Handler handler)
    {
        super(handler);
        this.handler = handler;
        x = 96;
        y = 72;
        lineHeight = 45;
        lineWidth = 448;
        centerOfCenterLineX = x + 254;
        centerOfCenterLineY = y + 69;
        lineX = x + 30;
        line1Y = y + 48;
        line2Y = y + 93;
        line3Y = y + 138;
        line4Y = y + 183;
        line5Y = y + 228;
        line6Y = y + 273;
        line7Y = y + 318;
        line8Y = y + 363;
        line9Y = y + 408;
        infoBoxX = x + 502;
        infoBoxY = y + 42;
        infoBoxLineX = infoBoxX + 121;
        infoBoxLine1Y = infoBoxY + 25;
        infoBoxLine2Y = infoBoxLine1Y + 45;
        infoBoxLine3Y = infoBoxLine2Y + 45;
        buttonWidth = 185;
        buttonHeight = 37;
        buttonX = x + 530;
        buttonY = y + 290;
        button2Y = buttonY + buttonHeight + 10;
        handler.getWorld();
    }

	public void setSaveAmt() {
		amountOfSaves = handler.getWorld().getAmountOfSaves();
	}
	
    public void tick()
    {
    	if(!saveAmtSet) {
    		setSaveAmt();
    		saveAmtSet = true;
    	}
    	int mouseX = handler.getMouseManager().getMouseX();
    	int mouseY = handler.getMouseManager().getMouseY();
    	if(handler.getMouseManager().isLeftPressed()) {
	    	if(mouseX >= lineX && mouseX <= lineX + lineWidth) {
	    		if(mouseY >= line1Y && mouseY <= line1Y + lineHeight) {
	    			selectedSave = 1;
	    			saveIsSelected = true;
	        		dataLoaded = false;
	    		}else if(mouseY >= line2Y && mouseY <= line2Y + lineHeight) {
	    			selectedSave = 2;
	    			saveIsSelected = true;
	        		dataLoaded = false;
	    		}else if(mouseY >= line3Y && mouseY <= line3Y + lineHeight) {
	    			selectedSave = 3;
	    			saveIsSelected = true;
	        		dataLoaded = false;
	    		}else if(mouseY >= line4Y && mouseY <= line4Y + lineHeight) {
	    			selectedSave = 4;
	    			saveIsSelected = true;
	        		dataLoaded = false;
	    		}else if(mouseY >= line5Y && mouseY <= line5Y + lineHeight) {
	    			selectedSave = 5;
	    			saveIsSelected = true;
	        		dataLoaded = false;
	    		}else if(mouseY >= line6Y && mouseY <= line6Y + lineHeight) {
	    			selectedSave = 6;
	    			saveIsSelected = true;
	        		dataLoaded = false;
	    		}else if(mouseY >= line7Y && mouseY <= line7Y + lineHeight) {
	    			selectedSave = 7;
	    			saveIsSelected = true;
	        		dataLoaded = false;
	    		}else if(mouseY >= line8Y && mouseY <= line8Y + lineHeight) {
	    			selectedSave = 8;
	    			saveIsSelected = true;
	        		dataLoaded = false;
	    		}else if(mouseY >= line9Y && mouseY <= line9Y + lineHeight) {
	    			selectedSave = 9;
	    			saveIsSelected = true;
	        		dataLoaded = false;
	    		}
	    	}
    	}
    	if(mouseX >= buttonX && mouseX <= buttonX + buttonWidth) {
			if(mouseY >= buttonY && mouseY <= buttonY + buttonHeight) {
				colorForPlayButton = Color.YELLOW;
				if(handler.getMouseManager().isLeftPressed()) {
					LoadState.setLoading(true);
					LoadState.setToLoad(true);
					LoadState.setSaveToLoad(selectedSave);
					State.setState(handler.getGame().loadState);
				}
			}else if(mouseY >= button2Y && mouseY <= button2Y + buttonHeight) {
				colorForCancelButton = Color.YELLOW;
				if(handler.getMouseManager().isLeftPressed()) {
					LoadState.setLoading(false);
					LoadState.setToLoad(false);
					LoadState.setSaveToLoad(0);
					State.setState(handler.getGame().loadState);
				}
			}else {
				colorForPlayButton = Color.WHITE;
				colorForCancelButton = Color.WHITE;
			}
    	}else {
    		colorForPlayButton = Color.WHITE;
			colorForCancelButton = Color.WHITE;
    	}
    	if(saveIsSelected) {
    		Load.loadSaveData("save" + selectedSave, handler);
    		dataLoaded = true;
    	}
    }

    public void render(Graphics g)
    {
    	g.drawImage(Assets.startScreen, 0, 0, handler.getWidth(), handler.getHeight(), null);
		g.drawImage(Assets.saveSelectionScreen, x, y, 768, 576, null);
		for(int i = 1; i <= amountOfSaves; i++) {
			if(selectedSave != i)
				Text.drawString(g, "Save " + i, centerOfCenterLineX, centerOfCenterLineY + (45 * (i-1)), true, Color.WHITE, Assets.font40);
			else
				Text.drawString(g, "Save " + i, centerOfCenterLineX, centerOfCenterLineY + (45 * (i-1)), true, Color.YELLOW, Assets.font40);
		}
		if(dataLoaded) {
			Text.drawString(g, "Created: ", infoBoxLineX, infoBoxLine1Y - 10, true, Color.WHITE, Assets.font20);
			Text.drawString(g, handler.getGame().creationDate, infoBoxLineX, infoBoxLine1Y + 10, true, Color.WHITE, Assets.font20);
		
			Text.drawString(g, "Modified: ", infoBoxLineX, infoBoxLine2Y - 10, true, Color.WHITE, Assets.font20);
			Text.drawString(g, handler.getGame().modifiedDate, infoBoxLineX, infoBoxLine2Y + 10, true, Color.WHITE, Assets.font20);
			
			Text.drawString(g, "Difficulty: ", infoBoxLineX, infoBoxLine3Y - 10, true, Color.WHITE, Assets.font20);
			if(handler.getGame().getDifficultyLevel() == 0.5f)
				Text.drawString(g, "Easy", infoBoxLineX, infoBoxLine3Y + 10, true, Color.WHITE, Assets.font20);
			else if(handler.getGame().getDifficultyLevel() == 1)
				Text.drawString(g, "Normal", infoBoxLineX, infoBoxLine3Y + 10, true, Color.WHITE, Assets.font20);
			else if(handler.getGame().getDifficultyLevel() == 2)
				Text.drawString(g, "Hard", infoBoxLineX, infoBoxLine3Y + 10, true, Color.WHITE, Assets.font20);
			
			Text.drawString(g, "Play", buttonX + buttonWidth / 2, buttonY + buttonHeight / 2, true, colorForPlayButton, Assets.font40);
			Text.drawString(g, "Cancel", buttonX + buttonWidth / 2, button2Y + buttonHeight / 2, true, colorForCancelButton, Assets.font40);
		}
    }
	
}
