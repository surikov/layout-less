package layoutless.controls;

import java.awt.*;
import java.awt.event.*;
import tee.binding.properties.*;
import tee.binding.task.*;
import javax.swing.*;

/**
 * 
 * @author User
 */
public class SimpleWait extends JProgressBar {
    public ToggleProperty<SimpleWait> horizontal;
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
    public SimpleWait(Window win) {
	super();
	window = win;
	window.addWindowListener(windowAdapter);
	this.setIndeterminate(true);
	horizontal = new ToggleProperty<SimpleWait>(this);
	horizontal.property.value(true).afterChange(new Task() {
	    @Override public void doTask() {
		if (horizontal != null) {
		    if (horizontal.property.value()) {
			setOrientation(SwingConstants.HORIZONTAL);
		    }
		    else {
			setOrientation(SwingConstants.VERTICAL);
		    }
		}
	    }
	});
	if (horizontal.property.value()) {
	    setOrientation(SwingConstants.HORIZONTAL);
	}
	else {
	    setOrientation(SwingConstants.VERTICAL);
	}
    }
}
