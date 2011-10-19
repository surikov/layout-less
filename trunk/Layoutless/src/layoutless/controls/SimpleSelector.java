package layoutless.controls;

import java.awt.event.*;
import tee.binding.*;
import layoutless.*;
import javax.swing.*;
import javax.swing.event.*;
import java.text.*;
import java.math.*;

public class SimpleSelector<Kind> extends JComboBox {

    private DefaultComboBoxModel model;
    private SimpleSelector me;
    private Fit<Kind> fit;

    public SimpleSelector() {
	super();
	me = this;
	model = new DefaultComboBoxModel();
	this.setModel(model);
	model.addElement("111");
	model.addElement("222222");
	model.addElement("333");
	addItemListener(new ItemListener() {

	    @Override public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == e.SELECTED) {
		    System.out.println("" + me.getSelectedIndex());
		}
	    }
	});
    }
    public SimpleSelector fit(Fit fit){
	return this;
    }
}
