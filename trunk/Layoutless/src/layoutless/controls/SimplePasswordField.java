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
public class SimplePasswordField extends JPasswordField {
    public NoteProperty<SimplePasswordField> password;
    private SimplePasswordField me;
    private boolean lock;
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
    public SimplePasswordField(Window win) {
	super();
	window = win;
	window.addWindowListener(windowAdapter);
	me = this;
	lock = false;
	password = new NoteProperty<SimplePasswordField>(this);
	password.property.value("").afterChange(new Task() {
	    @Override public void doTask() {
		if (!lock) {
		    if (password != null) {
			lock = true;
			setText(password.property.value());
			lock = false;
		    }
		}
	    }
	});
	this.getDocument().addDocumentListener(new DocumentListener() {
	    @Override public void insertUpdate(DocumentEvent e) {
		if (password != null) {
		    lock = true;
		    me.password.property.value(me.getText());
		    lock = false;
		}
	    }
	    @Override public void removeUpdate(DocumentEvent e) {
		if (password != null) {
		    lock = true;
		    me.password.property.value(me.getText());
		    lock = false;
		}
	    }
	    @Override public void changedUpdate(DocumentEvent e) {
		if (password != null) {
		    lock = true;
		    me.password.property.value(me.getText());
		    lock = false;
		}
	    }
	});
    }
}
