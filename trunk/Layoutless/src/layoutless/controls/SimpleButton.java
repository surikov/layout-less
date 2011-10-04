package layoutless.controls;
import tee.binding.*;
import layoutless.*;
import javax.swing.*;
public class SimpleButton extends JButton{
    private Note text;
    private Toggle alignIconLeft;
    public SimpleButton() {
	text = new Note().value("").afterChange(new Task() {
	    @Override public void doTask() {
		if (text != null) {
		    setText(text.value());
		}
	    }
	});
	alignIconLeft = new Toggle().value(true).afterChange(new Task() {
	    @Override public void doTask() {
		if (alignIconLeft != null) {
		    if (!alignIconLeft.value()) {
			setHorizontalAlignment(SwingConstants.RIGHT);
		    } else {
			setHorizontalAlignment(SwingConstants.LEFT);
		    }
		}
	    }
	});
	if (!alignIconLeft.value()) {
	    setHorizontalAlignment(SwingConstants.RIGHT);
	} else {
	    setHorizontalAlignment(SwingConstants.LEFT);
	}
    }
    public SimpleButton text(String it) {
	text.value(it);
	return this;
    }
    public SimpleButton text(Note it) {
	text.bind(it);
	return this;
    }
    public Note text() {
	return text;
    }
    public SimpleButton alignIconLeft(boolean it) {
	alignIconLeft.value(it);
	return this;
    }
    public SimpleButton alignIconLeft(Toggle it) {
	alignIconLeft.bind(it);
	return this;
    }
    public Toggle alignIconLeft() {
	return alignIconLeft;
    }
}
