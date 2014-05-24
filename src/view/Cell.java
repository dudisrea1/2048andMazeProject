package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import Game2048.ColorsRGB;

public class Cell extends Canvas {
	ColorsRGB intToColor;
	private int value;

	public Cell(Composite parent, int style) {
		super(parent, style);
		Font f = getFont();
		Double calcfontsize = 2 * Math.log(value + 1);
		Font newfont = new Font(getDisplay(), f.getFontData()[0].getName(),
				16 - calcfontsize.intValue(), SWT.BOLD);
		setFont(newfont);
		/*intToRBG = new ColorsRGB();*/
		intToColor = new ColorsRGB(getDisplay());
		addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent arg0) {
				FontMetrics fm = arg0.gc.getFontMetrics();
				int width = fm.getAverageCharWidth();
				int mx = getSize().x / 2 - ("" + value).length() * width / 2;
				int my = getSize().y / 2 - fm.getHeight() / 2 - fm.getDescent();
				if (value > 0)
					arg0.gc.drawString("" + value, mx, my);
			}
		});
	}
	
	public void setValue(int i) {
		value = i;
		changeColor();
		redraw();
	}

	private void changeColor() {
		setBackground(intToColor.getColor(value));
	}

}