package Game2048;

import java.util.HashMap;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

/**
 * This class controls the colors of the tiles
 * 
 */
public class ColorsRGB {
	private HashMap<Integer, Color> intToColor = new HashMap<Integer, Color>();
	Display dsp;

	/**
	 * Constructor Creates a new ColorsRGB object based on a given Display, Sets
	 * the HashMashof intToColor according to setIntToRGB function
	 */
	public ColorsRGB(Display dsp) {
		intToColor = new HashMap<Integer, Color>();
		setIntToColor();
		this.dsp = dsp;
	}

	/**
	 * Initialize the intToColor hashmap and set the default values
	 */
	private void setIntToColor() {
		intToColor.put(0, new Color(dsp, 100, 100, 100));
		for (int i = 1; i < 8; i++) {
			intToColor.put(new Double(Math.pow(2, i)).intValue(), new Color(
					dsp, 255, 255 - 20 * i, 50));
		}
		for (int j = 1; j < 6; j++) {
			intToColor.put(new Double(Math.pow(2, j + 7)).intValue(),
					new Color(dsp, 255, 70, 50 * j));
		}
		for (int j = 1; j < 6; j++) {
			intToColor.put(new Double(Math.pow(2, j + 12)).intValue(),
					new Color(dsp, 255 - 35 * j, 70, 255));
		}
	}

	/**
	 * The getter returns a Color based on a given int number
	 * @param i - could be any value between 0 to 16
	 * @return
	 */
	public Color getColor(int i) {
		return intToColor.get(i);
	}
}
