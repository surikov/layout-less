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
public class SimpleButton extends JButton {
    public NoteProperty<SimpleButton> text;
    public TaskProperty<SimpleButton> task;
    public ToggleProperty<SimpleButton> normalAlignment;
    public ItProperty<SimpleButton, Icon> icon;
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
    public SimpleButton(Window win) {
	super();
	window = win;
	window.addWindowListener(windowAdapter);
	text = new NoteProperty<SimpleButton>(this);
	text.property.value("").afterChange(new Task() {
	    @Override public void doTask() {
		if (text != null) {
		    setText(text.property.value());
		}
	    }
	});
	normalAlignment = new ToggleProperty<SimpleButton>(this);
	normalAlignment.property.value(true).afterChange(new Task() {
	    @Override public void doTask() {
		if (normalAlignment != null) {
		    if (!normalAlignment.property.value()) {
			setVerticalTextPosition(AbstractButton.BOTTOM);
			setHorizontalTextPosition(AbstractButton.CENTER);
		    }
		    else {
			setHorizontalTextPosition(SwingConstants.TRAILING);
			setVerticalTextPosition(AbstractButton.CENTER);
		    }
		}
	    }
	});
	if (!normalAlignment.property.value()) {
	    setVerticalTextPosition(AbstractButton.BOTTOM);
	    setHorizontalTextPosition(AbstractButton.CENTER);
	}
	else {
	    setHorizontalTextPosition(SwingConstants.TRAILING);
	    setVerticalTextPosition(AbstractButton.CENTER);
	}
	task = new TaskProperty<SimpleButton>(this);
	this.addActionListener(new ActionListener() {
	    @Override public void actionPerformed(ActionEvent e) {
		if (task.property != null) {
		    task.property.start();
		}
	    }
	});
	icon = new ItProperty<SimpleButton, Icon>(this);
	icon.property.afterChange(new Task() {
	    @Override public void doTask() {
		if (icon != null) {
		    setIcon(icon.property.value());
		}
	    }
	});
    }
}
