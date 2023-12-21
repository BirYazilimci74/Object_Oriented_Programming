package game;

public class Launcher {
	public static void main(String[] args) {			
		Game game1 = new Game("Tetris1");
        game1.start();

		Game game2 = new Game("Tetris2");
		game2.start();
	}
}
