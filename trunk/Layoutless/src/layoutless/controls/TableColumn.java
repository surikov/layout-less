package layoutless.controls;

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

/**
 * 
 * @author User
 */
public class TableColumn {
    public NoteProperty<TableColumn> title;
    public NoteProperty<TableColumn> value;
    /**
     * 
     */
    public TableColumn() {
	title = new NoteProperty<TableColumn>(this);
	value = new NoteProperty<TableColumn>(this);
    }
}
