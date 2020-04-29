package main;

public class Launcher {

	public static final String VERSION_ID = "Alpha";
	
	public static void main(String[] args) {
		Game game = new Game("Survive Or Die " + VERSION_ID, 960, 720);
		game.start();
	}
	
}
