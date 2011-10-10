package layoutless.controls;

import tee.binding.*;
import layoutless.*;
import javax.swing.*;

public class SimpleLabel extends JLabel {
    private Note text;
    private Toggle normalAlignment;
    public SimpleLabel() {
	super();
	text = new Note().value("").afterChange(new Task() {
	    @Override public void doTask() {
		if (text != null) {
		    setText(text.value());
		}
	    }
	});
	normalAlignment = new Toggle().value(true).afterChange(new Task() {
	    @Override public void doTask() {
		if (normalAlignment != null) {
		    if (!normalAlignment.value()) {
			setHorizontalAlignment(SwingConstants.RIGHT);
		    } else {
			setHorizontalAlignment(SwingConstants.LEFT);
		    }
		}
	    }
	});
	if (!normalAlignment.value()) {
	    setHorizontalAlignment(SwingConstants.RIGHT);
	} else {
	    setHorizontalAlignment(SwingConstants.LEFT);
	}
    }
    public SimpleLabel text(String it) {
	text.value(it);
	return this;
    }
    public SimpleLabel text(Note it) {
	text.bind(it);
	return this;
    }
    public Note text() {
	return text;
    }
    public SimpleLabel normalAlignment(boolean it) {
	normalAlignment.value(it);
	return this;
    }
    public SimpleLabel normalAlignment(Toggle it) {
	normalAlignment.bind(it);
	return this;
    }
    public Toggle normalAlignment() {
	return normalAlignment;
    }
}
