package Screens;

import helper.ViewUtilities;
import model.ServerProperties;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class OptionScreen extends Dialog {
	private String difficulty = "Normal", SelectedGame = "Empty",
			SolverServerAddress = "127.0.0.1";
	private boolean sound = false, serverEnabled = false, changed = false;
	private int methodSelection = 0, portNumber = 5001, depth = 6;
	private Group GamesGroup, DifficultyGroup, SoundGroup, ServerGroup;
	private Button GameMaze_btn, Game2048_btn, normal, hard, on, off, OK_btn;
	private Text SolverServerAddress_text, SolverServerDepth, SolverServerPort;
	private Combo solverMethod;
	private Shell optinsScreen_shell;

	public OptionScreen(Shell parent) {
		super(parent, 0);
	}

	/**
	 * Set the default selection of the window
	 */
	private void setAllSelections() {
		GameMaze_btn.setSelection(SelectedGame.equals("Maze"));
		Game2048_btn.setSelection(SelectedGame.equals("2048"));
		normal.setSelection(difficulty.equals("Normal"));
		hard.setSelection(difficulty.equals("Hard"));
		on.setSelection(sound);
		off.setSelection(!sound);
		SolverServerAddress_text.setText(SolverServerAddress);
		if (portNumber >= 1)
			SolverServerPort.setText(new String("" + portNumber));
		else
			// Displays the default port used
			SolverServerPort.setText(new String("" + 5001));
	}

	/**
	 * Opens the new window shell
	 * 
	 * @return String that represent the chosen game
	 */
	public String open() {
		changed = false;
		optinsScreen_shell = new Shell(Display.getCurrent(), (SWT.CLOSE
				| SWT.TITLE | SWT.BORDER | SWT.APPLICATION_MODAL | SWT.RESIZE)
				& (~SWT.RESIZE));
		optinsScreen_shell.setLayout(new GridLayout(1, true));
		optinsScreen_shell.setText("Options");

		GamesGroup = new Group(optinsScreen_shell, SWT.SHADOW_OUT);
		GamesGroup.setText("Choose Game:");
		GamesGroup.setLayout(new GridLayout(2, true));
		GamesGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
				1, 1));

		Game2048_btn = new Button(GamesGroup, SWT.RADIO);
		Game2048_btn.setText("2048 Game");
		Game2048_btn.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				false, 1, 1));

		GameMaze_btn = new Button(GamesGroup, SWT.RADIO);
		GameMaze_btn.setText("Maze Game");
		GameMaze_btn.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				false, 1, 1));

		DifficultyGroup = new Group(optinsScreen_shell, SWT.SHADOW_OUT);
		DifficultyGroup.setText("Choose Difficulty:");
		DifficultyGroup.setLayout(new GridLayout(2, true));
		DifficultyGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true, 1, 1));

		normal = new Button(DifficultyGroup, SWT.RADIO);
		normal.setText("Normal");
		normal.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		normal.setSelection(true);

		hard = new Button(DifficultyGroup, SWT.RADIO);
		hard.setText("Hard");
		hard.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

		SoundGroup = new Group(optinsScreen_shell, SWT.SHADOW_OUT);
		SoundGroup.setText("Toggle Sound");
		SoundGroup.setLayout(new GridLayout(2, true));
		SoundGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
				1, 1));

		on = new Button(SoundGroup, SWT.RADIO);
		on.setText("On");
		on.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

		off = new Button(SoundGroup, SWT.RADIO);
		off.setText("Off");
		off.setSelection(true);
		off.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

		ServerGroup = new Group(optinsScreen_shell, SWT.SHADOW_OUT);
		ServerGroup.setText("Solver Server:");
		ServerGroup.setLayout(new GridLayout(2, true));
		ServerGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
				1, 1));

		Text serverAddress = new Text(ServerGroup, SWT.NONE);
		serverAddress.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true, 1, 1));
		serverAddress.setEnabled(false);
		serverAddress.setText("IP Address:");
		SolverServerAddress_text = new Text(ServerGroup, SWT.BORDER);
		SolverServerAddress_text.setLayoutData(new GridData(SWT.FILL, SWT.FILL,
				true, true, 1, 1));

		Text serverPort = new Text(ServerGroup, SWT.NONE);
		serverPort.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
				1, 1));
		serverPort.setEnabled(false);
		serverPort.setText("Port number:");
		SolverServerPort = new Text(ServerGroup, SWT.BORDER);
		SolverServerPort.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true, 1, 1));

		Text solverMethodtext = new Text(ServerGroup, SWT.NONE);
		solverMethodtext.setEnabled(false);
		solverMethodtext.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true, 1, 1));
		solverMethodtext.setText("Choose Method");
		solverMethod = new Combo(ServerGroup, SWT.READ_ONLY);
		solverMethod.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
				1, 1));
		solverMethod.setItems(new String[] { "Minimax", "Alpha-Beta",
				"Expectimax" });
		solverMethod.select(methodSelection);

		Text solverDepthtext = new Text(ServerGroup, SWT.NONE);
		solverDepthtext.setEnabled(false);
		solverDepthtext.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true, 1, 1));
		solverDepthtext.setText("Choose Depth");
		SolverServerDepth = new Text(ServerGroup, SWT.BORDER);
		SolverServerDepth.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true, 1, 1));
		SolverServerDepth.setText("" + depth);

		OK_btn = new Button(optinsScreen_shell, SWT.PUSH);
		OK_btn.setText("OK");
		OK_btn.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

		OK_btn.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (GameMaze_btn.getSelection()) {
					SelectedGame = "Maze";
					GameMaze_btn.setSelection(true);
				} else if (Game2048_btn.getSelection()) {
					SelectedGame = "2048";
					Game2048_btn.setSelection(true);
				}
				setSound(on.getSelection());
				if (normal.getSelection()) {
					setDifficulty("Normal");
				} else {
					setDifficulty("Hard");
				}
				HandleMissingValues();
				HandleInvalidValues();
				changed = true;
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				widgetSelected(arg0);

			}
		});
		setAllSelections();
		optinsScreen_shell.pack();
		optinsScreen_shell.open();

		while (!optinsScreen_shell.isDisposed()) {
			if (!Display.getCurrent().readAndDispatch()) {
				Display.getCurrent().sleep();
			}
		}
		return SelectedGame;
	}

	/**
	 * Handles invalid values for example invalid IP address, port or depth
	 */
	protected void HandleInvalidValues() {
		if (!optinsScreen_shell.isDisposed()) {
			String invalid = "";
			methodSelection = solverMethod.getSelectionIndex();
			if (ViewUtilities.VerifyAddress(SolverServerAddress_text.getText()))
				SolverServerAddress = SolverServerAddress_text.getText();
			else
				invalid += "Invalid IP address, must be X.X.X.X where x between 0-255.\n";
			portNumber = ViewUtilities.verifyNumberInRange(
					SolverServerPort.getText(), 1, 65535);
			if (portNumber == -1)
				invalid += "Invalid Port number, must be number between 1-65535.\n";
			depth = ViewUtilities.verifyNumberInRange(
					SolverServerDepth.getText(), 1, 15);
			if (depth == -1)
				invalid += "Invalid Depth chosen, must be a number between 1-15.";
			if (invalid.isEmpty()) {
				if (depth > 7)
					ViewUtilities
							.displayMessage(
									Display.getDefault(),
									optinsScreen_shell,
									"Depth is high",
									"You choose depth "
											+ depth
											+ ", this is a high number and will take time to calculate each step ",
									SWT.ICON_INFORMATION);
				serverEnabled = true;
				optinsScreen_shell.dispose();
			}

			else
				ViewUtilities.displayMessage(Display.getDefault(),
						optinsScreen_shell, "Invalid Parameters given",
						invalid, SWT.ERROR);
		}
	}

	/**
	 * Handles missing values for example missing IP address, port or depth
	 */
	protected void HandleMissingValues() {
		String missing = "";
		if (SolverServerAddress_text.getText().isEmpty()) {
			SolverServerAddress = "";
			missing += ", IP Address";
		}
		if (SolverServerPort.getText().isEmpty())
			missing += ", Port";
		if (SolverServerDepth.getText().isEmpty())
			missing += ", Depth";

		if (SelectedGame.equals("Maze")) {
			serverEnabled = false;
			ViewUtilities.displayMessage(Display.getDefault(),
					optinsScreen_shell, "Maze game",
					"Hints are not supported in Maze Game, Good luck :)",
					SWT.ICON_INFORMATION);
			optinsScreen_shell.dispose();
		}

		if (!optinsScreen_shell.isDisposed() && !missing.isEmpty()) {
			serverEnabled = false;
			ViewUtilities.displayMessage(Display.getDefault(),
					optinsScreen_shell, "Missing Server Parameters",
					"Hint will be disabled since " + missing.substring(2)
							+ " parameters are missing.", SWT.ICON_INFORMATION);
			optinsScreen_shell.dispose();
		}
	}

	/**
	 * Holds the solver server IP address
	 * 
	 * @return IP address
	 */
	public String getSolverServerAddress() {
		return SolverServerAddress;
	}

	/**
	 * Holds the solver server Port
	 * 
	 * @return Port number or -1 if port was not set
	 */
	public int getSolverPort() {
		return portNumber;
	}

	/**
	 * Holds the solver method that was chosen
	 * 
	 * @return number that represents the method 0 - Minimax 1 - Alpha-Beta 2 -
	 *         Expectimax
	 */
	public int getSolverMethod() {
		return methodSelection;
	}

	/**
	 * Holds the solver depth that was used
	 * 
	 * @return the depth value
	 */
	public int getSolverDepth() {
		return depth;
	}

	/**
	 * Holds the difficulty value
	 * 
	 * @return difficulty
	 */
	public String getDifficulty() {
		return difficulty;
	}

	/**
	 * Change the difficulty value
	 * 
	 * @param difficulty
	 */
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	/**
	 * Returns true if sound should be enabled
	 * 
	 * @return
	 */
	public boolean isSound() {
		return sound;
	}

	/**
	 * Changes the sound value
	 * 
	 * @param sound
	 *            boolean - whether to turn on (true) or off (false) the sound
	 *            of the game
	 */
	public void setSound(boolean sound) {
		this.sound = sound;
	}

	/**
	 * Holds the flag regarding server solvement
	 * 
	 * @return true if it has all needed parameters - valid IP,Port,Method and
	 *         Depth, else returns false
	 */
	public boolean serverEnabled() {
		return serverEnabled;
	}

	/**
	 * Holds the properties for the server as configured by the user
	 * 
	 * @return ServerProperties to be used by the presenter
	 */
	public ServerProperties getServerProperties() {
		return new ServerProperties(SolverServerAddress, portNumber,
				methodSelection, depth);
	}

	/**
	 * Holds the changes in the option screen
	 * 
	 * @return if nothing changed return false, if the user changed something
	 *         return true
	 */
	public boolean Changed() {
		return changed;
	}

}
