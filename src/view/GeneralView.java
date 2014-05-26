package view;

import helper.ViewUtilities;

import java.util.HashMap;
import java.util.Observable;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import Screens.OptionScreen;

public class GeneralView extends Observable implements View, Runnable {
	private Menu menuBar, fileMenu, helpMenu;
	private MenuItem fileMenuHeader, helpMenuHeader, fileOptionsItem,
			fileExitItem, fileSaveItem, fileLoadItem, helpGetHelpItem;
	private Label label, scorevalue, bestscorevalue;
	private Board board = null;
	private Button undo, saveGame, loadGame, newgame, hint;
	private Display display;
	private Shell shell;
	private Composite contentPanel;
	private StackLayout layout;
	private Game2048Board g2048b;
	private GameMazeBoard gMazeb;
	private Point s, d;
	private int userCommand, mazeDoubleClick = 0;
	boolean newGame = false, keyPressed = false, optionScreenOpened = false;
	private Listener mouseDown2048, mouseUp2048, keyUp2048, keyDown2048,
			keyDownMaze, keyUpMaze;
	private OptionScreen optionsScr;
	private String SelectedGame = "Empty", Difficulty = "Normal";
	private HashMap<Integer, Integer> movementMap = new HashMap<Integer, Integer>();
	private Text numberOfSteps;

	@Override
	public void displayBoard(final int[][] data) {
		if (!shell.isDisposed()) {
			display.asyncExec(new Runnable() {

				@Override
				public void run() {
					try {
						board.drawBoard(data);
						if (optionsScr.isSound()) {
							new Thread(new Runnable() {
								public void run() {
									try {
										Clip clip = AudioSystem.getClip();
										AudioInputStream inputStream = AudioSystem
												.getAudioInputStream(ClassLoader
														.getSystemResourceAsStream("Sounds/nav.wav"));
										clip.open(inputStream);
										clip.start();
									} catch (Exception e) {
										displayError(e.getMessage());
									}
								}
							}).start();
						}
						board.setFocus();
					} catch (Exception e) {
					}
					;
				}
			});
		}
	}

	@Override
	public int getUserCommand() {
		return userCommand;
	}

	private void setUserCommand(int cmd) {
		userCommand = cmd;
		updateObservers();
	}

	private void setUserCommand(int cmd, Object args) {
		userCommand = cmd;
		updateObservers(args);
	}

	@Override
	public void displayScore(final int score) {
		display.syncExec(new Runnable() {

			@Override
			public void run() {
				scorevalue.setText("Your score\t" + score);
			}
		});

	}

	@Override
	public void displayBestScore(final int score) {
		display.syncExec(new Runnable() {

			@Override
			public void run() {
				bestscorevalue.setText("Your best score\t" + score);
			}
		});

	}

	private void mouseMovement() {
		if (!optionScreenOpened) {
			// If we're not staying in the same place == if we moved
			if ((s.x - d.x) != 0 || (s.y - d.y) != 0) {
				// Calculate the degree
				Double degree = Math.toDegrees(Math.asin((s.y - d.y)
						/ Math.pow((Math.pow(s.x - d.x, 2) + Math.pow(
								s.y - d.y, 2)), 0.5)));

				// Calculate the way
				int way = 0;
				// If we moved from right to left way will be negative
				if (s.x > d.x)
					way = -1;
				// Else positive
				else
					way = 1;
				ConvertToMove(degree, way);
			}
		}
	}

	private void ConvertToMove(Double updown, int leftright) {
		if (updown >= 45)
			handleMove(SWT.ARROW_UP);
		else if (updown > -45 && leftright < 0)
			handleMove(SWT.ARROW_LEFT);
		else if (updown > -45 && leftright > 0)
			handleMove(SWT.ARROW_RIGHT);
		else
			handleMove(SWT.ARROW_DOWN);
	}

	private void handleMove(int arg) {
		if (newGame) {
			Integer cmd = movementMap.get(arg);
			if (cmd != null) {
				setUserCommand(cmd);
				undo.setEnabled(true);
			}
			mazeDoubleClick = 0;
			keyPressed = false;
		}
	}

	private void initListeners() {
		mouseDown2048 = new Listener() {
			@Override
			public void handleEvent(Event event) {
				s = Display.getCurrent().getCursorLocation();
			}
		};
		mouseUp2048 = new Listener() {
			@Override
			public void handleEvent(Event event) {
				d = Display.getCurrent().getCursorLocation();
				mouseMovement();
			}
		};

		keyUp2048 = new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				handleMove(arg0.keyCode);
			}
		};

		keyDown2048 = new Listener() {

			@Override
			public void handleEvent(Event arg0) {
				if (arg0.keyCode == (int) SWT.ESC) {
					handleMove(arg0.keyCode);
				}
			}
		};

		keyDownMaze = new Listener() {

			@Override
			public void handleEvent(Event arg0) {
				if (movementMap.containsKey(arg0.keyCode)) {
					if ((mazeDoubleClick == SWT.ARROW_UP && arg0.keyCode == SWT.ARROW_RIGHT)
							|| (mazeDoubleClick == SWT.ARROW_RIGHT && arg0.keyCode == SWT.ARROW_UP))
						mazeDoubleClick += 10;
					mazeDoubleClick += arg0.keyCode;
				}
			}
		};

		keyUpMaze = new Listener() {

			@Override
			public void handleEvent(Event arg0) {
				handleMove(mazeDoubleClick);
				mazeDoubleClick = 0;
			}
		};
	}

	private void sendMazeMoveToModel(int x, int y) {
		s = d;
		d = null;
		try {
			int tmp = movementMap.get(x * 5 + y * 3);
			if (tmp != 0) {
				setUserCommand(tmp);
				undo.setEnabled(true);
			}
		} catch (Exception e) {
		}
	}

	private void initMazeListeners() {
		for (int y = 0; y < 10; y++)
			for (int x = 0; x < 10; x++) {
				final int a = x, b = y;
				gMazeb.getCell(x, y).addListener(SWT.MouseDown, new Listener() {

					@Override
					public void handleEvent(Event arg0) {
						if (gMazeb.isMouse(b, a))
							gMazeb.setMousePressed(!gMazeb.isMousePressed());
					}
				});
				gMazeb.getCell(x, y).addMouseTrackListener(
						new MouseTrackListener() {

							@Override
							public void mouseHover(MouseEvent arg0) {
							}

							@Override
							public void mouseExit(MouseEvent arg0) {
							}

							@Override
							public void mouseEnter(MouseEvent arg0) {
								if (newGame) {
									if (gMazeb.isMouse(b, a)) {
										s = new Point(a, b);
									} else if (!gMazeb.isWall(b, a)
											&& gMazeb.isMousePressed()
											&& s != null) {
										d = new Point(a, b);
										int newx = d.x - s.x;
										int newy = s.y - d.y;
										if (-1 <= newx && newx <= 1
												&& -1 <= newy && newy <= 1)
											sendMazeMoveToModel(newx, newy);
									}
								}
							}
						});

			}
	}

	private void initComponents() {
		display = Display.getDefault();
		shell = new Shell(display);
		shell.setLayout(new GridLayout(4, false));
		shell.setSize(580, 530);
		shell.setMinimumSize(580, 530);
		shell.setText("Java Project");
		movementMap.put(0, 0);
		movementMap.put(SWT.ARROW_UP, 1);
		movementMap.put(SWT.ARROW_DOWN, 2);
		movementMap.put(SWT.ARROW_RIGHT, 3);
		movementMap.put(SWT.ARROW_LEFT, 4);
		movementMap.put((int) SWT.ESC, 55);
		/*
		 * SWT.ARROW_UP + SWT.ARROW_RIGHT equals to SWT.ARROW_DOWN +
		 * SWT.ARROW_LEFT Thus added 10 to that value to represent the change
		 */
		movementMap.put(SWT.ARROW_UP + SWT.ARROW_RIGHT + 10, 5);
		movementMap.put(SWT.ARROW_UP + SWT.ARROW_LEFT, 6);
		movementMap.put(SWT.ARROW_DOWN + SWT.ARROW_RIGHT, 7);
		movementMap.put(SWT.ARROW_DOWN + SWT.ARROW_LEFT, 8);

		movementMap.put(3, 1);
		movementMap.put(-3, 2);
		movementMap.put(5, 3);
		movementMap.put(-5, 4);
		movementMap.put(8, 5);
		movementMap.put(-2, 6);
		movementMap.put(2, 7);
		movementMap.put(-8, 8);

		display.getFocusControl();
		/*
		 * Adds a filter to display, if we clicked the mouse, save current
		 * position at s(source point) When mouse is released, save current
		 * position at d(destination point) and then call to mouseMovement
		 * function;
		 */

		initListeners();
		initButtonsandLabels();
		initMazeListeners();
		menuBar = new Menu(shell, SWT.BAR);
		fileMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
		fileMenuHeader.setText("File");

		fileMenu = new Menu(shell, SWT.DROP_DOWN);
		fileMenuHeader.setMenu(fileMenu);

		fileSaveItem = new MenuItem(fileMenu, SWT.PUSH);
		fileSaveItem.setText("Save");

		fileLoadItem = new MenuItem(fileMenu, SWT.PUSH);
		fileLoadItem.setText("Load");

		fileOptionsItem = new MenuItem(fileMenu, SWT.PUSH);
		fileOptionsItem.setText("Options");

		fileExitItem = new MenuItem(fileMenu, SWT.PUSH);
		fileExitItem.setText("Exit");

		helpMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
		helpMenuHeader.setText("Help");

		helpMenu = new Menu(shell, SWT.DROP_DOWN);
		helpMenuHeader.setMenu(helpMenu);

		helpGetHelpItem = new MenuItem(helpMenu, SWT.PUSH);
		helpGetHelpItem.setText("Get Help");

		fileExitItem.addSelectionListener(new ExitItemListener());
		fileSaveItem.addSelectionListener(new SaveItemListener());
		fileLoadItem.addSelectionListener(new LoadItemListener());
		helpGetHelpItem.addSelectionListener(new HelpItemListener());
		fileOptionsItem.addSelectionListener(new fileOptionsItemListener());

		optionsScr = new OptionScreen(shell);

		shell.setMenuBar(menuBar);
		shell.open();
	}

	private void initButtonsandLabels() {
		GridData scoresGrid = new GridData(SWT.FILL, SWT.FILL, false, false, 2,
				1);
		GridData buttonGrid = new GridData(SWT.FILL, SWT.FILL, false, false, 2,
				1);

		scorevalue = new Label(shell, SWT.FILL);
		scorevalue.setLayoutData(scoresGrid);
		scorevalue.setText("Your score\t0");

		bestscorevalue = new Label(shell, SWT.FILL);
		bestscorevalue.setLayoutData(scoresGrid);
		bestscorevalue.setText("Your best score\t0");

		newgame = new Button(shell, SWT.BUTTON1);
		newgame.setText("New Game");
		newgame.setLayoutData(buttonGrid);
		newgame.addSelectionListener(new NewGameItemListener());

		contentPanel = new Composite(shell, SWT.BORDER);
		contentPanel.setBackground(new Color(display, 100, 100, 100));
		layout = new StackLayout();
		contentPanel.setLayout(layout);
		contentPanel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
				2, 6));

		g2048b = new Game2048Board(contentPanel, SWT.BORDER);
		g2048b.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 5));

		gMazeb = new GameMazeBoard(contentPanel, SWT.BORDER);
		gMazeb.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 5));

		undo = new Button(shell, SWT.BUTTON1);
		undo.setText("Undo Move");
		undo.setLayoutData(buttonGrid);
		undo.addSelectionListener(new UndoItemListener());
		undo.setEnabled(false);

		saveGame = new Button(shell, SWT.BUTTON1);
		saveGame.setText("Save Game");
		saveGame.setLayoutData(buttonGrid);
		saveGame.addSelectionListener(new SaveItemListener());
		saveGame.setEnabled(false);

		loadGame = new Button(shell, SWT.BUTTON1);
		loadGame.setText("Load Game");
		loadGame.setLayoutData(buttonGrid);
		loadGame.addSelectionListener(new LoadItemListener());
		loadGame.setEnabled(true);

		Composite hintsGrp = new Composite(shell, SWT.NONE);
		hintsGrp.setLayout(new GridLayout(2, true));

		numberOfSteps = new Text(hintsGrp, SWT.BORDER);
		numberOfSteps.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false,
				false, 1, 1));
		numberOfSteps.setText("");
		numberOfSteps.setToolTipText("How many steps to calculate?");
		numberOfSteps.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent arg0) {
				if (arg0.keyCode == SWT.CR || arg0.keyCode == SWT.KEYPAD_CR) {
					new HintItemListener().widgetSelected(null);
				}
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
			}
		});

		hint = new Button(hintsGrp, SWT.BUTTON1);
		hint.setText("Hints");
		hint.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		hint.addSelectionListener(new HintItemListener());
		hint.setEnabled(false);

		label = new Label(shell, SWT.CENTER);
		label.setBounds(shell.getClientArea());
		label.setLayoutData(buttonGrid);
	}

	public void setStatusLabel(String string) {
		label.setText(string);
	}

	@Override
	public void run() {
		initComponents();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		setUserCommand(-1);
		display.dispose();
	}

	@Override
	public void EndOfGame() {
		display.syncExec(new Runnable() {

			@Override
			public void run() {
				ViewUtilities.displayMessage(display, shell, "Game Ended",
						"No more moves left.\n" + scorevalue.getText(),
						SWT.ICON_INFORMATION);
			}
		});
	}

	@Override
	public void displayError(final String string) {
		ViewUtilities
				.displayMessage(display, shell, "Error", string, SWT.ERROR);
	}

	private void updateObservers() {
		setChanged();
		notifyObservers();
		if (!shell.isDisposed())
			board.setFocus();
	}

	private void updateObservers(Object args) {
		setChanged();
		notifyObservers(args);
		if (!shell.isDisposed())
			board.setFocus();
	}

	@Override
	public void setUndo(final boolean state) {
		display.asyncExec(new Runnable() {

			@Override
			public void run() {
				undo.setEnabled(state);
			}
		});
	}

	private void GameSelection() {
		optionScreenOpened = true;
		SelectedGame = optionsScr.open();
		optionScreenOpened = false;
		if (!SelectedGame.equals("Empty") && optionsScr.Changed()) {
			switch (SelectedGame) {
			case "Maze":
				if (board instanceof Game2048Board || board == null) {
					shell.setText("Game Maze");
					display.removeFilter(SWT.MouseUp, mouseUp2048);
					display.removeFilter(SWT.MouseDown, mouseDown2048);
					display.removeFilter(SWT.KeyDown, keyDown2048);
					display.removeFilter(SWT.KeyUp, keyUp2048);
					display.addFilter(SWT.KeyDown, keyDownMaze);
					display.addFilter(SWT.KeyUp, keyUpMaze);
					layout.topControl = gMazeb;
					contentPanel.layout();
					board = gMazeb;
					setUserCommand(100);
				}
				break;
			case "2048":
				if (board instanceof GameMazeBoard || board == null) {
					shell.setText("Game 2048");
					display.removeFilter(SWT.KeyDown, keyDownMaze);
					display.removeFilter(SWT.KeyUp, keyUpMaze);
					display.addFilter(SWT.KeyUp, keyUp2048);
					display.addFilter(SWT.KeyDown, keyDown2048);
					display.addFilter(SWT.MouseUp, mouseUp2048);
					display.addFilter(SWT.MouseDown, mouseDown2048);
					layout.topControl = g2048b;
					contentPanel.layout();
					board = g2048b;
					setUserCommand(200);
				}
				break;
			}
			Difficulty = optionsScr.getDifficulty();
			setUserCommand(15, Difficulty);
			if (optionsScr.serverEnabled()) {
				setUserCommand(17, optionsScr.getServerProperties());
				hint.setEnabled(true);
			} else
				hint.setEnabled(false);
		}
	}

	private boolean StartGame() {
		undo.setEnabled(false);
		if (SelectedGame == null || SelectedGame.equals("Empty")) {
			GameSelection();
		}
		if (SelectedGame != null && !SelectedGame.equals("Empty")) {
			newGame = true;
			saveGame.setEnabled(true);
			return true;
		} else
			label.setText("Missing game option");
		return false;
	}

	/*
	 * Listeners classes
	 */

	class NewGameItemListener implements SelectionListener {

		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {
			widgetSelected(arg0);
		}

		@Override
		public void widgetSelected(SelectionEvent arg0) {
			if (StartGame()) {
				label.setText("New " + SelectedGame + " Game");
				setUserCommand(9);
			}
		}

	}

	class UndoItemListener implements SelectionListener {
		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {
			widgetSelected(arg0);
		}

		@Override
		public void widgetSelected(SelectionEvent arg0) {
			label.setText("Undo Move");
			newGame = true;
			setUserCommand(10);
			if (scorevalue.getText().equals("0"))
				undo.setEnabled(false);
			if (SelectedGame.equals("Maze"))
				gMazeb.setMousePressed(false);
		}

	}

	class ExitItemListener implements SelectionListener {
		public void widgetSelected(SelectionEvent event) {
			shell.close();
		}

		public void widgetDefaultSelected(SelectionEvent event) {
			widgetSelected(event);
		}
	}

	class SaveItemListener implements SelectionListener {
		public void widgetSelected(SelectionEvent event) {
			String platform = SWT.getPlatform();
			FileDialog saveFileDialog = new FileDialog(shell, SWT.SAVE);
			String[] filterStrings = new String[] { "XML files",
					"All Files (*)" };
			String[] filterExtension = new String[] { "*.xml", "*.*" };
			String filterPath = "/";
			if (platform.equals("win32") || platform.equals("wpf")) {
				filterPath = "c:\\";
			}
			saveFileDialog.setFilterExtensions(filterExtension);
			saveFileDialog.setFilterNames(filterStrings);
			saveFileDialog.setFilterPath(filterPath);
			saveFileDialog.setFileName("Game.xml");
			saveFileDialog.setFilterExtensions(filterExtension);
			label.setText("Game Saved");
			setUserCommand(11, saveFileDialog.open());
		}

		public void widgetDefaultSelected(SelectionEvent event) {
			widgetSelected(event);
		}
	}

	class LoadItemListener implements SelectionListener {
		public void widgetSelected(SelectionEvent event) {
			if (SelectedGame.equals("Empty"))
				StartGame();
			if (!SelectedGame.equals("Empty")) {
				String platform = SWT.getPlatform();
				FileDialog loadFileDialog = new FileDialog(shell, SWT.OPEN);
				String[] filterStrings = new String[] { "XML files",
						"All Files (*)" };
				String[] filterExtension = new String[] { "*.xml", "*.*" };
				String filterPath = "/";
				if (platform.equals("win32") || platform.equals("wpf")) {
					filterPath = "c:\\";
				}
				loadFileDialog.setFilterExtensions(filterExtension);
				loadFileDialog.setFilterNames(filterStrings);
				loadFileDialog.setFilterPath(filterPath);
				loadFileDialog.setFileName("Game.xml");
				loadFileDialog.setFilterExtensions(filterExtension);
				setUserCommand(12, loadFileDialog.open());
				label.setText("Loaded Game");
			} else
				label.setText("Missing Game Selection");
		}

		public void widgetDefaultSelected(SelectionEvent event) {
		}
	}

	class HintItemListener implements SelectionListener {
		public void widgetSelected(SelectionEvent event) {
			if (hint.isEnabled()) {
				if (!numberOfSteps.getText().isEmpty()) {
					int cmd = ViewUtilities.verifyNumberInRange(
							numberOfSteps.getText(), 0, Integer.MAX_VALUE);
					if (cmd != -1) {
						setUserCommand(55,
								Integer.parseInt(numberOfSteps.getText()));
						setUndo(true);
					} else
						ViewUtilities.displayMessage(display, shell, "Error",
								"Enter valid number as steps", SWT.ERROR);
				} else
					setStatusLabel("Missing number of steps");
			}
		}

		public void widgetDefaultSelected(SelectionEvent event) {
		}
	}

	class HelpItemListener implements SelectionListener {
		public void widgetSelected(SelectionEvent event) {
			Shell newshell = new Shell(
					Display.getCurrent(),
					(SWT.CLOSE | SWT.TITLE | SWT.BORDER | SWT.APPLICATION_MODAL | SWT.RESIZE)
							& (~SWT.RESIZE));
			newshell.setLayout(new FillLayout(SWT.NONE));
			newshell.setText("What is your color?");
			CLabel nlabel = new CLabel(newshell, SWT.BORDER);
			nlabel.setImage(new Image(display, "Resources/Colors.JPG"));
			newshell.pack();
			newshell.setMinimumSize(newshell.getSize());
			newshell.open();

			label.setText("Help");
		}

		public void widgetDefaultSelected(SelectionEvent event) {
		}
	}

	class fileOptionsItemListener implements SelectionListener {
		public void widgetSelected(SelectionEvent event) {
			GameSelection();
		}

		public void widgetDefaultSelected(SelectionEvent event) {
		}
	}

	@Override
	public boolean isShellDisposed() {
		return shell.isDisposed();
	}

	@Override
	public void disableUndo() {
		display.asyncExec(new Runnable() {

			@Override
			public void run() {
				undo.setEnabled(false);
			}
		});

	}
}