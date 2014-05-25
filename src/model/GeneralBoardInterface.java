package model;

import java.util.List;

public interface GeneralBoardInterface {
	int[][] getBoard();
	int getBoard(int x, int y);
	void setBoard(int x, int y, int val);
	void setBoard(int[][] board);
	int getScore();
	void setScore(int score);
	int getN();
	void setN(int n);
	boolean hasWon();
	boolean hasWon(int destination);
	void calcNumberOfEmptyCells();
	int getNumberOfEmptyCells();
	List<Integer> getEmptyCellIds();
	void setEmptyCell(int i, int j, int value);
	int getBoardHash();	
}
