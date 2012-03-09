package layoutless.demo;

import layoutless.decor.SimpleImage;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import layoutless.*;
import layoutless.decor.*;
import layoutless.decor.figures.*;
import layoutless.controls.*;
import layoutless.controls.table.*;
import tee.binding.*;
import tee.binding.it.*;
import tee.binding.task.*;
import tee.binding.it.*;
import tee.binding.these.*;
//import tee.binding.view.*;

/**
 * 
 * @author User
 */
public class Example extends JFrame {
    private Layoutless layoutless;
    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
	try {
	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (Throwable e) {
	    e.printStackTrace();
	}
	JFrame frame = new Example();
	frame.setVisible(true);
    }
    /**
     * 
     * @return
     */
    public static String getVersion() {
	return "1.2.9";
    }
     /**
      * 
      */
     public Example() {
	setLayout(new BorderLayout());
	setSize(500, 600);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	initComponents();
    }
    private void initComponents() {
	layoutless = new Layoutless();
	int labelsWidth=150;
	final SimpleStringField fle=new SimpleStringField(this);
	final SimplePasswordField psw=new SimplePasswordField(this);
	final SimpleNumberField nu=new SimpleNumberField(this);
	final Numeric num=new Numeric().value(20);
	Note bigTxt=new Note().value("Type here...\n");
/*
	ColumnNote nm = new ColumnNote();
	ColumnNumeric age = new ColumnNumeric();
	ColumnNote mail = new ColumnNote();
	ColumnToggle man = new ColumnToggle();
	View addrBook = new View()//
		.row(new Row().field(nm.is("Vasya")).field(man.is(true)).field(age.is(19)).field(mail.is("vpupkin@mail.ru")))//
		.row(new Row().field(nm.is("Petya")).field(man.is(true)).field(age.is(22)).field(mail.is("petrpetrov@gmail.com")))//
		.row(new Row().field(nm.is("Sasha")).field(man.is(true)).field(age.is(20)).field(mail.is("alxndr@aol.com")))//
		.row(new Row().field(nm.is("Masha")).field(man.is(false)).field(age.is(18)).field(mail.is("masha@mail.ru")))//
		.row(new Row().field(nm.is("Kolya")).field(man.is(true)).field(age.is(21)).field(mail.is("nikolay@gmail.com")))//
		.row(new Row().field(nm.is("Vanya")).field(man.is(true)).field(age.is(22)).field(mail.is("ivan@mail.ru")))//
		.row(new Row().field(nm.is("Olya")).field(man.is(false)).field(age.is(17)).field(mail.is("olga@aol.com")))//
		.row(new Row().field(nm.is("Vika")).field(man.is(false)).field(age.is(21)).field(mail.is("avictorya@gmail.com")))//
		.row(new Row().field(nm.is("Misha")).field(man.is(true)).field(age.is(23)).field(mail.is("mike@mail.ru")))//
		.row(new Row().field(nm.is("Glasha")).field(man.is(false)).field(age.is(20)).field(mail.is("glasha@gmail.com")))//
		;*/
	final These<String> fio = new These<String>();
	final Numerics age = new Numerics();
	final Notes mail = new Notes();
	final Toggles man = new Toggles();
	final Bundle sh = new Bundle()//
		.series(new Series().field(fio.is("Vasya")).field(man.is(true)).field(age.is(19)).field(mail.is("vpupkin@mail.ru")))//
		.series(new Series().field(fio.is("Petya")).field(man.is(true)).field(age.is(22)).field(mail.is("petrpetrov@gmail.com")))//
		.series(new Series().field(fio.is("Sasha")).field(man.is(true)).field(age.is(20)).field(mail.is("alxndr@aol.com")))//
		.series(new Series().field(fio.is("Masha")).field(man.is(false)).field(age.is(21)).field(mail.is("masha@mail.ru")))//
		.series(new Series().field(fio.is("Kolya")).field(man.is(true)).field(age.is(21)).field(mail.is("nikolay@gmail.com")))//
		.series(new Series().field(fio.is("Vanya")).field(man.is(true)).field(age.is(22)).field(mail.is("ivan@mail.ru")))//
		.series(new Series().field(fio.is("Olya")).field(man.is(false)).field(age.is(19)).field(mail.is("olga@aol.com")))//
		.series(new Series().field(fio.is("Vika")).field(man.is(false)).field(age.is(21)).field(mail.is("avictorya@gmail.com")))//
		.series(new Series().field(fio.is("Misha")).field(man.is(true)).field(age.is(21)).field(mail.is("mike@mail.ru")))//
		.series(new Series().field(fio.is("Glasha")).field(man.is(false)).field(age.is(20)).field(mail.is("glasha@gmail.com")))//
		;
final Numeric test=new Numeric().value(20);
	/*
	View list=addrBook.select(age.is().more(20)).sort(nm.ascending());
	final Numeric sel=new Numeric();
	final Note curMail=mail.at(list.row(sel));
	sel.value(1).afterChange(new Task(){
	    @Override public void doTask() {
		System.out.println("sel: "+sel.value()+", curMail: "+curMail.value());
	    }
	});
*/

	layoutless
		.item(new ComponentBox().component(new SimpleLabel(this)
			.text.is("File")
			.normalAlignment.is(false))
		    .width.is(labelsWidth)
		    .height.is(22)
		    .x.is(0)
		    .y.is(8+25*0)
		    )
		.item(new ComponentBox().component(fle.string.is(num.asNote()))
		    .width.is(layoutless.width.property.minus(labelsWidth).minus(16).minus(50))
		    .height.is(22)
		    .x.is(labelsWidth+8)
		    .y.is(8+25*0)
		    )
		.item(new ComponentBox().component(new SimpleButton(this)
			.text.is("...")
			.task.is(new Task(){@Override public void doTask() {
				System.out.println("...");
				}
			    })
			.normalAlignment.is(true))
		    .width.is(49)
		    .height.is(21)
		    .x.is(layoutless.width.property.minus(58))
		    .y.is(8+25*0)
		    )
		.item(new ComponentBox().component(new SimpleLabel(this)
			.text.is("Name")
			.normalAlignment.is(false))
		    .width.is(labelsWidth)
		    .height.is(22)
		    .x.is(0)
		    .y.is(8+25*1)
		    )
		.item(new ComponentBox().component(nu.numeric.is(num))
		    .width.is(layoutless.width.property.minus(labelsWidth).minus(16))
		    .height.is(22)
		    .x.is(labelsWidth+8)
		    .y.is(8+25*1)
		    )
		.item(new ComponentBox().component(new SimpleLabel(this)
			.text.is("Password")
			.normalAlignment.is(false))
		    .width.is(labelsWidth)
		    .height.is(22)
		    .x.is(0)
		    .y.is(8+25*2)
		    )
		.item(new ComponentBox().component(psw.password.is("111"))
		    .width.is(150)
		    .height.is(22)
		    .x.is(labelsWidth+8)
		    .y.is(8+25*2)
		    )
		.item(new ComponentBox().component(new SimpleNumericSlider(this).numeric.is(20))
		    .width.is(layoutless.width.property.minus(labelsWidth).minus(16))
		    .height.is(50)
		    .x.is(labelsWidth+8)
		    .y.is(8+25*3)
		    )
		.item(new ComponentBox().component(new SimpleCheck(this).text.is("test check"))
		    .width.is(layoutless.width.property.minus(labelsWidth).minus(16))
		    .height.is(22)
		    .x.is(labelsWidth+8)
		    .y.is(8+25*5)
		    )
		.item(new ComponentBox().component(new SimpleSelector(this,sh, fio.is()))
		    .width.is(layoutless.width.property.minus(labelsWidth).minus(16))
		    .height.is(22)
		    .x.is(labelsWidth+8)
		    .y.is(8+25*6)
		    )
		.item(new ComponentBox().component(new SimpleTextField(this).text.is(bigTxt))
		    .width.is(layoutless.width.property.minus(labelsWidth).minus(16))
		    .height.is(70)
		    .x.is(labelsWidth+8)
		    .y.is(8+25*7)
		    )
		/*.item(new ComponentBox()
		    .component(new SimpleTable()
			.column(new TableColumn().title("First"))
			.column(new TableColumn().title("Second"))
			)
		    .width(layoutless.width().minus(labelsWidth).minus(16))
		    .height(120)
		    .x(labelsWidth+8)
		    .y(8+25*7+70+4)
		    )*/
		.item(new ComponentBox().component(new SimpleList(this,sh, fio.is()))
		    .width.is(layoutless.width.property.minus(labelsWidth).minus(16))
		    .height.is(120)
		    .x.is(labelsWidth+8)
		    .y.is(8+25*7+70+4)
		    )
		.item(new ComponentBox().component(new SimpleLabel(this).text.is(mail.current()))
		    .width.is(layoutless.width.property.minus(labelsWidth).minus(16))
		    .height.is(20)
		    .x.is(labelsWidth+8)
		    .y.is(8+25*7+70+4+130)
		    )
                .item(new ComponentBox().component(new SimpleTable(this,sh)
			.column(new SimpleColumn(fio.is()).title.is("FIO").order.is(10))
			.column(new SimpleColumn(age.is().asNote()).title.is("Age").order.is(test))
			.column(new SimpleColumn(mail.is()).title.is("EMail"))
			)
		    .width.is(layoutless.width.property.minus(labelsWidth).minus(16))
		    .height.is(layoutless.height.property.minus(470))
		    .x.is(labelsWidth+8)
		    .y.is(8+25*7+70+4+130+20+8)
		    )
                .item(new ComponentBox().component(new Whiteboard(this)
                        .figure(new Streak()
			    .startX.is(0)
			    .startY.is(0)
			    .endX.is(100)
			    .endY.is(200)
			    .width.is(1)
			    .color.is(0xff000000)
			    )
                        )
		    .width.is(140)
		    .height.is(layoutless.height.property.minus(130))
		    .x.is(8)
		    .y.is(120)
		    )
		.item(new ComponentBox().component(new SimpleButton(this)
			.text.is("Test")
			.icon.is(new ImageIcon("ok.png"))
			.task.is(new Task(){@Override public void doTask() {
			    test.value(5);
				//System.out.println(sh.select().value());
				//sh.series(new Series().field(fio.is("1Vasya")).field(man.is(true)).field(age.is(19)).field(mail.is("1vpupkin@mail.ru")));
				//sh.drop(sh.select().value().intValue());
				}
			    })
			.normalAlignment.is(true))
		    .width.is(90)
		    .height.is(27)
		    .x.is(labelsWidth+8)
		    .y.is(layoutless.height.property.minus(40))
		    )
		.item(new ComponentBox().component(new SimpleImage().image(new ImageIcon("keys.png").getImage()))
		    .width.is(200)
		    .height.is(200)
		    .x.is(100)
		    .y.is(layoutless.height.property.minus(200))
		    /*.x(100)
		    .y(0)
		    .width(200)
		    .height(200)*/
		    )
		.item(new ComponentBox().component(new SimpleWait(this).horizontal.is(false))
		    .width.is(16)
		    .height.is(100)
		    .x.is(8)
		    .y.is(8)
		    )
		;
	//nu.minimum(5).maximum(9).decimalPlaces(3).numeric(17.123456);
	this.add(layoutless, BorderLayout.CENTER);
    }
}
