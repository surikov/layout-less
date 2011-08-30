package layoutless;

import javax.swing.*;
import java.awt.event.*;
import tee.binding.*;
import java.awt.*;

public class Layoutless extends JLayeredPane  implements ComponentListener {
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
}
