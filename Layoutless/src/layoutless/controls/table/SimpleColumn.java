package layoutless.controls.table;

import java.awt.*;
import java.awt.Component;
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
import sun.swing.*;

/**
 * 
 * @author User
 */
public class SimpleColumn extends TableColumn implements TableCellRenderer {

    public NoteProperty<SimpleColumn> title;
    public NumericProperty<SimpleColumn> width;
    public NumericProperty<SimpleColumn> order;
    public Bundle view;
    //private These<String> column;
    private Note cell;
    public SimpleTable table;
    DefaultTableCellRenderer renderer;

    /**
     * 
     */
    public SimpleColumn(It<String> c) {
        super();
        //table=t;
        renderer = new DefaultTableCellRenderer();
        
        
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
        this.setCellRenderer(this);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        //throw new UnsupportedOperationException("Not supported yet.");
        if (view != null) {
            view.probe(row);
        }
        if (cell != null) {
            renderer.setText(//"" + row + "/" + column + "/" + isSelected + "/" + 
                    cell.value());
        }
        if (isSelected) {
            //renderer.setOpaque(true);
            renderer.setBackground(table.getSelectionBackground());
            renderer.setForeground(table.getSelectionForeground());
        } else {
            //renderer.setOpaque(false);
            renderer.setBackground(table.getBackground());
            renderer.setForeground(table.getForeground());
        }
        /*
         Color    fg = DefaultLookup.getColor(this, this.ui, "Table.dropCellForeground");
         Color   bg = DefaultLookup.getColor(this, ui, "Table.dropCellBackground");
         if (isSelected) {
         renderer.setForeground(fg == null ? table.getSelectionForeground()
         : fg);
         super.setBackground(bg == null ? table.getSelectionBackground()
         : bg);
         } else {
         Color background = unselectedBackground != null
         ? unselectedBackground
         : table.getBackground();
         if (background == null || background instanceof javax.swing.plaf.UIResource) {
         Color alternateColor = DefaultLookup.getColor(this, ui, "Table.alternateRowColor");
         if (alternateColor != null && row % 2 == 0)
         background = alternateColor;
         }
         super.setForeground(unselectedForeground != null
         ? unselectedForeground
         : table.getForeground());
         super.setBackground(background);
         }
         */
        return renderer;
    }
}
