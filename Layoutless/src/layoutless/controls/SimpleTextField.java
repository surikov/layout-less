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
public class SimpleTextField extends JScrollPane {
    public NoteProperty<SimpleTextField> text;
    private SimpleTextField me;
    private boolean lock;
    private JTextArea area = new JTextArea();
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
    public SimpleTextField(Window win) {
	super();
	window = win;
	window.addWindowListener(windowAdapter);
	me = this;
	lock = false;
	area.setFont(area.getFont().deriveFont(12f)); // will only change size to 12pt
	text = new NoteProperty<SimpleTextField>(this);
	text.property.value("").afterChange(new Task() {
	    @Override public void doTask() {
		if (!lock) {
		    if (text != null) {
			lock = true;
			area.setText(text.property.value());
			lock = false;
		    }
		}
	    }
	});
	area.getDocument().addDocumentListener(new DocumentListener() {
	    @Override public void insertUpdate(DocumentEvent e) {
		if (text != null) {
		    lock = true;
		    me.text.property.value(area.getText());
		    lock = false;
		}
	    }
	    @Override public void removeUpdate(DocumentEvent e) {
		if (text != null) {
		    lock = true;
		    me.text.property.value(area.getText());
		    lock = false;
		}
	    }
	    @Override public void changedUpdate(DocumentEvent e) {
		if (text != null) {
		    lock = true;
		    me.text.property.value(area.getText());
		    lock = false;
		}
	    }
	});
	this.getViewport().add(area);
    }
}
