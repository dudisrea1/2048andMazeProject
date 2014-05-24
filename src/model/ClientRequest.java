package model;

import java.io.Serializable;

public class ClientRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private GeneralBoardInterface Board;
	private int depth, method;
	private Model model;
	
	public ClientRequest(GeneralBoardInterface Board, int depth, int method, Model model)
	{
		this.Board=Board;
		this.depth=depth;
		this.method=method;
		this.model=model;
	}

	public GeneralBoardInterface getBoard() {
		return Board;
	}

	public void setBoard(GeneralBoardInterface board) {
		Board = board;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getMethod() {
		return method;
	}

	public void setMethod(int method) {
		this.method = method;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}
	
	public void PrintBoard(){
		Board.Print();
	}

}
