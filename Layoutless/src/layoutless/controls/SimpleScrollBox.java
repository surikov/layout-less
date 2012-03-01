package layoutless.controls;

import tee.binding.properties.*;
import tee.binding.task.*;
import layoutless.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * 
 * @author User
 */
public class SimpleScrollBox extends JScrollPane {
    private Layoutless layoutless;
    public NumericProperty<SimpleScrollBox> innerWidth;
    public NumericProperty<SimpleScrollBox> innerHeight;
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
    public SimpleScrollBox(Window win) {
	window = win;
	window.addWindowListener(windowAdapter);
	this.setOpaque(false);
	this.getViewport().setOpaque(false);
	this.setBorder(null);
	layoutless = new Layoutless();
	innerWidth = new NumericProperty<SimpleScrollBox>(this);
	innerWidth.property.afterChange(new Task() {
	    @Override public void doTask() {
		adjust();
	    }
	});
	innerHeight = new NumericProperty<SimpleScrollBox>(this);
	innerHeight.property.afterChange(new Task() {
	    @Override public void doTask() {
		adjust();
	    }
	});
	innerWidth.property.value(500);
	innerHeight.property.value(500);
	this.getViewport().add(layoutless);
    }
    private void adjust() {
	if (innerWidth == null || innerHeight == null) {
	    return;
	}
	if (innerWidth.property.value() == null || innerHeight.property.value() == null) {
	    return;
	}
	if (innerWidth.property.value() > 1 && innerHeight.property.value() > 1) {
	    Dimension d = new Dimension(innerWidth.property.value().intValue(), innerHeight.property.value().intValue());
	    layoutless.setSize(d);
	    layoutless.setPreferredSize(d);
	}
	this.validate();
    }
    /**
     * 
     * @param it
     * @return
     */
    public SimpleScrollBox item(ComponentBox it) {
	if (it != null) {
	    this.layoutless.item(it);
	}
	return this;
    }
    /**
     * 
     * @param it
     */
    public void drop(JComponent it) {
	this.layoutless.drop(it);
    }
}
