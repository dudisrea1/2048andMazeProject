package view;

public interface View {
	/**
	 * Display the matrix receives on the board
	 * @param data
	 */
	void displayBoard(int[][] data);
	/**
	 * returns the user command
	 * @return
	 */
	int getUserCommand();
	/**
	 * Displays the score on the GUI
	 * @param score
	 */
	void displayScore(int score);
	/**
	 * Displays end of game message with score
	 */
	void EndOfGame();
	/**
	 * Change the undo button (enable / disable)
	 * @param state
	 */
	void setUndo(boolean state);
	/**
	 * Change the best score
	 * @param score
	 */
	void displayBestScore(int score);
	/**
	 * Display error message
	 * @param string
	 */
	void displayError(String string);
	/**
	 * Changes the status label string
	 * @param string
	 */
	void setStatusLabel(String string);
	/**
	 * Checks if the shell is still alive
	 * @return true if the shell is alive, false if the shell is closed
	 */
	boolean isShellDisposed();
	/**
	 * Change the undo button 
	 */
	void disableUndo();
}
