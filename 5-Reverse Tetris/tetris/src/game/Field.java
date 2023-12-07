package game;

import java.util.Arrays;
import java.util.Random;

import piece.Piece;
import piece.PieceShape;

import java.awt.*;

public class Field {
	private static final Color[] FIELD_COLORS = { Color.RED, Color.LIGHT_GRAY, Color.MAGENTA, Color.PINK, Color.GREEN,
			Color.ORANGE, Color.CYAN };

	private FieldSquare[][] field;
	private Color[][] fieldColor;
	private Random random;

	public Field(int fieldHeight, int fieldWidth) {
		this.random = new Random();
		this.initializeField(fieldHeight, fieldWidth);
	}

	private void initializeField(int fieldHeight, int fieldWidth) {
		this.field = new FieldSquare[fieldHeight][fieldWidth];
		this.fieldColor = new Color[fieldHeight][fieldWidth];

		for (int row = 0; row < field.length; row++) {
			for (int col = 0; col < field[0].length; col++) {
				if (row > 8 && this.random.nextInt(11) <= -1) {
					this.field[row][col] = FieldSquare.STACK;
					this.fieldColor[row][col] = Field.FIELD_COLORS[this.random.nextInt(Field.FIELD_COLORS.length)];					
				} else {
					this.field[row][col] = FieldSquare.EMPTY;
				}
			}
		}
	}

	private boolean checkRowInField(int row) {
		return row < this.field.length && row >= 0;
	}

	private boolean checkCollInField(int row, int col) {
		return col < this.field[row].length && col >= 0;
	}

	private boolean checkIsFieldBrick(int row, int col) {
		return (!this.checkRowInField(row) || !this.checkCollInField(row, col)
				|| this.field[row][col].equals(FieldSquare.STACK));
	}

	public int getHeight() {
		// return the number of rows for the field char matrix
		return this.field.length;
	}

	public int getWidth() {
		// return the number of columns for the first row of the char matrix
		// they are the same for every single other row
		return this.field[0].length;
	}

	public void render(Graphics g) {
		for (int row = 0; row < field.length; row++) {
			for (int col = 0; col < field[row].length; col++) {
				if (this.field[row][col].equals(FieldSquare.EMPTY)) {
					g.setColor(Color.BLUE);
					g.fillRect(col * 30, row * 30, 30, 30);
				} else if (this.field[row][col].equals(FieldSquare.STACK)) {
					g.setColor(this.fieldColor[row][col]);
					g.fillRect(col * 30, row * 30, 30, 30);
					g.setColor(Color.WHITE);				
					g.drawRect(col * 30, row * 30, 30, 30);
				}				
			}
		}
	}

	public boolean isPieceFallen(Piece piece) {
		int pieceX = piece.getX();
		int pieceY = piece.getY();

		PieceShape pieceShape = piece.getShape();
		int pieceHeight = pieceShape.getHeight();
		int pieceWidth = pieceShape.getWidth();

		for (int row = 0; row < pieceHeight; row++) {
			for (int col = 0; col < pieceWidth; col++) {
				if (pieceShape.isPieceBrick(row, col) && this.checkIsFieldBrick(pieceY + row + 1, pieceX + col)) {
					return true;
				}
			}
		}

		return false;
	}
	// ------------------------------------this method was added-------------------------------
	public boolean isPieceFloat(Piece piece) {
		int pieceX = piece.getX();
		int pieceY = piece.getY();

		PieceShape pieceShape = piece.getShape();
		int pieceHeight = pieceShape.getHeight();
		int pieceWidth = pieceShape.getWidth();

		for (int row = 0; row < pieceHeight; row++) {
			for (int col = 0; col < pieceWidth; col++) {
				if (pieceShape.isPieceBrick(row, col) && this.checkIsFieldBrick(pieceY + row - 1, pieceX + col)) {
					return true;
				}
			}
		}

		return false;
	}

	public boolean isPieceIntoBrick(Piece piece) {
		int pieceX = piece.getX();
		int pieceY = piece.getY();

		PieceShape pieceShape = piece.getShape();
		int pieceHeight = pieceShape.getHeight();
		int pieceWidth = pieceShape.getWidth();

		for (int row = 0; row < pieceHeight; row++) {
			for (int col = 0; col < pieceWidth; col++) {
				if (pieceShape.isPieceBrick(row, col) && checkIsFieldBrick(pieceY + row, pieceX + col)) {
					return true;
				}
			}
		}

		return false;
	}

	public boolean doesPieceTouchLeftWall(Piece piece) {
		int pieceX = piece.getX();
		int pieceY = piece.getY();

		PieceShape pieceShape = piece.getShape();
		int pieceHeight = pieceShape.getHeight();
		int pieceWidth = pieceShape.getWidth();

		for (int row = 0; row < pieceHeight; row++) {
			for (int col = 0; col < pieceWidth; col++) {
				if (pieceShape.isPieceBrick(row, col) && pieceX + col == 0) {
					return true;
				}
			}
		}

		return false;
	}

	public boolean doesPieceTouchRightWall(Piece piece) {
		int pieceX = piece.getX();
		int pieceY = piece.getY();

		PieceShape pieceShape = piece.getShape();
		int pieceHeight = pieceShape.getHeight();
		int pieceWidth = pieceShape.getWidth();

		for (int row = 0; row < pieceHeight; row++) {
			for (int col = 0; col < pieceWidth; col++) {
				if (pieceShape.isPieceBrick(row, col) && pieceX + col == this.field[pieceY + row].length - 1) {
					return true;
				}
			}
		}

		return false;
	}

	public boolean doesPieceTouchesBottom(Piece piece) {
		int pieceX = piece.getX();
		int pieceY = piece.getY();

		PieceShape pieceShape = piece.getShape();
		int pieceHeight = pieceShape.getHeight();
		int pieceWidth = pieceShape.getWidth();

		for (int row = 0; row < pieceHeight; row++) {
			for (int col = 0; col < pieceWidth; col++) {
				if (pieceShape.isPieceBrick(row, col) && pieceY + row == 19) {
					return true;
				}
			}
		}

		return false;
	}

	public boolean doesPieceTouchesCelling(Piece piece) {
		int pieceX = piece.getX();
		int pieceY = piece.getY();

		PieceShape pieceShape = piece.getShape();
		int pieceHeight = pieceShape.getHeight();
		int pieceWidth = pieceShape.getWidth();

		for (int row = 0; row < pieceHeight; row++) {
			for (int col = 0; col < pieceWidth; col++) {
				if (pieceShape.isPieceBrick(row, col) && pieceY + row == 20) {
					return true;
				}
			}
		}

		return false;
	}

	public boolean isPieceOut(Piece piece) {
		int pieceX = piece.getX();
		int pieceY = piece.getY();

		PieceShape pieceShape = piece.getShape();
		int pieceHeight = pieceShape.getHeight();
		int pieceWidth = pieceShape.getWidth();

		for (int row = 0; row < pieceHeight; row++) {
			for (int col = 0; col < pieceWidth; col++) {
				if (pieceShape.isPieceBrick(row, col) && (!this.checkRowInField(pieceY + row)
						|| !this.checkCollInField(pieceY + row, pieceX + col))) {
					return true;
				}
			}
		}

		return false;
	}

	public void placePiece(Piece piece) {
		int pieceX = piece.getX();
		int pieceY = piece.getY();

		PieceShape pieceShape = piece.getShape();
		int pieceHeight = pieceShape.getHeight();
		int pieceWidth = pieceShape.getWidth();

		for (int row = 0; row < pieceHeight; row++) {
			for (int col = 0; col < pieceWidth; col++) {
				if (pieceShape.isPieceBrick(row, col)) {
					this.field[pieceY + row][pieceX + col] = FieldSquare.STACK;
					this.fieldColor[pieceY + row][pieceX + col] = pieceShape.getColor();
				}
			}
		}
	}

	public void destroyFullRows() {
		for (int row = 0; row < this.field.length; row++) {
			boolean isFullRow = true;
			for (int col = 0; col < this.field[row].length; col++) {
				if (this.field[row][col].equals(FieldSquare.EMPTY)) {
					isFullRow = false;
					break;
				}
			}

			if (isFullRow) {
				//this.shiftRows(row);
				this.shiftRowsUp1(row);
			}
		}
	}

	public void shiftRows(int startingRow) {
		for (int row = startingRow; row > 0; row--) {
			for (int col = 0; col < this.field[row].length; col++) {
				this.field[row][col] = this.field[row - 1][col];
			}
		}
	}
	// ------------------------------------this method was added-------------------------------
	public void shiftRowsUp(int startingRow) {

		for (int row = 0; row < this.field.length - 1; row++) {
			for (int col = 0; col < this.field[row].length; col++) {
				this.field[row][col] = this.field[row + 1][col];
			}
		}

		/*for (int col = 0; col < this.field[this.field.length - 1].length; col++) {
			this.field[this.field.length - 1][col] = FieldSquare.EMPTY;
		}*/
	}

	public void shiftRowsUp1(int startingRow) {
		for (int col = 0; col < this.field[startingRow].length; col++) {
			this.field[startingRow][col] = FieldSquare.EMPTY;
		}

		for (int row = startingRow + 1; row < this.field.length; row++) {
			for (int col = 0; col < this.field[row].length; col++) {
				if (this.field[row][col].equals(FieldSquare.EMPTY))
				{
					this.field[row - 1][col] = FieldSquare.EMPTY;
				}
				else if (this.field[row][col].equals(FieldSquare.STACK))
				{
					this.field[row - 1][col] = FieldSquare.STACK;
				}
				else
					this.field[row - 1][col] = this.field[row + 1][col];
			}
		}


		for (int col = 0; col < this.field[this.field.length - 1].length; col++) {
			this.field[this.field.length - 1][col] = FieldSquare.EMPTY;
		}
	}
}
