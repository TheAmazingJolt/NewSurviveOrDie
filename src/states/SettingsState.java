package states;

import java.awt.Graphics;

import gfx.Assets;
import main.Handler;
import ui.Button;

public class SettingsState extends State{
	
	private Handler handler;
	
	private Button volumeButton;
	private Button cancelButton;
	
	private int buttonX;
	private int buttonY;
	private int buttonWidth;
	private int buttonHeight;
	
	public SettingsState(Handler handler) {
		super(handler);
		this.handler = handler;
		buttonX = handler.getWidth() / 2;
		buttonY = (handler.getHeight() / 3) * 2 - 30;
		buttonWidth = 480;
		buttonHeight = 120;
		volumeButton = new Button(Assets.button, buttonX, buttonY, buttonWidth, buttonHeight, true, "Volume", handler);
		cancelButton = new Button(Assets.button, buttonX, buttonY + buttonHeight + 10, buttonWidth, buttonHeight, true, "Cancel", handler);
	}

	public void tick() {
		volumeButton.tick();
		cancelButton.tick();
		if(cancelButton.isClicked()) {
			try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(State.getPreviousState() == handler.getGame().menuState) {
				State.setPreviousState(handler.getGame().settingsState);
				State.setState(handler.getGame().menuState);
			}else if(State.getPreviousState() == handler.getGame().gameState) {
				State.setPreviousState(handler.getGame().settingsState);
				State.setState(handler.getGame().gameState);
			}
		}
	}

	public void render(Graphics g) {
		volumeButton.render(g);
		cancelButton.render(g);
	}

}
