package layoutless.controls;

import java.awt.*;
import java.awt.event.*;
import tee.binding.it.*;
import tee.binding.task.*;
import layoutless.*;
import javax.swing.*;
import javax.swing.event.*;
import java.text.*;
import java.math.*;

/**
 * 
 * @author User
 */
public class SimpleCheck extends JCheckBox {

    private Toggle toggle;
    private SimpleCheck me;
    private boolean lock;
private Note text;
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
    public SimpleCheck(Window win) {
	super();
	window=win;
	window.addWindowListener(windowAdapter);
	me = this;
	lock = false;
	this.setOpaque(false);
	text = new Note().value("").afterChange(new Task() {
	    @Override public void doTask() {
		if (text != null) {
		    setText(text.value());
		}
	    }
	});
	toggle = new Toggle().value(false).afterChange(new Task() {

	    @Override public void doTask() {
		if (!lock) {
		    if (toggle != null) {
			lock = true;
			me.setSelected(toggle.value());
			lock = false;
		    }
		}
	    }
	});
	addChangeListener(new ChangeListener() {

	    @Override
	    public void stateChanged(ChangeEvent e) {
		toggle.value(me.isSelected());
	    }
	});
    }

    /**
     * 
     * @param it
     * @return
     */
    public SimpleCheck toggle(boolean it) {
	toggle.value(it);
	return this;
    }

    /**
     * 
     * @param it
     * @return
     */
    public SimpleCheck toggle(Toggle it) {
	toggle.bind(it);
	return this;
    }

    /**
     * 
     * @return
     */
    public Toggle numeric() {
	return toggle;
    }
    /**
     * 
     * @param it
     * @return
     */
    public SimpleCheck text(String it) {
	text.value(it);
	return this;
    }
    /**
     * 
     * @param it
     * @return
     */
    public SimpleCheck text(Note it) {
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
