package layoutless.controls;

import java.awt.*;
import java.awt.event.*;
import tee.binding.these.*;
import tee.binding.task.*;
import tee.binding.properties.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * 
 * @author User
 */
public class SimpleList extends JScrollPane {
    private DefaultListModel model;
    private JList list;
    private Bundle view;
    private These<String> column;
    public NumericProperty<SimpleList> selection;
    private Window window;
    public TaskProperty<SimpleList> task;
    boolean lockSelect = false;
    private WindowAdapter windowAdapter = new WindowAdapter() {
	@Override
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
    public SimpleList(Window win) {
	super();
	window = win;
	window.addWindowListener(windowAdapter);
	list = new JList();
	task = new TaskProperty<SimpleList>(this);
	this.getViewport().add(list);
	selection = new NumericProperty(this);
	selection.property.value(0).afterChange(new Task() {
	    @Override public void doTask() {
		if (selection.property != null) {
		    if (model != null) {
			int s = selection.property.value().intValue();
			if (s >= 0 && s < model.getSize()) {
			    list.setSelectedIndex(s);
			}
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
		selection.is(list.getSelectedIndex());
	    }
	});
	list.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
		    if (selection.property.value() >= 0 && selection.property.value() < model.getSize()) {
			if (task.property != null) {
			    task.property.start();
			}
		    }
		}
	    }
	});
    }
    private void requery() {
	lockSelect = true;
	if (view != null && column != null) {
	    model.removeAllElements();
	    for (int i = 0; i < view.size(); i++) {
		view.probe(i);
		model.addElement(column.is().value());
	    }
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
	selection.property.bind(view.select());
	requery();
	return this;
    }
}
