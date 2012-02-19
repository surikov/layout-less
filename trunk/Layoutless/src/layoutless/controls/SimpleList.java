package layoutless.controls;

import java.awt.*;
import java.awt.event.*;
import tee.binding.*;
import tee.binding.these.*;
//import tee.binding.view.*;
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
public class SimpleList extends JScrollPane {
    private DefaultListModel model;
    private SimpleList me;
    private JList list;
    private Bundle view;
    private These<String> column;
    private Numeric selection;
    private Window window;
    boolean lockSelect = false;
    private WindowAdapter windowAdapter = new WindowAdapter() {
	public void windowClosed(WindowEvent e) {
	    window.removeWindowListener(this);
	    //System.out.println(e+" / "+window.hashCode());
	    clear();
	}
    };
    private void clear() {
    }
    /**
     * 
     * @param win
     */
    public SimpleList(Window win) {
	super();
	window = win;
	window.addWindowListener(windowAdapter);
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
			//System.out.println("trig "+me.hashCode()+"/"+list.getSelectedIndex());
		    }
		}
	    }
	});
	model = new DefaultListModel();
	list.setModel(model);
	list.addListSelectionListener(new ListSelectionListener() {
	    @Override public void valueChanged(ListSelectionEvent e) {
		if (lockSelect) {
		    return;
		}
		selection.value(list.getSelectedIndex());
		//System.out.println("sel "+me.hashCode()+"/"+list.getSelectedIndex());
	    }
	});
	//System.out.println("done");
    }
    /*
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
     */
    private void requery() {
	lockSelect = true;
	if (view != null && column != null) {
	    //System.out.println("requery "+view.size());
	    model.removeAllElements();
	    for (int i = 0; i < view.size(); i++) {
		//view.move(i);
		//model.addElement(column.is());
		view.probe(i);
		model.addElement(column.is().value());
		//System.out.println(column.at(i));
	    }
	    //list.setSelectedIndex(2);
	}
	lockSelect = false;
    }
    /**
     * 
     * @param v
     * @param c
     * @return
     */
    public SimpleList bind(Bundle v, These<String> c) {
	column = c.watch(new Task() {
	    @Override
	    public void doTask() {
		requery();
	    }
	});
	view = new Bundle().bind(v).afterChange(new Task() {
	    @Override
	    public void doTask() {
		requery();
	    }
	});
	selection.bind(view.select());
	//System.out.println("view.select() "+view.select().value());
	requery();
	return this;
    }
}
