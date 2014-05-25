package model;

import java.util.List;

public interface GeneralBoardInterface {
	/**
	 * get the board matrix
	 * 
	 * @return the board matrix
	 */
	int[][] getBoard();

	/**
	 * get the value of the matrix at board[y][x]
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	int getBoard(int x, int y);

	/**
	 * sets the value of board[y][x] to be val
	 * 
	 * @param x
	 * @param y
	 * @param val
	 */
	void setBoard(int x, int y, int val);

	/**
	 * replaces the board with the current board sets a win flag if it's
	 * true(found a tile with the win size) or false;
	 * 
	 * @param board
	 */
	void setBoard(int[][] board);

	/**
	 * @return the score
	 */
	int getScore();

	/**
	 * changes the score according to a given parameter
	 * 
	 * @param score
	 *            the new score to set
	 */
	void setScore(int score);

	/**
	 * @return the size of the board
	 */
	int getN();

	/**
	 * changes the board size
	 * 
	 * @param n
	 */
	void setN(int n);

	/**
	 * check if the user has won the game, giving the criteria for winning as
	 * param - using 2048 as a winning value
	 * 
	 * @param destination
	 *            - what's the tile that the user need to achieve in order to
	 *            win the game
	 * @return true if user won, false if he didn't
	 */
	boolean hasWon();

	/**
	 * Same as hasWon but sets the winning value to be destination
	 * 
	 * @param destination
	 * @return
	 */
	boolean hasWon(int destination);

	/**
	 * calculate number of empty cells, store the score in data member name
	 * emptycells
	 */
	void calcNumberOfEmptyCells();

	/**
	 * Check if emptycells never initialized, if that's the case calculating the
	 * number of empty cells and then return the emptcells data member
	 * 
	 * @return amount of empty cells in board matrix
	 */
	int getNumberOfEmptyCells();

	/**
	 * Returns a list of integers that holds the amount of empty cell Ids - used
	 * to calculate the next move
	 * 
	 * @return List<Integer>
	 */
	List<Integer> getEmptyCellIds();

	/**
	 * Changes an empty cell value with a given value parameter changes
	 * board[i][j] value
	 * 
	 * @param i
	 * @param j
	 * @param value
	 */
	void setEmptyCell(int i, int j, int value);

	/**
	 * Converts the board object to int from String hashCode func
	 * 
	 * @return
	 */
	int getBoardHash();
}
