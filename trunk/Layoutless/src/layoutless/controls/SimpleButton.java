package layoutless.controls;

import tee.binding.*;
import layoutless.*;
import javax.swing.*;

public class SimpleButton extends JButton {
    private Note text;
    private Toggle alignIconLeft;
    private It<Icon> icon;
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
			setVerticalTextPosition(AbstractButton.BOTTOM);
			setHorizontalTextPosition(AbstractButton.CENTER);
		    } else {
			setHorizontalTextPosition(SwingConstants.TRAILING);
			setVerticalTextPosition(AbstractButton.CENTER);
		    }
		}
	    }
	});
	if (!alignIconLeft.value()) {
	    setVerticalTextPosition(AbstractButton.BOTTOM);
	    setHorizontalTextPosition(AbstractButton.CENTER);
	} else {
	    setHorizontalTextPosition(SwingConstants.TRAILING);
	    setVerticalTextPosition(AbstractButton.CENTER);
	}
	//System.out.println(getVerticalTextPosition());

	//

	icon = new It<Icon>().afterChange(new Task() {
	    @Override public void doTask() {
		if (icon != null) {
		    setIcon(icon.value());
		}
	    }
	});
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
    public SimpleButton icon(Icon it) {
	icon.value(it);
	return this;
    }
    public SimpleButton icon(It<Icon> it) {
	icon.bind(it);
	return this;
    }
    public It<Icon> icon() {
	return icon;
    }
}
