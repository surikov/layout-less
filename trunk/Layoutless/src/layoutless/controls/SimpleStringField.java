package layoutless.controls;
import tee.binding.it.*;
import tee.binding.task.*;
import layoutless.*;
import javax.swing.*;
import javax.swing.event.*;
public class SimpleStringField extends JTextField {
    private Note string;
    private SimpleStringField me;
    private boolean lock;
    public SimpleStringField() {
	super();
	me = this;
	lock = false;
	string = new Note().value("").afterChange(new Task() {
	    @Override public void doTask() {
		if (!lock) {
		    if (string != null) {
			lock = true;
			setText(string.value());
			lock = false;
		    }
		}
	    }
	});
	this.getDocument().addDocumentListener(new DocumentListener() {
	    @Override public void insertUpdate(DocumentEvent e) {
		if (string != null) {
		    lock = true;
		    me.string.value(me.getText());
		    lock = false;
		}
	    }
	    @Override public void removeUpdate(DocumentEvent e) {
		if (string != null) {
		    lock = true;
		    me.string.value(me.getText());
		    lock = false;
		}
	    }
	    @Override public void changedUpdate(DocumentEvent e) {
		if (string != null) {
		    lock = true;
		    me.string.value(me.getText());
		    lock = false;
		}
	    }
	});
    }
    public SimpleStringField string(String it) {
	string.value(it);
	return this;
    }
    public SimpleStringField string(Note it) {
	string.bind(it);
	return this;
    }
    public Note string() {
	return string;
    }
}
