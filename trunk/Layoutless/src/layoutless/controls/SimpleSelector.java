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
    //private Fit<Kind> fit;
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
		    System.out.println("" + me.getSelectedIndex());
		}
	    }
	});
    }
    public SimpleSelector bind(View v, ColumnNote c) {
	view = v;
	column = c;
	view.afterRefresh(new Task() {
	    @Override public void doTask() {
		System.out.println("refresh selector");
	    }
	});
	return this;
    }
    /* public SimpleSelector fit(Fit _fit) {
     this.fit.bind(_fit);
     return this;
     } */
}
