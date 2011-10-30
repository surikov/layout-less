package crudfx.control;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.image.*;
import crudfx.data.*;
import crudfx.interop.*;
import crudfx.control.*;
import crudfx.field.*;
import crudfx.util.*;
import javafx.stage.*;
import javafx.scene.shape.*;
import javafx.scene.input.*;
import javax.swing.table.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
/**
 * <p>Standard grid</p>
 * <p><b>Example</b>
 * <i>(doubleclick row or column header, press buttons to change data)</i></p>
 * <pre>
package javafxapplication49;

import crudfx.control.*;
import crudfx.interop.*;
import crudfx.data.*;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.stage.*;

var gridData:DataTree[];
var row:DataTree;

row=new DataTree();
row.node("name").asString("").value="Cyan";
row.node("red").asString("").value="0";
row.node("green").asString("").value="255";
row.node("blue").asString("").value="255";
insert row into gridData;
row=new DataTree();
row.node("name").asString("").value="Magenta";
row.node("red").asString("").value="255";
row.node("green").asString("").value="0";
row.node("blue").asString("").value="255";
insert row into gridData;
row=new DataTree();
row.node("name").asString("").value="Yellow";
row.node("red").asString("").value="255";
row.node("green").asString("").value="255";
row.node("blue").asString("").value="0";
insert row into gridData;
row=new DataTree();
row.node("name").asString("").value="Korn";
row.node("red").asString("").value="0";
row.node("green").asString("").value="0";
row.node("blue").asString("").value="0";
insert row into gridData;

def column1:GridColumn = GridColumn{key:"name" title:"Name" order:0 width:110
    action:function():Void{Alert.inform("column Name");}}
def column2:GridColumn = GridColumn{key:"red" title:"Red" order:1 width:60
    action:function():Void{Alert.inform("column Red");}}
def column3:GridColumn = GridColumn{key:"green" title:"Green" order:2 width:60
    action:function():Void{Alert.inform("column Green");}}
def column4:GridColumn = GridColumn{key:"blue" title:"Blue" order:3 width:60
    action:function():Void{Alert.inform("column Blue");}}

def gridColumns:GridColumn[] = [column1, column2, column3, column4];

var grid:StandardGrid;

Stage{
    title:"StandardGrid"
    width:400
    height:400
    scene:Scene{
        content:[
            UINode{
                width:300
                height:300
                translateX:10
                translateY:10
                content:grid=StandardGrid{
                    data:bind gridData
                    columns:gridColumns
                    action:function():Void{Alert.inform("row {grid.selected}");}
                    }
                }
            ,Button{
                text:"Add"
                translateX:10
                translateY:320
                action:function(){
                    row=new DataTree();
                    row.node("name").asString("").value="Some";
                    row.node("red").asString("").value="123";
                    row.node("green").asString("").value="123";
                    row.node("blue").asString("").value="123";
                    insert row into gridData;
                    grid.reset();
                    }
                }
            ,Button{
                text:"Delete"
                translateX:70
                translateY:320
                action:function(){
                    delete gridData[grid.selected] from gridData;
                    grid.reset();
                    }
                }
            ]
        }
    }
 * </pre>
 * @profile desktop
 * @author Sergey Surikov
 */
public class StandardGrid extends UIComponent{
    protected var scrollPane:JScrollPane;
    protected var table:JTable;
    /**
    Selected row
    */
    public var row:Integer=-1 on replace {
        select();
        };
    /**
    Action for double click
    */
    public var action:function():Void;
    //public var columnProperties:DataTree;
    /**
    Table columns - {@link GridColumn}
    */
    public var columns:GridColumn[];
    var stopSelect:Boolean=false;
    /**
    Array of rows
    */
    //public var data:DataTree[];
    public var data:Fields[];
    var dropCatcher:DropCatcher;
    public-init var onDrop:function(text:String[]):Void;
    var tableColumnModelListener:TableColumnModelListener;
    function select(){
        var n:Integer=0;
        if(table.getRowCount()>row and row>-1){
            table.setRowSelectionInterval(row,row);
            }
        else if(table.getRowCount()>0){
            row=0;
            table.setRowSelectionInterval(0,0);
            }
        }
    protected function deleteRow(f:Integer,l:Integer):Void{
        for(i in [f .. l]){
            GridTools.removeTableRow(table,f);
            }
        }
    /**
    Refill table from data array
    */
    public function reset(){
        GridTools.removeAllTableRows(table);
        recreateColumns();
        addRows(data);
        }
    protected function addRows(newElements:Fields[]){
        var f:Integer;
        for(row:Fields in newElements){
            GridTools.addTableRow(table);
            //for(cell:DataTree in row.children){
            for(c:GridColumn in columns){
                GridTools.setTableFieldValue(table
                    //,cell.asString("").value
                    ,row.get(c.key)
                    ,c.key
                    );
                }
            }
        select();
        }
    function sortColumns(){
        var key:String="";
        var currentOrder:Integer=-1;
        var tmp:Integer;
        for(main:GridColumn in columns){
            tmp=9999;
            for(inner:GridColumn in columns){
                if(inner.order>currentOrder){
                    if(inner.order<tmp){
                        tmp=inner.order;
                        key=inner.key;
                        }
                    }
                }
            currentOrder=tmp;
            GridTools.moveColumnToEnd(table, key);
            }
        }
    protected function renameColumn(key:String,title:String){
        table.getColumn(key).setHeaderValue(title);
        table.getTableHeader().resizeAndRepaint();
        }
    public function getColumn(n:Integer):GridColumn{
        var gc:GridColumn=null;
        var i:Integer=GridTools.toModel(table,n);
        if(i>-1)gc=this.columns[i];
        return gc;
        }
    protected function recreateColumns():Void{
        var cc:Integer=table.getColumnCount();
        var columnModel:DefaultTableColumnModel=table.getColumnModel() as DefaultTableColumnModel;
        var tableModel:DefaultTableModel=table.getModel() as DefaultTableModel;
        var tc:TableColumn;
        while(cc>0){
            tc=table.getColumn(table.getColumnName(0));
            table.removeColumn(tc);
            columnModel.removeColumn(tc);
            //tableModel.removeColumn(tc);
            cc--;
            }
        tableModel.setColumnCount(0);
        table.setColumnModel(new DefaultTableColumnModel());
        table.setModel(new DefaultTableModel());
        for(c:GridColumn in columns){
            c.grid=this;
            //println(c.title);
            GridTools.addTableColumn(table, c.title, c.key ,c.width);
                //,columnProperties
                //.node(c.key).node("width").asInteger(100).value);
        }
        sortColumns();
        var t:TableColumnModelListener=TableColumnModelListener{
        override public function columnAdded(e:TableColumnModelEvent):Void{}
        override public function columnRemoved(e:TableColumnModelEvent):Void{}
        override public function columnSelectionChanged(e:ListSelectionEvent):Void{}
        override public function columnMoved(e:TableColumnModelEvent):Void{
            if(e.getFromIndex()!=e.getToIndex()){
                var columnModel:DefaultTableColumnModel = table.getColumnModel() as DefaultTableColumnModel;
                var en=columnModel.getColumns();
                var tc:TableColumn;
                var modelIndex:Integer;
                var viewIndex:Integer;
                var key:String;
                while(en.hasMoreElements()){
                    tc=en.nextElement();
                    modelIndex=tc.getModelIndex();
                    viewIndex=GridTools.toView(table,modelIndex);
                    key=tc.getIdentifier().toString();
                    //columnProperties.node(key).node("order").asInteger(0).value=viewIndex;
                    for(c:GridColumn in columns) if(c.key.equals(key)) c.order=viewIndex;
                    }
                }
            }
      override public function columnMarginChanged(e:ChangeEvent):Void{
          //println("columnMarginChanged: {e}");
            var columnModel:DefaultTableColumnModel = table.getColumnModel() as DefaultTableColumnModel;
            var en=columnModel.getColumns();
            var tc:TableColumn;
            var modelIndex:Integer;
            var key:String;
            while(en.hasMoreElements()){
                tc=en.nextElement();
                modelIndex=tc.getModelIndex();
                key=tc.getIdentifier().toString();
                //columnProperties.node(key).node("width").asInteger(100).value=tc.getPreferredWidth();
                for(c:GridColumn in columns) if(c.key.equals(key)) c.width=tc.getPreferredWidth();
                }
            //scrollPane.doLayout();
            //table.doLayout();
            //println(table.getSize());
            //Decanter{};
            //table.setPreferredSize(new java.awt.Dimension(800,800));
            //scrollPane.doLayout();
            }
        };
        GridTools.addColumnModelListener(table,t,tableColumnModelListener);
        tableColumnModelListener=t;
        }
    init{
        scrollPane=new JScrollPane();
        table=GridTools.readOnlyTable();
        //table.setPreferredSize(new java.awt.Dimension(800,800));
        scrollPane.getViewport().add(table);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);
        table.setOpaque(false);
        recreateColumns();
        addRows(data);

        var sel:ListSelectionListener=ListSelectionListener{
            override function valueChanged(e:ListSelectionEvent):Void{
                if(stopSelect)return;
                if(table.getRowCount()>0){
                if(row!=table.getSelectedRow()){
                    row=table.getSelectedRow();
                    }
                }};
            };
        table.getSelectionModel().addListSelectionListener(sel);
        var mouseAdapter:MouseAdapter=MouseAdapter{
            override public function mousePressed(e:java.awt.event.MouseEvent):Void{
                //
                }
            override public function mouseReleased(e:java.awt.event.MouseEvent):Void{
                //
                }
            override public function mouseClicked(e:java.awt.event.MouseEvent):Void{
                if (e.getClickCount() == 2){
                    action();
                    }
                }
        }
        table.addMouseListener(mouseAdapter);
        var headerMouseAdapter:MouseAdapter=MouseAdapter{
            override public function mousePressed(e:java.awt.event.MouseEvent):Void{
                //
                }
            override public function mouseReleased(e:java.awt.event.MouseEvent):Void{
                //
                }
            override public function mouseClicked(e:java.awt.event.MouseEvent):Void{
                if (e.getClickCount() == 2){
                    try{
                        var colModel:TableColumnModel=table.getColumnModel();
                        var vColIndex:Integer=colModel.getColumnIndexAtX(e.getX());
                        if (vColIndex<0)return;
                        var mColIndex:Integer=table.convertColumnIndexToModel(vColIndex);
                        columns[mColIndex].action();
                        }
                    catch(x){
                        x.printStackTrace();
                        }
                    }
                }
        }
        table.getTableHeader().addMouseListener(headerMouseAdapter);
        dropCatcher=DropCatcher{
            component:scrollPane//this.table
            onDrop:onDrop
            };
        select();
        }

    override public function getJComponent(): JComponent{
        return scrollPane;
        }
    override public function highlight(s:String):Boolean{
        return false;
        }
    override public function gather():DataTree{
        var me:DataTree=DataTree{ name:"StandardGrid" };
        return me;
        }
    }
