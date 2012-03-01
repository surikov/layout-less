package layoutless.controls;

import java.awt.event.*;
import tee.binding.properties.*;
import tee.binding.task.*;
import tee.binding.these.*;
import javax.swing.*;
import java.awt.*;

/**
 * 
 * @author User
 */
public class SimpleSelector extends JComboBox {
    private DefaultComboBoxModel model;
    private SimpleSelector me;
    private Bundle view;
    private These<String> column;
    public NumericProperty<SimpleSelector> selection;
    private Window window;
    boolean lockSelect = false;
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
    public SimpleSelector(Window win) {
	super();
	me = this;
	window = win;
	window.addWindowListener(windowAdapter);
	model = new DefaultComboBoxModel();
	this.setModel(model);
	selection = new NumericProperty<SimpleSelector>(this);
	selection.property.value(0).afterChange(new Task() {
	    @Override public void doTask() {
		if (selection != null) {
		    int s = selection.property.value().intValue();
		    if (s >= 0 && s < me.model.getSize()) {
			me.setSelectedIndex(s);
		    }
		}
	    }
	});	
	addItemListener(new ItemListener() {
	    @Override public void itemStateChanged(ItemEvent e) {
		if (lockSelect) {
		    return;
		}
		if (e.getStateChange() == e.SELECTED) {
		    selection.property.value(me.getSelectedIndex());
		}
	    }
	});
    }
    private void requery() {
	if (view != null && column != null) {
	    lockSelect = true;
	    model.removeAllElements();
	    for (int i = 0; i < view.size(); i++) {
		view.probe(i);
		model.addElement(column.is().value());
	    }
	    lockSelect = false;
	}
    }
    /**
     * 
     * @param v
     * @param c
     * @return
     */
    public SimpleSelector bind(Bundle v, These<String> c) {
	column = c.watch(new Task() {
	    @Override
	    public void doTask() {
		//System.out.println("sfgb");
	    }
	});
	view = new Bundle().afterChange(new Task() {
	    @Override public void doTask() {
		requery();
	    }
	}).bind(v);
	requery();
	selection.property.bind(view.select());
	return this;
    }
}
