package Screens;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class OptionScreen extends Dialog {
	private String difficulty = "Normal", SelectedGame = "Empty",
			SolverServerAddress = "127.0.0.1";
	private boolean sound = false;
	private int methodSelection = 0, portNumber = 5001, depth = 6;
	private Group GamesGroup, DifficultyGroup, SoundGroup, ServerGroup;
	private Button GameMaze_btn, Game2048_btn, normal, hard, on, off, OK_btn;
	private Text SolverServerAddress_text, SolverServerDepth, SolverServerPort;
	private Combo solverMethod;

	public OptionScreen(Shell parent, int style) {
		super(parent, style);
	}

	public OptionScreen(Shell parent) {
		this(parent, 0);
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
		if(portNumber >=1)
			SolverServerPort.setText(new String(""+portNumber));
	}

	/**
	 * Opens the new window shell
	 * 
	 * @return String that represent the chosen game
	 */
	public String open() {
		final Shell optinsScreen_shell = new Shell(
				Display.getCurrent(),
				(SWT.CLOSE | SWT.TITLE | SWT.BORDER | SWT.APPLICATION_MODAL | SWT.RESIZE)
						& (~SWT.RESIZE));
		optinsScreen_shell.setLayout(new GridLayout(1, true));
		optinsScreen_shell.setText("Options");

		optinsScreen_shell.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				System.out.println(arg0.keyCode);
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				System.out.println(arg0.keyCode);
				
			}
		});
		
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
		serverAddress.setEditable(false);
		serverAddress.setText("IP Address:");
		SolverServerAddress_text = new Text(ServerGroup, SWT.BORDER);
		SolverServerAddress_text.setLayoutData(new GridData(SWT.FILL, SWT.FILL,
				true, true, 1, 1));

		Text serverPort = new Text(ServerGroup, SWT.NONE);
		serverPort.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
				1, 1));
		serverPort.setEditable(false);
		serverPort.setText("Port number:");
		SolverServerPort = new Text(ServerGroup, SWT.BORDER);
		SolverServerPort.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true, 1, 1));

		Text solverMethodtext = new Text(ServerGroup, SWT.NONE);
		solverMethodtext.setEditable(false);
		solverMethodtext.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true, 1, 1));
		solverMethodtext.setText("Choose Method");
		solverMethod = new Combo(ServerGroup, SWT.BORDER);
		solverMethod.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
				1, 1));
		solverMethod.setItems(new String[] { "Minimax", "Alpha-Beta",
				"Expectimax" });
		solverMethod.select(methodSelection);
		
		Text solverDepthtext = new Text(ServerGroup, SWT.NONE);
		solverDepthtext.setEditable(false);
		solverDepthtext.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true, 1, 1));
		solverDepthtext.setText("Choose Depth");
		SolverServerDepth= new Text(ServerGroup, SWT.BORDER);
		SolverServerDepth.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
				1, 1));
		SolverServerDepth.setText(""+depth);

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
					GameMaze_btn.setSelection(false);
					Game2048_btn.setSelection(true);
				}
				setSound(on.getSelection());
				if (normal.getSelection()) {
					setDifficulty("Normal");
				} else {
					setDifficulty("Hard");
				}
				SolverServerAddress = SolverServerAddress_text.getText();
				methodSelection = solverMethod.getSelectionIndex();
				if (verifyPort(SolverServerPort.getText())) {
					optinsScreen_shell.dispose();
				} else {
					displayMessage(optinsScreen_shell,
							"Port is not valid", SWT.ERROR);
				}
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
	 * Holds the solver server IP address
	 * 
	 * @return IP address
	 */
	public String getSolverServerAddress() {
		return SolverServerAddress;
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
	 * 
	 */
	private boolean verifyPort(String string) {
		if(SolverServerPort.getText().isEmpty()){
			portNumber = -1;
			return true;}
		char[] chars = new char[string.length()];
		string.getChars(0, chars.length, chars, 0);
		for (int i = 0; i < chars.length; i++) {
			if (!('0' <= chars[i] && chars[i] <= '9')) {
				return false;
			}
		}
		portNumber = Integer.parseInt(SolverServerPort.getText());
		if (portNumber >= 1 && portNumber <= 65535)
			return true;
		return false;
	}

	/**
	 * Displays an message box based on given string and event type
	 * 
	 * @param shell
	 * @param string
	 * @param event
	 *            ie, event can be SWT.ERROR or SWT.ICON_INFORMATION
	 */
	public void displayMessage(Shell shell, String string, int event) {
		MessageBox mb = new MessageBox(shell, event);
		mb.setText("Error");
		mb.setMessage(string);
		mb.open();
	}
}
