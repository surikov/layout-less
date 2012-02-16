package layoutless.controls;
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
/**
 * 
 * @author User
 */
public class TableColumn {
    private Note title;
    private Note value;
    /**
     * 
     */
    public TableColumn() {
	title = new Note();
	value = new Note();
    }
    /**
     * 
     * @param it
     * @return
     */
    public TableColumn title(String it) {
	title.value(it);
	return this;
    }
    /**
     * 
     * @param it
     * @return
     */
    public TableColumn title(Note it) {
	title.bind(it);
	return this;
    }
    /**
     * 
     * @return
     */
    public Note title() {
	return title;
    }
    /**
     * 
     * @param it
     * @return
     */
    public TableColumn value(Note it) {
	value.bind(it);
	return this;
    }
    /**
     * 
     * @return
     */
    public Note value() {
	return value;
    }
}
