package layoutless.controls;

import java.awt.*;
import java.awt.event.*;
import tee.binding.*;
//import tee.binding.view.*;
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
public class SimpleNumericSlider extends JSlider {

    private Numeric numeric;
    private SimpleNumericSlider me;
    private boolean lock;
    private Numeric minimum;
    private Numeric maximum;
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
    public SimpleNumericSlider(Window win) {
	super();
	window=win;
	window.addWindowListener(windowAdapter);
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
	if (minimum.value() + 1.0 <= maximum.value()) {
	    this.setMinimum(minimum.value().intValue());
	    this.setMaximum(maximum.value().intValue());
	    int sz = Math.abs(this.getMaximum() - this.getMinimum());
	    this.setMajorTickSpacing(sz);
	    this.setMinorTickSpacing(sz / 5);
	}
    }

    /**
     * 
     * @param it
     * @return
     */
    public SimpleNumericSlider numeric(double it) {
	numeric.value(it);
	return this;
    }

    /**
     * 
     * @param it
     * @return
     */
    public SimpleNumericSlider numeric(int it) {
	numeric.value(it);
	return this;
    }

    /**
     * 
     * @param it
     * @return
     */
    public SimpleNumericSlider numeric(Numeric it) {
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
    public SimpleNumericSlider minimum(double it) {
	minimum.value(it);
	return this;
    }

    /**
     * 
     * @param it
     * @return
     */
    public SimpleNumericSlider minimum(int it) {
	minimum.value(it);
	return this;
    }

    /**
     * 
     * @param it
     * @return
     */
    public SimpleNumericSlider minimum(Numeric it) {
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
    public SimpleNumericSlider maximum(double it) {
	maximum.value(it);
	return this;
    }

    /**
     * 
     * @param it
     * @return
     */
    public SimpleNumericSlider maximum(int it) {
	maximum.value(it);
	return this;
    }

    /**
     * 
     * @param it
     * @return
     */
    public SimpleNumericSlider maximum(Numeric it) {
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
}
