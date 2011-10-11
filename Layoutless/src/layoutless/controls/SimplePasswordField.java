package layoutless.controls;

import tee.binding.*;
import layoutless.*;
import javax.swing.*;
import javax.swing.event.*;

public class SimplePasswordField extends JPasswordField {

    private Note text;
    private SimplePasswordField me;
    private boolean lock;

    public SimplePasswordField() {
	super();
	me = this;
	lock = false;
	text = new Note().value("").afterChange(new Task() {

	    @Override public void doTask() {
		if (!lock) {
		    if (text != null) {
			lock = true;
			setText(text.value());
			lock = false;
		    }
		}
	    }
	});
	this.getDocument().addDocumentListener(new DocumentListener() {

	    @Override public void insertUpdate(DocumentEvent e) {
		if (text != null) {
		    lock = true;
		    me.text.value(me.getText());
		    lock = false;
		}
	    }

	    @Override public void removeUpdate(DocumentEvent e) {
		if (text != null) {
		    lock = true;
		    me.text.value(me.getText());
		    lock = false;
		}
	    }

	    @Override public void changedUpdate(DocumentEvent e) {
		if (text != null) {
		    lock = true;
		    me.text.value(me.getText());
		    lock = false;
		}
	    }
	});
    }

    public SimplePasswordField text(String it) {
	text.value(it);
	return this;
    }

    public SimplePasswordField text(Note it) {
	text.bind(it);
	return this;
    }

    public Note text() {
	return text;
    }
}
