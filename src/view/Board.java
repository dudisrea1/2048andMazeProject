package view;

import org.eclipse.swt.widgets.Composite;

/**
 * This interface represent the board used in the views, all boards must
 * implement the following methods
 * 
 * 
 */
public interface Board {
	/**
	 * Draw the current board based on matrix
	 * @param newboard - the board that need to draw
	 */
	public void drawBoard(int[][] newboard);

	/**
	 * Changes the focus to the current board
	 * @return true if it's focused and false if it's not focused
	 */
	public boolean setFocus();

	/**
	 * Changes the layout data of the board
	 * @param layoutData
	 */
	public void setLayoutData(Object layoutData);

	/**
	 * Changes the visibility of the board - whether to show it or not
	 * @param visible
	 */
	public void setVisible(boolean visible);

	/**
	 * Redraws the board
	 */
	public void redraw();

	/**
	 * Returns the parent of the board
	 * @return
	 */
	public Composite getParent();
}
