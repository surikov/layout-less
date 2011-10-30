package crudfx.control;
/**
 * @profile desktop
 * @author Sergey Surikov
 */
public class GridColumn {
    public var key:String;
    public var title:String on replace o=n{
        if(grid!=null){
            grid.renameColumn(key,n);
            }
        };
    public var grid:StandardGrid=null;
    public var width:Number=100;
    public var order:Integer=0;
    public var action:function():Void;
    }
