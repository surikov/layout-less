package layoutless.controls;
import java.awt.*;
import java.awt.event.*;
import tee.binding.it.*;
import tee.binding.task.*;
import layoutless.*;
import javax.swing.*;
import javax.swing.event.*;
/**
 * 
 * @author User
 */
public class SimpleStringField extends JTextField {
    private Note string;
    private SimpleStringField me;
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
    public SimpleStringField(Window win) {
	super();
	window=win;
	window.addWindowListener(windowAdapter);
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
    /**
     * 
     * @param it
     * @return
     */
    public SimpleStringField string(String it) {
	string.value(it);
	return this;
    }
    /**
     * 
     * @param it
     * @return
     */
    public SimpleStringField string(Note it) {
	string.bind(it);
	return this;
    }
    /**
     * 
     * @return
     */
    public Note string() {
	return string;
    }
}
