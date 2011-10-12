package layoutless.demo;

import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import layoutless.ComponentBox;
import layoutless.Layoutless;
import layoutless.controls.*;
import tee.binding.*;

public class Example extends JFrame {
    private Layoutless layoutless;
    public static void main(String[] args) {
	JFrame frame = new Example();
	frame.setVisible(true);
    }
    public Example() {
	setLayout(new BorderLayout());
	setSize(500, 400);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	initComponents();
    }
    private void initComponents() {
	layoutless = new Layoutless();
	int labelsWidth=150;
	final SimpleTextField fle=new SimpleTextField();
	final SimplePasswordField psw=new SimplePasswordField();
	final SimpleNumberField nu=new SimpleNumberField();
	layoutless
		.item(new ComponentBox()
		    .component(new SimpleLabel()
			.text("File")
			.normalAlignment(false))
		    .width(labelsWidth)
		    .height(22)
		    .x(0)
		    .y(8+25*0)
		    )
		.item(new ComponentBox()
		    .component(fle
			.text("123")
			)
		    .width(layoutless.width().minus(labelsWidth).minus(16).minus(50))
		    .height(22)
		    .x(labelsWidth+8)
		    .y(8+25*0)
		    )
		.item(new ComponentBox()
		    .component(new SimpleButton()
			.text("...")
			.task(new Task(){@Override public void doTask() {
				System.out.println("...");
				}
			    })
			.normalAlignment(true))
		    .width(49)
		    .height(21)
		    .x(layoutless.width().minus(58))
		    .y(8+25*0)
		    )
		.item(new ComponentBox()
		    .component(new SimpleLabel()
			.text("Name")
			.normalAlignment(false))
		    .width(labelsWidth)
		    .height(22)
		    .x(0)
		    .y(8+25*1)
		    )
		.item(new ComponentBox()
		    .component(nu)
		    .width(layoutless.width().minus(labelsWidth).minus(16))
		    .height(22)
		    .x(labelsWidth+8)
		    .y(8+25*1)
		    )
		.item(new ComponentBox()
		    .component(new SimpleLabel()
			.text("Password")
			.normalAlignment(false))
		    .width(labelsWidth)
		    .height(22)
		    .x(0)
		    .y(8+25*2)
		    )
		.item(new ComponentBox()
		    .component(psw.text("111"))
		    .width(150)
		    .height(22)
		    .x(labelsWidth+8)
		    .y(8+25*2)
		    )
		.item(new ComponentBox()
		    .component(new SimpleButton()
			.text("jb1")
			.icon(new ImageIcon("ok.png"))
			.task(new Task(){@Override public void doTask() {
				System.out.println(nu.numeric().value());
				}
			    })
			.normalAlignment(true))
		    .width(90)
		    .height(27)
		    .x(labelsWidth+8)
		    .y(layoutless.height().minus(40))
		    )
		.item(new ComponentBox()
		    .component(new SimpleIcon().icon(new ImageIcon("keys.png")))
		    .width(128)
		    .height(128)
		    .x(0)
		    .y(layoutless.height().minus(140))
		    )
		;
	//nu.minimum(5).maximum(9).decimalPlaces(3).numeric(17.123456);
	this.add(layoutless, BorderLayout.CENTER);
    }
}
