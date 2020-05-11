package states;

import java.awt.Graphics;

import gfx.Assets;
import main.Handler;
import saving.Load;
import ui.Button;

public class MenuState extends State{
	
	private Handler handler;
	
	private Button startButton;
	private Button settingsButton;
	
	private int buttonX;
	private int buttonY;
	private int buttonWidth;
	private int buttonHeight;
	
	public MenuState(Handler handler) {
		super(handler);
		this.handler = handler;
		buttonX = handler.getWidth() / 2;
		buttonY = (handler.getHeight() / 3) * 2 - 30;
		buttonWidth = 480;
		buttonHeight = 120;
		startButton = new Button(Assets.button, buttonX, buttonY, buttonWidth, buttonHeight, true, "Start Game", handler);
		settingsButton = new Button(Assets.button, buttonX, buttonY + buttonHeight + 10, buttonWidth, buttonHeight, true, "Settings", handler);
	}

	public void tick() {
		startButton.tick();
		settingsButton.tick();
		if(startButton.isClicked()) {
			LoadState.setCantLoad(Load.loadOtherData(handler));
			try {
				Thread.sleep(150);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
			State.setPreviousState(handler.getGame().menuState);
			State.setState(handler.getGame().loadState);
		}else if(settingsButton.isClicked()) {
			try {
				Thread.sleep(150);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
			State.setPreviousState(handler.getGame().menuState);
			State.setState(handler.getGame().settingsState);
		}
	}

	public void render(Graphics g) {
    	g.drawImage(Assets.startScreen, 0, 0, handler.getWidth(), handler.getHeight(), null);
		startButton.render(g);
		settingsButton.render(g);
	}

}
