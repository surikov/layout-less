package layoutless.controls;

import java.awt.*;
import java.awt.event.*;
import tee.binding.properties.*;
import tee.binding.task.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * 
 * @author User
 */
public class SimpleNumericSlider extends JSlider {
    public NumericProperty<SimpleNumericSlider> numeric;
    private SimpleNumericSlider me;
    private boolean lock;
    public NumericProperty<SimpleNumericSlider> minimum;
    public NumericProperty<SimpleNumericSlider> maximum;
    private Window window;
    private WindowAdapter windowAdapter = new WindowAdapter() {
	public void windowClosed(WindowEvent e) {
	    window.removeWindowListener(this);
	    clear();
	}
    };
    private void clear() {
    }
    /**
     * 
     * @param win
     */
    public SimpleNumericSlider(Window win) {
	super();
	window = win;
	window.addWindowListener(windowAdapter);
	me = this;
	this.setPaintLabels(true);
	this.setPaintTicks(true);
	this.setPaintTrack(true);
	this.setOpaque(false);
	minimum = new NumericProperty<SimpleNumericSlider>(this);
	minimum.property.value(-100.0).afterChange(new Task() {
	    @Override public void doTask() {
		adjust();
	    }
	});
	maximum = new NumericProperty<SimpleNumericSlider>(this);
	maximum.property.value(100.0).afterChange(new Task() {
	    @Override public void doTask() {
		adjust();
	    }
	});
	lock = false;
	numeric = new NumericProperty<SimpleNumericSlider>(this);
	numeric.property.value(0).afterChange(new Task() {
	    @Override public void doTask() {
		if (!lock) {
		    if (numeric != null) {
			lock = true;
			double d = numeric.property.value();
			if (d < minimum.property.value()) {
			    d = minimum.property.value();
			    numeric.property.value(d);
			}
			else {
			    if (d > maximum.property.value()) {
				d = maximum.property.value();
				numeric.property.value(d);
			    }
			}
			me.setValue((int) d);
			lock = false;
		    }
		}
	    }
	});
	addChangeListener(new ChangeListener() {
	    @Override
	    public void stateChanged(ChangeEvent e) {
		numeric.property.value(me.getValue());
	    }
	});
	adjust();
    }
    private void adjust() {
	if (minimum == null || maximum == null) {
	    return;
	}
	if (minimum.property.value() + 1.0 <= maximum.property.value()) {
	    this.setMinimum(minimum.property.value().intValue());
	    this.setMaximum(maximum.property.value().intValue());
	    int sz = Math.abs(this.getMaximum() - this.getMinimum());
	    this.setMajorTickSpacing(sz);
	    this.setMinorTickSpacing(sz / 5);
	}
    }
}
