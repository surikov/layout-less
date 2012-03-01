package layoutless.controls;

import java.awt.*;
import java.awt.event.*;
import tee.binding.properties.*;
import tee.binding.task.*;
import javax.swing.*;
import javax.swing.event.*;
import java.text.*;
import java.math.*;

/**
 * 
 * @author User
 */
public class SimpleNumberField extends JSpinner {
    public NumericProperty<SimpleNumberField> numeric;
    private SimpleNumberField me;
    private boolean lock;
    private SpinnerNumberModel model;
    public NumericProperty<SimpleNumberField> minimum;
    public NumericProperty<SimpleNumberField> maximum;
    private JSpinner.NumberEditor editor;
    public NumericProperty<SimpleNumberField> decimalPlaces;
    private DecimalFormat format;
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
    public SimpleNumberField(Window win) {
	super();
	window = win;
	window.addWindowListener(windowAdapter);
	me = this;
	lock = false;
	model = new SpinnerNumberModel();
	this.setModel(model);
	model.setValue(0.0);
	editor = new JSpinner.NumberEditor(this);
	this.setEditor(editor);
	format = editor.getFormat();
	minimum = new NumericProperty<SimpleNumberField>(this);
	minimum.property.value(Double.MIN_VALUE).afterChange(new Task() {
	    @Override public void doTask() {
		adjust();
	    }
	});
	maximum = new NumericProperty<SimpleNumberField>(this);
	maximum.property.value(Double.MAX_VALUE).afterChange(new Task() {
	    @Override public void doTask() {
		adjust();
	    }
	});
	decimalPlaces = new NumericProperty<SimpleNumberField>(this);
	decimalPlaces.property.value(1.0).afterChange(new Task() {
	    @Override public void doTask() {
		adjust();
	    }
	});
	numeric = new NumericProperty<SimpleNumberField>(this);
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
			model.setValue(d);
			lock = false;
		    }
		}
	    }
	});
	this.addChangeListener(new ChangeListener() {
	    @Override public void stateChanged(ChangeEvent e) {
		double v = 0;
		if (!lock) {
		    if (numeric != null) {
			lock = true;
			try {

			    if (model.getValue() instanceof Double) {
				v = (Double) model.getValue();
			    }
			    else {
				if (model.getValue() instanceof Integer) {
				    v = (Integer) model.getValue();
				}
			    }
			}
			catch (Throwable t) {
			    t.printStackTrace();
			}
			v = new BigDecimal(String.valueOf(v)).setScale(decimalPlaces.property.value().intValue(), BigDecimal.ROUND_HALF_UP).doubleValue();
			me.numeric.property.value(v);
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
	int dp = decimalPlaces.property.value().intValue();
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
	double mi = this.minimum.property.value();
	double ma = this.maximum.property.value();
	if (mi > ma) {
	    mi = ma;
	}
	format.setMinimumFractionDigits(dp);
	format.setMaximumFractionDigits(dp);
	model.setMaximum(ma);
	model.setMinimum(mi);
	model.setStepSize(ss);
	if (this.numeric != null) {
	    if (this.numeric.property != null) {
		if (this.numeric.property.value() < this.minimum.property.value()) {
		    this.numeric.property.value(this.minimum.property.value());
		}
		if (this.numeric.property.value() > this.maximum.property.value()) {
		    this.numeric.property.value(this.maximum.property.value());
		}
	    }
	}
    }
}
