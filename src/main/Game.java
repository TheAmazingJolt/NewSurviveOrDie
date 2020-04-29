package main;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import display.Display;
import gfx.Assets;
import gfx.GameCamera;
import input.KeyManager;
import input.MouseManager;
import states.GameState;
import states.State;

public class Game implements Runnable{

	private String title;
	
	private int width;
	private int height;
	
	private boolean running;
	private boolean debug = false;
	
	private Thread thread;
	private Display display;
	private BufferStrategy bs;
	private Graphics g;
	
	public State menuState;
	
	private Handler handler;
	private GameCamera gameCamera;
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	public Game(String title, int width, int height) {
		running = false;
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
	}
	
	public synchronized void start() {
		if(running) {
			return;
		}else {
			running = true;
			thread = new Thread(this);
			thread.start();
			return;
		}
	}
	
	public synchronized void stop() {
		if(!running) {
			return;
		}
		running = false;
		try {
			thread.join();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		init();
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0.0D;
		long lastTime = System.nanoTime();
		long timer = 0L;
		int ticks = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			if(delta >= 1.0D) {
				if(ticks > 60) {
					delta--;
				}
				tick();
				render();
				ticks++;
				delta--;
			}
			if(timer >= 1000000000) {
				System.out.println("FPS: " + ticks);
				ticks = 0;
				timer = 0L;
			}
		}
		stop();
	}
	
	private void init() {
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getFrame().addMouseWheelListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        Assets.init();
		handler = new Handler(this);
		gameCamera = new GameCamera(handler, 0.0F, 0.0F);
		menuState = new GameState(handler);
		State.setState(menuState);
	}
	
	private void tick() {
		width = display.getFrame().getWidth();
		height = display.getFrame().getHeight();
		keyManager.tick();
		if(State.getState() != null) {
			State.getState().tick();
		}
		
		if(!Display.frame.isShowing()) {
			System.exit(0);
		}
	}
	
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		if(State.getState() != null) {
			State.getState().render(g);
		}
		bs.show();
		g.dispose();
	}

	public GameCamera getGameCamera() {
		return gameCamera;
	}

	public void setGameCamera(GameCamera gameCamera) {
		this.gameCamera = gameCamera;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public KeyManager getKeyManager() {
		return keyManager;
	}

	public void setKeyManager(KeyManager keyManager) {
		this.keyManager = keyManager;
	}

	public MouseManager getMouseManager() {
		return mouseManager;
	}

	public void setMouseManager(MouseManager mouseManager) {
		this.mouseManager = mouseManager;
	}
	
}
