package layoutless;

import javax.swing.*;
import java.awt.event.*;
import tee.binding.*;
import java.awt.*;

public class ComponentBox extends JPanel {
    private Numeric width;
    private Numeric height;
    private Numeric x;
    private Numeric y;
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
    public Numeric width() {
	return width;
    }
    public Numeric height() {
	return height;
    }
    public Numeric x() {
	return x;
    }
    public Numeric y() {
	return y;
    }
    public ComponentBox width(Numeric it) {
	width.bind(it);
	return this;
    }
    public ComponentBox height(Numeric it) {
	height.bind(it);
	return this;
    }
    public ComponentBox x(Numeric it) {
	x.bind(it);
	return this;
    }
    public ComponentBox y(Numeric it) {
	y.bind(it);
	return this;
    }
    public ComponentBox width(int it) {
	width.value(it);
	return this;
    }
    public ComponentBox height(int it) {
	height.value(it);
	return this;
    }
    public ComponentBox x(int it) {
	x.value(it);
	return this;
    }
    public ComponentBox y(int it) {
	y.value(it);
	return this;
    }
    public ComponentBox width(double it) {
	width.value(it);
	return this;
    }
    public ComponentBox height(double it) {
	height.value(it);
	return this;
    }
    public ComponentBox x(double it) {
	x.value(it);
	return this;
    }
    public ComponentBox y(double it) {
	y.value(it);
	return this;
    }
    private void adjust() {
	if (width == null || height == null || x == null || y == null) {
	    return;
	}
	if (width.value() > 1 && height.value() > 1) {
	    this.setSize(width.value().intValue(), height.value().intValue());
	}
	this.setLocation(x.value().intValue(), y.value().intValue());
    }
    public ComponentBox component(JComponent it) {
	this.removeAll();
	this.add(it, BorderLayout.CENTER);
	adjust();
	return this;
    }
}