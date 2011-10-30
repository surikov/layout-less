package layoutless.controls;
import java.awt.event.*;
import tee.binding.*;
import tee.binding.view.*;
import tee.binding.it.*;
import tee.binding.task.*;
import layoutless.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.text.*;
import java.math.*;
import java.util.*;
public class SimpleTable extends JScrollPane {
    TableModel model;
    private SimpleTable me;
    private JTable table;
    private View view;
    private Vector<TableColumn> columns;
    private Numeric selection;
    public SimpleTable() {
	super();
	columns = new Vector<TableColumn>();
	this.setOpaque(false);
	this.getViewport().setOpaque(false);
	/*
	model = new AbstractTableModel() {
	    @Override public int getColumnCount() {
		return 5;
	    }
	    @Override public int getRowCount() {
		return 10;
	    }
	    @Override public Object getValueAt(int row, int col) {
		return new Integer(row * col);
	    }
	};
	
	table = new JTable(model);*/
	this.getViewport().add(table);
    }
    public SimpleTable column(TableColumn it) {
	this.columns.add(it);
	return this;
    }
}
