package layoutless;

import javax.swing.*;
import java.awt.event.*;
import tee.binding.*;
import java.awt.*;

public class Layoutless extends JPanel implements ComponentListener {
    private Numeric width;
    private Numeric height;
    public Layoutless() {
	width = new Numeric().value(100);
	height = new Numeric().value(100);
	this.setOpaque(false);
	this.setLayout(null);
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
