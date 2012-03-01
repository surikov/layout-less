package layoutless.decor;

import java.awt.event.*;
import tee.binding.it.*;
import tee.binding.task.*;
import layoutless.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.util.*;

public class Whiteboard extends JPanel {
    private Window window;
    //private Numeric width;
    //private Numeric height;
    private Vector<Figure> figures = new Vector<Figure>();
    private WindowAdapter windowAdapter = new WindowAdapter() {
	public void windowClosed(WindowEvent e) {
	    window.removeWindowListener(this);
	    //System.out.println(e+" / "+window.hashCode());
	    clear();
	}
    };
    private void clear() {
    }
    public Whiteboard(Window win) {
	super();
	window = win;
	/*width = new Numeric().value(100).afterChange(new Task() {
	    @Override
	    public void doTask() {
		resize();
	    }
	});
	height = new Numeric().value(100).afterChange(new Task() {
	    @Override
	    public void doTask() {
		resize();
	    }
	});*/
	this.setOpaque(false);
	//resize();
    }
    public Whiteboard figure(Figure f) {
	figures.add(f);
	return this;
    }
    /*private void resize() {
	int w = 100;
	int h = 100;
	if (width != null) {
	    if (width.value() != null) {
		w = width.value().intValue();
	    }
	}
	if (height != null) {
	    if (height.value() != null) {
		w = height.value().intValue();
	    }
	}
	this.setPreferredSize(new Dimension(w, h));
	this.setSize(w, h);
    }*/
    @Override
    protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	Graphics2D g2 = (Graphics2D) g;
	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	for (int i = 0; i < figures.size(); i++) {
	    figures.get(figures.size() - i - 1).paint(g2);
	}
    }
    /*public Whiteboard width(int nn) {
	width.value(nn);
	return this;
    }
    public Whiteboard width(double nn) {
	width.value(nn);
	return this;
    }
    public Whiteboard width(Numeric nn) {
	width.bind(nn);
	return this;
    }
    public Numeric width() {
	return width;
    }
    public Whiteboard height(int nn) {
	height.value(nn);
	return this;
    }
    public Whiteboard height(double nn) {
	height.value(nn);
	return this;
    }
    public Whiteboard height(Numeric nn) {
	height.bind(nn);
	return this;
    }
    public Numeric height() {
	return height;
    }*/
}
