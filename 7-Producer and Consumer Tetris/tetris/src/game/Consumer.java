package game;

import display.Display;
import piece.Piece;
import piece.PieceGenerator;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Consumer extends Thread{
    private static final int NEXT_PIECE_X = 11;
    private static final int NEXT_PIECE_Y = 1;

    public static final int STARTING_PIECE_X = 4;
    public static final int STARTING_PIECE_Y = 0;

    private static final int DISPLAY_WIDTH = 300;
    private static final int DISPLAY_HEIGHT = 600;

    private static final int FIELD_WIDTH = 10;
    private static final int FIELD_HEIGHT = 20;

    private Producer producer;
    private Display display;
    private String title;
    private boolean running = false;
    @SuppressWarnings("unused")
    private InputHandler inputHandler;
    private BufferStrategy bs;
    private Graphics graphics;
    private Piece nextPiece;
    private boolean paused;

    public static Field field;
    public static Piece currentPiece;


    public Consumer(String title,Producer producer) {
        this.setTitle(title);
        this.field = new Field(Consumer.FIELD_HEIGHT, Consumer.FIELD_WIDTH);
        this.setCurrentPiece(PieceGenerator.generatePiece());
        this.producer = producer;
        //this.setNextPiece(PieceGenerator.generatePiece(Consumer.NEXT_PIECE_X, Consumer.NEXT_PIECE_Y));
    }

    private String getTitle() {
        return title;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    private boolean isRunning() {
        return running;
    }

    private void setRunning(boolean running) {
        this.running = running;
    }

    private Piece getCurrentPiece() {
        return currentPiece;
    }

    private void setCurrentPiece(Piece piece) {
        this.currentPiece = piece;
    }

    private Piece getNextPiece() {
        return Producer.nextPiece;
    }

    /*private void setNextPiece(Piece nextPiece) {
        this.nextPiece = nextPiece;
    }*/

    private boolean isPaused() {
        return paused;
    }

    private void setPaused(boolean paused) {
        this.paused = paused;
    }

    private void init() {
        this.display = new Display(this.getTitle(), Consumer.DISPLAY_WIDTH, Consumer.DISPLAY_HEIGHT);
        this.inputHandler = new InputHandler(this.display, this);
        this.setRunning(true);
    }

    // The method that will update all the variables
    private void tick() {

        if (this.isPaused()) {
            return;
        }

        Piece currentPiece = this.getCurrentPiece();

        if (this.field.isPieceFallen(currentPiece)) {
            this.field.placePiece(currentPiece);
            this.field.destroyFullRows();
            this.swithToNextPiece();
        } else {
            currentPiece.tick();
        }
    }

    // The method that will draw everything on the canvas
    private void render() {
        this.bs = display.getCanvas().getBufferStrategy();
        if (this.bs == null) {
            this.display.getCanvas().createBufferStrategy(2);
            return;
        }

        this.graphics = this.bs.getDrawGraphics();

        this.graphics.clearRect(0, 0, this.display.getWidth(), this.display.getHeight());

        this.graphics.drawLine(300, 0, 300, 600);

        this.field.render(this.graphics);
        currentPiece.render(this.graphics);
        //*************************--We dont want to see the next piece in Consumer--********************************
        //this.nextPiece.render(this.graphics);

        bs.show();
        this.graphics.dispose();
    }

    public void run() {
        this.init();

        while (this.isRunning()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }


            this.tick();
            this.render();
        }
    }

    public void pause() {
        this.paused = true;
    }

    public void resumeGame() {
        this.paused = false;
    }

    private void swithToNextPiece() {

        Piece nextPiece = this.getNextPiece();
        nextPiece.movePieceToStartingPoing();
        this.setCurrentPiece(nextPiece);

        producer.generateNewPiece();
        //this.setNextPiece(PieceGenerator.generatePiece(Consumer.NEXT_PIECE_X, Consumer.NEXT_PIECE_Y));
        //this.setNextPiece(Producer.nextPiece);
    }

    public void rotatePiece() {
        this.currentPiece.rotate();
        if (this.field.isPieceOut(this.currentPiece) || this.field.isPieceIntoBrick(this.currentPiece)) {
            this.currentPiece.undoRotate();
        } else {
            this.render();
        }
    }

    public void movePieceLeft() {
        if (!this.field.doesPieceTouchLeftWall(this.currentPiece)) {
            this.currentPiece.moveLeft();
            if (this.field.isPieceIntoBrick(this.currentPiece)) {
                this.currentPiece.moveRight();
            } else {
                this.render();
            }
        }
    }

    public void movePieceRight() {
        if (!this.field.doesPieceTouchRightWall(this.currentPiece)) {
            this.currentPiece.moveRight();
            if (this.field.isPieceIntoBrick(this.currentPiece)) {
                this.currentPiece.moveLeft();
            } else {
                this.render();
            }
        }
    }

    public void movePieceDown() {
        if (!this.field.doesPieceTouchesBottom(this.currentPiece)) {
            this.currentPiece.moveDown();
            if (this.field.isPieceIntoBrick(this.currentPiece)) {
                this.currentPiece.moveUp();
            } else {
                this.render();
            }
        }
    }
}
