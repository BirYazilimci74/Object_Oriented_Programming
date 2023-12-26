package game;

import display.Display;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
	private Producer producer;
	private Consumer consumer;
		
	public InputHandler(Display display, Producer producer) {
		display.getCanvas().addKeyListener(this);
		this.producer = producer;
	}
	//************************************************--Overload here--*****************************************
	public InputHandler(Display display, Consumer consumer) {
		display.getCanvas().addKeyListener(this);
		this.consumer = consumer;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		//*******************************************--control consumer--**************************************
		if (keyCode == KeyEvent.VK_SPACE) {
			consumer.rotatePiece();
		} else if (keyCode == KeyEvent.VK_DOWN) {
			consumer.movePieceDown();
		} else if (keyCode == KeyEvent.VK_LEFT) {
			consumer.movePieceLeft();
		} else if (keyCode == KeyEvent.VK_RIGHT) {
			consumer.movePieceRight();
		} else if (keyCode == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		} else if (keyCode == KeyEvent.VK_P) {
			consumer.pause();
			producer.pause();
		} else if (keyCode == KeyEvent.VK_S) {
			consumer.resumeGame();
			producer.resumeGame();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
