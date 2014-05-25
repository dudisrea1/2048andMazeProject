package Game2048;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.GeneralBoardInterface;

public class Board2048 implements GeneralBoardInterface, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6529685098267757690L;
	private int[][] board;
	private int score, N, winSize = 2048, emptycells = -1;
	private boolean won;

	/**
	 * Constructor to create a new board
	 * 
	 * @param board
	 *            - matrix that represents the board
	 * @param score
	 *            - represents the board score
	 * @param N
	 *            - the size of the board
	 */
	public Board2048(int[][] board, int score, int N) {
		this.N = N;
		this.score = score;
		this.board = new int[N][N];
		setBoard(board);
	}

	/**
	 * Copy constructor
	 * 
	 * @param newboard
	 *            - the board to copy
	 */
	public Board2048(Board2048 newboard) {
		this.N = newboard.getN();
		this.score = newboard.getScore();
		this.board = new int[N][N];
		setBoard(newboard.getBoard());
	}

	public int[][] getBoard() {
		return board;
	}

	public int getBoard(int x, int y) {
		return board[y][x];
	}

	public void setBoard(int x, int y, int val) {
		board[y][x] = val;
	}

	public void setBoard(int[][] board) {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++) {
				this.board[i][j] = board[i][j];
				won = (this.board[i][j] == winSize);
			}
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getN() {
		return N;
	}

	public void setN(int n) {
		N = n;
	}

	/**
	 * check if the user has won the game
	 * 
	 * @return true if he did, false if he didn't
	 */
	public boolean hasWon() {
		return won;
	}

	public boolean hasWon(int destination) {
		winSize = destination;
		setBoard(this.board);
		return won;
	}

	public void calcNumberOfEmptyCells() {
		int count = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++) {
				if (this.board[i][j] == 0)
					++count;
			}
		emptycells = count;
	}

	public int getNumberOfEmptyCells() {
		if (emptycells == -1)
			calcNumberOfEmptyCells();
		return emptycells;
	}

	public List<Integer> getEmptyCellIds() {
		emptycells = 0;
		List<Integer> emptyCellsList = new ArrayList<Integer>();
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++) {
				if (this.board[i][j] == 0) {
					++emptycells;
					emptyCellsList.add(i * N + j);
				}
			}
		return emptyCellsList;
	}

	public void setEmptyCell(int i, int j, int value) {
		if (board[i][j] == 0) {
			board[i][j] = value;
			emptycells = -1;
		}
	}

	public int getBoardHash() {
		String str = "";
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++) {
				str += board[i][j];
			}
		return str.hashCode();
	}
}
