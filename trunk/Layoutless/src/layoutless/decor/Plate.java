package layoutless.decor;

import java.awt.event.*;
import tee.binding.it.*;
import tee.binding.task.*;
import layoutless.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.*;

/**
 * 
 * @author User
 */
public class Plate extends JPanel {

    private It<Color> color;

    /**
     * 
     */
    public Plate() {
	super();
	this.setOpaque(true);
	color = new It<Color>().afterChange(new Task() {

	    @Override public void doTask() {
		if (color != null) {
		    if (color.value() != null) {
			setBackground(color.value());
		    }
		}
	    }
	});
	color(Color.orange);
    }

    /**
     * 
     * @param it
     * @return
     */
    public Plate color(Color it) {
	color.value(it);
	return this;
    }

    /**
     * 
     * @param it
     * @return
     */
    public Plate color(It<Color> it) {
	color.bind(it);
	return this;
    }

    /**
     * 
     * @return
     */
    public It<Color> color() {
	return color;
    }
}
