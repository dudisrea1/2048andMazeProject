package model;

import java.io.Serializable;

public class ClientRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private GeneralBoardInterface Board;
	private int depth, method;
	private Model model;

	/**
	 * Client request holds the Board, depth,method and Model to use when
	 * contacting the server
	 * 
	 * @param Board
	 *            - the board (should implement GeneralBoardInterface) to work
	 *            on
	 * @param depth
	 *            - the depth of lookup
	 * @param method
	 *            - the method to use (0-minimax, 1-alphabeta,2-expectimax)
	 * @param model - which Model to use (2048Model or maybe MazeModel in the future) 
	 */
	public ClientRequest(GeneralBoardInterface Board, int depth, int method,
			Model model) {
		this.Board = Board;
		this.depth = depth;
		this.method = method;
		this.model = model;
	}

	
	/**
	 * Holds the board
	 * @return board used
	 */
	public GeneralBoardInterface getBoard() {
		return Board;
	}

	/**
	 * Changes the board used
	 * @param board
	 */
	public void setBoard(GeneralBoardInterface board) {
		Board = board;
	}

	/**
	 * Holds the depth value
	 * @return
	 */
	public int getDepth() {
		return depth;
	}

	/**
	 * Set the depth used
	 * @param depth
	 */
	public void setDepth(int depth) {
		this.depth = depth;
	}

	/**
	 * Returns the method to be used
	 * @return
	 */
	public int getMethod() {
		return method;
	}

	/**
	 * Changes the mode used
	 * @param method
	 */
	public void setMethod(int method) {
		this.method = method;
	}

	/**
	 * Holds the model used
	 * @return the model that is used
	 */
	public Model getModel() {
		return model;
	}

	/**
	 * Changes the model
	 * @param model
	 */
	public void setModel(Model model) {
		this.model = model;
	}

}
