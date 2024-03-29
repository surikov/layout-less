package layoutless.controls;

import layoutless.controls.table.*;
import java.awt.*;
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
import java.util.*;
import tee.binding.these.*;

/**
 * 
 * @author User
 */
public class SimpleTable extends JScrollPane implements ListSelectionListener {
    //DefaultTableColumnModel model;
 public TaskProperty<SimpleTable> task;
    private SimpleTable me;
    boolean lockSelect = false;
    private JTable table;
    private Bundle view;
    private boolean lockReorder = false;
    //private View view;
    private Vector<SimpleColumn> columns;
    public NumericProperty<SimpleTable> selection;
    private Window window;
    DefaultTableColumnModel columnModel;
    DefaultTableModel tableModel;
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
    public SimpleTable(Window win, Bundle v) {
        super();
        window = win;
        window.addWindowListener(windowAdapter);
        columns = new Vector<SimpleColumn>();
        task = new TaskProperty<SimpleTable>(this);
        //this.setOpaque(false);
        //this.getViewport().setOpaque(false);
        //model = new DefaultTableColumnModel();
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
         */
        table = new JTable() {

            public boolean isCellEditable(int rowIndex, int vColIndex) {
                return false;
            }
        };
        //table.setOpaque(false);
        columnModel = new DefaultTableColumnModel();
        tableModel = new DefaultTableModel();
        table.setAutoResizeMode(table.AUTO_RESIZE_OFF);
        table.setAutoCreateColumnsFromModel(false);
        table.setColumnModel(columnModel);
        table.setModel(tableModel);
        TableColumnModelListener tcml = new TableColumnModelListener() {

            @Override
            public void columnAdded(TableColumnModelEvent e) {
                //System.out.println("columnAdded "+e);
            }

            @Override
            public void columnRemoved(TableColumnModelEvent e) {
                //System.out.println("columnRemoved "+e);
            }

            @Override
            public void columnMoved(TableColumnModelEvent e) {
                //System.out.println("columnMoved "+e);
                if (lockReorder) {
                    return;
                }
                lockReorder = true;
                for (int i = 0; i < columnModel.getColumnCount(); i++) {
                    SimpleColumn sc = (SimpleColumn) columnModel.getColumn(i);
                    sc.order.property.value(i);
                    //for (int i = 0; i < columns.size(); i++) {
                    //columns.get(i).width.property.value(columns.get(i).getPreferredWidth());
                    //System.out.println(i + ": " + sc.title.property.value() 
                    //+ ": " + columns.get(i).width.property.value()
                    //+ ": " + sc.getModelIndex()
                    //);
                }
                lockReorder = false;
            }

            @Override
            public void columnMarginChanged(ChangeEvent e) {
                for (int i = 0; i < columns.size(); i++) {
                    columns.get(i).width.property.value(columns.get(i).getPreferredWidth());
                    //System.out.println(i + ": " + columns.get(i).title.property.value() + ": " + columns.get(i).width.property.value());
                }
            }

            @Override
            public void columnSelectionChanged(ListSelectionEvent e) {
                //System.out.println("columnSelectionChanged "+e);
            }
        };
        columnModel.addColumnModelListener(tcml);
        this.getViewport().add(table);
        //cell.bind(c);
        view = new Bundle().afterChange(new Task() {

            @Override
            public void doTask() {
                requery();
            }
        }).bind(v);

        selection = new NumericProperty(this);
        selection.property.value(0).afterChange(new Task() {

            @Override
            public void doTask() {
                //System.out.println("trigger "+selection.property.value());
                if (selection.property != null) {
                    if (tableModel != null) {
                        int s = selection.property.value().intValue();
                        if (s >= 0 && s < tableModel.getRowCount()) {
                            //list.setSelectedIndex(s);
                            ListSelectionModel selectionModel = table.getSelectionModel();
                            selectionModel.setSelectionInterval(s, s);
                        }
                    }
                }
                //System.out.println("*"+selection.property.value());
            }
        });
        table.getSelectionModel().addListSelectionListener(this);
        view = new Bundle().bind(v).afterChange(new Task() {

            @Override
            public void doTask() {
                requery();
            }
        });

        requery();
        //System.out.println(":"+selection.property.value()+"/"+view.select().value());
        //double nn=view.select().value();

        selection.property.bind(view.select());

        //if(nn)
        //System.out.println(":"+selection.property.value());
        table.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    //System.out.println(":" + selection.property.value());
                    if (selection.property.value() >= 0 && selection.property.value() < tableModel.getRowCount()) {
			if (task.property != null) {
			    task.property.value().start();
			}
		    }
                }
            }
        });
//System.out.println("init "+selection.property.value());
    }

    private void requery() {
        lockSelect = true;
        this.tableModel.setRowCount(0);
        if (view != null) {
            if (view.size() > 0) {
                for (int ii = 0; ii < view.size(); ii++) {
                    Vector v = new Vector();
                    for (int i = 0; i < table.getColumnCount(); i++) {
                        v.add("?");
                    }
                    //DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                    tableModel.addRow(v);
                }
            }
        }
        lockSelect = false;
    }
    /*private void adjustColumnWidth() {
     int cnt = columnModel.getColumnCount();
     for (int i = 0; i < cnt; i++) {
     TableColumn clmn = columnModel.getColumn(i);
     //System.out.println(i+": " + clmn.getPreferredWidth());
     }
     }*/
    /*
     private void addTableColumn(String title, String id, int width) {
     //DefaultTableColumnModel columnModel = (DefaultTableColumnModel) table.getColumnModel();
     TableColumn c1 = new TableColumn(columnModel.getColumnCount());
     c1.setIdentifier(id);
     c1.setHeaderValue(title);
     c1.setPreferredWidth(width);
     columnModel.addColumn(c1);
     //DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
     tableModel.addColumn(id);
     }*/

    /**
     * 
     * @param it
     * @return
     */
    public SimpleTable column(SimpleColumn it) {
        lockSelect = true;
        this.columns.add(it);
        //this.addTableColumn(it.title.property.value(), "c" + this.columns.size(), 70);
        columnModel.addColumn(it);
        tableModel.addColumn(it);
        it.table = this;
        it.view = view;
        reorder();
lockSelect = false;
int s = selection.property.value().intValue();
                        if (s >= 0 && s < tableModel.getRowCount()) {
                            //list.setSelectedIndex(s);
                            ListSelectionModel selectionModel = table.getSelectionModel();
                            selectionModel.setSelectionInterval(s, s);
                        }
        return this;
    }

    private int findOrder(SimpleColumn c) {
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            if (c == columnModel.getColumn(i)) {
                return i;
            }
        }
        return 0;
    }

    public void reorder() {
        if (lockReorder) {
            return;
        }
        lockReorder = true;
        //System.out.println("sort");
        Collections.sort(columns, new ColumnComparator());
        for (int i = 0; i < columns.size(); i++) {
            SimpleColumn clmn = columns.get(columns.size() - i - 1);
            int nn = findOrder(clmn);
            //System.out.println(i + ": " + clmn.title.property.value() + ": " + clmn.order.property.value()+": "+nn);
            table.moveColumn(nn, 0);
        }
        lockReorder = false;
        /*for (int i = 0; i < columnModel.getColumnCount(); i++) {
         int ordr = 0;
         SimpleColumn clmn = (SimpleColumn) columnModel.getColumn(i);
         System.out.println(i + ": " + clmn.title.property.value() + ": " + clmn.order.property.value());
         }*/
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (lockSelect) {
            return;
        }
        selection.is(table.getSelectedRow());

        //System.out.println(table.getSelectedRow());
    }
}
