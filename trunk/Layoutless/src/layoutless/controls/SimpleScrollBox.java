package layoutless.controls;

import tee.binding.*;
import tee.binding.view.*;
import tee.binding.it.*;
import tee.binding.task.*;
import layoutless.*;
import java.awt.*;
import javax.swing.*;

public class SimpleScrollBox extends JScrollPane {

    private Layoutless layoutless;
    private Numeric width;
    private Numeric height;

    public SimpleScrollBox() {
	this.setOpaque(false);
	this.getViewport().setOpaque(false);
	this.setBorder(null);
	layoutless = new Layoutless();
	width = new Numeric().afterChange(new Task() {

	    @Override public void doTask() {
		adjust();
	    }
	});
	height = new Numeric().afterChange(new Task() {

	    @Override public void doTask() {
		adjust();
	    }
	});
	width.value(500);
	height.value(500);
	//layoutless.setSize(500, 500);
	//layoutless.setPreferredSize(new Dimension(500, 500));
	this.getViewport().add(layoutless);
    }

    private void adjust() {
	//System.out.println(width + "/" + height);
	if (width == null || height == null) {
	    return;
	}
	if (width.value() == null || height.value() == null) {
	    return;
	}
	//System.out.println("+" + width.value() + "/" + height.value());
	if (width.value() > 1 && height.value() > 1) {
	    Dimension d = new Dimension(width.value().intValue(), height.value().intValue());
	    layoutless.setSize(d);
	    layoutless.setPreferredSize(d);
	}
	//this.setLocation(x.value().intValue(), y.value().intValue());
	//this.invalidate();
	this.validate();
	//this.doLayout();
	//this.repaint();
    }

    public SimpleScrollBox item(ComponentBox it) {
	this.layoutless.item(it);
	return this;
    }

    public void drop(JComponent it) {
	this.layoutless.drop(it);
    }

    public SimpleScrollBox width(Numeric it) {
	width.bind(it);
	return this;
    }

    public SimpleScrollBox height(Numeric it) {
	height.bind(it);
	return this;
    }

    public SimpleScrollBox width(int it) {
	width.value(it);
	return this;
    }

    public SimpleScrollBox height(int it) {
	height.value(it);
	return this;
    }

    public SimpleScrollBox width(double it) {
	width.value(it);
	return this;
    }

    public SimpleScrollBox height(double it) {
	height.value(it);
	return this;
    }
}