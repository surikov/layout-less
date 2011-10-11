package layoutless.controls;

import tee.binding.*;
import layoutless.*;
import javax.swing.*;
import javax.swing.event.*;
import java.text.*;
import java.math.*;
public class SimpleNumberField extends JSpinner {

    private Numeric numeric;
    private SimpleNumberField me;
    private boolean lock;
    private SpinnerNumberModel model;
    private Numeric minimum;
    private Numeric maximum;
    private JSpinner.NumberEditor editor;
    private Numeric decimalPlaces;
    private DecimalFormat format;

    public SimpleNumberField() {
	super();
	me = this;
	lock = false;
	model = new SpinnerNumberModel();
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
	decimalPlaces = new Numeric().value(2.0).afterChange(new Task() {

	    @Override public void doTask() {
		adjust();
	    }
	});
	numeric = new Numeric().value(10.023).afterChange(new Task() {

	    @Override public void doTask() {
		if (!lock) {
		    if (numeric != null) {
			lock = true;
			double d = numeric.value();
			if (d < minimum.value()) {
			    d=minimum.value();
			    numeric.value(d);
			} else {
			    if (d > maximum.value()) {
				 d=maximum.value();
				 numeric.value(d);
			    }
			}
			model.setValue(d);
			lock = false;
		    }
		}
	    }
	});
	this.setModel(model);
	model.setValue(0.0);
	editor = new JSpinner.NumberEditor(this);
	this.setEditor(editor);
	format = editor.getFormat();
	this.addChangeListener(new ChangeListener() {

	    @Override public void stateChanged(ChangeEvent e) {
		double v = 0;
		if (!lock) {
		    if (numeric != null) {
			lock = true;
			try {

			    if (model.getValue() instanceof Double) {
				v = (Double) model.getValue();
			    } else {
				if (model.getValue() instanceof Integer) {
				    v = (Integer) model.getValue();
				}
			    }
			} catch (Throwable t) {
			    t.printStackTrace();
			}
			v=new BigDecimal(String.valueOf(v)).setScale(decimalPlaces.value().intValue(), BigDecimal.ROUND_HALF_UP).doubleValue();
			me.numeric.value(v);
			lock = false;
		    }
		}
	    }
	});
	adjust();
    }

    private void adjust() {
	if (minimum == null) {
	    return;
	}
	if (maximum == null) {
	    return;
	}
	if (decimalPlaces == null) {
	    return;
	}
	int dp = decimalPlaces.value().intValue();
	if (dp < 0) {
	    dp = 0;
	}
	if (dp > 10) {
	    dp = 10;
	}
	double ss = 1.0;
	if (dp > 0) {
	    ss = 1.0 / (Math.pow(10, dp));
	}
	double mi = this.minimum.value();
	double ma = this.maximum.value();
	if (mi > ma) {
	    mi = ma;
	}
	format.setMinimumFractionDigits(dp);
	format.setMaximumFractionDigits(dp);
	model.setMaximum(ma);
	model.setMinimum(mi);
	model.setStepSize(ss);
	if (this.numeric.value() < this.minimum.value()) {
	    this.numeric.value(this.minimum.value());
	}
	if (this.numeric.value() > this.maximum.value()) {
	    this.numeric.value(this.maximum.value());
	}
    }
    public SimpleNumberField numeric(double it) {
	numeric.value(it);
	return this;
    }

    public SimpleNumberField numeric(int it) {
	numeric.value(it);
	return this;
    }

    public SimpleNumberField numeric(Numeric it) {
	numeric.bind(it);
	return this;
    }

    public Numeric numeric() {
	return numeric;
    }

    public SimpleNumberField minimum(double it) {
	minimum.value(it);
	return this;
    }

    public SimpleNumberField minimum(int it) {
	minimum.value(it);
	return this;
    }

    public SimpleNumberField minimum(Numeric it) {
	minimum.bind(it);
	return this;
    }

    public Numeric minimum() {
	return minimum;
    }

    public SimpleNumberField maximum(double it) {
	maximum.value(it);
	return this;
    }

    public SimpleNumberField maximum(int it) {
	maximum.value(it);
	return this;
    }

    public SimpleNumberField maximum(Numeric it) {
	maximum.bind(it);
	return this;
    }

    public Numeric maximum() {
	return maximum;
    }

    public SimpleNumberField decimalPlaces(double it) {
	decimalPlaces.value(it);
	return this;
    }

    public SimpleNumberField decimalPlaces(int it) {
	decimalPlaces.value(it);
	return this;
    }

    public SimpleNumberField decimalPlaces(Numeric it) {
	decimalPlaces.bind(it);
	return this;
    }

    public Numeric decimalPlaces() {
	return decimalPlaces;
    }
}
