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
public class SimpleWait extends JProgressBar {

    private Toggle horizontal;
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
    public SimpleWait(Window win) {
	super();
	window=win;
	window.addWindowListener(windowAdapter);
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

    /**
     * 
     * @param it
     * @return
     */
    public SimpleWait horizontal(boolean it) {
	horizontal.value(it);
	return this;
    }

    /**
     * 
     * @param it
     * @return
     */
    public SimpleWait horizontal(Toggle it) {
	horizontal.bind(it);
	return this;
    }

    /**
     * 
     * @return
     */
    public Toggle horizontal() {
	return horizontal;
    }
}
