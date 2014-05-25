package presenter;

import java.io.IOException;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

import model.Model;
import model.ModelMovements;
import model.ServerProperties;
import view.View;
import Game2048.Game2048Model;
import GameMaze.GameMazeModel;

public class Presenter implements Observer {
	View ui;
	Model model = null;
	ModelMovements mvGeneral, mv2048, mvMaze;
	Game2048Model g2048;
	GameMazeModel gMaze;
	Socket serverSocket = null;

	/**
	 * Constructor that builds the presenter
	 * 
	 * @param model
	 *            sets the model to work with
	 * @param ui
	 *            sets the needed UI
	 */
	public Presenter(Model model, View ui) {
		this.ui = ui;
		g2048 = new Game2048Model();
		mv2048 = new ModelMovements(g2048);
		gMaze = new GameMazeModel();
		mvMaze = new ModelMovements(gMaze);
	}

	/**
	 * Update function will be called once one of the observables
	 * notifyObservers
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg0 == ui) {
			switch (ui.getUserCommand()) {
			case -1:
				if (serverSocket != null && !serverSocket.isClosed()) {
					try {
						serverSocket.close();
					} catch (IOException e1) {
					}
				}
				break;
			// Starts a new Game
			case 9:
				model.InitBoard();
				break;
			// Undo Move
			case 10:
				model.undoMove();
				break;
			// Saves the model to file
			case 11:
				model.Save((String) arg1);
				break;
			// Loads an existing model if given path
			case 12:
				if (model.Load((String) arg1)) {
					ui.displayBoard(model.getBoard());
					ui.displayScore(model.getScore());
					ui.displayBestScore(model.getBestScore());
					ui.setUndo(model.getMoves().size() != 0);
				}
				break;
			// Changes the difficulty
			case 15:
				model.setDifficulty(arg1.toString());
				break;
			// Sets the client server properties
			case 17:
				final ServerProperties sp = (ServerProperties) arg1;
				// Opens a thread in order not to block the GUI
				// Sets the serverSocket to work against, if the socket was used
				// - close it before opening a new one
				// if failed to connect display Error

				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							if (serverSocket != null)
								serverSocket.close();
							serverSocket = new Socket(sp
									.getSolverServerAddress(), sp
									.getSolverServerPort());
							model.setSolverServerProperties(sp);
						} catch (IOException e) {
							if (!ui.isShellDisposed())
								ui.displayError("Failed to connect to Solver server");
						}

					}
				}).start();
				break;
			// Changes the model to work with Specific game model.
			// Set the model to work as Maze
			case 100:
				if (model == null || model.getClass() != GameMazeModel.class) {
					g2048.deleteObserver(this);
					gMaze.addObserver(this);
					model = gMaze;
					mvGeneral = mvMaze;
					if (gMaze.getMoves().size() > 0)
						ui.setUndo(true);
					else
						ui.setUndo(false);
				}
				break;
			// Changes the model to work with Specific game model.
			// Set the model to work as Game2048
			case 200:
				if (model == null || model.getClass() != Game2048Model.class) {
					gMaze.deleteObserver(this);
					g2048.addObserver(this);
					model = g2048;
					mvGeneral = mv2048;
					if (g2048.getMoves().size() > 0)
						ui.setUndo(true);
					else
						ui.setUndo(false);
				}
				break;
			// Hint needed
			case 55:
				if (!ui.isShellDisposed() && model.CanAskServer()
						&& !model.CheckEndOfGame()) {
					model.ExecuteMoves((int) arg1, serverSocket);
				}
				break;
			default:
				if (mvGeneral != null)
					mvGeneral.Command(ui.getUserCommand());
				break;
			}
			// If the notification received from other source (model) will
			// redisplay the board, score, bestscore, checks if there's no more
			// moves and end the game if true
		} else {
			if (!ui.isShellDisposed()) {
				ui.displayBoard(model.getBoard());
				ui.displayScore(model.getScore());
				ui.displayBestScore(model.getBestScore());
				if (model.CheckEndOfGame()) {
					ui.EndOfGame();
				}
				if (model.getMoves().size() == 0)
					ui.disableUndo();
			}
		}
	}
}
