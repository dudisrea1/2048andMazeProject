package model;

/**
 * Class is in charge of the movements of a model
 *
 */
public class ModelMovements {
	Model m;
	
	public ModelMovements(Model model)
	{
		this.m = model;
	}
	/**
	 * Executes the model command as needed
	 * @param i - which command to execute:
	 * 1: move Up
	 * 2: move Down
	 * 3: move Right
	 * 4: move Left
	 * 5: Move Up+Right
	 * 6: Move Up+Left
	 * 7: Move Down+Right
	 * 8: Move Down+Left
	 */
	public void Command(int i) {
		switch(i)
		{
		case 1:
			m.moveUp();
			break;
		case 2:
			m.moveDown();
			break;
		case 3:
			m.moveRight();
			break;
		case 4:
			m.moveLeft();
			break;
		case 5:
			m.moveUp_Right();
			break;
		case 6:
			m.moveUp_Left();
			break;
		case 7:
			m.moveDown_Right();
			break;
		case 8:
			m.moveDown_Left();
			break;
		}
	}
}
