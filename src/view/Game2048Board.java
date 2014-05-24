package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

public class Game2048Board extends Composite implements Board {
	int[][] boardData; // the data of the board
	int N = 4;
	Cell cell[][];

	public Game2048Board(Composite parent, int style) {
		super(parent, style); // call canvas Ctor
		boardData = new int[N][N];
		setLayout(new GridLayout(N, true));
		cell = new Cell[N][N];
		for (int y = 0; y < N; y++)
			for (int x = 0; x < N; x++) {
				cell[y][x] = new Cell(this, SWT.BORDER);
				cell[y][x].setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
						true));
				cell[y][x].setValue(boardData[y][x]);
			}
	}

	@Override
	public void drawBoard(int[][] newboard) {
		this.boardData = newboard;
		for (int y = 0; y < N; y++)
			for (int x = 0; x < N; x++) {
				cell[y][x].setValue(boardData[y][x]);
			}
	}

}