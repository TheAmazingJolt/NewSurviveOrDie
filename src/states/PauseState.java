package states;

import gfx.Assets;
import gfx.Text;
import java.awt.Color;
import java.awt.Graphics;
import main.Handler;
import ui.Button;

// Referenced classes of package states:
//            State

public class PauseState extends State
{
	
	private Button button1;
	private Button button2;
	private Button button3;
	
	private int buttonX;
	private int button2X;
	private int buttonY;
	private int buttonWidth;
	private int buttonHeight;

    public PauseState(Handler handler)
    {
        super(handler);
        buttonX = (handler.getWidth() / 8) * 3;
        button2X = (handler.getWidth() / 8) * 5;
        buttonY = (handler.getHeight() / 3) * 2;
        buttonWidth = 210;
        buttonHeight = 120;
        button1 = new Button(Assets.smallButton, buttonX, buttonY - 30, buttonWidth, buttonHeight, true, "Resume", handler);
        button2 = new Button(Assets.smallButton, button2X, buttonY - 30, buttonWidth, buttonHeight, true, "Exit", handler);
        button3 = new Button(Assets.button, handler.getWidth()/2, buttonY + buttonHeight - 10, 480, buttonHeight, true, "Settings", handler);
    }

    public void tick()
    {
    	button1.tick();
    	button2.tick();
    	button3.tick();
    	if(button1.isClicked()) {
            State.setPreviousState(handler.getGame().pauseState);
            State.setState(handler.getGame().gameState);
    	}else if(button2.isClicked()) {
			try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            State.setPreviousState(handler.getGame().pauseState);
            State.setState(handler.getGame().saveState);
    	}else if(button3.isClicked()) {
    		try {
    			Thread.sleep(150);
    		}catch(InterruptedException e) {
    			e.printStackTrace();
    		}
    		State.setPreviousState(handler.getGame().pauseState);
    		State.setState(handler.getGame().settingsState);
    	}
    }

    public void render(Graphics g)
    {
    	g.drawImage(Assets.startScreen, 0, 0, handler.getWidth(), handler.getHeight(), null);
        Text.drawString(g, "Paused", handler.getWidth() / 2, handler.getHeight() / 7, true, Color.WHITE, Assets.font70);
        button1.render(g);
        button2.render(g);
        button3.render(g);
    }
    
}
