package layoutless.controls;

import tee.binding.*;
import layoutless.*;
import javax.swing.*;

public class SimpleLabel extends JLabel {
    private Note text;
    private Toggle alignTextLeft;
    public SimpleLabel() {
	text = new Note().value("").afterChange(new Task() {
	    @Override public void doTask() {
		if (text != null) {
		    setText(text.value());
		}
	    }
	});
	alignTextLeft = new Toggle().value(true).afterChange(new Task() {
	    @Override public void doTask() {
		if (alignTextLeft != null) {
		    if (!alignTextLeft.value()) {
			setHorizontalAlignment(SwingConstants.RIGHT);
		    } else {
			setHorizontalAlignment(SwingConstants.LEFT);
		    }
		}
	    }
	});
	if (!alignTextLeft.value()) {
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
    public SimpleLabel alignTextLeft(boolean it) {
	alignTextLeft.value(it);
	return this;
    }
    public SimpleLabel alignTextLeft(Toggle it) {
	alignTextLeft.bind(it);
	return this;
    }
    public Toggle alignTextLeft() {
	return alignTextLeft;
    }
}
