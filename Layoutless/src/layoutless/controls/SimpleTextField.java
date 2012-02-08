package layoutless.controls;

import java.awt.*;
import java.awt.event.*;
import tee.binding.it.*;
import tee.binding.task.*;
import layoutless.*;
import javax.swing.*;
import javax.swing.event.*;

public class SimpleTextField extends JScrollPane {

    private Note text;
    private SimpleTextField me;
    private boolean lock;
    private JTextArea area = new JTextArea();
private Window window;
    private WindowAdapter windowAdapter=new WindowAdapter(){
	    public void windowClosed(WindowEvent e){
		window.removeWindowListener(this);
		//System.out.println(e+" / "+window.hashCode());
		clear();
		}
	    };
    private void clear(){

    }
    public SimpleTextField(Window win) {
	super();
	window=win;
	window.addWindowListener(windowAdapter);
	me = this;
	lock = false;
	area.setFont(area.getFont().deriveFont(12f)); // will only change size to 12pt
	text = new Note().value("").afterChange(new Task() {

	    @Override public void doTask() {
		if (!lock) {
		    if (text != null) {
			lock = true;
			area.setText(text.value());
			lock = false;
		    }
		}
	    }
	});
	area.getDocument().addDocumentListener(new DocumentListener() {

	    @Override public void insertUpdate(DocumentEvent e) {
		if (text != null) {
		    lock = true;
		    me.text.value(area.getText());
		    lock = false;
		}
	    }

	    @Override public void removeUpdate(DocumentEvent e) {
		if (text != null) {
		    lock = true;
		    me.text.value(area.getText());
		    lock = false;
		}
	    }

	    @Override public void changedUpdate(DocumentEvent e) {
		if (text != null) {
		    lock = true;
		    me.text.value(area.getText());
		    lock = false;
		}
	    }
	});
	this.getViewport().add(area);
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
