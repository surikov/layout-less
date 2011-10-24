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
    private //private Fit<Kind> fit;
	    View view;
    ColumnNote column;

    public SimpleSelector() {
	super();
	me = this;
	//fit = new Fit<Kind>();
	model = new DefaultComboBoxModel();
	this.setModel(model);
	/* model.addElement("111");
	 model.addElement("222222");
	 model.removeAllElements();
	 model.addElement("333"); */
	addItemListener(new ItemListener() {

	    @Override public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == e.SELECTED) {
		    System.out.println("selector " + me.getSelectedIndex());
		}
	    }
	});
    }

    private void requery() {
	System.out.println("requery");
	if (view != null && column != null) {
	    model.removeAllElements();
	    for (int i = 0; i < view.size(); i++) {
		view.move(i);
		model.addElement(column.is().value());
	    }
	}
    }

    public SimpleSelector bind(View v, ColumnNote c) {
	column = c;
	view = v.select().afterRefresh(new Task() {

	    @Override public void doTask() {
		requery();
	    }
	});
	requery();
	return this;
    }
    /* public SimpleSelector fit(Fit _fit) {
     this.fit.bind(_fit);
     return this;
     } */
}
