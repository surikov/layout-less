package layoutless.controls;

import java.awt.*;
import java.awt.event.*;
import tee.binding.properties.*;
import tee.binding.task.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * 
 * @author User
 */
public class SimpleCheck extends JCheckBox {
    public ToggleProperty<SimpleCheck> toggle;
    private SimpleCheck me;
    private boolean lock;
    public NoteProperty<SimpleCheck> text;
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
    public SimpleCheck(Window win) {
	super();
	window = win;
	window.addWindowListener(windowAdapter);
	me = this;
	lock = false;
	this.setOpaque(false);
	text = new NoteProperty<SimpleCheck>(this);
	text.property.value("").afterChange(new Task() {
	    @Override public void doTask() {
		if (text != null) {
		    setText(text.property.value());
		}
	    }
	});
	toggle = new ToggleProperty<SimpleCheck>(this);
	toggle.property.value(false).afterChange(new Task() {
	    @Override public void doTask() {
		if (!lock) {
		    if (toggle != null) {
			lock = true;
			me.setSelected(toggle.property.value());
			lock = false;
		    }
		}
	    }
	});
	addChangeListener(new ChangeListener() {
	    @Override
	    public void stateChanged(ChangeEvent e) {
		toggle.property.value(me.isSelected());
	    }
	});
    }
}
