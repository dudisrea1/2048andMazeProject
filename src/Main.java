import model.GeneralModel;
import presenter.Presenter;
import view.GeneralView;

/**
 * 
 * The program base concept was to create a GUI game of Maze and 2048 game. The
 * project enhanced so now you could actully configure a server to give you
 * hints in the 2048 game
 * 
 * In general the Main will create a new UI object and model object, since the project works in MVC,
 * we will create a Presenter that will observe both of these objects for needed actions.
 * 
 * Then starts the UI in a different thread.
 * Have Fun !
 * 
 * @author Dudi Ben Shushan - 200819993
 * @author Sagi Yosefia - 303011175
 * 
 * 
 */
public class Main {
	public static void main(String[] args) {
		GeneralView ui = new GeneralView();
		GeneralModel m = new GeneralModel();
		Presenter p = new Presenter(m, ui);
		m.addObserver(p);
		ui.addObserver(p);
		Thread t = new Thread(ui);
		t.run();
	}
}
