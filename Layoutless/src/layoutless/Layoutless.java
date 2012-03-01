package layoutless;

import javax.swing.*;
import java.awt.event.*;
import tee.binding.properties.*;
import java.awt.*;

/**
 * 
 * @author User
 */
public class Layoutless extends JLayeredPane implements ComponentListener {
    public NumericProperty<Layoutless> width;
    public NumericProperty<Layoutless> height;
    /**
     * 
     */
    public Layoutless() {
	super();
	this.add(new JInternalFrame());
	width = new NumericProperty<Layoutless>(this);
	width.property.value(100);
	height = new NumericProperty<Layoutless>(this);
	height.property.value(100);
	this.setOpaque(false);
	this.addComponentListener(this);
    }
    /**
     * 
     * @return
     */
    public static String getVersion() {
	return "1.5.2";
    }
    @Override public void componentResized(ComponentEvent e) {
	width.property.value(getSize().width);
	height.property.value(getSize().height);
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
    /**
     * 
     * @param it
     * @return
     */
    public Layoutless item(ComponentBox it) {
	this.add(it);
	return this;
    }
    /**
     * 
     * @param it
     */
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
