package Screens;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class OptionScreen extends Dialog {
	private String difficulty = "Normal";
	private String SelectedGame = "Empty";
	private String SolverServerAddress="";
	private boolean sound = false;
	private Group GamesGroup, DifficultyGroup, SoundGroup,ServerGroup;
	private Button GameMaze_btn, Game2048_btn, normal, hard, on, off, OK_btn;
	private Text SolverServerAddress_text, SolverServerDepth;
	
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

		GamesGroup = new Group(optinsScreen_shell, SWT.SHADOW_OUT);
		GamesGroup.setText("Choose Game:");
		GamesGroup.setLayout(new GridLayout(2, true));
		GamesGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
				1, 1));

		
		GameMaze_btn = new Button(GamesGroup, SWT.RADIO);
		GameMaze_btn.setText("Maze Game");
		GameMaze_btn.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				false, 1, 1));

		Game2048_btn = new Button(GamesGroup, SWT.RADIO);
		Game2048_btn.setText("2048 Game");
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
		ServerGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,1, 1));
		
		
		Text serverAddress = new Text(ServerGroup,SWT.BORDER);
		serverAddress.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,1, 1));
		serverAddress.setEditable(false);
		serverAddress.setText("IP Address:");
		SolverServerAddress_text = new Text(ServerGroup, SWT.BORDER);
		SolverServerAddress_text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,1, 1));
		
		Text serverPort = new Text(ServerGroup,SWT.BORDER);
		serverPort.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,1, 1));
		serverPort.setEditable(false);
		serverPort.setText("Port number:");
		SolverServerDepth = new Text(ServerGroup, SWT.BORDER);
		SolverServerDepth.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,1, 1));
		
		
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
				if (on.getSelection()) {
					setSound(true);
					/* on.setSelection(true) */;
				} else {
					setSound(false);
					/* off.setSelection(true) */;
				}
				if (normal.getSelection()) {
					setDifficulty("Normal");
					/* normal.setSelection(true); */} else {
					setDifficulty("Hard");
				}
				if(SolverServerAddress_text.getText()!="")
					SolverServerAddress=SolverServerAddress_text.getText();
				else
					SolverServerAddress="";
				
				
				optinsScreen_shell.dispose();
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
}
