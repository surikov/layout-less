package layoutless.controls;
import java.awt.*;
import java.awt.event.*;
import tee.binding.it.*;
import tee.binding.task.*;
import layoutless.*;
import javax.swing.*;
/**
 * 
 * @author User
 */
public class SimpleButton extends JButton {
    private Note text;
    private Task task;
    private Toggle normalAlignment;
    private It<Icon> icon;
    private Window window;
    private WindowAdapter windowAdapter=new WindowAdapter(){
	    public void windowClosed(WindowEvent e){
		window.removeWindowListener(this);
		//System.out.println(e+" / "+window.hashCode());
		clear();
		}
	    };
    private void clear(){

    }
    /**
     * 
     * @param win
     */
    public SimpleButton(Window win) {
	super();
	window=win;
	window.addWindowListener(windowAdapter);
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
    /**
     * 
     * @param it
     * @return
     */
    public SimpleButton text(String it) {
	text.value(it);
	return this;
    }
    /**
     * 
     * @param it
     * @return
     */
    public SimpleButton text(Note it) {
	text.bind(it);
	return this;
    }
    /**
     * 
     * @return
     */
    public Note text() {
	return text;
    }
    /**
     * 
     * @param it
     * @return
     */
    public SimpleButton normalAlignment(boolean it) {
	normalAlignment.value(it);
	return this;
    }
    /**
     * 
     * @param it
     * @return
     */
    public SimpleButton normalAlignment(Toggle it) {
	normalAlignment.bind(it);
	return this;
    }
    /**
     * 
     * @return
     */
    public Toggle normalAlignment() {
	return normalAlignment;
    }
    /**
     * 
     * @param it
     * @return
     */
    public SimpleButton icon(Icon it) {
	icon.value(it);
	return this;
    }
    /**
     * 
     * @param it
     * @return
     */
    public SimpleButton icon(It<Icon> it) {
	icon.bind(it);
	return this;
    }
    /**
     * 
     * @return
     */
    public It<Icon> icon() {
	return icon;
    }
    /**
     * 
     * @param it
     * @return
     */
    public SimpleButton task(Task it) {
	task = it;
	return this;
    }
    /**
     * 
     * @return
     */
    public Task task() {
	return task;
    }
}
