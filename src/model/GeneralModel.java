package model;

import java.util.Observable;
import java.util.Stack;

public class GeneralModel extends Observable implements Model {

	@Override
	public void moveUp() {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveDown() {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveLeft() {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveRight() {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveUp_Right() {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveUp_Left() {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveDown_Right() {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveDown_Left() {
		// TODO Auto-generated method stub

	}

	@Override
	public void undoMove() {
		// TODO Auto-generated method stub

	}

	@Override
	public int[][] getBoard() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBoard(int[][] board) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setScore(int score) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getBestScore() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setBestScore(int bestScore) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("rawtypes")
	@Override
	public Stack getMoves() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void setMoves(Stack moves) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean CheckEndOfGame() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void InitBoard() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean Save(String path) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Load(String path) {
		// TODO Auto-generated method stub
		return false;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean movement(int integer, int integer2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String ArrayToString(Integer[] bestMove) {
		// TODO Auto-generated method stub
		return null;
	}

}
