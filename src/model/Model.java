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
	 * 
	 * @return true if the game is over, else return false
	 */
	boolean CheckEndOfGame();

	/**
	 * Initialize the current board
	 */
	void InitBoard();

	/**
	 * Saves the model
	 * 
	 * @param path
	 *            export the model to XML in the path given as param
	 * @return true if save succeed or false if save failed
	 */
	boolean Save(String path);

	/**
	 * Loads the model based on a given path
	 * 
	 * @param path
	 *            the destination target file to load
	 * @return boolean - whether the XML load succeed or not
	 */
	boolean Load(String path);

	/**
	 * Changes the difficulty of the game
	 * 
	 * @param arg1
	 *            Normal or Hard difficulty
	 */
	void setDifficulty(String arg1);

	/**
	 * Returns Int array contains the best move to execute
	 * 
	 * @param myServer
	 *            the server that we need to ask for solution
	 * @return Integer[] - reprensents the best move
	 */
	Integer[] GetBestMove(Socket myServer);

	/**
	 * @return the error message (if any)
	 */
	String GetErrorMessage();

	/**
	 * Moving the board according to the movement received
	 * 
	 * @param integer
	 * @param integer2
	 * @return true if the board was changed or not
	 */
	boolean movement(int integer, int integer2);

	/**
	 * Shifts a given Integer array to move string
	 * 
	 * @param bestMove
	 * @return String that represents the move that was made
	 */
	String ArrayToString(Integer[] bestMove);

	/**
	 * Sets the solver server properties
	 * 
	 * @param arg1
	 *            - properties
	 */
	void setSolverServerProperties(ServerProperties arg1);

	/**
	 * Holds the ServerProperties values, if it's not set then the server is not
	 * configured thus can't ask it
	 * 
	 * @return true if can ask server (ServerProperties != null) and false if
	 *         it's equals to null
	 */
	boolean CanAskServer();

	/**
	 * Executes "arg1" moves - sends the myServer the board and request for a
	 * move, for arg1 times.
	 * 
	 * @param arg1
	 * @param myServer
	 */
	void ExecuteMoves(final int arg1, final Socket myServer);
}
