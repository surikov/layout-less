package layoutless.controls;

import tee.binding.*;
import layoutless.*;
import javax.swing.*;

public class SimpleIcon extends JLabel {

    private It<Icon> icon;

    public SimpleIcon() {
	super();
	icon = new It<Icon>().afterChange(new Task() {

	    @Override public void doTask() {
		if (icon != null) {
		    setIcon(icon.value());
		}
	    }
	});
    }
    public SimpleIcon icon(Icon it) {
	icon.value(it);
	return this;
    }

    public SimpleIcon icon(It<Icon> it) {
	icon.bind(it);
	return this;
    }

    public It<Icon> icon() {
	return icon;
    }
}
