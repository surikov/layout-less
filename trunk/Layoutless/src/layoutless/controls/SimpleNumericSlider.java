package layoutless.controls;

import tee.binding.*;
import layoutless.*;
import javax.swing.*;
import javax.swing.event.*;
import java.text.*;
import java.math.*;

public class SimpleNumericSlider extends JSlider {

    private Numeric numeric;
    private SimpleNumericSlider me;
    private boolean lock;
    private Numeric minimum;
    private Numeric maximum;

    public SimpleNumericSlider() {
	super();
	me = this;
	this.setPaintLabels(true);
	this.setPaintTicks(true);
	this.setPaintTrack(true);
	this.setOpaque(false);
	minimum = new Numeric().value(-100.0).afterChange(new Task() {

	    @Override public void doTask() {
		adjust();
	    }
	});
	maximum = new Numeric().value(100.0).afterChange(new Task() {

	    @Override public void doTask() {
		adjust();
	    }
	});
	lock=false;
	numeric = new Numeric().value(0).afterChange(new Task() {

	    @Override public void doTask() {
		if (!lock) {
		    if (numeric != null) {
			//System.out.println(numeric.value());
			lock = true;
			double d = numeric.value();
			if (d < minimum.value()) {
			    d = minimum.value();
			    numeric.value(d);
			} else {
			    if (d > maximum.value()) {
				d = maximum.value();
				numeric.value(d);
			    }
			}
			me.setValue((int)d);
			lock = false;
		    }
		}
	    }
	});
	addChangeListener(new ChangeListener() {

	    @Override
	    public void stateChanged(ChangeEvent e) {
		numeric.value(me.getValue());
	    }
	});
	adjust();
    }

    private void adjust() {
	if (minimum == null || maximum == null) {
	    return;
	}
	if (minimum.value() + 1 < maximum.value()) {
	    this.setMinimum(minimum.value().intValue());
	    this.setMaximum(maximum.value().intValue());
	    int sz = Math.abs(this.getMaximum() - this.getMinimum());
	    this.setMajorTickSpacing(sz);
	    this.setMinorTickSpacing(sz / 5);
	}
    }

    public SimpleNumericSlider numeric(double it) {
	numeric.value(it);
	return this;
    }

    public SimpleNumericSlider numeric(int it) {
	numeric.value(it);
	return this;
    }

    public SimpleNumericSlider numeric(Numeric it) {
	numeric.bind(it);
	return this;
    }

    public Numeric numeric() {
	return numeric;
    }

    public SimpleNumericSlider minimum(double it) {
	minimum.value(it);
	return this;
    }

    public SimpleNumericSlider minimum(int it) {
	minimum.value(it);
	return this;
    }

    public SimpleNumericSlider minimum(Numeric it) {
	minimum.bind(it);
	return this;
    }

    public Numeric minimum() {
	return minimum;
    }

    public SimpleNumericSlider maximum(double it) {
	maximum.value(it);
	return this;
    }

    public SimpleNumericSlider maximum(int it) {
	maximum.value(it);
	return this;
    }

    public SimpleNumericSlider maximum(Numeric it) {
	maximum.bind(it);
	return this;
    }

    public Numeric maximum() {
	return maximum;
    }
}
