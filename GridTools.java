package crudfx.control;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.util.*;
import javax.swing.plaf.basic.*;
import javax.swing.border.*;
import javax.swing.plaf.*;
/**
 * @profile desktop
 * @author Sergey Surikov
 */

class VerticalScrollBarUI extends BasicScrollBarUI {

    ImageIcon upOn;
    ImageIcon upOff;
    ImageIcon downOn;
    ImageIcon downOff;
    //Color color = new Color(255, 255, 255, 100);
    //Color fill = new Color(0x223366);
    JComponent track;
    JComponent thumb;

    public VerticalScrollBarUI(
            ImageIcon upOn, ImageIcon upOff, ImageIcon downOn, ImageIcon downOff ,JComponent track,JComponent thumb
            ) {
        this.upOff = upOff;
        this.upOn = upOn;
        this.downOff = downOff;
        this.downOn = downOn;
        this.track=track;
        this.thumb=thumb;
    }

    protected JButton createDecreaseButton(int orientation) {
        JButton button = new JButton();
        button.setRolloverEnabled(true);
        button.setIcon(upOff);
        button.setRolloverIcon(upOn);
        button.setBorder(new EmptyBorder(0, 0, 0, 0));
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(upOn.getIconWidth(),upOn.getIconHeight()));
        return button;
    }

    protected JButton createIncreaseButton(int orientation) {
        JButton button = new JButton("");
        button.setRolloverEnabled(true);
        button.setIcon(downOff);
        button.setRolloverIcon(downOn);
        button.setBorder(new EmptyBorder(0, 0, 0, 0));
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(downOn.getIconWidth(),downOn.getIconHeight()));
        return button;
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        track.setSize(trackBounds.width
                ,trackBounds.height
                //-upOn.getIconHeight()/2-downOn.getIconHeight()/2
                );
        //track.doLayout();
        GridTools.layoutTree(track);
        //g.translate(0,2*upOn.getIconHeight()-8);
        g.translate(0,upOn.getIconHeight());
        track.paint(g);
        //g.translate(0,-2*upOn.getIconHeight()+8);
        g.translate(0,-upOn.getIconHeight());
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        thumb.setSize(thumbBounds.width
                ,thumbBounds.height
                //-upOn.getIconHeight()/2-downOn.getIconHeight()/2
                );
        //thumb.doLayout();
        GridTools.layoutTree(thumb);
        g.translate(thumbBounds.x,thumbBounds.y
                //+upOn.getIconHeight()
                 );
        thumb.paint(g);
        g.translate(-thumbBounds.x,-thumbBounds.y
                 //-upOn.getIconHeight()
                 );
    }
}

class HorizontalScrollBarUI extends BasicScrollBarUI {

    ImageIcon leftOn;
    ImageIcon leftOff;
    ImageIcon rightOn;
    ImageIcon rightOff;
    //Color color = new Color(255, 255, 255, 100);
    //Color fill = new Color(0x223366);
    JComponent track;
    JComponent thumb;

    public HorizontalScrollBarUI(
            ImageIcon leftOn, ImageIcon leftOff, ImageIcon rightOn, ImageIcon rightOff ,JComponent track,JComponent thumb
            ) {
        this.leftOff = leftOff;
        this.leftOn = leftOn;
        this.rightOff = rightOff;
        this.rightOn = rightOn;
        this.track=track;
        this.thumb=thumb;
    }

    protected JButton createDecreaseButton(int orientation) {
        JButton button = new JButton();
        button.setRolloverEnabled(true);
        button.setIcon(leftOff);
        button.setRolloverIcon(leftOn);
        button.setBorder(new EmptyBorder(0, 0, 0, 0));
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        //button.setText("null");
        button.setPreferredSize(new Dimension(leftOn.getIconWidth(),leftOn.getIconHeight()));
        return button;
        /*leftOn.getIconWidth()
        JButton button = new JButton();
        button.setRolloverEnabled(true);
        button.setIcon(upOff);
        button.setRolloverIcon(upOn);
        button.setBorder(new EmptyBorder(0, 0, 0, 0));
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        return button;
        */

    }

    protected JButton createIncreaseButton(int orientation) {
        JButton button = new JButton("");
        button.setRolloverEnabled(true);
        button.setIcon(rightOff);
        button.setRolloverIcon(rightOn);
        button.setBorder(new EmptyBorder(0, 0, 0, 0));
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(rightOn.getIconWidth(),rightOn.getIconHeight()));
        return button;
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        track.setSize(trackBounds.width
                //-leftOn.getIconWidth()/2-rightOn.getIconWidth()/2
                ,trackBounds.height);
        //track.doLayout();
        GridTools.layoutTree(track);
        g.translate(leftOn.getIconWidth(),0);
        track.paint(g);
        g.translate(-leftOn.getIconWidth(),0);
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        thumb.setSize(thumbBounds.width
                //-leftOn.getIconWidth()/2-rightOn.getIconWidth()/2
                ,thumbBounds.height);
        //thumb.doLayout();
        GridTools.layoutTree(thumb);
        g.translate(thumbBounds.x
                //+leftOn.getIconWidth()/2
                ,0);
        thumb.paint(g);
        g.translate(-thumbBounds.x
                //-leftOn.getIconWidth()/2
                ,0);
    }
}

class EmptyTableCellRenderet implements TableCellRenderer {

    JLabel label;
    //JLabel selectedLabel;
boolean art=false;
    public EmptyTableCellRenderet(
            JLabel la//, JLabel se
            ) {
        label = la;
        //selectedLabel = se;
        if(label instanceof ArtGridCellValue)art=true;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
      //System.out.println(label.getClass()+"/"+row+"/"+column);
      //ArtGridCellValue
      /*if(label instanceof ArtGridCellValue){
        ((ArtGridCellValue)label).change(value.toString(), isSelected, row, column);
        return label;
      }*/
        /*if (isSelected) {

            selectedLabel.setText(value.toString());
            return selectedLabel;
        } else {*/
            //label.setText(value.toString());
            //label.setText(""+value);
            //return label;
        //}
      if(art)((ArtGridCellValue)label).change(value.toString(), isSelected, row, column);
      else label.setText(""+value);
      return label;
    }
}
class VJScrollBar extends JScrollBar{
    ScrollBarUI scrollBarUI;
    public VJScrollBar(ScrollBarUI ui){
        super(JScrollBar.VERTICAL);
        scrollBarUI=ui;
        setOpaque(false);
    }
    public void setUI(ScrollBarUI ui){
        super.setUI(scrollBarUI);
    }
}
class HJScrollBar extends JScrollBar{
    ScrollBarUI scrollBarUI;
    public HJScrollBar(ScrollBarUI ui){
        super(JScrollBar.HORIZONTAL);
        scrollBarUI=ui;
        setOpaque(false);
    }
    public void setUI(ScrollBarUI ui){
        super.setUI(scrollBarUI);
    }
}
public class GridTools {
    public static void layoutTree(JComponent c){
        c.doLayout();
        for(int i=0;i<c.getComponentCount();i++){
            try{
            layoutTree((JComponent)c.getComponent(i));
            }catch(Throwable t){
            //
            }
        }
    }
    public static void installScrollBars(JScrollPane scrollPane, ImageIcon upOn, ImageIcon upOff, ImageIcon downOn, ImageIcon downOff
            , ImageIcon leftOn, ImageIcon leftOff, ImageIcon rightOn, ImageIcon rightOff
            ,JComponent verticalTrack,JComponent verticalThumb
            ,JComponent horizontalTrack,JComponent  horizontalThumb
            ) {
        try {
            VerticalScrollBarUI verticalScrollBarUI = new VerticalScrollBarUI(
                    upOn, upOff, downOn, downOff,verticalTrack,verticalThumb);
            VJScrollBar vJScrollBar=new VJScrollBar(verticalScrollBarUI);
            scrollPane.setVerticalScrollBar(vJScrollBar);

            HorizontalScrollBarUI horizontalScrollBarUI = new HorizontalScrollBarUI(
                    leftOn, leftOff, rightOn, rightOff,horizontalTrack,horizontalThumb);
            HJScrollBar hJScrollBar=new HJScrollBar(horizontalScrollBarUI);
            scrollPane.setHorizontalScrollBar(hJScrollBar);
/*
            java.awt.event.ComponentAdapter t=new java.awt.event.ComponentAdapter(){
                public void componentResized(java.awt.event.ComponentEvent e){
                    System.out.println(e);
                    }
                };scrollPane.addComponentListener(t);*/
        } catch (Throwable t) {
            t.printStackTrace();
        }

    }

    public static void setupArtGrid(JTable table
            , JLabel cellLabel
            //, JLabel selectedCellLabel
            , JLabel headerLabel
            ) {
        EmptyTableCellRenderet h = new EmptyTableCellRenderet(headerLabel);//, headerLabel);
        EmptyTableCellRenderet r = new EmptyTableCellRenderet(cellLabel);//, selectedCellLabel);
        DefaultTableColumnModel columnModel = (DefaultTableColumnModel) table.getColumnModel();
        table.getTableHeader().setDefaultRenderer(h);
        table.setBorder(new EmptyBorder(0, 0, 0, 0));
        table.getTableHeader().setBackground(new java.awt.Color(255, 255, 255, 1));
        for (Enumeration<TableColumn> e = columnModel.getColumns(); e.hasMoreElements();) {
            TableColumn tc = e.nextElement();
            tc.setCellRenderer(r);
        }
    }

    public static JTable readOnlyTable() {
        JTable table = new JTable() {
            /*public void paint(Graphics g){
              System.out.println("repaint");
              super.paint(g);
              }*/
            public boolean isCellEditable(int rowIndex, int vColIndex) {
                return false;
            };
        };
        DefaultTableColumnModel columnModel = new DefaultTableColumnModel();
        DefaultTableModel tableModel = new DefaultTableModel();
        table.setAutoResizeMode(table.AUTO_RESIZE_OFF);
        table.setAutoCreateColumnsFromModel(false);
        table.setColumnModel(columnModel);
        table.setModel(tableModel);
        return table;
    }

    public static void addTableColumn(JTable table, String title, String id, int width) {
        DefaultTableColumnModel columnModel = (DefaultTableColumnModel) table.getColumnModel();
        TableColumn c1 = new TableColumn(columnModel.getColumnCount());
        c1.setIdentifier(id);
        c1.setHeaderValue(title);
        c1.setPreferredWidth(width);
        columnModel.addColumn(c1);
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.addColumn(id);
    }

    public static void addTableRow(JTable table) {
        Vector v = new Vector();
        for (int i = 0; i < table.getColumnCount(); i++) {
            v.add("?");
        }
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.addRow(v);
    }

    public static void removeTableRow(JTable table, int row) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.removeRow(row);
    }
public static void removeAllTableRows(JTable table) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
    }
    public static void setTableValue(JTable table, String value, int row, int column) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setValueAt(value, row, column);
    }

    public static int toView(JTable table, int mColIndex) {
        for (int c = 0; c < table.getColumnCount(); c++) {
            TableColumn col = table.getColumnModel().getColumn(c);
            if (col.getModelIndex() == mColIndex) {
                return c;
            }
        }
        return -1;
    }

    public static int toModel(JTable table, int vColIndex) {
        if (vColIndex >= table.getColumnCount()) {
            return -1;
        }
        return table.getColumnModel().getColumn(vColIndex).getModelIndex();
    }

    public static void setTableFieldValue(JTable table, String value, String column) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        DefaultTableColumnModel columnModel = (DefaultTableColumnModel) table.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            if (columnModel.getColumn(i).getIdentifier().toString().toUpperCase().equals(column.toUpperCase())) {
                int n = toModel(table, i);
                tableModel.setValueAt(value, tableModel.getRowCount() - 1, n);
                break;
            }
        }
    }

    public static void addColumnModelListener(JTable table
            , TableColumnModelListener tableColumnModelListener
            , TableColumnModelListener oldListener
            ) {
        DefaultTableColumnModel columnModel = (DefaultTableColumnModel) table.getColumnModel();
        //TableColumnModelListener[] t=columnModel.getColumnModelListeners();
        //for(int i=0;i<t.length;i++)
        columnModel.removeColumnModelListener(oldListener);
        columnModel.addColumnModelListener(tableColumnModelListener);
    }

    public static void moveColumnToEnd(JTable table, String key) {
        TableColumn tc = table.getColumn(key);
        table.moveColumn(toView(table, tc.getModelIndex()), table.getColumnCount() - 1);
    }
}
