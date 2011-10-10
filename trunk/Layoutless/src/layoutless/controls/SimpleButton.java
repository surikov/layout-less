package layoutless.controls;
import java.awt.event.*;
import tee.binding.*;
import layoutless.*;
import javax.swing.*;
public class SimpleButton extends JButton {
    private Note text;
    private Task task;
    private Toggle normalAlignment;
    private It<Icon> icon;
    public SimpleButton() {
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
			setVerticalTextPosition(AbstractButton.BOTTOM);
			setHorizontalTextPosition(AbstractButton.CENTER);
		    } else {
			setHorizontalTextPosition(SwingConstants.TRAILING);
			setVerticalTextPosition(AbstractButton.CENTER);
		    }
		}
	    }
	});
	if (!normalAlignment.value()) {
	    setVerticalTextPosition(AbstractButton.BOTTOM);
	    setHorizontalTextPosition(AbstractButton.CENTER);
	} else {
	    setHorizontalTextPosition(SwingConstants.TRAILING);
	    setVerticalTextPosition(AbstractButton.CENTER);
	}
	task = null;
	this.addActionListener(new ActionListener() {
	    @Override public void actionPerformed(ActionEvent e) {
		if (task != null) {
		    task.start();
		}
	    }
	});
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
    public SimpleButton normalAlignment(boolean it) {
	normalAlignment.value(it);
	return this;
    }
    public SimpleButton normalAlignment(Toggle it) {
	normalAlignment.bind(it);
	return this;
    }
    public Toggle normalAlignment() {
	return normalAlignment;
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
    public SimpleButton task(Task it) {
	task = it;
	return this;
    }
    public Task task() {
	return task;
    }
}
