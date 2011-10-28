package layoutless.controls;

import tee.binding.it.*;
import tee.binding.task.*;
import layoutless.*;
import javax.swing.*;

public class SimpleWait extends JProgressBar {

    private Toggle horizontal;

    public SimpleWait() {
	super();
	this.setIndeterminate(true);
	horizontal = new Toggle().value(true).afterChange(new Task() {

	    @Override public void doTask() {
		if (horizontal != null) {
		    if (horizontal.value()) {
			setOrientation(SwingConstants.HORIZONTAL);
		    } else {
			setOrientation(SwingConstants.VERTICAL);
		    }
		}
	    }
	});
	if (horizontal.value()) {
	    setOrientation(SwingConstants.HORIZONTAL);
	} else {
	    setOrientation(SwingConstants.VERTICAL);
	}
    }

    public SimpleWait horizontal(boolean it) {
	horizontal.value(it);
	return this;
    }

    public SimpleWait horizontal(Toggle it) {
	horizontal.bind(it);
	return this;
    }

    public Toggle horizontal() {
	return horizontal;
    }
}
