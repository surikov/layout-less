package layoutless;

import javax.swing.*;
import java.awt.event.*;
import tee.binding.it.*;
import tee.binding.task.*;
import java.awt.*;

public class Layoutless extends JLayeredPane implements ComponentListener {
    private Numeric width;
    private Numeric height;
    public Layoutless() {
	super();
	this.add(new JInternalFrame());
	width = new Numeric().value(100);
	height = new Numeric().value(100);
	this.setOpaque(false);
	this.addComponentListener(this);
    }
    public static String getVersion() {
	return "1.5.2";
    }
    @Override public void componentResized(ComponentEvent e) {
	width.value(getSize().width);
	height.value(getSize().height);
    }
    @Override public void componentMoved(ComponentEvent e) {
	//
    }
    @Override public void componentShown(ComponentEvent e) {
	//
    }
    @Override public void componentHidden(ComponentEvent e) {
	//
    }
    public Numeric width() {
	return width;
    }
    public Numeric height() {
	return height;
    }
    public Layoutless item(ComponentBox it) {
	this.add(it);
	return this;
    }
    public void drop(JComponent it) {
	for (int i = 0; i < this.getComponentCount(); i++) {
	    Component c = this.getComponents()[i];
	    if (c instanceof ComponentBox) {
		ComponentBox cb = (ComponentBox) c;
		Component t = cb.getComponent(0);
		if (t == it) {
		    cb.remove(t);
		    cb.unbind();
		    this.remove(cb);
		    this.repaint();
		}
	    }
	}
    }
}
