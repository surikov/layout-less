package layoutless;

import javax.swing.*;
import java.awt.event.*;
import tee.binding.it.*;
import tee.binding.task.*;
import java.awt.*;

/**
 * 
 * @author User
 */
public class ComponentBox extends JPanel {
    private Numeric width;
    private Numeric height;
    private Numeric x;
    private Numeric y;
    /**
     * 
     */
    public ComponentBox() {
	width = new Numeric().value(100).afterChange(new Task() {
	    @Override public void doTask() {
		adjust();
	    }
	});
	height = new Numeric().value(100).afterChange(new Task() {
	    @Override public void doTask() {
		adjust();
	    }
	});
	x = new Numeric().value(0).afterChange(new Task() {
	    @Override public void doTask() {
		adjust();
	    }
	});
	y = new Numeric().value(0).afterChange(new Task() {
	    @Override public void doTask() {
		adjust();
	    }
	});
	setOpaque(false);
	setLayout(new BorderLayout());
    }
    /**
     * 
     * @return
     */
    public Numeric width() {
	return width;
    }
    /**
     * 
     * @return
     */
    public Numeric height() {
	return height;
    }
    /**
     * 
     * @return
     */
    public Numeric x() {
	return x;
    }
    /**
     * 
     * @return
     */
    public Numeric y() {
	return y;
    }
    /**
     * 
     * @param it
     * @return
     */
    public ComponentBox width(Numeric it) {
	width.bind(it);
	return this;
    }
    /**
     * 
     * @param it
     * @return
     */
    public ComponentBox height(Numeric it) {
	height.bind(it);
	return this;
    }
    /**
     * 
     * @param it
     * @return
     */
    public ComponentBox x(Numeric it) {
	x.bind(it);
	return this;
    }
    /**
     * 
     * @param it
     * @return
     */
    public ComponentBox y(Numeric it) {
	y.bind(it);
	return this;
    }
    /**
     * 
     * @param it
     * @return
     */
    public ComponentBox width(int it) {
	width.value(it);
	return this;
    }
    /**
     * 
     * @param it
     * @return
     */
    public ComponentBox height(int it) {
	height.value(it);
	return this;
    }
    /**
     * 
     * @param it
     * @return
     */
    public ComponentBox x(int it) {
	x.value(it);
	return this;
    }
    /**
     * 
     * @param it
     * @return
     */
    public ComponentBox y(int it) {
	y.value(it);
	return this;
    }
    /**
     * 
     * @param it
     * @return
     */
    public ComponentBox width(double it) {
	width.value(it);
	return this;
    }
    /**
     * 
     * @param it
     * @return
     */
    public ComponentBox height(double it) {
	height.value(it);
	return this;
    }
    /**
     * 
     * @param it
     * @return
     */
    public ComponentBox x(double it) {
	x.value(it);
	return this;
    }
    /**
     * 
     * @param it
     * @return
     */
    public ComponentBox y(double it) {
	y.value(it);
	return this;
    }
    private void adjust() {
	if (width == null || height == null || x == null || y == null) {
	    return;
	}
	if (width.value() > 1 && height.value() > 1) {
	    Dimension d=new Dimension(width.value().intValue(), height.value().intValue());
	    this.setSize(d);
	    this.setPreferredSize(d);
	}
	this.setLocation(x.value().intValue(), y.value().intValue());
	//this.invalidate();
	this.validate();
	//this.doLayout();
	//this.repaint();
    }
    /**
     * 
     * @param it
     * @return
     */
    public ComponentBox component(JComponent it) {
	this.removeAll();
	this.add(it, BorderLayout.CENTER);
	adjust();
	return this;
    }
    /**
     * 
     */
    public void unbind(){
	this.x.unbind();
	this.y.unbind();
	this.width.unbind();
	this.height.unbind();
    }
}