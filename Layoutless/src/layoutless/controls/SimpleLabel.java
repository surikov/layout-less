package layoutless.controls;

import java.awt.*;
import java.awt.event.*;
import tee.binding.*;
//import tee.binding.view.*;
import tee.binding.it.*;
import tee.binding.properties.*;
import tee.binding.task.*;
import layoutless.*;
import javax.swing.*;

/**
 * 
 * @author User
 */
public class SimpleLabel extends JLabel {
    final public NoteProperty<SimpleLabel> text= new NoteProperty<SimpleLabel>(this);
    public ToggleProperty<SimpleLabel> normalAlignment;
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
    public SimpleLabel(Window win) {
	super();
	window = win;
	window.addWindowListener(windowAdapter);
	//text = new NoteProperty<SimpleLabel>(this);
	text.property.value("").afterChange(new Task() {
	    @Override public void doTask() {
		if (text != null) {
		    setText(text.property.value());
		}
	    }
	});
	this.setVerticalAlignment(this.TOP);
	normalAlignment = new ToggleProperty<SimpleLabel>(this);
	normalAlignment.property.value(true).afterChange(new Task() {
	    @Override public void doTask() {
		if (normalAlignment != null) {
		    if (!normalAlignment.property.value()) {
			setHorizontalAlignment(SwingConstants.RIGHT);
		    }
		    else {
			setHorizontalAlignment(SwingConstants.LEFT);
		    }
		}
	    }
	});
	if (!normalAlignment.property.value()) {
	    setHorizontalAlignment(SwingConstants.RIGHT);
	}
	else {
	    setHorizontalAlignment(SwingConstants.LEFT);
	}
    }
}
