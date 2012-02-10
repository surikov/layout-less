package layoutless.controls;

import java.awt.*;
import java.awt.event.*;
import tee.binding.*;
import tee.binding.view.*;
import tee.binding.it.*;
import tee.binding.task.*;
import layoutless.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * 
 * @author User
 */
public class SimplePasswordField extends JPasswordField {

    private Note text;
    private SimplePasswordField me;
    private boolean lock;
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
    /**
     * 
     * @param win
     */
    public SimplePasswordField(Window win) {
	super();
	window=win;
	window.addWindowListener(windowAdapter);
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

    /**
     * 
     * @param it
     * @return
     */
    public SimplePasswordField text(String it) {
	text.value(it);
	return this;
    }

    /**
     * 
     * @param it
     * @return
     */
    public SimplePasswordField text(Note it) {
	text.bind(it);
	return this;
    }

    /**
     * 
     * @return
     */
    public Note text() {
	return text;
    }
}
