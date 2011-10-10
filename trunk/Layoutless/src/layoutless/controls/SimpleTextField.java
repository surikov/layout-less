package layoutless.controls;
import tee.binding.*;
import layoutless.*;
import javax.swing.*;
import javax.swing.event.*;
public class SimpleTextField extends JTextField {
    private Note text;
    private SimpleTextField me;
    private boolean lock;
    public SimpleTextField() {
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
    public SimpleTextField text(String it) {
	text.value(it);
	return this;
    }
    public SimpleTextField text(Note it) {
	text.bind(it);
	return this;
    }
    public Note text() {
	return text;
    }
}
