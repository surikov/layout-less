package layoutless.controls;

import java.awt.*;
import java.awt.event.*;
import tee.binding.*;
import tee.binding.view.*;
import tee.binding.it.*;
import tee.binding.task.*;
import layoutless.*;
import javax.swing.*;
import javax.swing.event.*;
import java.text.*;
import java.math.*;
/**
 * 
 * @author User
 */
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
private Window window;
    private WindowAdapter windowAdapter=new WindowAdapter(){
	    public void windowClosed(WindowEvent e){
		window.removeWindowListener(this);
		//System.out.println(e+" / "+window.hashCode());
		clear();
		}
	    };
    private void clear(){

    }
    /**
     * 
     * @param win
     */
    public SimpleNumberField(Window win) {
	super();
	window=win;
	window.addWindowListener(windowAdapter);
	me = this;
	lock = false;
	model = new SpinnerNumberModel();
	minimum = new Numeric().value(Double.MIN_VALUE).afterChange(new Task() {

	    @Override public void doTask() {
		adjust();
	    }
	});
	maximum = new Numeric().value(Double.MAX_VALUE).afterChange(new Task() {

	    @Override public void doTask() {
		adjust();
	    }
	});
	decimalPlaces = new Numeric().value(1.0).afterChange(new Task() {

	    @Override public void doTask() {
		adjust();
	    }
	});
	numeric = new Numeric().value(0).afterChange(new Task() {

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
    /**
     * 
     * @param it
     * @return
     */
    public SimpleNumberField numeric(double it) {
	numeric.value(it);
	return this;
    }

    /**
     * 
     * @param it
     * @return
     */
    public SimpleNumberField numeric(int it) {
	numeric.value(it);
	return this;
    }

    /**
     * 
     * @param it
     * @return
     */
    public SimpleNumberField numeric(Numeric it) {
	numeric.bind(it);
	return this;
    }

    /**
     * 
     * @return
     */
    public Numeric numeric() {
	return numeric;
    }

    /**
     * 
     * @param it
     * @return
     */
    public SimpleNumberField minimum(double it) {
	minimum.value(it);
	return this;
    }

    /**
     * 
     * @param it
     * @return
     */
    public SimpleNumberField minimum(int it) {
	minimum.value(it);
	return this;
    }

    /**
     * 
     * @param it
     * @return
     */
    public SimpleNumberField minimum(Numeric it) {
	minimum.bind(it);
	return this;
    }

    /**
     * 
     * @return
     */
    public Numeric minimum() {
	return minimum;
    }

    /**
     * 
     * @param it
     * @return
     */
    public SimpleNumberField maximum(double it) {
	maximum.value(it);
	return this;
    }

    /**
     * 
     * @param it
     * @return
     */
    public SimpleNumberField maximum(int it) {
	maximum.value(it);
	return this;
    }

    /**
     * 
     * @param it
     * @return
     */
    public SimpleNumberField maximum(Numeric it) {
	maximum.bind(it);
	return this;
    }

    /**
     * 
     * @return
     */
    public Numeric maximum() {
	return maximum;
    }

    /**
     * 
     * @param it
     * @return
     */
    public SimpleNumberField decimalPlaces(double it) {
	decimalPlaces.value(it);
	return this;
    }

    /**
     * 
     * @param it
     * @return
     */
    public SimpleNumberField decimalPlaces(int it) {
	decimalPlaces.value(it);
	return this;
    }

    /**
     * 
     * @param it
     * @return
     */
    public SimpleNumberField decimalPlaces(Numeric it) {
	decimalPlaces.bind(it);
	return this;
    }

    /**
     * 
     * @return
     */
    public Numeric decimalPlaces() {
	return decimalPlaces;
    }
}
