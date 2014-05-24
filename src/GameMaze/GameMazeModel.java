package GameMaze;

import java.util.Observable;
import java.util.Stack;

import model.GeneralBoardInterface;
import model.Model;
import Converter.GameMazeXMLManager;

public class GameMazeModel extends Observable implements Model {

	private int[][] Maze_Array;
	private int MazeRows, MazeColumns;
	private int[] CurrentState = new int[2];
	private int[] GoalState = new int[2];
	private int score, bestScore = 0;
	private Stack<Integer[]> MovesStack = new Stack<Integer[]>();
	private GameMazeXMLManager GameMazeModelXMLManager = new GameMazeXMLManager();
	private String ErrorMessage="";

	public GameMazeModel() {
		MazeRows = 10;
		MazeColumns = 10;
		Maze_Array = new int[MazeRows][MazeColumns];
		// set walls around the maze
		for (int i = 0; i < MazeColumns; i++) {
			Maze_Array[i][0] = -1;
			Maze_Array[i][MazeRows - 1] = -1;
		}
		for (int i = 0; i < MazeRows; i++) {
			Maze_Array[0][i] = -1;
			Maze_Array[MazeColumns - 1][i] = -1;
		}

		for (int i = 1; i < MazeColumns - 2; i++) {
			Maze_Array[3][i] = -1;
			Maze_Array[5][i + 1] = -1;
		}

	}

	/**
	 * Copy constructer
	 * 
	 * @param newmodel
	 *            receives a GameMazeModel and creates an identical model
	 */
	public GameMazeModel(GameMazeModel newmodel) {
		this.Maze_Array = newmodel.Maze_Array;
		this.MazeRows = newmodel.MazeRows;
		this.MazeColumns = newmodel.MazeColumns;
		this.CurrentState = newmodel.CurrentState;
		this.GoalState = newmodel.GoalState;
		this.score = newmodel.score;
		this.bestScore = newmodel.bestScore;
		this.MovesStack = newmodel.MovesStack;
	}

	/**
	 * Creates a new game maze model based on all parameters
	 * 
	 * @param Board
	 * @param CurrentStateColumn
	 * @param CurrentStateRow
	 * @param Score
	 * @param BestScore
	 * @param MovesStack
	 */
	public GameMazeModel(int[][] Board, int CurrentStateColumn,
			int CurrentStateRow, int Score, int BestScore, Stack MovesStack) {
		MazeRows = 10;
		MazeColumns = 10;
		Maze_Array = new int[MazeColumns][MazeRows];

		Maze_Array = Board;

		CurrentState[0] = CurrentStateColumn;
		CurrentState[1] = CurrentStateRow;

		this.score = Score;
		this.bestScore = BestScore;
		this.MovesStack = MovesStack;

	}

	/**
	 * @return the column length
	 */
	public int GetMazeColumns() {
		return MazeColumns;
	}

	/**
	 * @return the rows length
	 */
	public int GetMazeRows() {
		return MazeRows;
	}

	/**
	 * Sets the Mouse position at board[column][row]
	 * 
	 * @param column
	 * @param row
	 * @return true or false whether the operation succeed
	 */
	public boolean SetStartState(int column, int row) {
		try {
			Maze_Array[column][row] = 1;

			return true;
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * Set the cheese position state at board[column][row]
	 * 
	 * @param column
	 * @param row
	 * @return true or false whether the operation succeed
	 */
	public boolean SetGoalState(int column, int row) {
		try {
			Maze_Array[column][row] = 2;
			GoalState[0] = column;
			GoalState[1] = row;

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Set the wall position state at board[column][row]
	 * 
	 * @param column
	 * @param row
	 * @return true or false whether the operation succeed
	 */
	public boolean SetWall(int column, int row) {
		try {
			Maze_Array[column][row] = -1;
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * Check if the location is a valid position for the board, that you're
	 * inside the board and the current position state is not a wall
	 * 
	 * @param column
	 * @param row
	 * @return true or false whether the position is a alid state
	 */
	public boolean IsValidState(int column, int row) {
		try {
			if (row >= 0 && column >= 0) {
				if (Maze_Array[row][column] != -1) {
					return true;
				} else
					return false;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * Moves the mouse Up
	 */
	@Override
	public void moveUp() {
		if (IsValidState(CurrentState[1], CurrentState[0] - 1)) {
			MovesStack.push(new Integer[] { CurrentState[0], CurrentState[1],
					score });
			Maze_Array[CurrentState[0]][CurrentState[1]] = 0;
			Maze_Array[CurrentState[0] - 1][CurrentState[1]] = 1;
			CurrentState[0]--;
			score += 10;
			setChanged();
			notifyObservers();
		}

	}

	/**
	 * Moves the mouse Down
	 */
	@Override
	public void moveDown() {
		if (IsValidState(CurrentState[1], CurrentState[0] + 1)) {
			MovesStack.push(new Integer[] { CurrentState[0], CurrentState[1],
					score });
			Maze_Array[CurrentState[0]][CurrentState[1]] = 0;
			Maze_Array[CurrentState[0] + 1][CurrentState[1]] = 1;
			CurrentState[0]++;
			score += 10;
			setChanged();
			notifyObservers();
		}

	}

	/**
	 * Moves the mouse Left
	 */
	@Override
	public void moveLeft() {
		if (IsValidState(CurrentState[1] - 1, CurrentState[0])) {
			MovesStack.push(new Integer[] { CurrentState[0], CurrentState[1],
					score });
			Maze_Array[CurrentState[0]][CurrentState[1]] = 0;
			Maze_Array[CurrentState[0]][CurrentState[1] - 1] = 1;
			CurrentState[1]--;
			score += 10;
			setChanged();
			notifyObservers();
		}

	}

	/**
	 * Moves the mouse Right
	 */
	@Override
	public void moveRight() {
		if (IsValidState(CurrentState[1] + 1, CurrentState[0])) {
			MovesStack.push(new Integer[] { CurrentState[0], CurrentState[1],
					score });
			Maze_Array[CurrentState[0]][CurrentState[1]] = 0;
			Maze_Array[CurrentState[0]][CurrentState[1] + 1] = 1;
			CurrentState[1]++;
			score += 10;
			setChanged();
			notifyObservers();
		}

	}

	/**
	 * Moves the mouse Up and Right
	 */
	@Override
	public void moveUp_Right() {
		if (IsValidState(CurrentState[1] + 1, CurrentState[0] - 1)) {
			MovesStack.push(new Integer[] { CurrentState[0], CurrentState[1],
					score });
			Maze_Array[CurrentState[0]][CurrentState[1]] = 0;
			Maze_Array[CurrentState[0] - 1][CurrentState[1] + 1] = 1;
			CurrentState[1]++;
			CurrentState[0]--;
			score += 15;
			setChanged();
			notifyObservers();
		}
	}

	/**
	 * Moves th mouse Up and Left
	 */
	@Override
	public void moveUp_Left() {
		if (IsValidState(CurrentState[1] - 1, CurrentState[0] - 1)) {
			MovesStack.push(new Integer[] { CurrentState[0], CurrentState[1],
					score });
			Maze_Array[CurrentState[0]][CurrentState[1]] = 0;
			Maze_Array[CurrentState[0] - 1][CurrentState[1] - 1] = 1;
			CurrentState[1]--;
			CurrentState[0]--;
			score += 15;
			setChanged();
			notifyObservers();
		}

	}

	/**
	 * Moves the mouse Down and Right
	 */
	@Override
	public void moveDown_Right() {
		if (IsValidState(CurrentState[1] + 1, CurrentState[0] + 1)) {
			MovesStack.push(new Integer[] { CurrentState[0], CurrentState[1],
					score });
			Maze_Array[CurrentState[0]][CurrentState[1]] = 0;
			Maze_Array[CurrentState[0] + 1][CurrentState[1] + 1] = 1;
			CurrentState[1]++;
			CurrentState[0]++;
			score += 15;
			setChanged();
			notifyObservers();
		}

	}

	/**
	 * Moves the mouse Down and Left
	 */
	@Override
	public void moveDown_Left() {
		if (IsValidState(CurrentState[1] - 1, CurrentState[0] + 1)) {
			MovesStack.push(new Integer[] { CurrentState[0], CurrentState[1],
					score });
			Maze_Array[CurrentState[0]][CurrentState[1]] = 0;
			Maze_Array[CurrentState[0] + 1][CurrentState[1] - 1] = 1;
			CurrentState[1]--;
			CurrentState[0]++;
			score += 15;
			setChanged();
			notifyObservers();
		}
	}

	@Override
	public void InitBoard() {
		// reset current state position
		if (CurrentState[0] != 0 && CurrentState[1] != 0)
			Maze_Array[CurrentState[0]][CurrentState[1]] = 0;

		MovesStack.clear();
		score = 0;
		SetStartState(MazeColumns - 2, MazeRows - 1);
		SetGoalState(1, 0);
		CurrentState[0] = MazeRows - 2;
		CurrentState[1] = MazeColumns - 1;

		setChanged();
		notifyObservers();
	}

	@Override
	public void undoMove() {
		if (!MovesStack.isEmpty()) {
			Integer[] move = MovesStack.pop();
			if (CurrentState[0] == GoalState[0]
					&& CurrentState[1] == GoalState[1])
				Maze_Array[CurrentState[0]][CurrentState[1]] = 2;
			else
				Maze_Array[CurrentState[0]][CurrentState[1]] = 0;
			Maze_Array[move[0]][move[1]] = 1;
			CurrentState[0] = move[0];
			CurrentState[1] = move[1];
			score = move[2];

			setChanged();
			notifyObservers();
		}

	}

	@Override
	public int[][] getBoard() {
		return Maze_Array;
	}

	@Override
	public int getScore() {
		return score;
	}

	@Override
	public boolean CheckEndOfGame() {
		if (CurrentState[0] == GoalState[0] && CurrentState[1] == GoalState[1]) {

			if (bestScore < score)
				bestScore = score;
			return true;
		}

		else
			return false;
	}


	@Override
	public int getBestScore() {
		return bestScore;
	}

	@Override
	public Stack<Integer[]> getMoves() {

		return MovesStack;
	}

	@Override
	public void setScore(int score) {
		this.score = score;

	}

	@Override
	public void setBestScore(int bestScore) {
		this.bestScore = bestScore;

	}

	@Override
	public void setBoard(int[][] board) {
		Maze_Array = board;

	}

	@Override
	public boolean Save(String path) {
		return GameMazeModelXMLManager.ToFile(new GameMazeModel(this), path);
	}

	@Override
	public boolean Load(String path) {
		GameMazeModel LoadedModel = (GameMazeModel) GameMazeModelXMLManager
				.FromFile(path);
		if (LoadedModel == null)
			return false;
		this.setBoard(LoadedModel.getBoard());
		this.setScore(LoadedModel.getScore());
		this.setBestScore(LoadedModel.getBestScore());
		this.setMoves(LoadedModel.getMoves());
		this.CurrentState = LoadedModel.CurrentState;
		return true;
	}

	@Override
	public void setMoves(Stack moves) {
		this.MovesStack = moves;

	}

	@Override
	public void setDifficulty(String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public Integer[] GetBestMove(int depth,int method) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSolverServerAddress(String IP) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String GetErrorMessage() {
		return ErrorMessage;
	}

}
