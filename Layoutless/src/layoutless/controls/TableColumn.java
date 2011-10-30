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
public class TableColumn {
    private Note title;
    private Note value;
    public TableColumn() {
	title = new Note();
	value = new Note();
    }
    public TableColumn title(String it) {
	title.value(it);
	return this;
    }
    public TableColumn title(Note it) {
	title.bind(it);
	return this;
    }
    public Note title() {
	return title;
    }
    public TableColumn value(Note it) {
	value.bind(it);
	return this;
    }
    public Note value() {
	return value;
    }
}
