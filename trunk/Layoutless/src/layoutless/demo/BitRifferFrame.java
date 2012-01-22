package layoutless.demo;

import layoutless.decor.*;
import layoutless.controls.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import layoutless.*;
import tee.binding.*;
import tee.binding.it.*;
import tee.binding.task.*;
import tee.binding.view.*;
class EditSampleForm extends JDialog {
    int sampleNum;
    public void go(int s) {
	sampleNum=s;
	setLayout(new BorderLayout());
	setSize(600, 400);
	setLocation(64,16);
	this.setModal(true);
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	Layoutless layoutless = new Layoutless();
	
	add(layoutless, BorderLayout.CENTER);
	setVisible(true);
    }
}
class SamplesForm extends JDialog {
    public void go() {
	setLayout(new BorderLayout());
	setSize(600, 400);
	setLocation(32,8);
	this.setModal(true);
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	final ColumnNote name = new ColumnNote();
	final View view=new View()
		.row(new Row().field(name.is("One")))
		.row(new Row().field(name.is("Two")))
		.row(new Row().field(name.is("Three")))
		;
	Layoutless layoutless = new Layoutless();
	final SimpleList li=new SimpleList();
	layoutless
		.item(new ComponentBox()
		    .component(li.bind(view, name.is()))
			.width(layoutless.width().minus(16))
			.height(layoutless.height().minus(8+27+8+8))
			.x(8)
			.y(8)
		    )
		.item(new ComponentBox()
		    .component(new SimpleButton()
			.text("Load")
			.task(new Task(){@Override public void doTask(){
			    int s=li.selection().value().intValue();
			    view.move(s);
			    System.out.println(name.is().value());
			    }})
			//.normalAlignment(true)
			)
		    .width(90)
		    .height(27)
		    .x(8)
		    .y(layoutless.height().minus(8+27))
		    )
		.item(new ComponentBox()
		    .component(new SimpleButton()
			.text("Edit")
			.task(new Task(){@Override public void doTask(){new EditSampleForm().go(0);}})
			//.normalAlignment(true)
			)
		    .width(90)
		    .height(27)
		    .x(8+8+90)
		    .y(layoutless.height().minus(8+27))
		    )
		;
	add(layoutless, BorderLayout.CENTER);
	setVisible(true);
    }
}

class RiffsForm {
    public void go() {
	System.out.println("Riffs");
    }
}
class MainForm extends JFrame {
    public void go() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLayout(new BorderLayout());
	setSize(600, 400);
	Layoutless layoutless = new Layoutless();
	layoutless.item(new ComponentBox()
		    .component(new SimpleButton()
			.text("Riffs")
			.task(new Task(){@Override public void doTask(){new RiffsForm().go();}})
			.normalAlignment(true))
		    .width(90)
		    .height(27)
		    .x(16)
		    .y(16)
		    )
		.item(new ComponentBox()
		    .component(new SimpleButton()
			.text("Samples")
			.task(new Task(){@Override public void doTask(){new SamplesForm().go();}})
			.normalAlignment(true))
		    .width(90)
		    .height(27)
		    .x(16+90+8)
		    .y(16)
		    )
		;
	add(layoutless, BorderLayout.CENTER);
	setVisible(true);
    }
}

public class BitRifferFrame {
    public static void main(String[] args) {
	try {
	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	}
	catch (Throwable e) {
	    e.printStackTrace();
	}
	new MainForm().go();
    }
}
