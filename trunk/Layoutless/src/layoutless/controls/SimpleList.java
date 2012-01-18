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
import java.text.*;
import java.math.*;

public class SimpleList extends JScrollPane {

    private DefaultListModel model;
    private SimpleList me;
    private JList list;
    private View view;
    private Note column;
    private Numeric selection;

    public SimpleList() {
	super();
	list = new JList();
	//list.setOpaque(false);
	//this.setOpaque(false);
	//this.getViewport().setOpaque(false);
	//list.setBackground(Color.red);
	//this.getViewport().setBackground(Color.blue);
	//this.setBackground(Color.green);

	this.getViewport().add(list);
	me = this;
	selection = new Numeric().value(0).afterChange(new Task() {

	    @Override public void doTask() {
		if (selection != null) {
		    int s = selection.value().intValue();
		    if (s >= 0 && s < me.model.getSize()) {
			list.setSelectedIndex(s);
		    }
		}
	    }
	});
	model = new DefaultListModel();
	list.setModel(model);
	list.addListSelectionListener(new ListSelectionListener() {

	    @Override public void valueChanged(ListSelectionEvent e) {
		selection.value(list.getSelectedIndex());
	    }
	});
    }

    public Numeric selection() {
	return selection;
    }

    public SimpleList selection(Numeric it) {
	selection.bind(it);
	return this;
    }

    public SimpleList selection(double it) {
	selection.value(it);
	return this;
    }

    public SimpleList selection(int it) {
	selection.value(it);
	return this;
    }

    private void requery() {
	//System.out.println("requery");
	if (view != null && column != null) {
	    model.removeAllElements();
	    for (int i = 0; i < view.size(); i++) {
		view.move(i);
		model.addElement(column.value());
	    }
	}
    }

    public SimpleList bind(View v, Note c) {
	column = c;
	view = v.select(new Toggle().value(true)).afterChange(new Task() {

	    @Override public void doTask() {
		requery();
	    }
	});
	requery();
	return this;
    }
}
