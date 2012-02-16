package layoutless.controls;
import java.awt.*;
import java.awt.event.*;
import tee.binding.*;
//import tee.binding.view.*;
import tee.binding.it.*;
import tee.binding.task.*;
import layoutless.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.text.*;
import java.math.*;
import java.util.*;
/**
 * 
 * @author User
 */
public class SimpleTable extends JScrollPane {
    DefaultTableColumnModel model;
    private SimpleTable me;
    private JTable table;
    //private View view;
    private Vector<TableColumn> columns;
    private Numeric selection;
    private Window window;
    private WindowAdapter windowAdapter=new WindowAdapter(){
	    public void windowClosed(WindowEvent e){
		window.removeWindowListener(this);
		//System.out.println(e+" / "+window.hashCode());
		clear();
		}
	    };
    private void clear(){

    }
    /**
     * 
     * @param win
     */
    public SimpleTable(Window win) {
	super();
	window=win;
	window.addWindowListener(windowAdapter);
	columns = new Vector<TableColumn>();
	this.setOpaque(false);
	this.getViewport().setOpaque(false);
	model=new DefaultTableColumnModel();
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
    /**
     * 
     * @param it
     * @return
     */
    public SimpleTable column(TableColumn it) {
	this.columns.add(it);
	return this;
    }
}
