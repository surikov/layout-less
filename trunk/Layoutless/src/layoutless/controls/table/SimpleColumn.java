package layoutless.controls.table;

import java.awt.event.*;
import tee.binding.*;
import tee.binding.properties.*;
import tee.binding.it.*;
import tee.binding.task.*;
import layoutless.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.text.*;
import java.math.*;
import tee.binding.these.*;
import layoutless.controls.*;

/**
 * 
 * @author User
 */
public class SimpleColumn extends TableColumn {
    public NoteProperty<SimpleColumn> title;
    public NumericProperty<SimpleColumn> width;
    public NumericProperty<SimpleColumn> order;
    private Bundle view;
    //private These<String> column;
    private Note cell;
    public SimpleTable table;
    /**
     * 
     */
    public SimpleColumn(It<String> c) {
	super();
	//table=t;
	cell = new Note().bind(c);
	title = new NoteProperty<SimpleColumn>(this);
	width = new NumericProperty<SimpleColumn>(this);
	order = new NumericProperty<SimpleColumn>(this);
	new Note().afterChange(new Task() {
	    @Override
	    public void doTask() {
		if (title.property.value() != null) {
		    setHeaderValue(title.property.value());
		}
	    }
	}).bind(title.property);
	new Numeric().afterChange(new Task() {
	    @Override
	    public void doTask() {
		if (width.property.value() != null) {
		    setPreferredWidth(width.property.value().intValue());
		}
	    }
	}).bind(width.property);
	new Numeric().afterChange(new Task() {
	    @Override
	    public void doTask() {
		if (table != null) {
		    table.reorder();
		}
	    }
	}).bind(order.property);
    }
}
