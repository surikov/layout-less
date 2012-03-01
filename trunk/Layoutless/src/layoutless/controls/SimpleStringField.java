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
public class SimpleStringField extends JTextField {
    private SimpleStringField me;
    private boolean lock;
    private Window window;
    public NoteProperty<SimpleStringField> string;
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
    public SimpleStringField(Window win) {
	super();
	window = win;
	window.addWindowListener(windowAdapter);
	me = this;
	lock = false;
	string = new NoteProperty<SimpleStringField>(this);
	string.property.afterChange(new Task() {
	    @Override public void doTask() {
		if (!lock) {
		    if (string != null) {
			lock = true;
			setText(string.property.value());
			lock = false;
		    }
		}
	    }
	});
	this.getDocument().addDocumentListener(new DocumentListener() {
	    @Override public void insertUpdate(DocumentEvent e) {
		if (string != null) {
		    lock = true;
		    me.string.property.value(me.getText());
		    lock = false;
		}
	    }
	    @Override public void removeUpdate(DocumentEvent e) {
		if (string != null) {
		    lock = true;
		    me.string.property.value(me.getText());
		    lock = false;
		}
	    }
	    @Override public void changedUpdate(DocumentEvent e) {
		if (string != null) {
		    lock = true;
		    me.string.property.value(me.getText());
		    lock = false;
		}
	    }
	});
    }
}
