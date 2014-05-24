package model;

import java.net.Socket;
import java.util.Stack;

public interface Model {

	void moveUp();

	void moveDown();

	void moveLeft();

	void moveRight();

	void moveUp_Right();

	void moveUp_Left();

	void moveDown_Right();

	void moveDown_Left();

	/**
	 * Initiate undo - change the board state to the state before the last move
	 */
	void undoMove();

	/**
	 * Returns the board
	 * 
	 * @return int[][] which represents the board data
	 */
	int[][] getBoard();

	/**
	 * Changes the model board
	 * 
	 * @param board
	 *            - change current model board with this given matrix
	 */
	void setBoard(int[][] board);

	/**
	 * Get the score calculated in the model
	 * 
	 * @return int score
	 */
	int getScore();

	/**
	 * Changes he score
	 * 
	 * @param score
	 *            the new score
	 */
	void setScore(int score);

	/**
	 * Get the best score calculated in the model
	 * 
	 * @return
	 */
	int getBestScore();

	/**
	 * Changes the best score in the model
	 * 
	 * @param bestScore
	 */
	void setBestScore(int bestScore);

	/**
	 * Return a stack of moves done by the player
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	Stack getMoves();

	/**
	 * Based on a given stack, changes the current existing stack in the model
	 * 
	 * @param moves
	 */
	@SuppressWarnings("rawtypes")
	void setMoves(Stack moves);

	/**
	 * Returns the state of the game - if it's over or not
	 * @return true if the game is over, else return false
	 */
	boolean CheckEndOfGame();

	/**
	 * Initialize the current board 
	 */
	void InitBoard();

	/**
	 * Saves the model
	 * @param path export the model to XML in the path given as param
	 * @return true if save succeed or false if save failed
	 */
	boolean Save(String path);

	/**
	 * Loads the model based on a given path
	 * @param path the destination target file to load
	 * @return boolean - whether the XML load succeed or not
	 */
	boolean Load(String path);

	/**
	 * Changes the difficulty of the game
	 * @param arg1 Normal or Hard difficulty
	 */
	void setDifficulty(String arg1);

	Integer[] GetBestMove(Socket myServer);
	
	String GetErrorMessage();

	boolean movement(int integer, int integer2);

	String ArrayToString(Integer[] bestMove);

	void setSolverServerProperties(ServerProperties arg1);

	boolean CanAskServer();

//	int[][] getBoardArr();

	void ExecuteMoves(final int arg1,final Socket myServer);
}
