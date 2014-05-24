package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class GameMazeBoard extends Composite implements Board {
	private int[][] board;
	final private int N = 10;
	private Label cel_lbl[][] = new Label[N][N];

	private boolean MousePressed = false;

	/*
	 * private int[] MousePressCell = new int[2]; private int[] MouseReleseCell
	 * = new int[2];
	 */

	public GameMazeBoard(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(N, true));
		board = new int[N][N];
		for (int y = 0; y < N; y++)
			for (int x = 0; x < N; x++) {
				cel_lbl[y][x] = new Label(this, SWT.BORDER);
				cel_lbl[y][x].setLayoutData(new GridData(SWT.FILL, SWT.FILL,
						true, true, 0, 0));
				cel_lbl[y][x].setBackground(new Color(getDisplay(), 100, 100,
						100));
			}
	}

	public void drawBoard(int[][] data) {
		for (int y = 0; y < data.length; y++)
			for (int x = 0; x < data[0].length; x++) {
				switch (data[y][x]) {
				case -1:
					cel_lbl[y][x].setImage(null);
					cel_lbl[y][x].setBackground(new Color(cel_lbl[y][x]
							.getDisplay(), new RGB(0, 0, 0)));
					break;

				case 1:
					cel_lbl[y][x].setBackground(new Color(cel_lbl[y][x]
							.getDisplay(), new RGB(255, 255, 255)));
					cel_lbl[y][x].setImage(new Image(getDisplay(),
							"Resources/mouse.png"));
					break;

				case 0:
					cel_lbl[y][x].setImage(null);
					cel_lbl[y][x].setBackground(new Color(cel_lbl[y][x]
							.getDisplay(), new RGB(255, 255, 255)));
					break;

				case 2:
					cel_lbl[y][x].setBackground(new Color(cel_lbl[y][x]
							.getDisplay(), new RGB(255, 255, 255)));
					cel_lbl[y][x].setImage(new Image(getDisplay(),
							"Resources/cheese.png"));
					break;
				}
				board[y][x] = data[y][x];
			}
	}

	/**
	 * Returns a label of a specific cell
	 * 
	 * @param x
	 * @param y
	 * @return the cell[y][x] if exists, else returns null
	 */
	public Label getCell(int x, int y) {
		if (0 <= y && y < N && 0 <= x && x < N)
			return cel_lbl[y][x];
		return null;
	}

	/**
	 * @param y
	 * @param x
	 * @return true if the mouse is at board[y][x]
	 */
	public boolean isMouse(int y, int x) {
		return board[y][x] == 1;
	}

	/**
	 * retuns true if the cheese is at board[y][x]
	 * 
	 * @param y
	 * @param x
	 * @return
	 */
	public boolean isCheese(int y, int x) {
		return board[y][x] == 2;
	}

	/**
	 * @param y
	 * @param x
	 * @return true if board[y][x] is a wall
	 */
	public boolean isWall(int y, int x) {
		return board[y][x] == -1;
	}

	/**
	 * @return true if the mouse was pressed
	 */
	public boolean isMousePressed() {
		return MousePressed;
	}

	/**
	 * Changes the mousePressed boolean to be true(if mouse was clicked) and
	 * false(if mouse was clicked again)
	 * 
	 * @param mousePressed
	 */
	public void setMousePressed(boolean mousePressed) {
		MousePressed = mousePressed;
	}

}
