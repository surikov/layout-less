package layoutless.controls;
import java.awt.event.*;
import tee.binding.*;
import tee.binding.view.*;
import tee.binding.it.*;
import tee.binding.task.*;
import tee.binding.these.*;
import layoutless.*;
import javax.swing.*;
import javax.swing.event.*;
import java.text.*;
import java.math.*;
public class SimpleSelector extends JComboBox {
    private DefaultComboBoxModel model;
    private SimpleSelector me;
    private //private Fit<Kind> fit;
	    Bundle view;
    private These<String> column;
    private Numeric selection;
    public SimpleSelector() {
	super();
	me = this;
	selection = new Numeric().value(0).afterChange(new Task() {
	    @Override public void doTask() {
		if (selection != null) {
		    int s = selection.value().intValue();
		    if (s >= 0 && s < me.model.getSize()) {
			me.setSelectedIndex(s);
		    }
		}
	    }
	});
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
		    //System.out.println("selector " + me.getSelectedIndex());
		    selection.value(me.getSelectedIndex());
		}
	    }
	});
    }
    /*public Numeric selection() {
	return selection;
    }
    public SimpleSelector selection(Numeric it) {
	selection.bind(it);
	return this;
    }
    public SimpleSelector selection(double it) {
	selection.value(it);
	return this;
    }
    public SimpleSelector selection(int it) {
	selection.value(it);
	return this;
    }*/
    private void requery() {
	//System.out.println("requery");
	if (view != null && column != null) {
	    model.removeAllElements();
	    for (int i = 0; i < view.size(); i++) {
		//view.move(i);
		//model.addElement(column.value());
		model.addElement(column.at(i));
	    }
	}
    }
    public SimpleSelector bind(Bundle v, These<String> c) {
	column = c;
	/*view = v.select(new Toggle().value(true)).afterChange(new Task() {
	    @Override public void doTask() {
		requery();
	    }
	});*/
	view = new Bundle().afterChange(new Task() {

	    @Override public void doTask() {
		requery();
	    }
	}).bind(v);
	selection.bind(view.select());
	requery();
	return this;
    }
    /* public SimpleSelector fit(Fit _fit) {
     this.fit.bind(_fit);
     return this;
     } */
}
