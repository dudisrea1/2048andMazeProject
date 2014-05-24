package view;

public interface View {
	void displayBoard(int[][] data);
	int getUserCommand();
	void displayScore(int score);
	void EndOfGame();
	void setUndo(boolean state);
	void displayBestScore(int score);
	void displayError(String string);
	void setStatusLabel(String string);
	boolean isShellDisposed();
	void disableUndo();
}
