package layoutless.controls;

import java.awt.event.*;
import tee.binding.it.*;
import tee.binding.task.*;
import tee.binding.these.*;
import javax.swing.*;
import java.awt.*;

public class SimpleSelector extends JComboBox {

    private DefaultComboBoxModel model;
    private SimpleSelector me;
    private Bundle view;
    private These<String> column;
    private Numeric selection;
    private Window window;
    private WindowAdapter windowAdapter = new WindowAdapter() {

	public void windowClosed(WindowEvent e) {
	    window.removeWindowListener(this);
	    clear();
	}
    };

    private void clear() {
    }

    public SimpleSelector(Window win) {
	super();
	me = this;
	window = win;
	window.addWindowListener(windowAdapter);
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
	model = new DefaultComboBoxModel();
	this.setModel(model);
	addItemListener(new ItemListener() {

	    @Override public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == e.SELECTED) {
		    selection.value(me.getSelectedIndex());
		}
	    }
	});
    }

    private void requery() {
	if (view != null && column != null) {
	    model.removeAllElements();
	    for (int i = 0; i < view.size(); i++) {
		model.addElement(column.at(i));
	    }
	}
    }

    public SimpleSelector bind(Bundle v, These<String> c) {
	column = c.watch(new Task(){

	    @Override
	    public void doTask() {
		System.out.println("sfgb");
	    }
	});
	view = new Bundle().afterChange(new Task() {

	    @Override public void doTask() {
		requery();
	    }
	}).bind(v);
	selection.bind(view.select());
	requery();
	return this;
    }
}
