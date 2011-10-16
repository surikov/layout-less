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
    private SpinnerNumberModel model;
    private Numeric minimum;
    private Numeric maximum;
    private Numeric step;
    public SimpleNumericSlider() {
	super();
	me=this;
	this.setPaintLabels(true);
	this.setPaintTicks(true);
	this.setPaintTrack(true);
	this.setMajorTickSpacing(64);
	this.setMinorTickSpacing(16);
	addChangeListener(new ChangeListener (){
	    @Override
	    public void stateChanged(ChangeEvent e) {
		//System.out.println(me);
	    }
	});
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
    
    
    public SimpleNumericSlider step(double it) {
	step.value(it);
	return this;
    }
    public SimpleNumericSlider step(int it) {
	step.value(it);
	return this;
    }
    public SimpleNumericSlider step(Numeric it) {
	step.bind(it);
	return this;
    }
    public Numeric step() {
	return step;
    }
    
}
